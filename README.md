# Android App Development using GenAI - Grama-Suvidha Portal

Grama-Suvidha Portal is a native Android application designed as a digital progress tracker for village-level development works. The app acts like a transparent "Digital Notice Board" where citizens can view local infrastructure projects, understand their current progress, and share feedback.

## 1. Problem Statement

There is often a transparency gap in rural infrastructure development. Citizens in a Panchayat are usually unaware of the progress of local works such as community hall construction, borewell installation, road repairs, pond rejuvenation, and drainage improvements.

This lack of information reduces public trust and community ownership. A mobile platform is needed to visualize "Digital Progress" for village-level assets so every citizen can understand where development stands.

## 2. Project Vision

Grama-Suvidha is a transparency-focused project tracker for Panchayat-level development. It lists government-funded projects in a local area and shows important information such as:

- Project name
- Village/location
- Budget
- Current status
- Completion percentage
- Latest update
- Citizen satisfaction rating
- Issue reporting option

The goal is to make project information simple, visible, and accessible to villagers.

## 3. App Usage and User Flow

### Project List

Users can view all local development works in one place. Example projects include:

- Main Road Repair
- Borewell Installation
- Drinking Water Pipeline
- School Building Repair
- Village Drainage Work

### Progress View

Each project card shows a progress indicator and project status, such as:

- Ongoing
- Completed
- Delayed

The progress bar visually represents the stored completion percentage.

### Citizen Feedback

The app includes a citizen issue desk concept where users can report project-related issues. The app also shows a public satisfaction score to represent citizen feedback.

### Language Support

The project vision includes English and Kannada support so every villager can participate comfortably. The current implementation is structured as a foundation for this multilingual flow.

## 4. Key Features

- Native Android app built with Kotlin.
- Modern UI built using Jetpack Compose.
- Panchayat project dashboard.
- Project progress cards.
- Budget and completion percentage display.
- Citizen satisfaction display.
- Issue reporting entry point.
- Admin mode toggle for official project updates.
- Offline-friendly mock project data foundation.

## 5. Technical Implementation

### Android Stack

- Kotlin
- Jetpack Compose
- Material 3
- Android Gradle Plugin
- Gradle Wrapper

### UI Layer

The app uses Jetpack Compose for a clean, reactive interface. The main dashboard is implemented in:

```text
app/src/main/java/com/gramasuvidha/portal/MainActivity.kt
```

### Mock Data Structure

The current app uses local mock project data through a Kotlin data model:

```kotlin
private data class Project(
    val title: String,
    val village: String,
    val status: String,
    val budget: String,
    val progress: Float,
    val satisfaction: String,
    val nextUpdate: String
)
```

Each project stores:

- `title`: Name of the development work
- `village`: Village or local area name
- `status`: Current project state
- `budget`: Allocated budget
- `progress`: Completion value from `0.0` to `1.0`
- `satisfaction`: Citizen rating summary
- `nextUpdate`: Latest public update

This mock structure can later be moved to a local JSON file or Room database for offline caching.

### Offline Viewing

The app currently uses local in-app mock data, so the project dashboard can be viewed without a live internet connection. A future production version can extend this with Room or DataStore to cache project lists after syncing from an API.

### Progress Bar Logic

Project progress is represented using a Float value:

```text
0.68 = 68% complete
1.00 = 100% complete
```

The Compose progress bar reads this value and displays the matching completion percentage on the screen.

### Image Loading Plan

The project vision includes mock before/after project site photos. A production version can use Coil or Glide to load these images from local assets, a mock API, or cached remote URLs.

## 6. Impact Goals

### Good Governance

The app improves transparency and accountability by making Panchayat project details visible to citizens.

### Citizen Engagement

Citizens can move from being passive residents to active "Progress Pilots" who monitor and respond to local development work.

### Infrastructure Quality

Public visibility and peer monitoring can encourage better work quality, timely completion, and more responsible project execution.

## 7. Success Criteria

- The app supports offline viewing using local mock project data.
- The progress bar accurately reflects the stored project percentage.
- The app architecture supports English and Kannada expansion.
- The mock project data structure is documented.
- The app clearly shows project status, budget, progress, satisfaction, and issue reporting.

## 8. Project Structure

```text
Grama-Suvidha-Portal/
|-- app/
|   |-- build.gradle.kts
|   |-- proguard-rules.pro
|   `-- src/main/
|       |-- AndroidManifest.xml
|       |-- java/com/gramasuvidha/portal/MainActivity.kt
|       `-- res/
|-- gradle/
|   |-- libs.versions.toml
|   `-- wrapper/
|-- build.gradle.kts
|-- settings.gradle.kts
|-- gradle.properties
`-- README.md
```

## 9. Running the App

Build the debug APK:

```bash
./gradlew assembleDebug
```

The generated APK is created at:

```text
app/build/outputs/apk/debug/app-debug.apk
```

Build the release APK:

```bash
./gradlew assembleRelease
```

The generated release APK is created at:

```text
app/build/outputs/apk/release/app-release.apk
```
