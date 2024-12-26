package com.example.coraje

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.coraje.ui.theme.CorajeTheme
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily

import androidx.compose.ui.unit.sp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CorajeTheme {
                CorajeApp()

            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CorajeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    Scaffold(


        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Coraje",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleSmall.copy(

                            fontSize = 24.sp, // Tamaño de la letra
                            fontWeight = FontWeight.Bold // Estilo de la letra
                        )


                    )
                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = colorResource(R.color.button_color)
                )
            )
        }

    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(colorResource(R.color.background_color)),
            color = colorResource(R.color.background_color)
        )
        {
            when(currentStep){
                1 ->{
                    CorrajeTextAndImage(
                        textLabeResourceId = R.string.coraje_inicial,
                        drawableResourceId = R.drawable.coraje1,
                        contentDescriptionResourceId = R.string.coraje_inicial_descripcion,
                        onImageClick = {
                            currentStep = 2
                            squeezeCount = (2..4).random()

                        }
                    )
                }
                2->{
                    CorrajeTextAndImage(
                        textLabeResourceId = R.string.coraje_medio,
                        drawableResourceId = R.drawable.coraje2,
                        contentDescriptionResourceId = R.string.coraje_medio_descripcion,
                        onImageClick = {
                            squeezeCount--
                            if (squeezeCount == 0) {
                                currentStep = 3
                            }
                        }
                    )
                }
                3 ->{
                    CorrajeTextAndImage(
                        textLabeResourceId = R.string.coraje_semi_final,
                        drawableResourceId = R.drawable.coraje3,
                        contentDescriptionResourceId = R.string.coraje_semi_final_descripcion,
                        onImageClick = {
                            currentStep = 4
                            squeezeCount = (2..4).random()
                        }
                    )

                }
                4 ->{
                    CorrajeTextAndImage(
                        textLabeResourceId = R.string.coraje_final,
                        drawableResourceId = R.drawable.coraje4,
                        contentDescriptionResourceId = R.string.coraje_final_descripcion,
                        onImageClick = {
                            currentStep = 1
                        }
                    )
                }


            }

        }

    }

}

@Composable
fun CorrajeTextAndImage(
    textLabeResourceId: Int,
    drawableResourceId: Int,
    contentDescriptionResourceId: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Button(
                onClick = onImageClick,
                shape = RoundedCornerShape(dimensionResource(R.dimen.button_corner_radius)),
                colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.button_color))
            )
            {
                Image(
                    painter = painterResource(drawableResourceId),
                    contentDescription = stringResource(contentDescriptionResourceId),
                    modifier = Modifier
                        .width(dimensionResource(R.dimen.button_image_width))
                        .height(dimensionResource(R.dimen.button_image_height))
                        .padding(dimensionResource(R.dimen.button_interior_padding))
                )
            }
            Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_vertical)))
            Text(
                text = stringResource(textLabeResourceId),
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CorajeTheme {
        CorajeApp()
    }
}