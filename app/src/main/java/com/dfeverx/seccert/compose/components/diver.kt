package com.dfeverx.seccert.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LeftDividerWithText(title: String) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier/*.padding(horizontal = 16.dp)*/.padding(top = 24.dp, bottom = 16.dp)
    ) {
        Divider(
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .height(24.dp)
                .width(8.dp)
        )
        Subtitle1(text = title, Modifier.padding(start = 8.dp))
    }

}
