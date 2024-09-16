package com.example.pokedexpokemon.presentationLayer.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.window.DialogProperties


@Composable
fun CustomDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit,
    confirmButton: String,
    modifier: Modifier = Modifier,
    dismissButton: String? = null,
    icon: ImageVector? = null,
    title: String? = null,
    text: String? = null,
    properties: DialogProperties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true,
        usePlatformDefaultWidth = true
    )
) {
    AlertDialog(onDismissRequest = { onDismissRequest() },
        modifier = modifier,
        properties = properties,
        confirmButton = {
            Button(onClick = { onConfirmRequest() }) {
                Text(text = confirmButton)
            }
        },
        dismissButton = @Composable {
            dismissButton?.let {
                Button(onClick = { onDismissRequest() }) {
                    Text(text = it)
                }
            }
        },
        title = { title?.let { Text(text = it) } },
        text = { text?.let { Text(text = it) } },
        icon = { icon?.let { Icon(imageVector = it, contentDescription = "") } })
}