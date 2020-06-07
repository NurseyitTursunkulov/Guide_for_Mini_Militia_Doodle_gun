package com.example.guideforminimilitiadoodlegun.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.guideforminimilitiadoodlegun.MuhamedSAVViewModel
import com.example.guideforminimilitiadoodlegun.R
import com.example.guideforminimilitiadoodlegun.util.EventObserver
import com.example.guideforminimilitiadoodlegun.util.removeFullScreengun
import kotlinx.android.synthetic.main.detail_gun_viewpager.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class GunScreenSlidePageFragment :
    Fragment(R.layout.detail_gun_viewpager) {
    private lateinit var content: String
    private var gunposition: Int = 0

    val gunviewModel: MuhamedSAVViewModel by sharedViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            content = it.getString(CONTENT_afeaf, "")
            gunposition = it.getInt(POSITION_efdadfm)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeFullScreengun()
        (activity as AppCompatActivity).supportActionBar?.show()
        content_text_view.text = content
        toolbar_gun.title = gunviewModel.navigateToDetailGunEvent.value?.peekContent()?.title
        toolbar_gun.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        gunviewModel.showAdvertGunEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialGunAdvertSafe(gunviewModel.interstitialAdGun)
        })
        showBannerAdvert(ad_view_detail_pager, gunviewModel.showAdvertStateGun)

        Glide
            .with(this)
            .load(getRandomImagegun())
            .fitCenter()
            .into(imageView)

        showRateMeDialoggun()
    }

    override fun onResume() {
        super.onResume()
        if (gunposition % 2 == 0) {
            gunviewModel.showGunAdvert()
        }
    }

    companion object {
        const val POSITION_efdadfm = "position_"
        const val CONTENT_afeaf = "content_"

        @JvmStatic
        fun newInstance(position: Int, content: String) =
            GunScreenSlidePageFragment().apply {
                arguments = Bundle().apply {
                    putInt(POSITION_efdadfm, position)
                    putString(CONTENT_afeaf, content)
                }
            }
    }
}