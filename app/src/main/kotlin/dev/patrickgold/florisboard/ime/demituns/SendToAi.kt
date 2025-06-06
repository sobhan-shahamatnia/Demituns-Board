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

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.patrickgold.florisboard.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import org.json.JSONArray
import org.json.JSONObject
import kotlin.math.log

object FromApi {
    var resultText by mutableStateOf("")
}

class SendToAi {

    // Replace your OpenAI API key here
    var openAIApiKey: String = BuildConfig.OPENAI_API_KEY
         // Example default


    suspend fun buildPrompt() = withContext(Dispatchers.IO) {
        try {
            Log.d("DemitDebug10", "${PromptMode.selectedMode}")
            var aiPrompt = ""
            var systemPrompt = ""
            var selectedTone: String = ToneSelection.selectedTone

            val originalText = TextCaptureManager.capturedText
            if (originalText.isBlank()) {
                FromApi.resultText = ""
                return@withContext
            }

            val promptBuilder = StringBuilder()

            when (PromptMode.selectedMode) {
                "Grammer" -> {
                    systemPrompt = "You are a professional translator. Always provide accurate, natural-sounding translations in the requested language."
                    aiPrompt = "Check only the grammar of the sentence and correct it if needed. If no correction is needed, " +
                        "simply type 'This sentence already has correct grammar.'"
                    Log.d("DemitDebug10", "Grammer selected")
                }
                "Translate" -> {
                    systemPrompt = "You are a professional translator. Always provide accurate, natural-sounding translations in the requested language."
                    aiPrompt = "Translate the following text written in Farsi or Finglish (Farsi words rendered using the English alphabet) " +
                        "into ${SelectedLanguage.selectedToLanguage}. Ensure that the translation adopts a $selectedTone style and reads naturally as if " +
                        "it were written by a native speaker, but do not remove or completely alter any part of the original text.\n\n"
                    Log.d("DemitDebug14", "Translate(Fn)-(En) selected")
                }
                "Rewrite" -> {
                    systemPrompt = "You are a professional translator. Always provide accurate, natural-sounding translations in the requested language."
                    aiPrompt = "just Rewrite the Text"
                    Log.d("DemitDebug10", "Rewrite selected")
                }
                else -> {
                    aiPrompt = "false"
                    systemPrompt = "false"
                    Log.d("DemitDebug20", "Unknown option selected")
                }
            }

            promptBuilder.append(aiPrompt)
            promptBuilder.append(originalText)
            Log.d("DemitDebug18", "promptBuilder: ${promptBuilder.toString()}")
            val fullPrompt = promptBuilder.toString()

            val jsonBody = JSONObject().apply {
                put("model", "gpt-3.5-turbo")
                put("messages", JSONArray().apply {
                    put(JSONObject().apply {
                        put("role", "system")
                        put("content", systemPrompt)
                    })
                    put(JSONObject().apply {
                        put("role", "user")
                        put("content", fullPrompt)
                    })
                })
                put("max_tokens", 1000)
            }

            val mediaType = "application/json; charset=utf-8".toMediaTypeOrNull()
            val requestBody = RequestBody.create(mediaType, jsonBody.toString())

            val request = okhttp3.Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .header("Authorization", "Bearer $openAIApiKey")
                .header("Content-Type", "application/json")
                .post(requestBody)
                .build()

            val client = OkHttpClient()
            client.newCall(request).execute().use { response ->
                if (!response.isSuccessful) {
                    Log.e("ChatGPT", "OpenAI API error: ${response.code} => ${response.body?.string()}")
                    FromApi.resultText = "Error: ${response.code}"
                    return@withContext
                }

                val responseStr = response.body?.string() ?: ""
                Log.d("ChatGPT", "OpenAI API raw response: $responseStr")

                val jsonObj = JSONObject(responseStr)
                val choices = jsonObj.getJSONArray("choices")
                if (choices.length() > 0) {
                    val firstChoice = choices.getJSONObject(0)
                    val messageObj = firstChoice.getJSONObject("message")
                    val result = messageObj.getString("content").trim()
                    FromApi.resultText = result

                    TextCaptureManager.updateCapturedText(FromApi.resultText)

                    Log.d("ChatGPT", "OpenAI API response: $result")
                }
            }
        } catch (e: Exception) {
            Log.e("buildPrompt", "Error occurred: ${e.message}")
            FromApi.resultText = "Error: ${e.message}"
        }
    }




}
