package com.example.lesson_23_drawer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Drawer() {
    val items = listOf(
        DrawerItem(
            Icons.Default.Favorite,
            "Favorite"
        ),
        DrawerItem(
            Icons.Default.Home,
            "Home"
        ),
        DrawerItem(
            Icons.Default.Info,
            "Info"
        ),
    )

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val selectedItem = remember {
        mutableStateOf(items[0])
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Image(
                    painter = painterResource(id = R.drawable.head),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(15.dp))
                items.forEach {
                    NavigationDrawerItem(
                        modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                        icon = {
                               Icon(
                                   imageVector = it.imageVector,
                                   contentDescription = it.title
                               )
                        },
                        label = {
                                Text(text = it.title)
                        },
                        selected = selectedItem.value == it,
                        onClick = {
                            coroutineScope.launch {
                                selectedItem.value = it
                                drawerState.close()
                            }
                        }
                    )
                }
            }
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Button(onClick = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                }) {
                    Text(text = "Click me")
                }
            }
        }
    )
}

data class DrawerItem(
    val imageVector: ImageVector,
    val title: String
)

