package ru.itmo.edugoolda.core.widget.lesson

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.theme.AppTheme
import ru.itmo.edugoolda.core.theme.custom.CustomTheme

@Composable
fun LessonInfoStudentListItem(
    name: String,
    createdAt: String,
    onClick: () -> Unit,
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
        IconButton(
            onClick = onClick,
            modifier = Modifier.size(40.dp)
        ) {
            Icon(
                modifier = Modifier.size(40.dp),
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Apply"
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun StudentHeaderPreview() {
    AppTheme {
        LessonInfoStudentListItem(
            name = "Введение в геометрию",
            createdAt = "19:47 15.04.24",
            onClick = { },
        )
    }
}
