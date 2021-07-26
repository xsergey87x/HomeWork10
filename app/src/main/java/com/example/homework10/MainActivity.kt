package com.example.homework10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.commit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharPreferences = getSharedPreferences("main", Context.MODE_PRIVATE)
        val login = sharPreferences.getString("login", "0")
        val password = sharPreferences.getString("password", "0")

        val splashFragment = SplashFragment()
        val mainFragment = MainFragment()
        val accountFragment = AcountFragment()

        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add(R.id.frameID, splashFragment)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if ((login == "Admin") && (password == "123456")) {
                supportFragmentManager.commit {
                    replace(R.id.frameID, accountFragment).addToBackStack(null)
                }
            } else {
                supportFragmentManager.commit {
                    replace(R.id.frameID, mainFragment).addToBackStack(null)
                }
            }
        }, 2000)
    }
}