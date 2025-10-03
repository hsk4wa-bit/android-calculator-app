# 📱 Android Calculator App

A feature-rich calculator app built with **Kotlin** and **Material Design 3** for Android. Perfect for beginners learning Android development!

## ✨ Features

### 🧮 Core Calculator Functions
- **Basic Arithmetic**: Addition (+), Subtraction (-), Multiplication (×), Division (÷)
- **Decimal Support**: Handle decimal numbers with precision
- **Input Validation**: Prevents invalid inputs and handles edge cases
- **Error Handling**: Graceful handling of division by zero and other errors

### 🧠 Memory Functions
- **M+**: Add current display value to memory
- **M-**: Subtract current display value from memory  
- **MR**: Recall value from memory
- **MC**: Clear memory
- **Memory Indicator**: Visual "M" indicator when memory contains a value

### 📜 Calculation History
- **History Panel**: Toggle-able history view showing recent calculations
- **Scrollable List**: View up to 50 recent calculations
- **Clean Format**: Easy-to-read calculation format (e.g., "2 + 3 = 5")

### 🎨 Theme Support
- **Light Theme**: Clean, bright interface for day use
- **Dark Theme**: Easy on the eyes for low-light environments
- **Theme Toggle**: Tap the moon 🌙 icon to switch themes instantly
- **Persistent Settings**: Your theme choice is remembered between app launches

### 📱 User Interface
- **Material Design 3**: Modern, accessible design following Google's latest guidelines
- **Portrait Only**: Optimized layout for portrait orientation
- **Large Buttons**: Easy-to-tap buttons with visual feedback
- **Clear Display**: Large, monospace font for easy number reading
- **Smooth Animations**: Subtle animations and ripple effects

## 🏗️ Project Structure

```
Calculator_App/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/calculator/
│   │   │   ├── MainActivity.kt          # Main app logic and UI handling
│   │   │   └── HistoryAdapter.kt        # RecyclerView adapter for history
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   │   ├── activity_main.xml    # Main calculator layout
│   │   │   │   └── history_item.xml     # Individual history item layout
│   │   │   ├── values/
│   │   │   │   ├── colors.xml           # App color definitions
│   │   │   │   ├── strings.xml          # Text resources
│   │   │   │   └── themes.xml           # Light theme styles
│   │   │   ├── values-night/
│   │   │   │   └── themes.xml           # Dark theme styles
│   │   │   ├── drawable/
│   │   │   │   ├── display_bg.xml       # Display background styling
│   │   │   │   └── memory_indicator_bg.xml # Memory indicator styling
│   │   │   └── xml/
│   │   │       ├── backup_rules.xml     # Backup configuration
│   │   │       └── data_extraction_rules.xml # Data extraction rules
│   │   ├── AndroidManifest.xml          # App configuration and permissions
│   │   └── proguard-rules.pro          # Code obfuscation rules
│   └── build.gradle                     # App-level build configuration
├── build.gradle                         # Project-level build configuration
├── settings.gradle                      # Project settings
├── gradle.properties                    # Gradle properties
└── README.md                           # This file
```

## 🚀 Getting Started

### Prerequisites
1. **Android Studio**: Download and install [Android Studio](https://developer.android.com/studio)
2. **Minimum Requirements**:
   - Android 7.0 (API level 24) or higher
   - 50MB of storage space

### Installation Steps

#### Option 1: Import into Android Studio
1. **Open Android Studio**
2. Click **"Open an Existing Project"**
3. Navigate to the `Calculator_App` folder and select it
4. Click **"OK"**
5. Wait for Gradle sync to complete (this may take a few minutes)
6. Click the **"Run"** button (▶️) or press `Shift + F10`

#### Option 2: Command Line (Advanced)
```bash
# Navigate to the project directory
cd Calculator_App

# Build the project
./gradlew build

# Install on connected device/emulator
./gradlew installDebug
```

### First Run
1. **Connect your Android device** via USB (enable Developer Options and USB Debugging)
   - OR start an **Android Virtual Device (AVD)** in Android Studio
2. **Run the app** from Android Studio
3. The calculator will open in light theme by default
4. Try tapping the **🌙 button** to switch to dark theme!

## 🎯 How to Use

### Basic Calculations
1. **Tap number buttons** to enter numbers
2. **Tap operator buttons** (+, -, ×, ÷) to select operation
3. **Tap more numbers** for the second operand
4. **Tap equals (=)** to see the result

### Memory Functions
- **M+**: Adds current display to memory
- **M-**: Subtracts current display from memory
- **MR**: Shows stored memory value
- **MC**: Clears memory (M indicator disappears)

### Additional Features
- **C button**: Clears everything (current input, operation, memory not affected)
- **⌫ button**: Deletes last entered digit
- **. button**: Adds decimal point
- **History button**: Shows/hides calculation history
- **🌙 button**: Switches between light and dark themes

## 🛠️ Technical Details

### Architecture
- **Single Activity**: All functionality in `MainActivity.kt`
- **RecyclerView**: Efficient scrolling history list
- **SharedPreferences**: Persistent theme storage
- **Material Design**: Modern UI components and theming

### Key Technologies
- **Language**: Kotlin 1.9.10
- **UI Framework**: Android Views with Material Design 3
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 34 (Android 14)

### Code Highlights
- **Error Handling**: Division by zero protection
- **Input Validation**: Prevents invalid decimal inputs
- **Memory Management**: Efficient calculation history storage
- **Theme Persistence**: User preferences saved across app restarts

## 🧪 Testing

### Manual Testing Checklist
- [ ] Basic arithmetic operations work correctly
- [ ] Memory functions store and recall values
- [ ] History shows previous calculations
- [ ] Theme switching works and persists
- [ ] Edge cases handled (division by zero, multiple decimals)
- [ ] App works in portrait orientation
- [ ] All buttons respond with visual feedback

### Common Issues & Solutions

**Issue**: App crashes on startup
- **Solution**: Ensure minimum SDK is API 24+, clean and rebuild project

**Issue**: Theme doesn't change
- **Solution**: Check if device is in power saving mode, restart app

**Issue**: History doesn't show
- **Solution**: Perform a calculation first, then tap "History" button

## 🎓 Learning Opportunities

This project teaches:
- **Android Activity Lifecycle**
- **Kotlin Programming**
- **Material Design Implementation**
- **RecyclerView Usage**
- **SharedPreferences**
- **Theme and Style Management**
- **Event Handling**
- **XML Layout Design**

## 🚀 Future Enhancements

Ideas for expanding the calculator:
- Scientific functions (sin, cos, sqrt, etc.)
- Unit conversions
- History search and filtering
- Landscape mode support
- Haptic feedback
- Voice input
- Widget for home screen

## 📱 Screenshots

The app features:
- **Clean, modern interface** with large, easy-to-tap buttons
- **Beautiful light and dark themes** that adapt to your preference
- **Calculation history panel** that slides in and out
- **Memory indicator** that appears when memory contains a value
- **Smooth animations** and material design ripple effects

## 🤝 Contributing

This is a learning project! Feel free to:
- Experiment with the code
- Add new features
- Improve the UI design
- Fix bugs you discover
- Share your modifications

## 📄 License

This project is open source and available under the MIT License.

---

**Happy Coding! 🎉**

*Built with ❤️ for Android developers learning Kotlin*