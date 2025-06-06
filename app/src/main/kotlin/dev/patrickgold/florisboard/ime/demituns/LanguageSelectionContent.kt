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

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("SuspiciousIndentation")
@Composable
fun LanguageSelectionContent() {



    data class Language(val fullName: String, val shortName: String)

    val fromLanguages = listOf(
        Language(fullName = "Auto", shortName = "Auto"),

        Language(fullName = "Afrikaans", shortName = "AF"),
        Language(fullName = "Albanian", shortName = "SQ"),
        Language(fullName = "Amharic", shortName = "AM"),
        Language(fullName = "Arabic", shortName = "AR"),
        Language(fullName = "Armenian", shortName = "HY"),

        Language(fullName = "Azerbaijani", shortName = "AZ"),
        Language(fullName = "Basque", shortName = "EU"),
        Language(fullName = "Belarusian", shortName = "BE"),
        Language(fullName = "Bengali", shortName = "BN"),
        Language(fullName = "Bosnian", shortName = "BS"),

        Language(fullName = "Bulgarian", shortName = "BG"),
        Language(fullName = "Catalan", shortName = "CA"),
        Language(fullName = "Chinese (Simplified)", shortName = "ZH-CN"),
        Language(fullName = "Chinese (Traditional)", shortName = "ZH-TW"),
        Language(fullName = "Croatian", shortName = "HR"),

        Language(fullName = "Czech", shortName = "CS"),
        Language(fullName = "Danish", shortName = "DA"),
        Language(fullName = "Dutch", shortName = "NL"),
        Language(fullName = "English", shortName = "EN"),
        Language(fullName = "Estonian", shortName = "ET"),

        Language(fullName = "Filipino", shortName = "FIL"),
        Language(fullName = "Finnish", shortName = "FI"),
        Language(fullName = "French", shortName = "FR"),
        Language(fullName = "Galician", shortName = "GL"),
        Language(fullName = "Georgian", shortName = "KA"),

        Language(fullName = "German", shortName = "DE"),
        Language(fullName = "Greek", shortName = "EL"),
        Language(fullName = "Gujarati", shortName = "GU"),
        Language(fullName = "Haitian Creole", shortName = "HT"),
        Language(fullName = "Hebrew", shortName = "HE"),

        Language(fullName = "Hindi", shortName = "HI"),
        Language(fullName = "Hungarian", shortName = "HU"),
        Language(fullName = "Icelandic", shortName = "IS"),
        Language(fullName = "Indonesian", shortName = "ID"),
        Language(fullName = "Irish", shortName = "GA"),

        Language(fullName = "Italian", shortName = "IT"),
        Language(fullName = "Japanese", shortName = "JA"),
        Language(fullName = "Kannada", shortName = "KN"),
        Language(fullName = "Kazakh", shortName = "KK"),
        Language(fullName = "Khmer", shortName = "KM"),

        Language(fullName = "Korean", shortName = "KO"),
        Language(fullName = "Kurdish", shortName = "KU"),
        Language(fullName = "Kyrgyz", shortName = "KY"),
        Language(fullName = "Lao", shortName = "LO"),
        Language(fullName = "Latvian", shortName = "LV"),

        Language(fullName = "Lithuanian", shortName = "LT"),
        Language(fullName = "Luxembourgish", shortName = "LB"),
        Language(fullName = "Macedonian", shortName = "MK"),
        Language(fullName = "Malay", shortName = "MS"),
        Language(fullName = "Maltese", shortName = "MT"),

        Language(fullName = "Maori", shortName = "MI"),
        Language(fullName = "Marathi", shortName = "MR"),
        Language(fullName = "Mongolian", shortName = "MN"),
        Language(fullName = "Myanmar (Burmese)", shortName = "MY"),
        Language(fullName = "Nepali", shortName = "NE"),

        Language(fullName = "Norwegian", shortName = "NO"),
        Language(fullName = "Farsi", shortName = "FA"),
        Language(fullName = "Finglish", shortName = "FING"),
        Language(fullName = "Polish", shortName = "PL"),
        Language(fullName = "Portuguese", shortName = "PT"),

        Language(fullName = "Punjabi", shortName = "PA"),
        Language(fullName = "Romanian", shortName = "RO"),
        Language(fullName = "Russian", shortName = "RU"),
        Language(fullName = "Serbian", shortName = "SR"),
        Language(fullName = "Sinhala", shortName = "SI"),

        Language(fullName = "Slovak", shortName = "SK"),
        Language(fullName = "Slovenian", shortName = "SL"),
        Language(fullName = "Somali", shortName = "SO"),
        Language(fullName = "Spanish", shortName = "ES"),
        Language(fullName = "Swahili", shortName = "SW"),

        Language(fullName = "Swedish", shortName = "SV"),
        Language(fullName = "Tagalog", shortName = "TL"),
        Language(fullName = "Tajik", shortName = "TG"),
        Language(fullName = "Tamil", shortName = "TA"),
        Language(fullName = "Telugu", shortName = "TE"),

        Language(fullName = "Thai", shortName = "TH"),
        Language(fullName = "Turkish", shortName = "TR"),
        Language(fullName = "Ukrainian", shortName = "UK"),
        Language(fullName = "Urdu", shortName = "UR"),
        Language(fullName = "Uzbek", shortName = "UZ"),

        Language(fullName = "Vietnamese", shortName = "VI"),
        Language(fullName = "Welsh", shortName = "CY"),
        Language(fullName = "Xhosa", shortName = "XH"),
        Language(fullName = "Yiddish", shortName = "YI"),
        Language(fullName = "Zulu", shortName = "ZU")
    )


    val toLanguages = listOf(

        Language(fullName = "Afrikaans", shortName = "AF"),
        Language(fullName = "Albanian", shortName = "SQ"),
        Language(fullName = "Amharic", shortName = "AM"),
        Language(fullName = "Arabic", shortName = "AR"),
        Language(fullName = "Armenian", shortName = "HY"),

        Language(fullName = "Azerbaijani", shortName = "AZ"),
        Language(fullName = "Basque", shortName = "EU"),
        Language(fullName = "Belarusian", shortName = "BE"),
        Language(fullName = "Bengali", shortName = "BN"),
        Language(fullName = "Bosnian", shortName = "BS"),

        Language(fullName = "Bulgarian", shortName = "BG"),
        Language(fullName = "Catalan", shortName = "CA"),
        Language(fullName = "Chinese (Simplified)", shortName = "ZH-CN"),
        Language(fullName = "Chinese (Traditional)", shortName = "ZH-TW"),
        Language(fullName = "Croatian", shortName = "HR"),

        Language(fullName = "Czech", shortName = "CS"),
        Language(fullName = "Danish", shortName = "DA"),
        Language(fullName = "Dutch", shortName = "NL"),
        Language(fullName = "English", shortName = "EN"),
        Language(fullName = "Estonian", shortName = "ET"),

        Language(fullName = "Filipino", shortName = "FIL"),
        Language(fullName = "Finnish", shortName = "FI"),
        Language(fullName = "French", shortName = "FR"),
        Language(fullName = "Galician", shortName = "GL"),
        Language(fullName = "Georgian", shortName = "KA"),

        Language(fullName = "German", shortName = "DE"),
        Language(fullName = "Greek", shortName = "EL"),
        Language(fullName = "Gujarati", shortName = "GU"),
        Language(fullName = "Haitian Creole", shortName = "HT"),
        Language(fullName = "Hebrew", shortName = "HE"),

        Language(fullName = "Hindi", shortName = "HI"),
        Language(fullName = "Hungarian", shortName = "HU"),
        Language(fullName = "Icelandic", shortName = "IS"),
        Language(fullName = "Indonesian", shortName = "ID"),
        Language(fullName = "Irish", shortName = "GA"),

        Language(fullName = "Italian", shortName = "IT"),
        Language(fullName = "Japanese", shortName = "JA"),
        Language(fullName = "Kannada", shortName = "KN"),
        Language(fullName = "Kazakh", shortName = "KK"),
        Language(fullName = "Khmer", shortName = "KM"),

        Language(fullName = "Korean", shortName = "KO"),
        Language(fullName = "Kurdish", shortName = "KU"),
        Language(fullName = "Kyrgyz", shortName = "KY"),
        Language(fullName = "Lao", shortName = "LO"),
        Language(fullName = "Latvian", shortName = "LV"),

        Language(fullName = "Lithuanian", shortName = "LT"),
        Language(fullName = "Luxembourgish", shortName = "LB"),
        Language(fullName = "Macedonian", shortName = "MK"),
        Language(fullName = "Malay", shortName = "MS"),
        Language(fullName = "Maltese", shortName = "MT"),

        Language(fullName = "Maori", shortName = "MI"),
        Language(fullName = "Marathi", shortName = "MR"),
        Language(fullName = "Mongolian", shortName = "MN"),
        Language(fullName = "Myanmar (Burmese)", shortName = "MY"),
        Language(fullName = "Nepali", shortName = "NE"),

        Language(fullName = "Norwegian", shortName = "NO"),
        Language(fullName = "Farsi", shortName = "FA"),
        Language(fullName = "Finglish", shortName = "FING"),
        Language(fullName = "Polish", shortName = "PL"),
        Language(fullName = "Portuguese", shortName = "PT"),

        Language(fullName = "Punjabi", shortName = "PA"),
        Language(fullName = "Romanian", shortName = "RO"),
        Language(fullName = "Russian", shortName = "RU"),
        Language(fullName = "Serbian", shortName = "SR"),
        Language(fullName = "Sinhala", shortName = "SI"),

        Language(fullName = "Slovak", shortName = "SK"),
        Language(fullName = "Slovenian", shortName = "SL"),
        Language(fullName = "Somali", shortName = "SO"),
        Language(fullName = "Spanish", shortName = "ES"),
        Language(fullName = "Swahili", shortName = "SW"),

        Language(fullName = "Swedish", shortName = "SV"),
        Language(fullName = "Tagalog", shortName = "TL"),
        Language(fullName = "Tajik", shortName = "TG"),
        Language(fullName = "Tamil", shortName = "TA"),
        Language(fullName = "Telugu", shortName = "TE"),

        Language(fullName = "Thai", shortName = "TH"),
        Language(fullName = "Turkish", shortName = "TR"),
        Language(fullName = "Ukrainian", shortName = "UK"),
        Language(fullName = "Urdu", shortName = "UR"),
        Language(fullName = "Uzbek", shortName = "UZ"),

        Language(fullName = "Vietnamese", shortName = "VI"),
        Language(fullName = "Welsh", shortName = "CY"),
        Language(fullName = "Xhosa", shortName = "XH"),
        Language(fullName = "Yiddish", shortName = "YI"),
        Language(fullName = "Zulu", shortName = "ZU")
    )


        @OptIn(ExperimentalMaterial3Api::class)
        TopAppBar(
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
        )
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top
        ) {
            // First Language Column
            Box(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp, vertical = 16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = SelectedLanguage.selectedFromLanguage.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn {
                        items(fromLanguages) { language ->
                            Row(
                                modifier = Modifier
                                    .height(38.dp)
                                    .clickable {
                                        Log.d("Language", language.fullName)
                                    },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = language.shortName == SelectedLanguage.selectedFromLanguage,
                                    onClick = { SelectedLanguage.selectedFromLanguage = language.shortName }
                                )
                                Box {
                                    Text(
                                        text = language.fullName,
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

            // Second Language Column
            Box(
                modifier = Modifier
                    .background(Color(0xFFEFEFEF))
                    .weight(1f)
                    .padding(horizontal = 10.dp, vertical = 16.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = SelectedLanguage.selectedToLanguage.toString(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    LazyColumn {
                        items(toLanguages) { language ->
                            Row(
                                modifier = Modifier
                                    .height(38.dp)
                                    .clickable { },
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                RadioButton(
                                    selected = language.shortName == SelectedLanguage.selectedToLanguage,
                                    onClick = { SelectedLanguage.selectedToLanguage = language.shortName }
                                )
                                Box {
                                    Text(
                                        text = language.fullName,
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



}
