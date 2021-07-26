package com.example.homework10

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.service.autofill.Validators.or
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onResume() {
        super.onResume()

        val sharPreferences = activity?.getSharedPreferences("main", Context.MODE_PRIVATE)
        val login = view?.findViewById<TextInputLayout>(R.id.login)
        val password = view?.findViewById<TextInputLayout>(R.id.password)
        val loginT = view?.findViewById<TextInputEditText>(R.id.loginText)
        val passwordT = view?.findViewById<TextInputEditText>(R.id.passwordText)

        fun test() {
            if ((loginT?.text.isNullOrEmpty()) or (passwordT?.text.isNullOrEmpty())) {
                login?.error = if (loginT?.text.isNullOrEmpty()) {
                    "login could not be empty"
                } else {
                    null
                }
                password?.error = if (passwordT?.text.isNullOrEmpty()) {
                    "password could not be empty"
                } else {
                    null
                }

            } else {

                if ((loginT?.text.toString() == "Admin") && (passwordT?.text.toString() == "123456")) {
                    sharPreferences?.edit()?.putString("login", "Admin")?.apply()
                    sharPreferences?.edit()?.putString("password", "123456")?.apply()

                    Toast.makeText(activity, "Login and password are correct", Toast.LENGTH_SHORT)
                        .show()
                    val colGreen = context?.let { ContextCompat.getColor(it, R.color.green) }
                    login?.boxBackgroundColor = colGreen!!
                    password?.boxBackgroundColor = colGreen

                    Handler(Looper.getMainLooper()).postDelayed({
                        parentFragmentManager.commit {
                            replace(R.id.frameID, AcountFragment()).addToBackStack(null)
                        }
                    }, 2000)

                } else {
                    login?.error = if (loginT?.text.toString() != "Admin") {
                        "Login are not correct"
                    } else {
                        null
                    }
                    password?.error = if (passwordT?.text.toString() != "123456") {
                        "Password are not correct"
                    } else {
                        null
                    }
                }
            }
        }
        passwordT?.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    test()
                    true
                }
                else -> false
            }
        }
    }
}
