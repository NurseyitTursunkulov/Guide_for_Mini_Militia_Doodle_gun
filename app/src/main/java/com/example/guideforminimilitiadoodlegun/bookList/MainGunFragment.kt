package com.example.guideforminimilitiadoodlegun.bookList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.guideforminimilitiadoodlegun.MuhamedSAVViewModel
import com.example.guideforminimilitiadoodlegun.R
import com.example.guideforminimilitiadoodlegun.util.EventObserver
import com.example.guideforminimilitiadoodlegun.util.removeFullScreengun
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainGunFragment : Fragment() {

    lateinit var viewgunDataBinding: com.example.guideforminimilitiadoodlegun.databinding.FragmentMaingunBinding
    lateinit var listAdapter: GunsAdapter
    val mainviewModel: MuhamedSAVViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        removeFullScreengun()
        (activity as AppCompatActivity).supportActionBar?.show()
        viewgunDataBinding =
            com.example.guideforminimilitiadoodlegun.databinding.FragmentMaingunBinding.inflate(
                inflater,
                container,
                false
            ).apply {
                viewmodel = mainviewModel
            }
        return viewgunDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainviewModel.navigateToDetailGunEvent.observe(viewLifecycleOwner,
            EventObserver {
                findNavController().navigate(R.id.action_mainFragment_to_bookDetailFragment)
            })
        mainviewModel.showAdvertGunEvent.observe(viewLifecycleOwner, EventObserver {
            if (mainviewModel.interstitialAdGun.isLoaded) {
                mainviewModel.interstitialAdGun.show()
            } else {
                Log.d("Nurs", "mainfrag The interstitial wasn't loaded yet.")
            }
        })
        initgunAdapter()
    }

    override fun onPause() {
        mainviewModel.adView?.pause()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        mainviewModel.adView?.resume()
    }

    override fun onDestroy() {
        mainviewModel.adView?.destroy()
        super.onDestroy()
    }
}