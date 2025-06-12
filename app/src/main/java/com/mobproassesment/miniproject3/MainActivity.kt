package com.mobproassesment.miniproject3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.mobproassesment.miniproject3.ui.screen.MainScreen
import com.mobproassesment.miniproject3.ui.theme.Miniproject3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Miniproject3Theme {
                MainScreen()
            }
        }
    }
}

