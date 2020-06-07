package com.example.guideforminimilitiadoodlegun

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guideforminimilitiadoodlegun.util.EventObserver
import com.example.guideforminimilitiadoodlegun.util.divideTextToParts
import com.example.guideforminimilitiadoodlegun.util.initAdvertgun
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SplashFragmentGun : Fragment(R.layout.splash_fragmentgun) {

    private val viewModel: MuhamedSAVViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.adView = initAdvertgun(requireContext())
        val content = viewModel.items.value
        content?.let { bookList ->
            divideTextToParts(bookList)
        }

        viewModel.splashStateGun.observe(viewLifecycleOwner,
            EventObserver {
                when (it) {
                    is SplashStateGun.MainActivityGun -> {
                        findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
                    }
                }
            })
        viewModel.showAdvertGunEvent.observe(viewLifecycleOwner, EventObserver {
            if (viewModel.interstitialAdGun.isLoaded) {
                viewModel.interstitialAdGun.show()
            } else {
                Log.d("Nurs", "splash The interstitial wasn't loaded yet.")
            }
        })
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

}