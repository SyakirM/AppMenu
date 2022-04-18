package com.sygame.apiintentdetail

import java.io.Serializable


data class DataModel (var meals: ArrayList<Data>) {
    //nama parameter harus sama seperti di API
    data class Data (var strMeal: String?, var strMealThumb: String?, var idMeal: String? ): Serializable
}