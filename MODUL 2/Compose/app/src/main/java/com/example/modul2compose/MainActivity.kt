package com.example.modul2compose

import android.R.attr.enabled
import android.R.attr.icon
import android.R.attr.type
import android.R.id.icon
import android.annotation.SuppressLint
import android.graphics.Paint
import android.os.Bundle
import android.widget.NumberPicker
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.modul2compose.ui.theme.Modul2ComposeTheme
import kotlin.math.roundToInt

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
    modifier: Modifier,
    content: @Composable (PaddingValues) -> Unit,
) {
    Scaffold(
        modifier =  modifier.fillMaxSize(),
    ) { innerPadding ->
        content(innerPadding)
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun TipContent(modifier: Modifier = Modifier) {
    Column (
        modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        var amount by remember { mutableStateOf("") }
        var checked by remember { mutableStateOf(false) }
        val amountDouble = amount.toDoubleOrNull()
        var selectedOption by remember { mutableStateOf("15%") }

        Text(
            modifier = Modifier
                .fillMaxWidth(0.67f)
                .padding(top = 20.dp, bottom = 20.dp),
            textAlign = TextAlign.Start,
            text = "Calculate Tip"
        )

        TextField(
            value = amount,
            onValueChange = { amount = it },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachMoney,
                    contentDescription = null
                )
            },
            label = { Text("Bill Amount") }
        )

        TipPercentageDropdown(
            currentSelection = selectedOption,
            onOptionSelected = { newPercentage -> selectedOption = newPercentage }
        )

        val percent = selectedOption.replace("%", "")
        var tipAmount = (amountDouble?.times(percent.toDouble())?.div(100)) ?: 0.00

        Row(
            modifier = Modifier.fillMaxWidth(0.67f)
                .padding(bottom = 20.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Round up tip?")

            Switch(
                checked = checked,
                onCheckedChange = { checked = it }
            )
        }

        if (checked) tipAmount = kotlin.math.ceil(tipAmount)
        val formattedTip = String.format("%.2f", tipAmount)
        Text(text = "Tip Amount: $$formattedTip", fontSize = 33.sp, fontWeight = FontWeight.Bold)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TipPercentageDropdown(currentSelection: String, onOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val tipPercentage = listOf("15%", "18%", "20%")


    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
    ) {
        TextField(
            modifier = Modifier.menuAnchor(
                type = MenuAnchorType.PrimaryNotEditable,
                enabled = true
            ),

            label = { Text("Tip Percentage") },
            leadingIcon = { Text("%") },
            value = currentSelection,
            onValueChange = {},
            readOnly = true,
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) }
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            tipPercentage.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}



@Preview
@Composable
fun MyAppPreview() {
    Modul2ComposeTheme {
        MyApp(Modifier, { TipContent(Modifier) })
    }
}