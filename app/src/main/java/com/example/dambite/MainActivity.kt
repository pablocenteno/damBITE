package com.example.dambite

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dambite.opensqlite.MiBDOpenHelper
import com.example.dambite.viewModel.PlatosViewModel
import androidx.activity.viewModels


class MainActivity : AppCompatActivity() {

    private lateinit var miBDOpenHelper : MiBDOpenHelper

    private val favoritosViewModel : PlatosViewModel by viewModels()

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = applicationContext

        setContentView(R.layout.activity_main)
        miBDOpenHelper = MiBDOpenHelper(this, null)
        favoritosViewModel.setDBHelper(miBDOpenHelper)
        favoritosViewModel.modificarLista(miBDOpenHelper.obtenerPlatos())

    }



    override fun onStart() {
        super.onStart()

    }

}