package ru.itmo.edugoolda.features.auth.main

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.R

import ru.itmo.edugoolda.core.widget.bottom_bar.BottomBarItem
import ru.itmo.edugoolda.core.widget.bottom_bar.CustomBottomBar

import androidx.compose.material3.*
import ru.itmo.edugoolda.core.theme.AppTheme

@Composable
fun MainScreen(
    bottomBarItems: List<BottomBarItem>,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Scaffold(
        modifier = modifier,
        bottomBar = { BottomBar(bottomBarItems) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .padding(padding)
                    .fillMaxSize()
            ) {
                content()
            }
        }
    )
}

@Composable
private fun BottomBar(items: List<BottomBarItem>) {
    var selectedItem by remember { mutableIntStateOf(0) }
    CustomBottomBar(
        items = items,
        selectedItemIndex = selectedItem,
        onItemClick = { index ->
            selectedItem = index
        },
        modifier = Modifier,
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewMainScreen() {
    AppTheme {
        MainScreen(
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Welcome!")
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = { }) {
                        Text("Click me")
                    }
                }
            },
            bottomBarItems = listOf(
                BottomBarItem("Главная", R.drawable.ic_24_home),
                BottomBarItem("Создать", R.drawable.ic_24_add),
                BottomBarItem("Группы", R.drawable.ic_24_groups),
                BottomBarItem("Профиль", R.drawable.ic_24_profile)
            )
        )
    }
}
