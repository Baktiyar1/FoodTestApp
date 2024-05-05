package com.example.data.cloud.models

import com.google.gson.annotations.SerializedName

data class CloudTag(@SerializedName("id") val id: Int, @SerializedName("name") val name: String)