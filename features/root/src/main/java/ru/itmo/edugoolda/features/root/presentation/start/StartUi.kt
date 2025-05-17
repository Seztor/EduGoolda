package ru.itmo.edugoolda.features.root.presentation.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.itmo.edugoolda.core.theme.custom.CustomTheme

@Composable
fun StartUi(
    component: StartComponent,
    modifier: Modifier = Modifier
) {
    Box(modifier.fillMaxSize(), Alignment.Center) {
        Text("EduGoolda", style = CustomTheme.typography.title.bold)
    }
}