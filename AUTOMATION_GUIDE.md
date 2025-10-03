# 🚀 Automated Build & Deployment Guide

Welcome to your comprehensive CI/CD automation system! This guide explains all the automated workflows set up for your Android Calculator project.

## 📋 Overview

Your repository now has **5 powerful automated workflows** that handle everything from building to monitoring:

| Workflow | Trigger | Purpose |
|----------|---------|---------|
| 🚀 Build & Release | Every push/PR | Build APKs, run tests, quality checks |
| 🏷️ Auto Version & Release | Push to main | Auto-increment version, create releases |
| 🔄 Pull Request Automation | PR creation | Validate PRs, auto-label, welcome messages |
| 📊 Project Health Monitor | Daily at 9 AM UTC | Health checks, dependency updates |
| Legacy workflows | Various | Original CI/CD setup |

## 🔧 Workflow Details

### 1. 🚀 Build & Release Workflow
**File:** `.github/workflows/build-and-release.yml`

**What it does:**
- ✅ Builds both Debug and Release APKs
- 🧪 Runs all unit tests
- 📊 Performs lint checks and code quality analysis
- 📦 Uploads APK files as downloadable artifacts
- 📢 Sends build notifications

**Triggers:**
- Push to `main` or `develop` branches
- Pull requests to `main`
- Manual releases

**Artifacts Generated:**
- `debug-apk-{commit-sha}` - Debug APK for testing
- `release-apk-{commit-sha}` - Release APK for distribution
- `lint-results-{commit-sha}` - Lint analysis reports

### 2. 🏷️ Auto Version & Release Workflow
**File:** `.github/workflows/auto-version-release.yml`

**What it does:**
- 📈 Automatically increments version numbers
- 🔨 Builds release APK
- 🏷️ Creates GitHub releases with downloadable APKs
- 📝 Commits version changes back to repository

**Triggers:**
- Push to `main` branch (when source code changes)
- Skipped if commit message contains `[skip-release]`

**Versioning Strategy:**
- **Version Code:** Auto-incremented integer
- **Version Name:** Patch version increment (1.0.0 → 1.0.1)

**Example:**
```
Current: v1.0.0 (code: 1)
After push: v1.0.1 (code: 2)
```

### 3. 🔄 Pull Request Automation
**File:** `.github/workflows/pr-automation.yml`

**What it does:**
- 🔍 Validates PR code quality
- 📏 Analyzes PR size and complexity
- 🏷️ Auto-applies labels based on file changes
- 💬 Posts welcome comments for new contributors
- 📦 Generates test APKs for PR review

**Auto-Labels Applied:**
- `kotlin` - Contains Kotlin code changes
- `ui` - Contains XML/layout changes
- `build` - Contains Gradle build changes
- `ci/cd` - Contains workflow changes
- `documentation` - Contains README/docs changes
- `size/small|medium|large` - Based on lines changed

### 4. 📊 Project Health Monitor
**File:** `.github/workflows/project-health.yml`

**What it does:**
- 🏥 Daily health checks and builds
- 📈 Generates project statistics
- 🔍 Scans for security vulnerabilities
- 🔄 Checks for dependency updates
- 💾 Verifies project structure integrity

**Runs:**
- Daily at 9:00 AM UTC
- Manually via GitHub Actions tab

**Reports Generated:**
- Daily health report (posted as GitHub issue)
- Dependency update analysis
- Backup verification status

## 🎯 How to Use These Workflows

### For Development

1. **Regular Development:**
   ```bash
   # Make your changes
   git add .
   git commit -m "feat: add new calculator function"
   git push origin main
   ```
   - ✅ Auto-builds and tests
   - 🏷️ Auto-creates release
   - 📦 Generates downloadable APK

2. **Feature Branches:**
   ```bash
   git checkout -b feature/scientific-mode
   # Make changes
   git push origin feature/scientific-mode
   # Create PR via GitHub
   ```
   - 🔍 Validates your changes
   - 📏 Analyzes PR size
   - 🏷️ Auto-applies labels

3. **Skip Auto-Release:**
   ```bash
   git commit -m "docs: update README [skip-release]"
   ```
   - Builds and tests normally
   - Skips version increment and release

### For Releases

**Automatic Releases** (Recommended):
- Just push to `main` - everything is automated!
- Version numbers increment automatically
- APKs are built and attached to releases

**Manual Releases:**
- Go to GitHub Actions → "Auto Version & Release"
- Click "Run workflow" → "Run workflow"

### For Monitoring

**Daily Health Reports:**
- Check the "Issues" tab for daily health reports
- Reports include build status, code metrics, and dependency updates

**Build Status:**
- Green checkmarks = All good ✅
- Red X = Something needs attention ❌
- Check the "Actions" tab for detailed logs

## 📱 Downloading Built APKs

### From Releases (Recommended)
1. Go to **Releases** tab in your repository
2. Download the latest `calculator-v{version}.apk`
3. Install on your Android device

### From Actions Artifacts
1. Go to **Actions** tab
2. Click on any completed workflow run
3. Scroll down to **Artifacts** section
4. Download the APK zip file

## 🔧 Customization Options

### Modify Version Increment Strategy
Edit `.github/workflows/auto-version-release.yml`:
```yaml
# For major version bump (1.0.0 → 2.0.0)
NEW_VERSION="$((MAJOR + 1)).0.0"

# For minor version bump (1.0.0 → 1.1.0)  
NEW_VERSION="$MAJOR.$((MINOR + 1)).0"
```

### Change Build Triggers
Edit any workflow file's `on:` section:
```yaml
on:
  push:
    branches: [ main, develop, feature/* ]  # Add more branches
  schedule:
    - cron: '0 12 * * *'  # Change time
```

### Add Custom Labels
Edit `.github/workflows/pr-automation.yml`:
```javascript
if (hasCustomCondition) labels.push('custom-label');
```

## 🚨 Troubleshooting

### Build Failures
1. Check **Actions** tab for error logs
2. Common issues:
   - Kotlin compilation errors
   - Missing dependencies
   - Test failures

### Release Not Created
- Ensure commit doesn't have `[skip-release]`
- Check if source code actually changed
- Verify workflow permissions

### APK Not Downloadable
- Wait for workflow to complete (green checkmark)
- Check **Artifacts** section in workflow run
- Ensure you have repository access

## 📚 Additional Resources

### GitHub Actions Documentation
- [GitHub Actions Basics](https://docs.github.com/en/actions)
- [Android CI/CD](https://docs.github.com/en/actions/automating-builds-and-tests/building-and-testing-java-with-gradle)

### Android Development
- [Android Studio Guide](https://developer.android.com/studio/intro)
- [Kotlin Documentation](https://kotlinlang.org/docs/home.html)

## 🎉 What's Next?

Now that your automation is set up, you can focus on:

1. **Adding Features:**
   - Scientific calculator functions
   - Unit conversions
   - Themes and customization

2. **Learning More:**
   - Android development patterns
   - Advanced CI/CD techniques
   - Code quality best practices

3. **Contributing:**
   - Open source your calculator
   - Accept community contributions
   - Build a developer community

---

**🎊 Congratulations!** You now have a professional-grade automated development pipeline. Every commit triggers builds, tests, and potential releases. Your project is ready for serious development and collaboration!

Need help? Create an issue in your repository, and the automation will help categorize and track it! 🤖