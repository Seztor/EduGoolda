package ru.itmo.edugoolda.core.widget.bottom_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.localized
import dev.icerock.moko.resources.desc.Raw
import dev.icerock.moko.resources.desc.StringDesc
import ru.itmo.edugoolda.core.R
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme

@Composable
fun <T> CustomBottomBar(
    items: Iterable<T>,
    selectedItem: T,
    getString: (T) -> StringDesc,
    getIcon: (T) -> Int,
    onItemClick: (T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .background(CustomTheme.colors.background.backgroundPrimary)
                .selectableGroup(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEach { item ->
                BottomBarItem(
                    item = item,
                    isSelected = item == selectedItem,
                    onClick = { onItemClick(item) },
                    modifier = Modifier.weight(.1f),
                    getString = getString,
                    getIcon = getIcon,
                )
            }
        }
    }
}

@Composable
private fun <T> BottomBarItem(
    item: T,
    getString: (T) -> StringDesc,
    getIcon: (T) -> Int,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Icon(
            painter = painterResource(id = getIcon(item)),
            contentDescription = null,
            tint = if (isSelected) CustomTheme.colors.content.contentActive else CustomTheme.colors.content.contentTertiary,
            modifier = Modifier.size(24.dp)
        )
        Text(
            text = getString(item).localized(),
            color = if (isSelected) CustomTheme.colors.content.contentPrimary else CustomTheme.colors.content.contentTertiary,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            style = CustomTheme.typography.caption.regular
        )
    }
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun CustomBottomBarPreview() {
    AppTheme {
        val items = listOf(
            Pair("Home", R.drawable.ic_24_heart),
            Pair("Search", R.drawable.ic_24_heart),
            Pair("Profile", R.drawable.ic_24_heart),
            Pair("Settings", R.drawable.ic_24_heart)
        )
        var selectedItem by remember { mutableIntStateOf(0) }
        CustomBottomBar(
            items = items,
            selectedItem = items[selectedItem],
            onItemClick = { item ->
                selectedItem = items.indexOf(item)
            },
            getString = { StringDesc.Raw(it.first) },
            getIcon = { it.second },
            modifier = Modifier,
        )
    }
}
