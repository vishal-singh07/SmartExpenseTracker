# Smart Expense Tracker

A fully featured Android expense tracker app built with **Kotlin**, **Jetpack Compose (Material 3)**, **Room Database**, and **MVVM architecture**.  
The app allows adding daily expenses, viewing them in list and report formats, generating weekly expense bar charts, switching between light/dark themes, and persisting data locally using Room.

---

## 🚀 Features

- **Expense Entry** — Add expenses with title, amount, category, notes.
- **Expense List** — View expenses filtered by date.
- **Expense Report** — Weekly breakdown with a Compose bar chart and category totals.
- **Room Database** — Offline persistence with DAO, Repository, and ViewModel pattern.
- **Bottom Navigation** — Navigate between Entry, List, Report, and Settings screens.
- **Theme Switching** — Toggle between light and dark modes from the Settings screen.
- **Material 3 Design** — Modern components and theming.

---

## 📸 Screenshots

<div >
  <img width="412" height="869" alt="Entry Screen" src="https://github.com/user-attachments/assets/b351f3ea-4979-454b-91f7-1784e628c539" />
  <img width="399" height="864" alt="ListScreen" src="https://github.com/user-attachments/assets/86dd3f0b-13ee-4a9a-89e4-d9b44dfa59a8" />
  <img width="407" height="862" alt="Report Screen" src="https://github.com/user-attachments/assets/c39a7739-627c-4ce6-871c-91d25271518a" />
  <img width="410" height="872" alt="Settings Screen" src="https://github.com/user-attachments/assets/367e9186-d211-49b2-af2e-35957cae7e09" />
  <img width="407" height="869" alt="Dark Mode" src="https://github.com/user-attachments/assets/c9112be0-cb51-4f88-9d24-df1f97935eb5" />
</div>

---

## 🛠️ Tech Stack

- **Kotlin**
- **Jetpack Compose (Material 3)**
- **Room Database**
- **StateFlow in ViewModel**
- **Scaffold + BottomNavigation**
- **MVVM Architecture**

---

## ▶️ How to Run

1. Clone this repository or copy the source code into Android Studio.
2. Build & run on Android 8.0+. 
3. Use the bottom navigation to switch between Entry, List, Report, and Settings. 
4. Toggle light/dark theme under Settings.

---

## 📊 Screens

- **Entry Screen:** Form for adding expenses.
- **List Screen:** Displays recorded expenses.
- **Report Screen:** Weekly totals with bar chart.
- **Settings Screen:** Dark mode toggle.

---

## AI Usage Summary

- AI assistance was used throughout the development of the Smart Expense Tracker project to streamline
coding and architectural decisions. 
- I used combination of GitHub Copilot in Android Studio and
ChatGPT. 
- Copilot was used mainly for auto code completions and suggestions, usage of shortcut
prompts such as "/doc", "/feedback", "/fix" for generating documentation of the code, get feedback
on the implementation and improve the code and fix errors in the code. 
- Prompts were used to scaffold MVVM structure, set up Room integration, and create Jetpack Compose UI
for multiple screens with Material 3 styling. 
- AI helped design and debug the bottom navigation,
weekly bar chart drawing, and theme toggle integration across composables. 
- Final refinements included restructuring MainActivity to lift theme state and modifying the NavGraph to handle
settings navigation cleanly.

---
