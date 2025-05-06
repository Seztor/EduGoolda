package ru.itmo.edugoolda.features.home.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.itmo.edugoolda.core.R
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme
import ru.itmo.edugoolda.core.widget.bottom_bar.BottomBarItem
import ru.itmo.edugoolda.core.widget.bottom_bar.CustomBottomBar
import ru.itmo.edugoolda.core.widget.button.AppButton
import ru.itmo.edugoolda.core.widget.button.ButtonType

data class LessonListItem(
    val name: String,
    val time: String,
    val result: String
)

data class PendingRequestsListItem(
    val groupName: String,
    val time: String
)

@Composable
fun Home(
    lessonListItems: List<LessonListItem>,
    requestListItems: List<PendingRequestsListItem>,
    onAllResultsClick: () -> Unit,
    onAllRequestsClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 30.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "EduGoolda",
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold,
                fontSize = 52.sp
            )
        }

        Spacer(modifier = Modifier.height(5.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                painter = painterResource(R.drawable.ic_40_solutions),
                contentDescription = "View",

                )
            Text(
                text = "Ближайшие уроки",
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        SolutionsList(lessonListItems)

        // All Solutions button
        AppButton(
            modifier = Modifier
                .clickable(onClick = onAllResultsClick)
                .fillMaxWidth(),

            buttonType = ButtonType.Secondary,
            onClick = {},
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = "Все результаты",
                style = CustomTheme.typography.button.bold,
            )
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(R.drawable.ic_24_app_button_path),
                contentDescription = null
            )
        }

        HorizontalDivider(modifier = Modifier.padding(vertical = 1.dp))

        // Requests section header
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 10.dp),
                painter = painterResource(R.drawable.ic_40_group_join_request),
                contentDescription = "View",

                )
            Text(
                text = "Мои заявки",
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        }

        // Request list
        JoinRequestsList(requestListItems)

        // All requests button
        AppButton(
            modifier = Modifier
                .clickable(onClick = onAllRequestsClick)
                .fillMaxWidth(),

            buttonType = ButtonType.Secondary,
            onClick = {},
            contentPadding = PaddingValues(12.dp)
        ) {
            Text(
                text = "Все заявки",
                style = CustomTheme.typography.button.bold,
            )
            Icon(
                modifier = Modifier.padding(start = 12.dp),
                painter = painterResource(R.drawable.ic_24_app_button_path),
                contentDescription = null
            )
        }
    }
}

@Composable
fun SolutionsList(items: List<LessonListItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        items.forEach { item ->
            SolutionItem(
                name = item.name,
                result = item.result,
                time = item.time
            )
        }
    }
}

@Composable
fun SolutionItem(
    name: String,
    result: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = name, style = CustomTheme.typography.caption.regular, fontSize = 16.sp)
            Text(
                text = result,
                style = CustomTheme.typography.caption.regular,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Text(
            text = time,
            style = CustomTheme.typography.caption.regular,
            color = CustomTheme.colors.content.contentTertiary
        )
    }
}

@Composable
fun JoinRequestsList(items: List<PendingRequestsListItem>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        items.forEach { item ->
            JoinRequestItem(
                name = item.groupName,
                time = item.time
            )
        }
    }
}

@Composable
fun JoinRequestItem(
    name: String,
    time: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = name,
                style = CustomTheme.typography.caption.regular,
                fontSize = 16.sp
            )
            Text(
                text = time,
                style = CustomTheme.typography.caption.regular,
                color = CustomTheme.colors.content.contentTertiary,
                fontSize = 16.sp
            )
        }

        Text(
            text = "На рассмотрении",
            style = CustomTheme.typography.caption.regular,
            color = CustomTheme.colors.content.contentTertiary,
            fontSize = 16.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun HomePreview() {

    AppTheme {
        Column {
            Home(
                lessonListItems = listOf(
                    LessonListItem("Математика", "15.04.2025", "0/6"),
                    LessonListItem("Русский язык", "15.04.2025", "6/8"),
                    LessonListItem("Информатика", "15.04.2025", "5/6"),
                ),
                requestListItems = listOf(
                    PendingRequestsListItem("Математика", "19:48 \t 15.04.2025"),
                    PendingRequestsListItem("Русский язык", "19:48 \t 15.04.2025"),
                    PendingRequestsListItem("Информатика", "19:48 \t 15.04.2025")
                ),
                onAllResultsClick = {},
                onAllRequestsClick = { }
                )
            val items = listOf(
                BottomBarItem("Главная", R.drawable.ic_24_home),
                BottomBarItem("Уроки", R.drawable.ic_40_solutions),
                BottomBarItem("Профиль", R.drawable.ic_24_profile)
            )
            var selectedItem by remember { mutableIntStateOf(0) }
            CustomBottomBar(
                items = items,
                selectedItemIndex = selectedItem,
                onItemClick = { index ->
                    selectedItem = index
                },
                modifier = Modifier.height(60.dp),
            )
        }
    }
}