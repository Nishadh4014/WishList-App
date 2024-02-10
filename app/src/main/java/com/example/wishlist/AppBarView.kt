package com.example.wishlist

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarView(title: String,
               onBackNavClicked: () -> Unit = {}) {



        val navigationIcon: (@Composable () -> Unit) = {
            if(!title.contains("My Wishlist"))
            IconButton(onClick = { onBackNavClicked() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = Color.Black,
                    contentDescription = null
                )
            }
        }

    TopAppBar(title = { Text(text = title, color = Color.Black)}, modifier = Modifier
        .padding(5.dp)
        .shadow(
            elevation = 5.dp,
            spotColor = Color.DarkGray,
            shape = RoundedCornerShape(10.dp)
        )
        .background(colorResource(id = R.color.app_bar_background)),
        navigationIcon = navigationIcon)


}