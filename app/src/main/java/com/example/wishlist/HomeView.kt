package com.example.wishlist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.rememberDismissState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.wishlist.data.Wish

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(

    navController: NavController,
    viewModel: WishViewModel

) {

    Scaffold(

        topBar = { AppBarView(title = "My Wishlist")},

        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.padding(all = 20.dp),
                contentColor = Color.Black,
                onClick = {
                    navController.navigate(Screen.AddScreen.route + "/0L")
                }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }


    ) {
        val wishList = getData(viewModel = viewModel)
        println(wishList.value)
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {

            //TODO solve error
            // fetching current wishes

            items(wishList.value) {
                wish ->
                val dismissState = rememberDismissState(
                    confirmValueChange = {
                        if(it == DismissValue.DismissedToEnd || it == DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                        }
                        true
                    }
                )
                
                SwipeToDismiss(state = dismissState, background = {},
                    directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd),
                    dismissContent = {
                        WishItem(wish) {
                            val id = wish.id
                            navController.navigate(Screen.AddScreen.route + "/$id")      // opening a particular wish by providing it's id
                        }
                    }
                )


            }
        }
    }

}

@Composable
fun getData(viewModel: WishViewModel): State<List<Wish>> {
    return viewModel.allWishes.collectAsState(initial = emptyList())
}


