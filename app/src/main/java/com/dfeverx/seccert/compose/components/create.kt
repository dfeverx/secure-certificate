/*
package com.dfeverx.dockify.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.dfeverx.seccert.compose.theme.WhiteShadeTransparent50
import com.dfeverx.dockify.utils.compose.dashedBorder
import com.dfeverx.seccert.compose.components.MyCard

@Composable
fun Create(toPermission: () -> (Unit)) {

    Row(
        modifier = Modifier
            .padding(horizontal = 24.dp)
            .background(WhiteShadeTransparent50)
            .clickable { toPermission() }
            .dashedBorder(
                BorderStroke(4.dp, Color.LightGray),
                RectangleShape, 8.dp, 16.dp
            )
//            .border(2.dp, SolidColor(Color.LightGray), RectangleShape)
            .padding(8.dp),

        ) {

        MyCard(modifier = Modifier.weight(1f), Icons.Outlined.Add, "Scan document") {
            toPermission()
        }
//        MyCard(modifier = Modifier.weight(1f), Icons.Outlined.Place, "Gallery")
//        MyCard(modifier = Modifier.weight(1f), Icons.Outlined.Person, "Sample")

    }

}
*/
