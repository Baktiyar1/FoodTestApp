@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.foodtestapp.ui.screen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.compose.rememberNavController
import com.example.foodtestapp.ui.navigation.SetupNavGraph
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalFoundationApi
@SuppressLint("CoroutineCreationDuringComposition")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            SetupNavGraph(navController = navController)
        }
    }
}