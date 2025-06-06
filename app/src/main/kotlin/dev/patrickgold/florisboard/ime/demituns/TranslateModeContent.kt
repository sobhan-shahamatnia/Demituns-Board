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
import android.view.inputmethod.InputConnection
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.Autorenew
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Construction
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import dev.patrickgold.florisboard.FlorisImeService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import org.florisboard.lib.android.AndroidInternalR

@Composable
fun TranslateModeContent() {
    val textOfTextField = remember { mutableStateOf("") }
    // Create an instance of SendToAi
    val sendToAi = SendToAi()


    Column (modifier = Modifier
        .fillMaxSize(),
        verticalArrangement = Arrangement.Top
    ) {

        Box(modifier = Modifier.weight(1f)
        ){

            TextField(
                enabled = true,
                value = if(!TextCaptureManager.capturedText.isBlank()) TextCaptureManager.capturedText else "AI Result Show Here ...",
                onValueChange = { textOfTextField.value = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFFDCDCDC),
                    focusedIndicatorColor = Color(0xFF969696),
                    focusedLabelColor = Color(0xFFDCDCDC),
                    unfocusedIndicatorColor = Color(0xFF969696),
                    unfocusedContainerColor = Color(0xFFDCDCDC),
                ),
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .absolutePadding(bottom = 0.dp),

                textStyle = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),


                )
        }
        Box(modifier = Modifier.wrapContentSize(),
            //contentAlignment = Alignment.Center
        ) {

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

                // Options
                Box(
                    modifier = Modifier
                        .clickable { SelectedIcon.showLanguageSelectionPopup = true }
                        .padding(vertical = 0.dp, horizontal = 20.dp)
                        .absolutePadding(top = 10.dp)
                    //.background(color = iconBackgroundColor)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(color = Color(0xFF212121))
                                .padding(7.dp)
                        ){
                            Icon(
                                modifier = Modifier.size(13.dp),
                                tint = Color(0xffdedede),
                                imageVector = Icons.Default.Construction,
                                contentDescription = "Options",
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Options",
                            fontSize = 11.sp,
                            color = Color(0xFF212121),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                }

                //Replace Button
                Box(
                    modifier = Modifier
                        .clickable {
                            val inputConnection: InputConnection? = FlorisImeService.currentInputConnection()
                            if (FromApi.resultText != null && FromApi.resultText != "") {
                                inputConnection?.beginBatchEdit()
                                inputConnection?.deleteSurroundingText(10000, 10000) // Clear the input box in smaller chunks
                                inputConnection?.commitText("", 1) // Ensure the input box is empty
                                inputConnection?.commitText(FromApi.resultText, 1) // Replace with new text
                                inputConnection?.endBatchEdit()
                            }

                            Log.d("DemitDebug15", textOfTextField.value)
                        }
                        .padding(vertical = 0.dp, horizontal = 20.dp)
                        .absolutePadding(top = 10.dp)
                    //.background(color = iconBackgroundColor)
                ) {

                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(color = Color(0xFF212121))
                                .padding(7.dp)
                        ){
                            Icon(
                                modifier = Modifier.size(13.dp),
                                tint = Color(0xffdedede),
                                imageVector = Icons.Default.Autorenew,
                                contentDescription = "Replace",
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Replace",
                            fontSize = 11.sp,
                            color = Color(0xFF212121),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                }


                //Done Button
                Box(
                    modifier = Modifier
                        .clickable { // Launch a coroutine to call buildPrompt()
                            CoroutineScope(Dispatchers.IO).launch {
                                Log.d("DemitDebug13", "Done Lunched")
                                sendToAi.buildPrompt()
                            } }
                        .padding(vertical = 0.dp, horizontal = 20.dp)
                        .absolutePadding(top = 10.dp)
                    //.background(color = iconBackgroundColor)
                ) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(color = Color(0xFF212121))
                                .padding(7.dp)
                        ){
                            Icon(
                                modifier = Modifier.size(13.dp),
                                tint = Color(0xffdedede),
                                imageVector = Icons.Default.Check,
                                contentDescription = "Done",
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Done",
                            fontSize = 11.sp,
                            color = Color(0xFF212121),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                }
            }


        }



    }

}
