package com.dfeverx.seccert.compose.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dfeverx.seccert.compose.components.Subtitle1


@Composable
fun MyCard(
    modifier: Modifier,
    icon: ImageVector,
    title: String,
    toPermission: (() -> Unit?)? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        /* Card(
             shape = MaterialTheme.shapes.medium,
             elevation = 50.dp,
             backgroundColor = Color.White,
             modifier = Modifier
                 .padding(8.dp)
                 .height(100.dp)
                 .fillMaxWidth()
         ) {*/
        Column(
            modifier = Modifier
                .padding(8.dp)
                .height(100.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
         /*   IconButton(
                onClick = { *//*toPermission?.let { it() }*//* }, modifier = Modifier
                    .fillMaxWidth()
                    .height(74.dp)
            ) {*/
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier
//                        .background(color = MaterialTheme.colors.background, shape = CircleShape)

                        .padding(top = 16.dp)
                        .width(42.dp)
                        .height(42.dp),
                )
//            }


            Subtitle1(text = title, modifier = Modifier.padding(bottom = 8.dp))
        }
//        }

    }
}

@Preview
@Composable
fun CardPreview() {
//    MyCard()

}
