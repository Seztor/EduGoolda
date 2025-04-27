package ru.itmo.edugoolda.features.profile.presentation.viewProfile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.pulltorefresh.PullToRefreshState
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.itmo.edugoolda.core.widget.PullRefreshLceWidget
import ru.itmo.edugoolda.data.user.api.Profile
import ru.itmo.edugoolda.features.profile.R

@Composable
fun ProfileUI(
    component: RealProfileComponent,
    modifier: Modifier
) {
    val profileState by component.profileState.collectAsState()
    PullRefreshLceWidget(
        state = profileState,
        onRefresh = component::onRefresh,
        onRetryClick = {
            component.onRetryClick()
        },
    ) { data: Profile, refreshing: Boolean ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
        ) {
            //row with text and 3dot

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = data.name)
            Text(text = stringResource(id = R.string.profile_bio_header))
            Text(text = "sample text")
            Text(text = stringResource(id = R.string.profile_email_header))
            Text(text = "mail@sample.com")

        }
    }


}