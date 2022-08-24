package com.example.dependencyinjection.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog

open class BaseActivity : AppCompatActivity() {
    var progressDialog: AppCompatDialog? = null
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = this
    }

}