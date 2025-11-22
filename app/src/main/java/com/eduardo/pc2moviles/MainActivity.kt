package com.eduardo.pc2moviles

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.eduardo.pc2moviles.ui.theme.Pc_002Theme
import com.eduardo.pc2moviles.presentation.navigation.AppNavGraph

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Pc_002Theme {
                AppNavGraph()
            }
        }
    }
}
