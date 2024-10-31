# Product Management Application

A simple product management application that utilizes **TabLayout** and **ViewPager** to organize and move products between different states (New, Scheduled, Delivered). The app is designed with SQLite database support and provides seamless transitions across different product management tabs.

## 🚀 Objective
The aim of this project is to create a **basic product management application** where users can add, view, and manage products. Products flow between three tabs: New Order, Schedule, and Delivered, based on user actions.

## 🌟 Features
- **Tab Layout with ViewPager**: Three tabs for product management (New Order, Schedule, Delivered).
- **SQLite Database** for persistent product storage.
- **Floating Action Button (FAB)** to add new products.
- **Product Actions**: Edit, Delete, Schedule, and Deliver functionality within the app.
- **Dynamic Fragment Update**: Move products across tabs based on user actions.

## 📋 Development Guidelines
- **TabLayout** and **ViewPager** are used to display three product categories.
- **SQLiteHelper** is implemented to manage product data.
- **ProductDB** class handles database operations (CRUD).
- **Product** class stores product details like title, price, and date.
- **FAB** allows users to add new products which start in the New Order tab.
- Consistent use of **ConstraintLayout** ensures responsive design.

## 💻 App Flow
1. **Main Activity**: Hosts the TabLayout with three tabs—New Order, Schedule, Delivered.
    - **New Order Tab**: Displays products added through the FAB.
    - **Schedule Tab**: Shows products scheduled for delivery.
    - **Delivered Tab**: Displays delivered products.
2. **Product Actions**:
    - **New Order Tab**: Edit, Delete, Schedule.
    - **Schedule Tab**: Deliver the scheduled products.
3. **SQLite DB**: Stores product information and updates product status as actions are performed.

## 🛠 Technologies Used
- **Android SDK** for app development.
- **TabLayout** and **ViewPager** for tab-based navigation.
- **SQLiteHelper** for database interactions.
- **Java** for business logic and managing fragments.

## 🛠 File Structure
- **Product.java**: Represents a product with attributes like title, price, and date.
- **ProductDB.java**: Handles database operations like insert, update, and delete.
- **NewOrderFragment.java**: Displays new products.
- **ScheduleFragment.java**: Displays scheduled products.
- **DeliveredFragment.java**: Displays delivered products.
- **ViewPagerAdapter.java**: Manages the tab layout and fragments.

## 🎨 Design Consistency
- Follow a consistent design across all tabs, ensuring that the user experience is intuitive and seamless.
- **FAB** for product addition is prominent and accessible.

## 🧪 Testing
- Validate transitions between tabs (New Order → Schedule → Delivered).
- Ensure proper data storage and retrieval using **SQLite**.
- Test the edit, delete, schedule, and deliver actions for reliability.

## 📸 Demo
<div style="display: flex; justify-content: center; align-items: center;">
    <video class="as" src="https://github.com/user-attachments/assets/c8a9a6e2-e8d3-4a07-86a7-5000f72dd252" controls="controls" style="max-width: 100%;">
        Your browser does not support the video tag.
    </video>
</div>

---

Made with ❤ by **Tayyab Anees**.
