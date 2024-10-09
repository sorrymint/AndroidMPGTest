package com.example.mpgtesting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mpgtesting.ui.theme.MPGTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MPGTestingTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MPGCalculator()
                }
            }
        }
    }
}

@Composable
fun MPGCalculator() {
    var distance by remember { mutableStateOf("") }
    var fuelConsumed by remember { mutableStateOf("") }
    var mpg by remember { mutableStateOf(0.0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        DistanceInput(distance) { newDistance ->
            distance = newDistance
        }
        Spacer(modifier = Modifier.height(20.dp))
        FuelConsumedInput(fuelConsumed) { newFuelConsumed ->
            fuelConsumed = newFuelConsumed
        }
        Spacer(modifier = Modifier.height(20.dp))
        CalculateButton(distance, fuelConsumed) { newMpg ->
            mpg = newMpg
        }
        Result(mpg)
    }
}

@Composable
fun DistanceInput(distance: String, onDistanceChanged: (String) -> Unit) {
    TextField(
        value = distance,
        onValueChange = { value ->
            onDistanceChanged(value)
        },
        label = { Text("Distance (miles)") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("DistanceInput")
    )
}

@Composable
fun FuelConsumedInput(fuelConsumed: String, onFuelConsumedChanged: (String) -> Unit) {
    TextField(
        value = fuelConsumed,
        onValueChange = { value ->
            onFuelConsumedChanged(value)
        },
        label = { Text("Fuel Consumed (gallons)") },
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .fillMaxWidth()
            .testTag("FuelConsumedInput")
    )
}

@Composable
fun CalculateButton(distance: String, fuelConsumed: String, onMpgCalculated: (Double) -> Unit) {
    Button(
        onClick = {
            val calculatedMpg = distance.toDoubleOrNull()
                ?.let { fuelConsumed.toDoubleOrNull()?.let { it1 -> calculateMPG(it, it1) } }
            if (calculatedMpg != null) {
                onMpgCalculated(calculatedMpg)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .testTag("CalculateButton")
    ) {
        Text("Calculate MPG")
    }
}

@Composable
fun Result(mpg: Double) {
    Text(
        text = "MPG: $mpg",
        modifier = Modifier
            .padding(top = 16.dp)
            .testTag("Result")
    )
}

internal fun calculateMPG(distance: Double, fuelConsumed: Double): Double {
    if(distance <= 0 || fuelConsumed < 0){
        return 0.0
    }
    return distance / fuelConsumed
}

@Preview(showBackground = true)
@Composable
fun PreviewMPGCalculator() {
    MPGCalculator()
}
