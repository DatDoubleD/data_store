package com.doanducdat.learndatastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //C1:tu dong update nhu live data???
        val userManager:UserManager = UserManager(this)
        CoroutineScope(Dispatchers.Main).launch{
            launch {
                userManager.userNameFlow.collect {
                    txt_user.text = it
                }
            }
            launch {
                userManager.passwordFlow.collect {
                    txt_password.text = it
                }
            }
        }
        //C2: livedata
      /*  userManager.userNameFlow.asLiveData().observe(this, {
            txt_user.text = it
        })*/
        //
        btn_save.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                userManager.writeData(edt_user.text.toString(), edt_password.text.toString())
            }
        }
    }
}