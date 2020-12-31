package com.github.spacedelivery.androidapp.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.github.spacedelivery.androidapp.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder

abstract class BaseFragment<VM : BaseViewModel, DB : ViewDataBinding>(@LayoutRes private val layoutId: Int) :
    Fragment() {

    protected lateinit var binding: DB

    abstract val viewModel: VM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(layoutInflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeBaseStatus()
    }

    private fun observeBaseStatus() {
        viewModel.errorState.observe(viewLifecycleOwner) { status ->
            val message = status.exception.message ?: getString(R.string.error_general_message)
            MaterialAlertDialogBuilder(requireContext()).apply {
                setTitle(message)
                setPositiveButton("OK", null)
            }.show()

        }
    }

}