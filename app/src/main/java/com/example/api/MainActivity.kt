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

        // Create bind Activity/View
        listView = findViewById(R.id.lista)

        //Search users method
        getUsers()
    }

    fun getUsers() {

        //Variavle server configuration
        val retrofitClient = NetworkUtils.getRetrofitInstance("https://reqres.in")

        // Apply the requisition
        val endpoint = retrofitClient.create(Endpoint::class.java)
        val callback = endpoint.getUsers()

        //Take the answer
        callback.enqueue(object : Callback<PageUser> {


            override fun onFailure(call: Call<PageUser>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            //On Sucess
            override fun onResponse(call: Call<PageUser>, response: Response<PageUser>) {
                response.body()?.let {
                    it.data.forEach {
                        //Set first name + last name
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