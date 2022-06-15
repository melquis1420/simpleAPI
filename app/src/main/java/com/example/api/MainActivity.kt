package com.example.api

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.ProxyFileDescriptorCallback
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.example.api.model.PageUser
import com.example.api.networking.Endpoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    lateinit var listView: ListView
    lateinit var adapter: ArrayAdapter<String>
    var list: List<String> = ArrayList<String>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //
        listView = findViewById(R.id.lista)

        getUser()
    }

    fun getUser() {
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://reqrest.in")


        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getUser()


        callback.enqueue(object : Callback<PageUser> {


            override fun onFailure(call: Call<PageUser>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<PageUser>, response: Response<PageUser>) {
                response.body()?.let {
                    it.data.forEach {

                        list += it.firstName + "" + it.lastName
                    }

                    setValues()
                }
            }
        })
    }

    fun setValues() {
        adapter = ArrayAdapter(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            list.toTypedArray()
        )
        listView.adapter = adapter
    }
}