# 🚀 Quick Reference Card

## 🔥 Most Common Actions

### 🎯 Make a Change & Auto-Release
```bash
# Edit your code, then:
git add .
git commit -m "feat: add new feature"
git push origin main
# ✅ Auto-builds, tests, versions, and releases!
```

### 🔄 Create a Pull Request
```bash
git checkout -b feature/my-feature
# Make changes
git add .
git commit -m "feat: implement my feature"
git push origin feature/my-feature
# Then create PR on GitHub
# ✅ Auto-validates, labels, and tests!
```

### 📦 Download Latest APK
1. Go to **Releases** tab
2. Click latest release
3. Download `calculator-v{version}.apk`

### 🚫 Skip Auto-Release
```bash
git commit -m "docs: update README [skip-release]"
```

## 🔍 Check Build Status

| Location | What to Check |
|----------|---------------|
| **Actions Tab** | ✅ Green = Good, ❌ Red = Fix needed |
| **Releases Tab** | 📱 Download latest APK |
| **Issues Tab** | 📊 Daily health reports |
| **PR Tab** | 🔄 Auto-validation status |

## 🏷️ Auto-Labels Reference

| Files Changed | Auto-Label |
|---------------|------------|
| `*.kt` | `kotlin` |
| `*.xml` | `ui` |
| `build.gradle` | `build` |
| `.github/workflows/*` | `ci/cd` |
| `README*` | `documentation` |

## ⚡ Emergency Commands

### Force Manual Release
1. Go to **Actions** tab
2. Click "Auto Version & Release"
3. Click "Run workflow"

### Skip Broken Build
```bash
git commit -m "fix: emergency fix [skip-release]"
```

### Check What Will Release
```bash
git log --oneline main..HEAD
```

---
**💡 Tip:** Bookmark this page for quick reference while developing!