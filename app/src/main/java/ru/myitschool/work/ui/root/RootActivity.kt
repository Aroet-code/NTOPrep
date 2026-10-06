package ru.myitschool.work.ui.root

import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import ru.myitschool.work.ui.screen.AppNavHost
import ru.myitschool.work.ui.theme.WorkTheme

class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        enableEdgeToEdge()
        setContent {
            WorkTheme {
                AppNavHost(
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }
}