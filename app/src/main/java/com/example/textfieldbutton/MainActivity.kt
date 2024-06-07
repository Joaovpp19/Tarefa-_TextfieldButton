package com.example.textfieldbutton

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.textfieldbutton.ui.theme.DebugButtonColors
import com.example.textfieldbutton.ui.theme.ErrorButtonColors
import com.example.textfieldbutton.ui.theme.InfoButtonColors
import com.example.textfieldbutton.ui.theme.TextfieldButtonTheme
import com.example.textfieldbutton.ui.theme.WarningButtonColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextfieldButtonTheme {
                // A surface container using the 'background' color from the theme
               App()
            }
        }
    }
}

@Composable
private fun App() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = colorScheme.background
    ){ Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(R.drawable.logo_da_etec),
                contentDescription = "Logo da Etec Zona Leste",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .border(1.5.dp, colorScheme.onSecondaryContainer, CircleShape)
            )

            Greeting("Atividade de PAM2 ")
            GreetingName("Aluno: João Vitor - 3ºDS")

            var nome by remember { mutableStateOf("") }

            TextCampo(
                text = nome,
                onTextChanged = { novoNome -> nome = novoNome }
            )
            ActionButton(
                text = "Nota: I",
                buttonColors = ErrorButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) { Log.e(TAG, "Nota I")
            }
            ActionButton(
                text = "Nota: R",
                buttonColors = WarningButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) { Log.w(TAG, "Nota R")
            }
            ActionButton(
                text = "Nota: B",
                buttonColors = DebugButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) { Log.d(TAG, "Nota B")
            }
            ActionButton(
                text = "Nota: MB",
                buttonColors = InfoButtonColors(),
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Log.i(TAG, "Nota MB")
            }
        }
    }
}


@Composable
fun ActionButton(
    text: String,
    buttonColors: ButtonColors = ButtonDefaults.buttonColors(),
    modifier: Modifier = Modifier,
    block: () -> Unit
) {
    ElevatedButton(
        onClick = block,
        shape = RoundedCornerShape(5.dp),
        colors = buttonColors,
        modifier = Modifier
    )
    {
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextCampo(
    text: String,
    onTextChanged: (String) -> Unit,
) {
    TextField(
        value = text,
        onValueChange = { newValue ->
            onTextChanged(newValue)
            Log.d("Nome", newValue)
        },
        label = { Text("Nome:") },
        placeholder = { Text("Digite seu Nome: ") }
    )
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier){
    val nome = "PAM2"
    Text(
        text = "Tarefa de $nome",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = colorScheme.secondary,
        fontSize = 24.sp
    )
}

@Composable
fun GreetingName(name: String, modifier: Modifier = Modifier){
    val nome = "João Vitor"
    Text(
        text = "Aluno: $nome - 3ºDS",
        style = MaterialTheme.typography.bodyLarge.copy(
            fontWeight = FontWeight.Bold
        ),
        color = colorScheme.secondary,
        fontSize = 24.sp
    )
}