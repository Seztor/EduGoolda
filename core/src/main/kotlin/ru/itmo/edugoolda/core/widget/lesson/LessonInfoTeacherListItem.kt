package ru.itmo.edugoolda.core.widget.lesson

import androidx.compose.material3.IconButton
import androidx.compose.ui.tooling.preview.Preview

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import ru.itmo.edugoolda.core.theme.custom.CustomTheme

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import ru.itmo.edugoolda.core.R
import ru.itmo.edugoolda.core.theme.AppTheme

@Composable
fun LessonInfoTeacherListItem(
    name: String,
    createdAt: String,
    onEditClick: () -> Unit,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                text = name,
                style = CustomTheme.typography.body.regular
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = createdAt,
                style = CustomTheme.typography.caption.regular
            )
        }
        Row {
            IconButton(
                onClick = onEditClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_24_edit),
                    contentDescription = "Apply"
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(
                onClick = onDeleteClick,
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_24_delete),
                    contentDescription = "Deny"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StudentHeaderPreview() {
    AppTheme {
        LessonInfoTeacherListItem(
            name = "Математика 7Б",
            createdAt = "19:47 15.04.24",
            onEditClick = { },
            onDeleteClick = { }
        )
    }
}
