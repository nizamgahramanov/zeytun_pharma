package com.example.zeytunpharma.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun ZeytunPharmaTheme(
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colors = ZeytunPharmaColors,
        typography = ZeytunPharmaTypography,
        shapes = ZeytunPharmaShapes,
        content = content
    )
}