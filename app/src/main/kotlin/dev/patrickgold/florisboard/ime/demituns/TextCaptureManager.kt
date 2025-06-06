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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import android.util.Log

object TextCaptureManager {
    var isCaptureEnabled: Boolean = true

    // Mutable state for the captured text
    var capturedText by mutableStateOf("")
        private set

    fun appendText(text: String) {
        if (!isCaptureEnabled) return
        capturedText += text
        Log.d("TextCapture", "Appended text: $text")
    }

    fun updateCapturedText(text: String) {
        if (!isCaptureEnabled) return
        capturedText = text
        Log.d("TextCapture", "Set captured text: \"$text\"")
    }

    fun clearCapturedText() {
        capturedText = ""
    }
}
