package com.example.guideforminimilitiadoodlegun

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.guideforminimilitiadoodlegun.bookList.Gun
import com.example.guideforminimilitiadoodlegun.util.Event
import com.example.guideforminimilitiadoodlegun.util.getString
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MuhamedSAVViewModel(application: Application) : AndroidViewModel(application) {
    private val _splashState = MutableLiveData<Event<SplashStateGun>>()
    val splashStateGun: LiveData<Event<SplashStateGun>> = _splashState

    var adView: AdView? = null
    lateinit var interstitialAdGun: InterstitialAd

    private val _showAdvertEvent: MutableLiveData<Event<Boolean>> =
        MutableLiveData<Event<Boolean>>()
    val showAdvertGunEvent: LiveData<Event<Boolean>> = _showAdvertEvent

    var showAdvertStateGun = false

    private val _navigateToDetailEvent = MutableLiveData<Event<Gun>>()
    val navigateToDetailGunEvent: LiveData<Event<Gun>> = _navigateToDetailEvent

    private val _items = MutableLiveData<List<Gun>>().apply {
        value = getBooksListGun()
    }

    val items: LiveData<List<Gun>> = _items

    init {
        viewModelScope.launch {
            delay(3000)
            _splashState.postValue(
                Event(
                    SplashStateGun.MainActivityGun()
                )
            )
            showGunAdvert()
        }
    }

    fun showGunAdvert() {
        if (showAdvertStateGun)
            _showAdvertEvent.postValue(Event(showAdvertStateGun))
    }

    fun openBookGun(gun: Gun) {
        _navigateToDetailEvent.postValue(
            Event(
                gun
            )
        )
    }

    private fun getBooksListGun(): List<Gun> {
        return listOf(
            Gun(
                title = getString(R.string.book1title),
                body = getString(R.string.book1body),
                imageId = R.drawable.gun1
            ),
            Gun(
                title = getString(R.string.book_1_title),
                body = getString(R.string.book_1_body),
                imageId = R.drawable.gun2
            ),
            Gun(
                title = getString(R.string.book_2_title),
                body = getString(R.string.book_2_body),
                imageId = R.drawable.gun3
            ),
            Gun(
                title = getString(R.string.book_3_title),
                body = getString(R.string.book_3_body),
                imageId = R.drawable.gun4
            ),
            Gun(
                title = getString(R.string.book_4_title),
                body = getString(R.string.book_4_body),
                imageId = R.drawable.gun4
            ),
            Gun(
                title = getString(R.string.book_5_title),
                body = getString(R.string.book_5_body),
                imageId = R.drawable.gun5
            ),
            Gun(
                title = getString(R.string.book_6_title),
                body = getString(R.string.book_6_body),
                imageId = R.drawable.gun6
            ),
            Gun(
                title = getString(R.string.book_7_title),
                body = getString(R.string.book_7_body),
                imageId = R.drawable.gun7
            ),
            Gun(
                title = getString(R.string.book_8_title),
                body = getString(R.string.book_8_body),
                imageId = R.drawable.gun8
            ),
            Gun(
                title = getString(R.string.book_9_title),
                body = getString(R.string.book_9_body),
                imageId = R.drawable.gun9
            ),
            Gun(
                title = getString(R.string.book_10_title),
                body = getString(R.string.book_10_body),
                imageId = R.drawable.gun10
            )
        )
    }
}

sealed class SplashStateGun {
    class MainActivityGun : SplashStateGun()
}
