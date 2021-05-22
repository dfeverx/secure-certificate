package com.dfeverx.seccert.compose.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PrimaryButton(
    buttonText: String,
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    onClick: () -> Unit,
) {


    Button(
        onClick = {if (!isLoading){ onClick()} },
        modifier = modifier
            .height(54.dp),
        shape = CircleShape
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
        } else {
            Text(text = buttonText)

        }
    }
}

@Composable
fun SecondaryButton(
    buttonText: String, modifier: Modifier = Modifier, isLoading: Boolean = false,
    onClick: () -> Unit
) {

    OutlinedButton(
        onClick = { if (!isLoading){ onClick()} },
        modifier = modifier
            .height(54.dp),
        shape = CircleShape, border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colors.primary,
                modifier = Modifier.size(24.dp)
            )
        } else {
            Text(text = buttonText)

        }
    }


}
