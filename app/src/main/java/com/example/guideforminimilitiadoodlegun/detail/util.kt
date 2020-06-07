package com.example.guideforminimilitiadoodlegun.detail

import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.androidsx.rateme.RateMeDialog
import com.androidsx.rateme.RateMeDialogTimer
import com.example.guideforminimilitiadoodlegun.R
import com.example.guideforminimilitiadoodlegun.bookList.gungetAdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.android.synthetic.main.activity_screen_slidegun.*
import java.util.*


fun GunDetailFragment.initPendingIndicatorViewgun() {
    val book = gunviewModel.navigateToDetailGunEvent.value?.peekContent()

    gunpageIndicatorView.count =
        book?.listOfContentPerPage?.size ?: 1 // specify total count of indicators

    gunpageIndicatorView.selection = 1
}

fun Fragment.showBannerAdvert(adView: AdView, showAdvertState: Boolean) {
    if (showAdvertState) {
        adView.visibility = View.VISIBLE
        val adRequest = gungetAdRequest()
        adView.loadAd(adRequest)
    }
}

fun Fragment.showRateMeDialoggun() {
    RateMeDialogTimer.onStart(requireContext())
    if (RateMeDialogTimer.shouldShowRateDialog(requireContext(), 1, 2)) {
        RateMeDialog.Builder(requireActivity().packageName, "")
            .setHeaderBackgroundColor(resources.getColor(R.color.colorPrimary))
            .setBodyBackgroundColor(resources.getColor(R.color.dialog_body))
            .showAppIcon(R.mipmap.ic_logo_foreground)
            .enableFeedbackByEmail("")
            .setRateButtonBackgroundColor(resources.getColor(R.color.dialog_button))
            .build()
            .show(requireActivity().fragmentManager, "plain-dialog")
    }
}

fun showInterstitialGunAdvertSafe(interstitialAdgun: InterstitialAd) {
    if (interstitialAdgun.isLoaded) {
        interstitialAdgun.show()
    } else {
        Log.d("Nurs", "first The interstitial wasn't loaded yet.")
    }
}

fun GunScreenSlidePageFragment.getRandomImagegun(): Int {
    val imagesgun =
        intArrayOf(
            R.drawable.gun1, //ok
            R.drawable.gun2, //ok
            R.drawable.gun3, //ok
            R.drawable.gun4, //ok
            R.drawable.gun5, //ok
            R.drawable.gun6, //ok
            R.drawable.gun7, //ok
            R.drawable.gun8, //ok
            R.drawable.gun9, //ok
            R.drawable.gun10 //ok
        )
    val rand = Random()
    return imagesgun[rand.nextInt(imagesgun.size)]
}