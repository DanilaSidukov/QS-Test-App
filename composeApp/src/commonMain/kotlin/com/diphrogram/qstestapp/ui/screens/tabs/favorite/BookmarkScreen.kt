package com.diphrogram.qstestapp.ui.screens.tabs.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.diphrogram.qstestapp.MR
import dev.icerock.moko.resources.compose.stringResource
import org.jetbrains.compose.resources.painterResource

object BookmarkScreen: Tab {

    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(MR.strings.bookmark_key)
            val icon = painterResource("drawable/ic_bookmark.xml")

            return TabOptions(
                index = 3u,
                title = title,
                icon = icon
            )

        }

    @Composable
    override fun Content() {

        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(top = paddingValues.calculateTopPadding(), start = 24.dp, end = 24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                Text(
                    text = stringResource(MR.strings.bookmark_key),
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Coming soon"
                )

            }
        }

    }


}