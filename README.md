# DemitunsBoard Beta (FlorisBoard + AI Translation Tools)

## 🔍 Overview

This project is a fork of [FlorisBoard](https://github.com/florisboard/florisboard) that adds an **OpenAI-powered on-the-fly translation** feature. As you type, your text is sent to OpenAI’s API, translated into your chosen language, and immediately inserted into any text field—no copy-paste required.

## ✨ Features

### 1. OpenAI-Powered Translation  
<img src="https://github.com/sobhan-shahamatnia/Demituns-Board/blob/main/docs/1.png" alt="OpenAI Translation" width="300" />

### 2. Customizable Target Language  
<img src="https://github.com/sobhan-shahamatnia/Demituns-Board/blob/main/docs/2.png" alt="OpenAI Translation" width="300" />

### 3. Customizable Conversational Tone  
<img src="https://github.com/sobhan-shahamatnia/Demituns-Board/blob/main/docs/3.png" alt="OpenAI Translation" width="300" />


## 📦 Installation

1. **Clone the repository**  
   ```bash
   git clone https://github.com/<your-username>/demitunsboard.git
   cd demitunsboard

2. **Add your OpenAI API key**

// ime/demituns/SendToAi.kt
private val openAIApiKey: String = "YOUR_OPENAI_API_KEY_HERE"

