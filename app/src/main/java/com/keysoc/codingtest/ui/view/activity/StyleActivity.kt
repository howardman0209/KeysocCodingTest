package com.keysoc.codingtest.ui.view.activity

import com.keysoc.codingtest.R
import com.keysoc.codingtest.databinding.ActivityStyleBinding
import com.keysoc.codingtest.ui.base.MVVMActivity
import com.keysoc.codingtest.ui.viewModel.StyleViewModel

class StyleActivity : MVVMActivity<StyleViewModel, ActivityStyleBinding>() {
    override fun getViewModelInstance(): StyleViewModel = StyleViewModel()

    override fun setBindingData() {
        binding.view = this
        binding.viewModel = viewModel
    }

    override fun getLayoutResId(): Int = R.layout.activity_style
}