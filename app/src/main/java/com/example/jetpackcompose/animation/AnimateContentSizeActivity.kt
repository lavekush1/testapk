package com.example.jetpackcompose.animation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Box
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.currentTextStyle
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp

class AnimateContentSizeActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimateContentSizeDemo()
        }
    }
}

@Composable
fun AnimateContentSizeDemo() {
    Column(
        Modifier.wrapContentHeight()
            .fillMaxWidth()
            .padding(50.dp)
            .background(Color.Gray)
            .padding(50.dp)
    ) {
        text()
        Spacer(Modifier.height(20.dp))
        button()
        Spacer(Modifier.height(20.dp))
        image()
    }
}

@Composable
fun text() {
    val shortText = "Click me"
    val longText = "Very long text\nthat spans across\nmultiple lines"
    var short by remember { mutableStateOf(true) }
    Box(modifier = Modifier
        .background(Color.Blue,
            RoundedCornerShape(15.dp)
        )
        .clickable { short = !short }
        .padding(20.dp)
        .wrapContentSize()
        .animateContentSize { startSize, endSize -> println("$startSize -> $endSize") }
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = currentTextStyle().copy(color = Color.White)
        )
    }
}

@Composable
fun button() {
    val shortText = "Short"
    val longText = "Very loooooong text"
    var short by remember { mutableStateOf(true) }
    Button(
        { short = !short }
    ) {
        Text(
            if (short) {
                shortText
            } else {
                longText
            },
            style = currentTextStyle().copy(color = Color.White),
            modifier = Modifier.animateContentSize()
        )
    }
}

@Composable
fun image() {
    var portraitMode by remember { mutableStateOf(true) }
    Box(
        Modifier.clickable { portraitMode = !portraitMode }
            .sizeIn(maxWidth = 300.dp, maxHeight = 300.dp)
            .background(if (portraitMode) Color(0xFFfffbd0) else Color(0xFFe3ffd9))
            .animateContentSize(tween(500))
            .aspectRatio(if (portraitMode) 3 / 4f else 16 / 9f)
    ) {
        Text(
            if (portraitMode) {
                "3 : 4"
            } else {
                "16 : 9"
            },
            style = currentTextStyle().copy(color = Color.Black)
        )
    }
}

