package com.example.wishlist

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField


import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish
import kotlinx.coroutines.launch
import java.nio.file.WatchEvent

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddEditDetailView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {
    println(viewModel.allWishes)
    val snackMessage = remember {
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScrollState()

    if(id != 0L) {           // updating, so need to display the current data

        val current_wish = viewModel.getWishById(id).collectAsState(initial = Wish("","",0L))
        viewModel.wishTitleState = current_wish.value.title
        viewModel.wishDescriptionState = current_wish.value.description
    } else {               // adding so current state would be empty
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }


    Scaffold(
        topBar = { AppBarView(title = if (id != 0L) "Update Wish" else "Add Wish") { navController.navigateUp() } } // back button functionality using simple navigateUp (bcz its rememberNavController so goes back to prev page)
    ) {
        Column(
            modifier = Modifier.padding(top = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,

        ) {

            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {
                viewModel.onWishTitleChanged(it)
            })
            // add spacer
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChanged = {
                    viewModel.onWishDescriptionChange(it)
                })
            Spacer(modifier = Modifier.height(10.dp))
            // add button
            Button(onClick = {
                      if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()) {

                          if(id != 0L) {
                              // update
                              viewModel.updateWish(
                                  Wish(
                                      title = viewModel.wishTitleState.trim(),
                                      description = viewModel.wishDescriptionState.trim(),
                                      id = id
                                  )
                              )
                          } else {
                              // add
                              viewModel.addWish(
                                  Wish(
                                      title = viewModel.wishTitleState.trim(),
                                      description = viewModel.wishDescriptionState.trim()
                                  )
                              )
                              snackMessage.value = "Wish has been created!!"
                          }

                      } else {
                          snackMessage.value = "Enter fields to create a wish"
                      }
                // now whenever use clicks the add/update wish button, show the snackbar state and navigate to home screen
                scope.launch {
                    navController.navigateUp()
                }

            }) {
                Text(
                    text = if (id != 0L) "Update Wish" else "Add Wish",
                    style = TextStyle(fontSize = 18.sp)
                )

            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChanged: (String)->Unit
) {
    OutlinedTextField(value = value, onValueChange = onValueChanged, label = { Text(text = label, color = Color.Black)}, modifier = Modifier.fillMaxWidth())
}