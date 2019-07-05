package com.tijo.personalchatapp


import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import com.tijo.personalchatapp.Model.Users
import kotlinx.android.synthetic.main.fragment_sign_up.*

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        register_new_user_material_button.setOnClickListener {
            if(checkIfIsEmput(username_edit_text.text.toString())){
                username_edit_text.setError("This field cannot be blank")
                return@setOnClickListener
            }
            if(checkIfIsEmput(password_edit_text.text.toString())){
                password_edit_text.setError("This field cannot be blank")
                return@setOnClickListener
            }
            if(checkIfIsEmput(verify_password_edit_text.text.toString())){
                verify_password_edit_text.setError("This field cannot be blank")
                return@setOnClickListener
            }
            if(!verifyPassword(password_edit_text.text.toString(), verify_password_edit_text.text.toString())){
                verify_password_edit_text.setError("Passwords do not match")
                return@setOnClickListener
            }
            else{
                Toast.makeText(context,"ALL TEST PASSED", Toast.LENGTH_LONG).show()
                var username = username_edit_text.text.toString()
                var password = verify_password_edit_text.text.toString()
                register_linear_layout.visibility = View.INVISIBLE
                register_progressbar.visibility= View.VISIBLE
                RegisterUserAndPassword(username, password)
            }
        }
    }

    private fun RegisterUserAndPassword(username: String, password: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword("$username@chat.com", password)
            .addOnCompleteListener {
                if(!it.isSuccessful) return@addOnCompleteListener
                    Log.e("SIGNUP", "SIGNUP SUCCESSFULL!!!! YYYYYAAAAA")
                saveUsernameToFirebaseDatabase(username, password)
            }.addOnFailureListener {
                Toast.makeText(context,"${it.message}", Toast.LENGTH_LONG).show()
            }
    }

    private fun saveUsernameToFirebaseDatabase(username:String , password: String) {
        val uid = FirebaseAuth.getInstance().uid?:""
//        val ref = FirebaseFirestore.getInstance().document("users").collection("$uid")
        val ref = FirebaseFirestore.getInstance().collection("users").document("$uid")
        var user = Users(username, password)

        ref.set(user)
            .addOnSuccessListener {
                Log.e("SIGNUP", "user added")
                var bundle= Bundle()
                bundle.putString("username", username)
                findNavController().navigate(R.id.action_signUpFragment2_to_chatFragment, bundle)
            }
            .addOnFailureListener {
                Log.e("SIGNUP", "error : ${it.message}")
            }
    }

    fun verifyPassword(pass:String, pass1: String): Boolean {
        if(pass.equals(pass1)){
            return true
        }
        return false
    }

    fun checkIfIsEmput(input:String): Boolean{
        if(TextUtils.isEmpty(input)){
            return true
        }
        return false
    }
}
