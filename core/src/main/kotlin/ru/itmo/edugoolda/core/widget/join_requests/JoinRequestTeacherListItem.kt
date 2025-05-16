package ru.itmo.edugoolda.core.widget.join_requests

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import ru.itmo.edugoolda.core.theme.AppTheme

@Composable
fun JoinRequestTeacherListItem(
    groupName: String,
    studentName: String,
    date: String,
    onAcceptClick: () -> Unit,
    onDeclineClick: () -> Unit,
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
                text = groupName,
                style = CustomTheme.typography.body.regular
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = studentName,
                style = CustomTheme.typography.body.regular
            )
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = date,
                style = CustomTheme.typography.caption.regular
            )
        }
        Row {
            IconButton(
                onClick = onAcceptClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Check,
                    contentDescription = "Apply"
                )
            }
            Spacer(modifier = Modifier.size(10.dp))
            IconButton(
                onClick = onDeclineClick,
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
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
        JoinRequestTeacherListItem(
            groupName = "Математика 7Б",
            studentName = "Иванов Василий",
            date = "19:47 15.04.24",
            onAcceptClick = { },
            onDeclineClick = { }
        )
    }
}
