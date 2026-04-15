package com.example.modul2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.modul2compose.ui.theme.Modul2ComposeTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Modul2ComposeTheme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp(modifier: Modifier = Modifier){
    TipScreen(
        modifier = modifier,
        topBar = { Greeting("hi") },
        bottomBar = {Greeting("yo")},
        content = { padding -> Greeting("he", modifier = Modifier.padding(padding))}
    )
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Composable
fun TipScreen(
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        modifier =  modifier,
        topBar = topBar,
        bottomBar = bottomBar
    ) { innerPadding ->
        content(innerPadding)
    }
}

@Preview
@Composable
fun MyAppPreview() {
Modul2ComposeTheme { MyApp() }
}