package ua.antibyte.designviewpagercatalog.presentation.fragment.island

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_island.*
import ua.antibyte.designviewpagercatalog.R
import ua.antibyte.designviewpagercatalog.data.model.IslandInfo

class IslandFragment : Fragment(R.layout.fragment_island) {
    private lateinit var animatorDecreaseScale: AnimatorSet
    private lateinit var animatorIncreaseScale: AnimatorSet

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAnimators()
        fillViewsData()
    }

    override fun onResume() {
        super.onResume()
        animatorDecreaseScale.start()
    }

    override fun onPause() {
        super.onPause()
        animatorIncreaseScale.start()
    }

    private fun getIslandInfoFromBundle() = arguments?.getSerializable(IslandInfo.KEY) as IslandInfo

    private fun initAnimators() {
        initDecreaseScaleAnimator()
        initIncreaseScaleAnimator()
    }

    private fun initDecreaseScaleAnimator() {
        animatorDecreaseScale = AnimatorInflater.loadAnimator(
            context,
            R.animator.animator_decrease_scale
        ) as AnimatorSet
        animatorDecreaseScale.setTarget(iv_island_logo)

    }

    private fun initIncreaseScaleAnimator() {
        animatorIncreaseScale = AnimatorInflater.loadAnimator(
            context,
            R.animator.animator_increase_scale
        ) as AnimatorSet
        animatorIncreaseScale.setTarget(iv_island_logo)
    }

    private fun fillViewsData() {
        getIslandInfoFromBundle().let {
            tv_island_name.text = it.name
            tv_island_period.text = it.period
            tv_island_departure.text = it.departure
            iv_island_logo.setImageResource(it.resId)
        }
    }
}