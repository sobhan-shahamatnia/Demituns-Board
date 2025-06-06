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
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object ToneSelection {
    var selectedTone by mutableStateOf("Formal")
}

@Composable
fun ToneSelectionContent() {

    val tones = listOf("Formal", "Informal", "Neutral", "Casual", "Professional", "Academic", "Friendly",
        "Emotional","Chat", "Poetic", "Technical", "Scientific", "Medical", "Legal", "Religious", "Political",
        "Historical", "Cultural", "Slang", "Jargon", "Colloquial", "Vulgar", "Obscene", "Profane", "Rude", "Insulting",
        "Threatening", "Abusive", "Hateful", "Derogatory",)

    @OptIn(ExperimentalMaterial3Api::class)
    (TopAppBar(
        colors = TopAppBarColors(
            containerColor = Color(0xFFDEDEDE),
            titleContentColor = Color.Black,
            actionIconContentColor = Color.Black,
            navigationIconContentColor = Color.Black,
            scrolledContainerColor = Color(0xFFDEDEDE)
        ),
        title = { Text("Select a Language") },
        navigationIcon = {
            IconButton(onClick = {
                SelectedIcon.showLanguageSelectionPopup = false
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }
    ))

    // First Language Column
    Box(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = ToneSelection.selectedTone,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(
                modifier = Modifier

                    .clickable {
                        Log.d("Tone", ToneSelection.selectedTone)
                    },
                horizontalAlignment =  Alignment.CenterHorizontally
            ) {
                Log.d("Tone", tones[10])
                items(tones.size) { index ->
                    //Radio Button
                    Row(
                        modifier = Modifier
                            .height(38.dp)
                            .clickable {
                                ToneSelection.selectedTone = tones[index]
                                Log.d("Tone", ToneSelection.selectedTone)
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = tones[index] == ToneSelection.selectedTone,
                            onClick = { ToneSelection.selectedTone = tones[index] }
                        )
                        Box {
                            Text(
                                text = tones[index],
                                fontSize = 11.sp,
                                modifier = Modifier.width(100.dp),
                                softWrap = true,
                                maxLines = Int.MAX_VALUE
                            )
                        }
                    }


                }
            }


        }
    }

}
