package com.example.hostapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.example.hostapplication.ui.theme.HostApplicationTheme
import com.example.aarlibrary.Echo;
import com.example.aarlibrary.R

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Echo().sayHello();
        setContent {
            HostApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val textColor = Color(
        ContextCompat.getColor(
            context,
            R.color.aarlib_black
        )
    )
    val title = stringResource(id = com.example.aarlibrary.R.string.aartitle)
    Column(modifier = modifier) {
        Text(
            text = "Hello（你好） ${title}!",
            color = textColor,
            modifier = modifier
        )
        Image(
            painter = painterResource(id = com.example.aarlibrary.R.drawable.i_am_monster),
            contentDescription = "Image from aarLibrary",
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HostApplicationTheme {
        Greeting("Android")
    }
}