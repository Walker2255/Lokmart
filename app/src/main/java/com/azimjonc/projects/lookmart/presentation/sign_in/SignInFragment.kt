package com.azimjonc.projects.lookmart.presentation.sign_in

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.azimjonc.projects.lookmart.R
import com.azimjonc.projects.lookmart.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    //    View binding
    private lateinit var binding: FragmentSignInBinding

    //    SignInViewModel bilan bog'lash
    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        subscribeToLiveData()
    }

    //    loading uchun
    private fun subscribeToLiveData() = with(binding) {
        viewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            progress.isVisible = isLoading
        }
        viewModel.events.observe(viewLifecycleOwner) { event ->
            when (event) {
                SignInViewModel.Event.ConnectionError -> toast(R.string.connection_error)
                SignInViewModel.Event.Error -> toast(R.string.error)
                SignInViewModel.Event.InvalidCredentials -> toast(R.string.invalid_credentials)
            }
        }
    }


    //  UI initalized
    private fun initUI() = with(binding) {
        //  when button click, data send view model
        signIn.setOnClickListener {
            viewModel.signIn(username.toString(), password.toString())
        }
    }


    //    Xatoliklarni qaytaruvchi funksiya
    private fun toast(message: Int) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }
}