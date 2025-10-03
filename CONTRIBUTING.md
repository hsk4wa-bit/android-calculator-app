# Contributing to Calculator App

Thank you for your interest in contributing to the Calculator App! This document provides guidelines and information for contributors.

## Getting Started

### Prerequisites
- Android Studio (latest stable version)
- JDK 17 or higher
- Git
- Basic knowledge of Kotlin and Android development

### Setting Up Development Environment

1. **Fork the repository**
   ```bash
   # Click "Fork" on GitHub, then clone your fork
   git clone https://github.com/YOUR_USERNAME/calculator-app.git
   cd calculator-app
   ```

2. **Set up upstream remote**
   ```bash
   git remote add upstream https://github.com/ORIGINAL_OWNER/calculator-app.git
   ```

3. **Open in Android Studio**
   - Import the `Calculator_App` folder
   - Wait for Gradle sync to complete

## Development Workflow

### Branch Strategy
- `main`: Production-ready code
- `develop`: Development branch for integration
- `feature/feature-name`: New features
- `bugfix/bug-description`: Bug fixes
- `hotfix/critical-fix`: Critical production fixes

### Making Changes

1. **Create a feature branch**
   ```bash
   git checkout develop
   git pull upstream develop
   git checkout -b feature/your-feature-name
   ```

2. **Make your changes**
   - Write clean, readable code
   - Follow existing code style
   - Add comments for complex logic
   - Update documentation if needed

3. **Test your changes**
   ```bash
   ./gradlew test           # Run unit tests
   ./gradlew lint           # Run code quality checks
   ./gradlew assembleDebug  # Build the app
   ```

4. **Commit your changes**
   ```bash
   git add .
   git commit -m "feat: add scientific calculator functions"
   ```

5. **Push and create pull request**
   ```bash
   git push origin feature/your-feature-name
   ```
   Then create a Pull Request on GitHub.

## Code Style Guidelines

### Kotlin Style
- Follow [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- Use meaningful variable and function names
- Keep functions small and focused
- Use data classes for simple data containers

### Android Specific
- Follow [Android Code Style](https://source.android.com/docs/setup/contribute/code-style)
- Use Material Design components
- Support both light and dark themes
- Handle configuration changes properly

### Example Code Style
```kotlin
class CalculatorViewModel : ViewModel() {
    private val _displayValue = MutableLiveData<String>()
    val displayValue: LiveData<String> = _displayValue
    
    fun addNumber(number: String) {
        // Clear, concise implementation
        val currentValue = _displayValue.value ?: "0"
        _displayValue.value = if (currentValue == "0") number else currentValue + number
    }
}
```

## Testing Guidelines

### Unit Tests
- Write tests for all business logic
- Use descriptive test names
- Follow Given-When-Then pattern

```kotlin
@Test
fun `given two numbers when adding then returns correct sum`() {
    // Given
    val calculator = Calculator()
    
    // When
    val result = calculator.add(2.0, 3.0)
    
    // Then
    assertEquals(5.0, result, 0.001)
}
```

### UI Tests
- Test critical user workflows
- Use Espresso for UI testing
- Test both light and dark themes

## Pull Request Guidelines

### Before Submitting
- [ ] Code compiles without warnings
- [ ] All tests pass
- [ ] Code follows style guidelines
- [ ] Documentation is updated
- [ ] Screenshots included for UI changes

### PR Description
- Clearly describe what changes were made
- Reference related issues
- Include screenshots for UI changes
- List testing steps

### Review Process
1. Automated checks must pass
2. Code review by maintainers
3. Manual testing if needed
4. Approval and merge

## Feature Requests

### Popular Feature Ideas
- Scientific calculator functions
- Unit conversions
- Calculation history export
- Voice input
- Widgets
- Haptic feedback

### Proposing New Features
1. Check existing issues first
2. Create detailed feature request
3. Discuss design and implementation
4. Get approval before starting work

## Bug Reports

### Good Bug Reports Include
- Clear description of the issue
- Steps to reproduce
- Expected vs actual behavior
- Device and OS information
- Screenshots or videos

### Example Bug Report
```
Title: Division by zero crashes app

Description: App crashes when dividing by zero instead of showing error

Steps:
1. Open calculator
2. Enter "5"
3. Tap "÷" 
4. Enter "0"
5. Tap "="

Expected: Show "Error" or "Cannot divide by zero"
Actual: App crashes

Device: Samsung Galaxy S21
OS: Android 12
App Version: 1.0.0
```

## Release Process

### Version Numbering
We use [Semantic Versioning](https://semver.org/):
- `MAJOR.MINOR.PATCH` (e.g., 1.2.3)
- Major: Breaking changes
- Minor: New features
- Patch: Bug fixes

### Release Checklist
- [ ] All tests pass
- [ ] Documentation updated
- [ ] Version number incremented
- [ ] Release notes prepared
- [ ] APK signed and tested

## Code of Conduct

### Our Standards
- Be respectful and inclusive
- Focus on constructive feedback
- Help others learn and grow
- Collaborate professionally

### Unacceptable Behavior
- Harassment or discrimination
- Trolling or insulting comments
- Publishing private information
- Unprofessional conduct

## Getting Help

### Resources
- [Android Developer Documentation](https://developer.android.com/docs)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)
- [Material Design Guidelines](https://material.io/design)

### Communication
- GitHub Issues: Bug reports and feature requests
- GitHub Discussions: General questions and ideas
- Code Reviews: Technical feedback on pull requests

## Recognition

Contributors will be:
- Listed in the app's credits
- Mentioned in release notes
- Given appropriate GitHub badges
- Invited to be maintainers (for significant contributions)

---

Thank you for contributing to Calculator App! 🎉