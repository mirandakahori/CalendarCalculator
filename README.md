# Calendar Calculator (カレンダー電卓)

## ① 基本機能「一般モード」
- 四則演算の計算（＋、－、×、÷）
- 「AC」「C」ボタンで表示内容を削除
- 「一般モード 履歴」ボタンをクリックして履歴一覧の確認が可能
- 一般モード履歴一覧の「すべて削除」ボタンで履歴がすべて削除

  <div style="display: flex; justify-content: center; margin-bottom: 10px;">
    <img src="https://github.com/user-attachments/assets/ebaef455-b678-4fba-88ef-8af464dbb1ad" alt="gen1" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/fd1ff281-ff9f-43b0-95cd-98300d96c019" alt="gen2" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/dce9d380-6eff-4f01-82ec-5e0d98bfce48" alt="gen3" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/96ba70d0-8236-41b5-ac1e-27a2ce8f917c" alt="gen4" style="width: 150px;">
  </div>
  
---

## ② オリジナル機能「日付モード」
- 特定の日付や曜日を算出する機能
- ＠ボタンをクリックし、「一般モード」と「日付モード」間を切り替える
- 「日付モード 履歴」ボタンをクリックして履歴一覧の確認が可能
- 日付モード履歴一覧の「すべて削除」ボタンで履歴がすべて削除

### 使用例
- **2003年06月25日は今日から何日前で、何曜日だったか知りたい**
    - ＠ 20030625 = 20240921.土/-7759.水  
    **結果**: 2003年06月25日は、2024年09月21日土曜日（計算当日）から7759日前の水曜日

- **今日から7674日前の日付と曜日を知りたい**
    - @ -7759 = 20240921.土/20030625.水  
    **結果**: 今日（計算当日 2024年09月21日土曜日）から7759日前は2003年06月25日の水曜日

### 計算結果の読み方
- **いつ計算したか**: 一番最初の値 = 20240921.土/～ ⇒ 「2024年09月21日に計算した」
- **何日後/何日前か**: -365 (365日前) / +365 (365日後)  例: @-7674 → 今日から7674日前の日付と曜日を計算

  <div style="display: flex; justify-content: center;">
    <img src="https://github.com/user-attachments/assets/967ea835-dc85-4d1a-961a-cd5c04d9a359" alt="date1" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/7ff92fe0-fe23-4c90-9c22-8e581559da80" alt="date2" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/4cb89b3b-9030-4b38-a6ec-07bd4281fad7" alt="date3" style="width: 150px; margin-right: 10px;">
    <img src="https://github.com/user-attachments/assets/66c6c155-ec1d-445b-9930-c7dc328d77a9" alt="date4" style="width: 150px;">
  </div>

------

# Calendar Calculator

## 1. Basic Function "General Mode"
- Perform arithmetic calculations ( +, -, ×, ÷ )
- Use the "AC" and "C" buttons to clear or delete the display
- Click the "General Mode History" button to view the history list
- Use the "Clear All" button in the General Mode History to delete all history

## 2. Original Function "Date Mode"
- Calculate specific dates and weekdays
- Click the @ button to switch between "General Mode" and "Date Mode"
- Click the "Date Mode History" button to view the history list
- Use the "Clear All" button in the Date Mode History to delete all history

### Usage Examples
- **How many days ago was June 25, 2003, and what day of the week was it?**
    - @ 20030625 = 20240921.Sat/-7759.Wed  
    **Result**: June 25, 2003, was 7759 days before Saturday, September 21, 2024 (the calculation date).

- **Find the date and day of the week 7674 days before today.**
    - @ -7759 = 20240921.Sat/20030625.Wed  
    **Result**: 7759 days before today (Saturday, September 21, 2024) is June 25, 2003, which is a Wednesday.

### How to read Calculation Results
- **When was the calculation made**: First value = 20240921.Sat/～ ⇒ "Calculated on September 21, 2024"
- **How many days later or earlier**: -365 (365 days earlier) / +365 (365 days later)  Example: @-7674 → Calculate the date and weekday 7674 days before today.


