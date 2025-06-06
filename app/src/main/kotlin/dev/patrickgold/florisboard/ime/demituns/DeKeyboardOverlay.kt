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

package dev.patrickgold.florisboard.ime.demituns

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.sp
import dev.patrickgold.florisboard.GlobalKeyboardSize
import dev.patrickgold.florisboard.ime.smartbar.quickaction.GlobalSmartBarSize


object PromptMode {
    var selectedMode by mutableStateOf("Translate")
}

@Composable
fun DeKeyboardPopup(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit = {},
) {


    val density = LocalDensity.current
    val heightPx = with(density) {GlobalKeyboardSize.keyboardheight.toDp()}
    val heightWithoutSmartbar = heightPx - GlobalSmartBarSize.SmartBarHeight
    Popup(
        offset = IntOffset(0, 0),
        alignment = Alignment.BottomEnd,

        ) {

        Box{
            Column {
                //Top Bar
                Box(
                    modifier = modifier
                        .size(GlobalKeyboardSize.keyboardWidth.dp, GlobalSmartBarSize.SmartBarHeight)
                        .background(Color(0xFFDCDCDC))
                        .drawBehind {
                            val strokeWidth = 1.dp.toPx()
                            drawLine(
                                color = Color(0xFF969696),
                                start = Offset(0f, size.height - strokeWidth / 2),
                                end = Offset(size.width, size.height - strokeWidth / 2),
                                strokeWidth = strokeWidth
                            )
                        },
                    contentAlignment = Alignment.CenterStart

                ) {

                    Row (modifier=Modifier,
                        verticalAlignment = Alignment.CenterVertically) {
                        Box(modifier = Modifier.wrapContentSize()) {
                            IconButton(onClick = { GlobalOverlayState.showOverlay = false}) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }

                        Box(modifier = Modifier.weight(1f).absolutePadding(right = 0.dp, bottom = 1.dp)) {

                            Row(
                                modifier = Modifier.fillMaxSize().horizontalScroll(rememberScrollState()), // Makes the row take all available horizontal space
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                // Translation Box button
                                Box(
                                    modifier = Modifier

                                        .align(alignment = Alignment.CenterVertically)
                                        .background(if (PromptMode.selectedMode == "Translate") Color.White else Color(
                                            0xFFDADADA
                                        ), shape = RectangleShape)
                                        .clickable {
                                            PromptMode.selectedMode = "Translate" }
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Translate,
                                            contentDescription = "Translate",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            fontSize = 12.sp,
                                            text = "(${SelectedLanguage.selectedFromLanguage})To(${SelectedLanguage.selectedToLanguage})"
                                        )
                                    }
                                }

                                // Rewrite Box button
                                Box(
                                    modifier = Modifier

                                        .align(alignment = Alignment.CenterVertically)
                                        .background(if (PromptMode.selectedMode == "Rewrite") Color.White else Color(
                                            0xFFDADADA
                                        ), shape = RectangleShape)
                                        .clickable { PromptMode.selectedMode = "Rewrite" }
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Create,
                                            contentDescription = "Rewrite",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            fontSize = 12.sp,
                                            text = "Rewrite"
                                        )
                                    }
                                }

                                // Reverse Translation
                                Box(
                                    modifier = Modifier

                                        .align(alignment = Alignment.CenterVertically)
                                        .background(if (PromptMode.selectedMode == "ReverseTranslate") Color.White else Color(
                                            0xFFDADADA
                                        ), shape = RectangleShape)
                                        .clickable { PromptMode.selectedMode = "ReverseTranslate" }
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Translate,
                                            contentDescription = "Reverse Translate",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            fontSize = 12.sp,
                                            text = "Reverse Translate (Auto) to (${SelectedLanguage.selectedFromLanguage})"
                                        )
                                    }
                                }

                                // Grammer Box button
                                Box(
                                    modifier = Modifier

                                        .align(alignment = Alignment.CenterVertically)
                                        .background(if (PromptMode.selectedMode == "Grammer") Color.White else Color(
                                            0xFFDADADA
                                        ), shape = RectangleShape)
                                        .clickable { PromptMode.selectedMode = "Grammer" }
                                        .padding(horizontal = 8.dp, vertical = 8.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Row(
                                        modifier = Modifier.fillMaxHeight(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Check,
                                            contentDescription = "Grammer",
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Text(
                                            fontSize = 12.sp,
                                            text = "Grammer"
                                        )
                                    }
                                }


                            }

                        }

                    }

                }

                //MainPopUp
                Box(
                    modifier = modifier
                        .size(GlobalKeyboardSize.keyboardWidth.dp, heightWithoutSmartbar)
                        .background(Color(0xFFDADADA)),

                    ) {
                    KeyboardOverlayGrid()
                }

            }

        }


    }

}
