package com.example.guideforminimilitiadoodlegun.detail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.guideforminimilitiadoodlegun.MainViewModel
import com.example.guideforminimilitiadoodlegun.R
import com.example.guideforminimilitiadoodlegun.util.EventObserver
import com.example.guideforminimilitiadoodlegun.util.removeFullScreen
import kotlinx.android.synthetic.main.detail_viewpager.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ScreenSlidePageFragment :
    Fragment(R.layout.detail_viewpager) {
    private lateinit var content: String
    private var position: Int = 0

    val viewModel: MainViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            content = it.getString(CONTENT, "")
            position = it.getInt(POSITION)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreen()
        (activity as AppCompatActivity).supportActionBar?.show()
        content_text_view.text = content
        toolbar.title = viewModel.navigateToDetailEvent.value?.peekContent()?.title
        toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        viewModel.showAdvertEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialAdvertSafe(viewModel.interstitialAd)
        })
        showBannerAdvert(ad_view_detail_pager, viewModel.showAdvertState)

        Glide
            .with(this)
            .load(getRandomImage())
            .fitCenter()
            .into(imageView)

        showRateMeDialog()
    }

    override fun onResume() {
        super.onResume()
        Log.d("Nurs", "onResume pos $position")
        if (position % 2 == 0) {
            Log.d("Nurs", "pos if $position")
            viewModel.showAdvert()
        }
    }

    companion object {
        const val POSITION = "position_"
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(position: Int, content: String) =
            ScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION, position)
                    putString(CONTENT, content)
                }
            }
    }
}