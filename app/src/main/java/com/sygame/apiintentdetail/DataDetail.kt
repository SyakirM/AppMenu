package com.sygame.apiintentdetail

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
class DataDetail : AppCompatActivity() {

    lateinit var bilderDetail: ImageView
    lateinit var judulDetail: TextView
    lateinit var idDetail: TextView

    private val detail by lazy { intent.getSerializableExtra("meals") as DataModel.Data}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)

        supportActionBar!!.title = "Detail Menu"
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        setupView()
        setupIntent()
    }

    private fun setupView(){
        bilderDetail = findViewById(R.id.bilderDetail)
        judulDetail = findViewById(R.id.judulDetail)
        idDetail = findViewById(R.id.idDetail)
    }

    private fun setupIntent(){
        Glide.with(bilderDetail).load(detail.strMealThumb).into(bilderDetail)
        judulDetail.setText(detail.strMeal)
        idDetail.setText(detail.idMeal)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}