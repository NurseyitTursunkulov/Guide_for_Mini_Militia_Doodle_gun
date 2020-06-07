package com.example.guideforminimilitiadoodlegun.bookList

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import java.util.*


fun MainGunFragment.initgunAdapter() {
    val viewModel = viewgunDataBinding.viewmodel
    if (viewModel != null) {
        listAdapter = GunsAdapter(viewModel)
        viewgunDataBinding.recyclerViewBooks.adapter = listAdapter
        viewgunDataBinding.recyclerViewBooks.layoutManager = GridLayoutManager(requireContext(), 2)
        if (viewModel.showAdvertStateGun)
            gunmakeOneSpanForAdView()
        viewModel.items.observe(viewLifecycleOwner, Observer {
            listAdapter.submitList(it)
        })
    } else {
        //            Timber.w("ViewModel not initialized when attempting to set up adapter.")
    }
}

class GunDiffCallback : DiffUtil.ItemCallback<Gun>() {

    override fun areItemsTheSame(oldItem: Gun, newItem: Gun): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Gun, newItem: Gun): Boolean {
        return oldItem == newItem
    }

}

@BindingAdapter("imageResource")
fun setImageResource(imageView: ImageView, resource: Int) {
    imageView.setImageResource(resource)

}

fun gungetAdRequest(): AdRequest? {
    val adRequest = AdRequest.Builder().build()
    val testDeviceIds = Arrays.asList("F5E4CD8EA025C4062D9E4BE54D002D25")
    val configuration =
        RequestConfiguration.Builder().setTestDeviceIds(testDeviceIds).build()
    MobileAds.setRequestConfiguration(configuration)
    return adRequest
}

private fun MainGunFragment.gunmakeOneSpanForAdView() {
    (viewgunDataBinding.recyclerViewBooks.layoutManager as GridLayoutManager)
        .spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
        override fun getSpanSize(position: Int): Int {
            return when (position) {
                0 -> 2
                else -> 1
            }
        }
    }
}
