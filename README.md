# Grama-Suvidha Portal 🏛️

A Civic-Tech Android application designed to bridge the gap between Panchayat officials and citizens. It promotes transparency in village development projects by allowing citizens to track progress, provide feedback, and report issues.

## 🚀 Features

### Citizen Side
- **Project Tracking**: View all development projects with status (Ongoing, Completed, Delayed).
- **Progress Visualization**: Animated progress bars and Before/After image galleries.
- **Project Timeline**: Detailed history of project updates.
- **Citizen Reviews**: Rate projects (1-5 stars) and leave comments.
- **Issue Reporting**: Report problems like poor quality or delays directly to officials.
- **Community Satisfaction Meter**: Real-time visual sentiment based on citizen ratings.
- **Multilingual Support**: Available in English and Kannada.
- **Offline-First**: Access all information even without internet connectivity.

### Admin Side (Panchayat Officials)
- **Secure Login**: Simple local authentication for demo purposes.
- **Admin Dashboard**: Real-time stats on projects and complaints.
- **Project Management**: Add new projects or edit existing ones.
- **Progress Updates**: Update progress percentages and status.
- **Timeline Management**: Add official updates to the project timeline.
- **Complaint Management**: View and resolve citizen reports.

## 🏗️ Architecture

The app follows **Clean Architecture** principles with a focus on **MVVM (Model-View-ViewModel)**.

- **UI Layer**: Jetpack Compose for a modern, reactive UI.
- **Domain Layer**: Repository pattern to abstract data sources.
- **Data Layer**: 
    - **Room Database**: Local persistence for projects, reviews, and complaints.
    - **DataStore**: Preferences for language and session management.
    - **WorkManager**: Background synchronization tasks.

## 💾 Database Schema (Room)

1. **Projects**: Stores project details, budget, status, and progress.
2. **Reviews**: Stores citizen ratings and comments linked to projects.
3. **Complaints**: Stores reported issues and their resolution status.
4. **Timeline Updates**: Stores official progress updates for project timelines.

## 🛠️ Tech Stack

- **Kotlin** & **Jetpack Compose**
- **Dagger Hilt** (Dependency Injection)
- **Room Database** (Local Storage)
- **Retrofit & GSON** (Mock API Handling)
- **Coil** (Image Loading)
- **WorkManager** (Background Sync)

## 📖 How to Run

1. Clone the repository.
2. Open in **Android Studio Ladybug (or newer)**.
3. Sync Gradle and run on an emulator/device.
4. **Admin Access**:
   - Go to **Settings** -> **Login as Admin**.
   - **Username**: `admin`
   - **Password**: `admin123`

---
*Built as a civic-tech demo project for internship/portfolio showcase.*
