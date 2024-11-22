package com.example.proyecto

import android.os.Bundle
import android.view.Menu
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.proyecto.ui.theme.ProyectoTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.ButtonDefaults
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProyectoTheme {
                Menu()

            }
        }
    }
}

@Composable
fun Menu(modifier: Modifier = Modifier) {


    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf("") }
    var inputValue by remember { mutableStateOf(TextFieldValue("")) }
    var resultado by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .then(
                Modifier.background(Color(0xFFE1F5FE))
            )
            .padding(16.dp)
    )
    Image(
            painter = painterResource(id = R.drawable.unidades),
    contentDescription = "",
    modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.3f)
        .padding(bottom = 16.dp),
    contentScale = ContentScale.Crop
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido al convertidor de unidades",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            ),
        )

        val option = listOf(
            "Horas a minutos",
            "Gramos a Kilogramos",
            "Segundos a minutos",
            "Metros a Centimetros"
        )

        Button(
            onClick = { expanded = !expanded },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.LightGray,
                contentColor = Color.Black
            ),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {

            Text(text = "Mostrar menu")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }

        ) {
            option.forEach { option ->
                DropdownMenuItem(
                    text = { Text(text = option) },
                    onClick = {
                        expanded = false
                        selectedOption = option
                        inputValue = TextFieldValue("")
                        resultado = ""
                    })
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        if (selectedOption.isNotEmpty()) {
            Text(
                text = "Ha seleccionado: $selectedOption",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = inputValue,
                onValueChange = { inputValue = it },
                label = { Text("Ingrese el valor para convertir") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = {
                    val input = inputValue.text.trim().toDoubleOrNull()
                    resultado = if (input != null)

                        when (selectedOption) {
                            "Horas a minutos" -> "$input Horas = ${input * 60} Minutos"
                            "Gramos a Kilogramos" -> "$input Gramos = ${input / 1000} Kilogramos"
                            "Segundos a minutos" -> "$input Segundos = ${input / 60} Minutos"
                            "Metros a Centimetros" -> "$input Metros = ${input * 100} Centimetros"
                            else -> "No valido"

                        } else {
                        "Ingrese un numero valido"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray,
                    contentColor = Color.Black
                ),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Convertir")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (resultado.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF8BBD0)
                    ),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = "Resultado:",
                            style = MaterialTheme.typography.titleMedium
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = resultado,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }
            }

        }
    }













