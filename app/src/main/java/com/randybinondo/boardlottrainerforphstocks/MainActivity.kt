package com.randybinondo.boardlottrainerforphstocks

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.randybinondo.boardlottrainerforphstocks.ui.numerickeypad.NumericKeypad
import com.randybinondo.boardlottrainerforphstocks.ui.popup.CustomPopup
import com.randybinondo.boardlottrainerforphstocks.ui.popup.IconWithCustomPopup
import com.randybinondo.boardlottrainerforphstocks.ui.popup.PopupState
import com.randybinondo.boardlottrainerforphstocks.ui.tables.DataProvider
import com.randybinondo.boardlottrainerforphstocks.ui.tables.MenuItem

import com.randybinondo.boardlottrainerforphstocks.ui.theme.BoardLotForPhilippineStocksTheme
import com.randybinondo.boardlottrainerforphstocks.ui.theme.Highlight20

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoardLotForPhilippineStocksTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScaffoldComposable()
                }
            }
        }
    }
}

@Composable
fun InputComposable() {
    var numberValue by remember {mutableStateOf<Double>(0.00)}
    val change : (String) -> Unit = { it ->
        Log.i("test", it.toString())
        //numberValue = it
        //numberValue = it.toDoubleOrNull() ?: numberValue
    }
    TextField(
        value = numberValue.toString(),
        onValueChange = { numberValue = it.toDouble() },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        label = { Text("Enter amount") }
    )
}

@Composable
fun DropdownMenuComposable(
    onChange: (Int) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("PHP", "USD")
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier.wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex],
            color = Color.White,
            modifier = Modifier
                .padding(end = 15.dp)
                .clickable(onClick = { expanded = true }))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEachIndexed { index, s ->
                val disabledText = ""
                DropdownMenuItem(
                    text = {
                        Text(text = s + disabledText)
                    },
                    onClick = {
                    selectedIndex = index
                    onChange(selectedIndex)
                    expanded = false
                })
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldComposable() {
    var selectedCurrencyIndex by remember { mutableStateOf(0) }
    val materialBlue700 = Color(0xFF1976D2)
    val keypadPopupState = remember { PopupState(false) }
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.height(60.dp)
            ) {
                Text(text = "Currency: PHP", color = Color.Black)
            }
        },
        topBar = {
            TopAppBar(
                title = {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "Board Lot Trainer")
                        DropdownMenuComposable(onChange = { value ->
                            selectedCurrencyIndex = value
                        })
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    titleContentColor = Color.White,
                    containerColor = materialBlue700
                )
            )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = { FloatingActionButton(onClick = {
            keypadPopupState.isVisible = !keypadPopupState.isVisible
        }){
            Text("Clear")
        } },
    ) {
        innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            ) {
            BoardLotComposable(selectedCurrencyIndex === 1)
            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier.size(100.dp)) {
                //Keypad(keypadPopupState)
            }
        }
    }
}

@Composable
fun Keypad(
    popupState: PopupState = remember {
        PopupState(false)
    }
){
    val inputVal = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()

    CustomPopup(
        popupState = popupState,
        onDismissRequest = {
            popupState.isVisible = false
        }
    ) {
        NumericKeypad(
            input = inputVal.value,
            scrollState = scrollState,
            onClick = { digit ->
                inputVal.value += digit.toString()
                Log.i("test", digit.toString())
            }
        )
    }

}

@Composable
fun PopupDemo() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val bottomStartState = remember { PopupState(false) }
        val bottomCenterState = remember { PopupState(false) }
        val bottomEndState = remember { PopupState(false) }
        val topStartState = remember { PopupState(false) }
        val topCenterState = remember { PopupState(false) }
        val topEndState = remember { PopupState(false) }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconWithCustomPopup(
                bottomStartState,
                "Bottom Start"
            )

            IconWithCustomPopup(
                bottomCenterState,
                "Bottom Center is long to make it center"
            )

            IconWithCustomPopup(
                bottomEndState,
                "Bottom End"
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconWithCustomPopup(
                topStartState,
                "Top Start"
            )

            IconWithCustomPopup(
                topCenterState,
                "Top Center is long to make it center"
            )

            IconWithCustomPopup(
                topEndState,
                "Top End"
            )
        }
    }
}

fun isWithinRange(value: Double, menuItem: MenuItem): Boolean {
    return menuItem.minimum <= value && menuItem.maximum >= value
}

fun getColor(amount: Double, menuItem: MenuItem): Color {
    return if(isWithinRange(amount, menuItem)) Highlight20 else Color.Transparent
}

@Composable
fun BoardLotComposable(currency: Boolean, amount: Double = 22.0) {
    val itemModifier = Modifier
        .wrapContentSize()
    val data = if (currency) DataProvider.usdboardLots.toMutableList() else DataProvider.boardlots.toMutableList()
    val stringList = listOf("Minimum", "Maximum", "Fluctuation", "Board Lot")
    val CELL_COUNT = 4

    val span: (LazyGridItemSpanScope) -> GridItemSpan = { GridItemSpan(CELL_COUNT -2) }

    val inputVal = remember { mutableStateOf("") }
    val scrollState = rememberScrollState()


    LazyVerticalGrid(
        columns = GridCells.Fixed(CELL_COUNT),
    ) {
        items(items = stringList) {
            Text(it, itemModifier, fontWeight = FontWeight.Bold)
        }
        data.forEachIndexed { index, value ->
            item {
                Box (modifier = Modifier.background(color = getColor(amount, value))){
                    Text(
                        text = if (index === 0) String.format(
                            "%.4f",
                            value.minimum
                        ) else if (value.minimum >= 5) String.format(
                            "%,.0f",
                            value.minimum
                        ) else value.minimum.toString(), itemModifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box (modifier = Modifier.background(color = getColor(amount, value))){
                    Text(
                        text = if (value.maximum >= 1999) String.format(
                            "%,.0f",
                            value.maximum
                        ) else value.maximum.toString(), itemModifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box (modifier = Modifier.background(color = getColor(amount, value))){
                    Text(
                        text = if (index === 0) String.format(
                            "%.4f",
                            value.fluctuation
                        ) else if (value.fluctuation >= 1) String.format(
                            "%.0f",
                            value.fluctuation
                        ) else value.fluctuation.toString(), itemModifier.align(Alignment.Center)
                    )
                }
            }
            item {
                Box (modifier = Modifier.background(color = getColor(amount, value))){
                    Text(text = String.format(
                        "%,d",
                        value.boardLot
                    ), itemModifier.align(Alignment.Center))
                }
            }
        }
        item (span = span) {
            //InputComposable()
            /*NumericKeypad(
                input = inputVal.value,
                scrollState = scrollState,
                onClick = { digit ->
                    inputVal.value += digit.toString()
                    Log.i("test", digit.toString())
                }
            )*/
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BoardLotForPhilippineStocksTheme {
        BoardLotComposable(false)
    }
}