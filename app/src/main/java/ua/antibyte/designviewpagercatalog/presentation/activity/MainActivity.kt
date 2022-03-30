package ua.antibyte.designviewpagercatalog.presentation.activity

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.antibyte.designviewpagercatalog.common.helper.BlurHelper
import ua.antibyte.designviewpagercatalog.R
import ua.antibyte.designviewpagercatalog.data.model.IslandInfo
import ua.antibyte.designviewpagercatalog.presentation.fragment.island.ViewPagerAdapter

class MainActivity : AppCompatActivity() {
    companion object {
        const val BLUR_SCALE = 1f
        const val BLUR_RADIUS = 15
    }

    private val islandInfoList: List<IslandInfo>
    private val blurBitmapList: ArrayList<Bitmap> = ArrayList()

    init {
        this.islandInfoList = ua.antibyte.designviewpagercatalog.common.islandInfoList
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewPager()
        blurBitmapList.add(getBlurBitmap(islandInfoList.first().resId))
        initBlurBitmapList()
    }

    private fun initBlurBitmapList() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 1 until islandInfoList.size) {
                val blurBitmap = getBlurBitmap(islandInfoList[i].resId)
                blurBitmapList.add(blurBitmap)
            }
        }
    }

    private fun getBlurBitmap(resId: Int): Bitmap {
        val bitmap = BitmapFactory.decodeResource(resources, resId)
        return BlurHelper.blur(bitmap, BLUR_SCALE, BLUR_RADIUS)
    }

    private fun initViewPager() {
        vp_main.clipToPadding = false
        vp_main.clipChildren = false
        vp_main.getChildAt(0).overScrollMode = View.OVER_SCROLL_NEVER
        vp_main.setPageTransformer(MarginPageTransformer(dpToPx(16)))

        val adapter = ViewPagerAdapter(this, islandInfoList)
        vp_main.adapter = adapter
        vp_main.offscreenPageLimit = 3

        val callback = createOnPageChangeCallback()
        vp_main.registerOnPageChangeCallback(callback)
    }

    private fun setBackgroundImage(bitmap: Bitmap) {
        iv_main_background.setImageBitmap(bitmap)
    }

    private fun dpToPx(dp: Int) = (dp * resources.displayMetrics.density).toInt()

    private fun createOnPageChangeCallback() = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            setBackgroundImage(blurBitmapList[position])
        }
    }
}