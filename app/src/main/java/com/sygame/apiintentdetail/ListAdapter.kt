package com.sygame.apiintentdetail

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ListAdapter(var list: ArrayList<DataModel.Data>, var listener : OnAdapterListener)
    : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var bilder:  ImageView = itemView.findViewById(R.id.bilder)
        var textJudul: TextView = itemView.findViewById(R.id.textJudul)
        var textId: TextView = itemView.findViewById(R.id.textId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.list_adapter,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var data = list[position]
        holder.textJudul.text = data.strMeal
        holder.textId.text = data.idMeal
        //menggunakan Glide untuk mendownload gambar
        Glide.with(holder.bilder)
            .load(data.strMealThumb)
            .centerCrop()
            .into(holder.bilder)

        Log.d("ListAdapter","image: ${data.strMealThumb}")

        holder.textJudul.setOnClickListener {
            listener.setRead(data)
        }
        holder.bilder.setOnClickListener {
            listener.onClik(data)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(data: List<DataModel.Data>){
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    interface OnAdapterListener{
        fun setRead (list: DataModel.Data)
        fun onClik (list: DataModel.Data)
    }
}