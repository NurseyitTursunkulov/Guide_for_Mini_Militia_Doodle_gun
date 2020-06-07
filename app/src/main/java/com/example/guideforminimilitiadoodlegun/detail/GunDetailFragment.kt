package com.example.guideforminimilitiadoodlegun.detail

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.guideforminimilitiadoodlegun.MuhamedSAVViewModel
import com.example.guideforminimilitiadoodlegun.R
import com.example.guideforminimilitiadoodlegun.util.removeFullScreengun
import kotlinx.android.synthetic.main.activity_screen_slidegun.*
import kotlinx.android.synthetic.main.fragment_book_detailun.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GunDetailFragment : Fragment(R.layout.activity_screen_slidegun) {

    val gunviewModel: MuhamedSAVViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreengun()
        (activity as AppCompatActivity).setSupportActionBar(toolbar_gun)
        (activity as AppCompatActivity).supportActionBar?.show()
        gun_pager.adapter = ScreenSlidePagerAdapter(requireActivity())
        initPendingIndicatorViewgun()


        gun_pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    gunpageIndicatorView.selection = position
                }
            })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // This callback will only be called when MyFragment is at least Started.
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            // Handle the back button event
            if (gun_pager.currentItem == 0) {
                // If the user is currently looking at the first step, allow the system to handle the
                // Back button. This calls finish() on this activity and pops the back stack.
                this@GunDetailFragment.findNavController().navigateUp()
            } else {
                // Otherwise, select the previous step.
                gun_pager.currentItem = gun_pager.currentItem - 1
                this@GunDetailFragment.findNavController().navigateUp()
            }
        }

    }


    private inner class ScreenSlidePagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int =
            gunviewModel.navigateToDetailGunEvent.value?.peekContent()?.listOfContentPerPage?.size
                ?: 1

        override fun createFragment(position: Int): Fragment {
            val content =
                gunviewModel.navigateToDetailGunEvent.value?.peekContent()?.listOfContentPerPage?.get(
                    position
                ) ?: ""
            return when (position) {
                0 -> GunScreenSlideFirstPageFragment.newInstance(content)
                else -> GunScreenSlidePageFragment.newInstance(
                    position,
                    content
                )
            }

        }
    }
}