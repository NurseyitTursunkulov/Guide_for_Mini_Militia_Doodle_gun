package com.example.guideforminimilitiadoodlegun.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.guideforminimilitiadoodlegun.MuhamedSAVViewModel
import com.example.guideforminimilitiadoodlegun.databinding.FragmentBookDetailunBinding
import com.example.guideforminimilitiadoodlegun.util.EventObserver
import kotlinx.android.synthetic.main.fragment_book_detailun.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class GunScreenSlideFirstPageFragment : Fragment() {
    lateinit var guncontent: String
    lateinit var gunviewDataBinding: FragmentBookDetailunBinding
    val gunviewModel: MuhamedSAVViewModel by sharedViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            guncontent = it.getString(CONTENT, "")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        gunviewModel.showGunAdvert()
        (activity as AppCompatActivity).supportActionBar?.show()
        (activity as AppCompatActivity).setSupportActionBar(toolbar_gun)
        gunviewDataBinding = FragmentBookDetailunBinding.inflate(inflater, container, false).apply {
            bookInfo = gunviewModel.navigateToDetailGunEvent.value?.peekContent()
        }
        return gunviewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        content_text_view.text = guncontent
        toolbar_gun.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        showBannerAdvert(ad_view_detail, gunviewModel.showAdvertStateGun)

        gunviewModel.showAdvertGunEvent.observe(viewLifecycleOwner, EventObserver {
            showInterstitialGunAdvertSafe(gunviewModel.interstitialAdGun)
        })
        gunviewModel.navigateToDetailGunEvent.value?.peekContent()?.imageId?.let {
            Glide
                .with(this)
                .load(it)
                .fitCenter()
                .into(image)
        }
    }

    companion object {
        const val CONTENT = "content_"

        @JvmStatic
        fun newInstance(content: String) =
            GunScreenSlideFirstPageFragment().apply {
                arguments = Bundle().apply {
                    putString(CONTENT, content)
                }
            }
    }
}