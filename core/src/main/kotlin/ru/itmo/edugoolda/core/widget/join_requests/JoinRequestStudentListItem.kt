package ru.itmo.edugoolda.core.widget.join_requests

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme

@Composable
fun JoinRequestStudentListItem(
    groupName: String,
    date: String,
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
                text = date,
                style = CustomTheme.typography.caption.regular
            )
        }
        Text(
            text = "На рассмотрении",
            style = CustomTheme.typography.caption.regular
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun StudentHeaderPreview() {
    AppTheme {
        JoinRequestStudentListItem(
            groupName = "Математика 7Б",
            date = "19:47 15.04.24",
        )
    }
}
