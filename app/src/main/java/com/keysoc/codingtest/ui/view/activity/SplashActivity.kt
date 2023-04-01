package com.keysoc.codingtest.ui.view.activity

import android.content.Intent
import android.os.Bundle
import com.keysoc.codingtest.R
import com.keysoc.codingtest.databinding.ActivitySplashBinding
import com.keysoc.codingtest.ui.base.MVVMActivity
import com.keysoc.codingtest.ui.viewModel.SplashViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : MVVMActivity<SplashViewModel, ActivitySplashBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            val intent = Intent(applicationContext, AlbumsListActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun getLayoutResId(): Int = R.layout.activity_splash

    override fun getViewModelInstance(): SplashViewModel = SplashViewModel()

    override fun setBindingData() {
        binding.view = this
        binding.viewModel = viewModel
    }

}