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


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import dev.patrickgold.florisboard.GlobalKeyboardSize
import dev.patrickgold.florisboard.ime.smartbar.quickaction.GlobalSmartBarSize


object SelectedLanguage {
    var selectedFromLanguage by mutableStateOf<String?>("FA")
    var selectedToLanguage by mutableStateOf<String?>("DE")
}

object OptionPopup {
    var selectedOption by mutableStateOf("Languages")
}

@Composable
fun LanguageSelectionPopup() {

    val density = LocalDensity.current
    val heightPx = with(density) { GlobalKeyboardSize.keyboardheight.toDp()}
    val heightWithoutSmartbar = heightPx - GlobalSmartBarSize.SmartBarHeight


    Popup(alignment = Alignment.TopEnd) {
        // Full-screen overlay with semi-transparent background.
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF))
        ) {
            // Use a Column with SpaceBetween to separate top content and bottom bar.
            Column() {

                Column(modifier = Modifier.weight(1f)) {
                    if ( OptionPopup.selectedOption == "Languages") {
                        LanguageSelectionContent()
                    } else if (OptionPopup.selectedOption == "Tone") {
                        ToneSelectionContent()
                    }

                }

                // Bottom bar with a row of buttons.
                Row(
                    modifier = Modifier
                        .wrapContentSize()
                        .fillMaxWidth()
                        .background(Color.LightGray) // Customize the background as needed
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {

                    //Languages Button
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(alignment = Alignment.CenterVertically)
                            .background(if (OptionPopup.selectedOption == "Languages") Color.White else Color(0xFFCACACA))
                            .clickable {
                                OptionPopup.selectedOption = "Languages"
                            }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Translate,
                                contentDescription = "Languages",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                fontSize = 12.sp,
                                text = "Languages"
                            )
                        }
                    }

                    //Tone Button
                    Box(
                        modifier = Modifier
                            .wrapContentSize()
                            .align(alignment = Alignment.CenterVertically)
                            .background(if (OptionPopup.selectedOption == "Tone") Color.White else Color(0xFFCACACA))
                            .clickable {
                                OptionPopup.selectedOption = "Tone"
                            }
                            .padding(horizontal = 8.dp, vertical = 8.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.wrapContentSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Contactless,
                                contentDescription = "Tone",
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                fontSize = 12.sp,
                                text = "Tone"
                            )
                        }
                    }
                }
            }




        }
    }

}


