/*
package com.dfeverx.seccert.compose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.dfeverx.dockify.R
import com.dfeverx.seccert.R
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun Wish() {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = { */
/*TODO*//*
 }) {
                Image(
                    painter = rememberCoilPainter(
                        request = R.mipmap.ic_launcher,
                        requestBuilder = { transformations(CircleCropTransformation()) }
//                        previewPlaceholder = R.drawable.placeholder,
                    ),
                    contentDescription = "stringResource(R.string.image_content_desc)",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(40.dp)
                )
            }



            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = {}, modifier = Modifier

            ) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onBackground,
                    modifier = Modifier.size(40.dp)
                )
            }
        }
        Subtitle1(text = "Hi,", Modifier.padding(top = 8.dp))
        H6(text = "Good Morning")
    }
}


*/
