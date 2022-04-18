package com.sygame.apiintentdetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sygame.apiintentdetail.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var recyclerVIew: RecyclerView

    private val api by lazy { ApiService().apiEndPoint }

    lateinit var listAdapter: ListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar!!.title = "Menu"

        setupView()
        setupRecyclerView()

    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun setupView(){
        recyclerVIew = findViewById(R.id.recyclerView)

    }

    private fun setupRecyclerView(){
        listAdapter = ListAdapter(arrayListOf(), object : ListAdapter.OnAdapterListener{
            override fun setRead(list: DataModel.Data) {
                Toast.makeText(applicationContext, list.strMeal, Toast.LENGTH_SHORT).show()
            }

            override fun onClik(list: DataModel.Data) {
                startActivity(Intent(this@MainActivity,DataDetail::class.java)
                    .putExtra("meals", list))
            }

        })
        recyclerVIew.layoutManager = LinearLayoutManager(this@MainActivity)
        recyclerVIew.adapter = listAdapter
    }

    private fun getData(){
        api.getData().enqueue(object : Callback<DataModel>{
            override fun onResponse(call: Call<DataModel>, response: Response<DataModel>) {
                if (response.isSuccessful){
                    val listMeals = response.body()!!.meals
                    listAdapter.setData(listMeals)
                }
            }
            override fun onFailure(call: Call<DataModel>, t: Throwable) { }
        })
    }


}