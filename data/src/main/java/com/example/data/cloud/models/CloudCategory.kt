package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class CloudCategory(@SerializedName("id") val id: Int, @SerializedName("name") val name: String)