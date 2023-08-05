package com.example.kmpproject.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kmpproject.Calculator

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                CalScreen()
            }

        }
    }
}



@Composable
fun OperationButton(operator: String, onClick: () -> Unit) {
    Button(onClick = onClick, modifier = Modifier
        .size(width = 70.dp, height = 70.dp)
        .clip(
            CircleShape
        )) {
        Text(operator)
    }
}

@Composable
fun ColoredButton(
    onClick: () -> Unit,
    color: Color,
    content: @Composable () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .size(width = 70.dp, height = 70.dp)
            .clip(CircleShape)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color)
        ) {
            content()
        }
    }
}

@Composable
fun CalScreen(){
    var num1 by remember { mutableStateOf("") }
    var num2 by remember { mutableStateOf("") }
    var operator by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column {
        Text("$result", modifier = Modifier.padding(20.dp), fontSize = 50.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Spacer(modifier = Modifier.height(150.dp))
        Column( horizontalAlignment = Alignment.End, modifier = Modifier
            .fillMaxWidth()
            .padding(end = 20.dp)) {
            Text(text = num1, modifier = Modifier.padding(end = 20.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = num2, modifier = Modifier.padding(end = 20.dp, bottom = 30.dp), fontSize = 30.sp, fontWeight = FontWeight.Bold, color = Color.White)

           
        }

        Row(verticalAlignment = Alignment.CenterVertically){
            Column(horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(6.dp)){

                for (rowIndex in 0 until 3) {
                    Row(
                        modifier = Modifier
                            .padding(start = 20.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        for (digit in 1..3) {
                            val number = rowIndex * 3 + digit
                            Button(onClick = {
                                if (operator.isEmpty()) {
                                    num1 += number.toString()
                                } else {
                                    num2 += number.toString()
                                }
                            },modifier = Modifier
                                .size(width = 70.dp, height = 70.dp)
                                .clip(
                                    CircleShape
                                )
                            , colors = ButtonDefaults.buttonColors(Color.White)
                            ) {
                                Text(number.toString(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
                            }
                        }
                    }

                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.padding(start = 20.dp)
                ) {
                    Button(onClick = {
                        if (operator.isEmpty()) {
                            num1 += "0"
                        } else {
                            num2 += "0"
                        }
                    },modifier = Modifier
                        .size(width = 70.dp, height = 70.dp)
                        .clip(
                            CircleShape
                        ), colors = ButtonDefaults.buttonColors(Color.White)) {
                        Text("0")
                    }
                    Button(onClick = {
                        if (operator.isEmpty()) {
                            num1 += "."
                        } else {
                            num2 += "."
                        }
                    }, modifier = Modifier
                        .size(width = 70.dp, height = 70.dp)
                        .clip(CircleShape), colors = ButtonDefaults.buttonColors(Color.White)) {
                        Text(".")
                    }

                    Button(onClick = {
                        num1 = ""
                        num2 = ""
                        operator = ""
                        result = ""
                    },modifier = Modifier
                        .size(width = 70.dp, height = 70.dp)
                        .clip(CircleShape)
                    ) {
                        Text("C")

                    }
                }


            }
            Column(horizontalAlignment = Alignment.End, modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                OperationButton("+") { operator = "+" }
                OperationButton("-") { operator = "-" }
                OperationButton("*") { operator = "*" }
                OperationButton("/") { operator = "/" }
            }


        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            if (num1.isNotEmpty() && num2.isNotEmpty() && operator.isNotEmpty()) {
                val n1 = num1.toDouble()
                val n2 = num2.toDouble()
                val calculator = Calculator()
                result = when (operator) {
                    "+" -> calculator.add(n1, n2).toString()
                        "-" -> calculator.subtract(n1, n2).toString()
                        "*" -> calculator.multiply(n1, n2).toString()
                        "/" -> calculator.divide(n1, n2).toString()
                    else -> ""
                }
            }
        }, modifier = Modifier
            .size(width = 420.dp, height = 50.dp)
            .padding(start = 20.dp, end = 20.dp)
            .clip(RoundedCornerShape(20.dp))) {
            Text("=", fontSize = 20.sp)
        }
    }

}

@Composable
@Preview(showBackground = true, showSystemUi = true, backgroundColor = 0xFFffffff)
fun CalcScreenPrev(){
    CalScreen()
}
