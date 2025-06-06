/*
 * Copyright (C) 2025 The FlorisBoard Contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.util.Log

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


object GlobalOverlayState {
    var showOverlay by mutableStateOf(false)
}

@Composable
fun DemitunsButton(onClick: () -> Unit, fgColor: Color) {
    IconButton(onClick = {
        GlobalOverlayState.showOverlay = !GlobalOverlayState.showOverlay
        Log.d("DemitDebug1", "GlobalOverlayState.showOverlay: ${GlobalOverlayState.showOverlay}")
    }) {
        Text(
            text = "De",
            color = fgColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Black
        )
    }
}
