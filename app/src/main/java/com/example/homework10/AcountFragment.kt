package com.example.homework10

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class AcountFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_acount, container, false)
    }

    override fun onResume() {
        super.onResume()

        val sharPreferences = activity?.getSharedPreferences("main", Context.MODE_PRIVATE)
        val login1 = sharPreferences?.getString("login", "0")
        val password1 = sharPreferences?.getString("password", "0")

        val textView = view?.findViewById<TextView>(R.id.login) as TextView
        val passwordView = view?.findViewById<TextView>(R.id.password) as TextView

        textView.text = login1.toString()
        passwordView.text = password1.toString()
    }
}