package ua.antibyte.designviewpagercatalog.presentation.fragment.island

import android.os.Bundle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ua.antibyte.designviewpagercatalog.presentation.activity.MainActivity
import ua.antibyte.designviewpagercatalog.data.model.IslandInfo

class ViewPagerAdapter(
    activity: MainActivity,
    private val islandInfoList: List<IslandInfo>
) : FragmentStateAdapter(activity) {

    override fun getItemCount() = islandInfoList.size

    override fun createFragment(position: Int) = IslandFragment().apply {
        arguments = Bundle().apply {
            putSerializable(IslandInfo.KEY, islandInfoList[position])
        }
    }
}