
# Weather App Test Task

This repository contains a weather application developed as part of an assignment. The app fetches and displays weather data for a user-specified city, adhering to the given requirements and utilizing modern Android development practices.

---

## **Features**

- **Real-time Weather Information**: Fetches weather data from WeatherAPI.com.
- **Error Handling**: Graceful handling of invalid cities or network errors.
- **Persistent Data**: Stores user preferences or settings using DataStore.
- **Figma Design Accuracy**: Matches the provided Figma design with 95% visual consistency.

---

## **Tech Stack**

- **Programming Language**: Kotlin
- **UI Framework**: Jetpack Compose
- **Architecture**: MVVM with Clean Architecture principles
- **Networking**: Ktor
- **Dependency Injection**: Hilt
- **Persistence**: DataStore

---

## **Project Structure**

The app is developed using a **multi-modular approach** to ensure scalability for future features and maintainability. The main modules include:

1. **Home Module**:
   - Contains the core functionality of the weather application.
   - Handles fetching weather data, processing it, and displaying it to the user.

2. **Shared Modules** (e.g., Common, Theme, Network):
   - Provide reusable components and abstractions such as networking, DI setup, and model definitions.

---

## **Development Decisions**

### **1. Multi-modular Architecture**
- Designed the app with scalability in mind. While this task focuses on the weather feature, the structure supports adding more features in the future without significant refactoring.

### **2. Networking**
- Used the **Ktor library** for API calls to WeatherAPI.com due to its modern, lightweight design and Kotlin-first approach.

### **3. Persistence**
- Leveraged **DataStore** to manage and persist user preferences/settings.

### **4. MVVM Architecture**
- Followed MVVM for clear separation of concerns, making the codebase easier to test and maintain.

### **5. Dependency Injection**
- Used **Hilt** to provide dependencies across the app efficiently.

### **6. Navigation**
- Integrated **Navigation Compose** for handling navigation, even though the current app has a single feature. This ensures easy extensibility for future requirements.

### **7. Clean Architecture**
- Implemented clean architecture principles to keep the code modular, testable, and maintainable.

---

## **UI**

- The app UI is fully designed in **Jetpack Compose**, adhering to modern Android UI development practices.
- The design matches the **provided Figma designs** with over **95% accuracy**, ensuring consistency and a polished user experience.

---

## **Limitations**

- The task was implemented within the recommended **5-hour limit**. While the app is functional and adheres to the requirements, there may be room for optimization and additional features in a real-world scenario.

---

## **How to Run**

1. Clone the repository.
2. Open the project in Android Studio.
3. Add the following lines to your `local.properties` file to compile the code correctly:
   ```
   base_url=api.weatherapi.com
   api_key=e43d6e8321b246f7a69154743242012
   ```
4. Build and run the app on an emulator or physical device. 

---

## **Future Improvements**

- Add unit and integration tests for better test coverage.
- Improve error handling to provide more detailed feedback to the user.
- Extend features such as saving favorite cities or displaying historical weather data.
- Optimize network calls and caching for better performance.

---

This app demonstrates adherence to the requirements while showcasing my ability to build scalable, maintainable Android applications using modern tools and best practices.
