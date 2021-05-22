/*
package com.dfeverx.seccert.compose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dfeverx.seccert.R

@Composable
 fun BottomSheetSelectableList(
    title: String,
    icon: Int,
    list: List<IconWithLabel>,
    selected: Int,
    apply: () -> Unit,
    cancel: () -> Unit,
    isProgress: Boolean,
    onItemClick: (IconWithLabel, Int) -> Unit
) {
    Column {
        BottomSheetHeader(title, icon)
        list.foreach { index, iconWithLabel ->
            ItemWithIconAndLabel(iconWithLabel, index == selected) {
                if (!isProgress) {
                    onItemClick(it, index)
                }
            }
        }
        BottomSheetFooterBtns(apply, cancel, isProgress)

    }
}

@Composable
fun ItemWithIconAndLabel(
    item: IconWithLabel = IconWithLabel("test", R.drawable.ic_tik),
    isSelected: Boolean = false,
    itemClick: (IconWithLabel) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(if (!isSelected) MaterialTheme.colors.background else MaterialTheme.colors.primaryVariant)
            .clickable { itemClick(item) }
            .padding(16.dp), horizontalArrangement = Arrangement.Start) {

        item.icon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = null,
                tint = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary, */
/*modifier = Modifier.size(24.dp)*//*

            )
        }

        LabelText(
            text = item.label,
            modifier = Modifier.padding(horizontal = 16.dp),
            color = if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.secondary
        )
        if (isSelected) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.ic_tik),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
            )
        }
    }
}

@Composable
fun BottomSheetHeader(title: String, icon: Int) {

    Row(
        Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = MaterialTheme.colors.onBackground, modifier = Modifier.size(36.dp)

        )
        H6(text = title, modifier = Modifier.padding(8.dp))

    }


}

@Composable
fun BottomSheetFooterBtns(onApply: () -> Unit, onCancel: () -> Unit, isProgress: Boolean) {
    Row(modifier = Modifier.padding(16.dp)) {
        SecondaryButton(
            stringResource(R.string.cancel),
            Modifier
                .weight(1f)
                .padding(0.dp, 0.dp, 8.dp, 0.dp), onClick = onCancel
        )
        PrimaryButton(
            stringResource(R.string.apply), Modifier
                .weight(1f)
                .padding(8.dp, 0.dp, 0.dp, 0.dp), onClick = onApply, isLoading = isProgress
        )
    }
}


class IconWithLabel(val label: String, val icon: Int? = null)

*/
