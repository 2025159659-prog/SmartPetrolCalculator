# Smart Petrol Cost Calculator with BUDI MADANI Rebate (Malaysia)

This is an Android-based mobile application developed as part of the **Mobile Technology** course assignment (20%). The application estimates total petrol costs in Malaysia and applies the BUDI MADANI fuel subsidy to eligible users to calculate the final payable amount and total savings.

---

## 📱 Features & Functionalities

- **Dynamic Petrol Selection:** Choose between RON95, RON97, or Diesel using an intuitive RadioGroup interface.
- **BUDI MADANI Subsidy Logic:** Automatically checks eligibility status (YES/NO) and petrol type to apply the RM1.99/liter rebate (strictly for RON95 eligible users only).
- **Accurate Real-Time Calculation:** Computes Total Petrol Cost, BUDI Rebate, and Total Savings up to 2 decimal points.
- **Robust Input Validation:** Prevents application crashes by warning users if input fields are left blank.
- **Modern User Interface:** Built using `ConstraintLayout` with a structured, user-friendly flow and a custom Toolbar.

---

## 🧮 Sample Calculation Logic

The application follows the exact guidelines provided in the assignment prompt:
1. **Total Petrol Cost** = `Fuel Usage (Liters)` × `Petrol Price per Liter`
2. **BUDI Rebate** = `Fuel Usage` × `Subsidy Rate (RM1.99/l)` *(Only applicable if Petrol Type is RON95 and Eligibility is YES)*
3. **Total Saving** = `Total Petrol Cost` - `BUDI Rebate`

### Test Case Example:
- **Input:** RON95, Price: RM4.27, Usage: 40 Liters, BUDI Status: YES
- **Total Cost:** 40 × RM4.27 = **RM170.80**
- **BUDI Rebate:** 40 × RM1.99 = **RM79.60**
- **Total Saving:** RM170.80 - RM79.60 = **RM91.20**

---

## 👤 Developer Information

- **Name:** WAN MUHAMMAD A'BID NADZRI BIN WAN MOHD ZAWAWI
- **Matric No:** 2025159659
- **Course:** ICT602
- **Faculty:** CS266

---

## 🛠️ Tech Stack & Requirements

- **IDE:** Android Studio (Ladybug / Koala or latest version)
- **Language:** Java
- **UI Layout:** XML (ConstraintLayout, RadioGroup, Spinner, CardView)
- **Minimum SDK:** Android 5.0 (API Level 21) or above

---

## 📜 Copyright Notice
© 2026 Smart Petrol Cost Calculator. Developed individually for academic grading purposes. All Rights Reserved.
