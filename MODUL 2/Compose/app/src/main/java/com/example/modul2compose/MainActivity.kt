package com.example.modul2compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
                MyApp(Modifier, { TipContent(Modifier) })
            }
        }
    }
}


@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    tipContent: @Composable (PaddingValues) -> Unit
) {
    TipScreen(
        modifier,
        tipContent,
    )
}

@Composable
fun TipScreen(
    modifier: Modifier = Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier =  modifier,
    ) { innerPadding ->
        content(innerPadding)
    }
}

@Composable
fun TipContent(
    modifier: Modifier = Modifier,
) {
    Column{
        Text("Calculate Tip")
    }
}

@Preview
@Composable
fun MyAppPreview() {
    Modul2ComposeTheme {
        MyApp(Modifier, { TipContent(Modifier) })
    }
}