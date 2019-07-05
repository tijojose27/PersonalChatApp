package com.tijo.personalchatapp


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_login_or_sign_up.*

class LoginOrSignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_or_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        signUp_material_button.setOnClickListener {
            findNavController().navigate(R.id.action_loginOrSignUpFragment3_to_signUpFragment2)
        }
    }
}
