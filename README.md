# Albay Reality

> An AR-based mobile application for cultural heritage education and tourism in Albay, Philippines.

Developed by **BARABad Development Team** — Bicol University, College of Science
BS Computer Science 3B | CS 119 – Software Engineering 2

---

## Table of Contents

1. [Project Overview](#project-overview)
2. [Features](#features)
3. [System Requirements](#system-requirements)
4. [Installation](#installation)
5. [Getting Started](#getting-started)
6. [User Guide](#user-guide)
7. [Architecture](#architecture)
8. [Functional Requirements](#functional-requirements)
9. [Non-Functional Requirements](#non-functional-requirements)
10. [Limitations](#limitations)
11. [Test Coverage Summary](#test-coverage-summary)
12. [Security and Compliance](#security-and-compliance)
13. [Stakeholders](#stakeholders)
14. [References](#references)
15. [Team](#team)

---

## Project Overview

Albay Reality is an Android mobile application that uses Augmented Reality (AR) to promote and preserve the cultural heritage of Albay, Philippines. The application allows users to view static 3D models of heritage landmarks, access curated historical information, complete gamified trivia, and navigate an interactive map of cultural sites across the province.

The project was motivated by the underutilization of digital tools in Albay's cultural promotion space. Roldan (2023) identified that digital technologies have not yet been fully leveraged for local cultural engagement. Albay Reality addresses this gap by combining ARCore-based model rendering, a cloud-synced quiz system, and interactive map navigation into a single, accessible platform.

The application targets three primary user groups: tourists visiting Albay, students using it as a supplementary learning tool, and locals seeking to rediscover their heritage.

---

## Features

### AR Model Display
Renders static 3D models of cultural heritage sites in augmented reality. Users point their device camera at a flat surface, and the model is placed and displayed on that surface. Users can explore the model from different angles.

- Up to 4 cultural sites in the initial release
- Each model is accompanied by validated descriptive text
- Models are capped at 30 MB each
- Requires ARCore-compatible device

### Interactive Map
Displays a map of Albay with location pins marking cultural heritage sites. Users can tap individual pins to view site-specific information. The map is sourced via an integrated Maps API (OpenMaps/Google Maps).

- Pan and zoom support
- Location pin tap reveals site details
- Map focused on the province of Albay only

### Quiz System (AR Game)
A gamified trivia system tied to cultural heritage sites. Quizzes are organized per site, with a countdown timer per question. Users earn points based on correct answers and remaining time.

- 20-second timer per question
- First tap is the only registered response (prevents multi-tap exploitation)
- Score displayed at quiz completion
- Points sync to cloud database
- Two leaderboards for social comparison

### Login and Account System
Users register and log in with a username and password. Account data is stored in Firebase with appropriate security measures.

- CRUD operations on user data
- Role-based access: Viewer and Administrator
- Session management with local and server sync
- Protected by the Philippine Privacy Act of 2012

### User Profile Management
Users can view and edit their profile information. Input validation is enforced on all fields. Changes can be saved or discarded, and blank fields retain their previous values.

### About Screen
Displays application information and developer credits.

### Leaderboards
Displays ranked user scores. Tapping a user entry shows a brief profile overview in a pop-up.

---

## System Requirements

### Hardware (Minimum)

The device must have an ARMv8-A processor (32-bit or 64-bit), at least 2 GB of RAM, a minimum of 500 MB of free internal storage, and a rear camera for AR functionality.

### Software

The application requires Android 9.0 (Pie) or newer. It depends on Google ARCore via AR Foundation for augmented reality, a Maps API (OpenMaps or Google Maps) for map navigation, and Firebase as the cloud database. An active internet connection is required for authentication, leaderboard updates, and cloud synchronization.

> Devices below Android 9.0 are outside the supported boundary. Features will not function as intended on unsupported OS versions.

---

## Installation

1. Download the APK file from the provided distribution source.
2. Open the downloaded `.apk` file on your Android device.
3. If prompted, allow installation from unknown sources in your device settings.
4. Tap **Install** and wait for the process to complete.
5. Tap **Open** to launch the application.

> The application is not currently available on the Google Play Store. Distribution is via direct APK.

---

## Getting Started

### Creating an Account

1. Open the application.
2. On the welcome screen, tap **Register**.
3. Enter the required information:
   - Username
   - Email address
   - Password (minimum 6 characters)
4. Tap **Submit** or **Register**.
5. On success, the app redirects to the Login screen.

**Registration error cases:**
- Empty or invalid fields show inline error messages.
- A duplicate email returns: *"Account registration failed."*
- An invalid email format returns: *"Invalid email format."*
- A password shorter than 6 characters returns: *"Password must be at least 6 characters."*

> Note: The system does not verify whether an email address is real. An account will be created for any validly formatted email address.

### Logging In

1. Open the application.
2. Tap **Log In** on the main screen.
3. Enter your registered username or email and password.
4. Tap **Log In**.
5. On success, the app redirects to the Home Dashboard.

**Login error cases:**
- Incorrect password returns: *"Incorrect password."*
- Unregistered email returns: *"Login Failed."*
- Empty fields return per-field error messages.

> The Forgot Password feature is not available in the current version.

### Logging Out

1. Navigate to the User Profile screen.
2. Tap the **Log Out** icon.
3. The app returns to the Login screen and ends the session.

---

## User Guide

### AR Model Viewing

1. From the Home Dashboard, tap **AR Catalogs**.
2. Select a cultural site and tap **View**.
3. Tap **AR Mode**.
4. Point your device camera at a flat, well-lit surface.
5. Wait for the 3D model to appear (target load time: 2–4 seconds offline; up to 8 seconds if fetched from cloud).
6. Move your device around the model to view it from different angles.
7. Interact with the model via swipe or pinch gestures.

**Troubleshooting AR:**
- If the model does not appear: confirm ARCore support, restart the app, and ensure the app is updated.
- If the model lags: close background apps, move to a better-lit area, or use a higher-performance device.
- If the model cannot load: check your internet connection and retry.

### Interactive Map

1. From the Home Dashboard, tap the **Interactive Map** button.
2. Grant location access if prompted.
3. The map loads within 2 seconds with visible pins for heritage sites across Albay.
4. Tap a pin to view site information (loads within 1 second for local data; 1–6 seconds for cloud data).
5. Use pinch gestures to zoom in and out.
6. Drag to pan across the map.

**Troubleshooting the Map:**
- If the map does not load: check your internet connection and restart the app.
- If pins are not visible: refresh the map or update the app.
- If the app becomes unresponsive: close background apps or restart your device.

### AR Quiz

1. From the Home Dashboard, tap **Game** or access **Quiz Mode** from the AR Catalogs screen.
2. Select a quiz tied to a cultural site.
3. Tap **Start Quiz**.
4. Select an answer before the 20-second timer runs out.
5. Only the first tap per question is recorded.
6. If time runs out without a selection, the question is marked incorrect and the quiz advances.
7. After completing all questions, the results screen shows your answers and score.
8. Points are synced to the cloud database and leaderboards are updated.

**Troubleshooting Quizzes:**
- If the quiz does not load: check your internet connection and restart the app.
- If answers are not recorded: ensure you tap before time expires and do not close the app mid-quiz.
- If scores do not update: refresh the app or log out and back in to force a sync.

### User Profile

1. From the Home Dashboard, tap the **Profile** icon.
2. Tap the **Edit Profile** (pencil) icon.
3. Modify the fields you want to update. Fields left blank retain their current values.
4. Tap **Save Changes** to apply. A confirmation modal appears on success.
5. Tap **Cancel Changes** to discard. A cancellation modal confirms no changes were saved.

**Validation rules:**
- Text fields do not accept digits or symbols.
- Email must follow a valid format.
- Duplicate emails from other accounts are rejected.
- New password and confirmation password must match.

---

## Architecture

The application follows a **three-tier client-application-database architecture**:

```
Client (Android Device)
├── QR/AR Scanning
├── AR Display
├── Interactive Map
├── Quiz/Game Module
├── User Interface Screens
└── Local Storage

        ↓ sends requests ↑ receives responses

Application Layer
├── Authentication Processing
├── Token Computation
├── Leaderboard Processing
├── QR Code Validation
└── Data Synchronization

        ↓ reads/writes ↑ returns data

Database and Asset Storage (Firebase)
├── User Account Data
├── Token Records
├── Leaderboard Data
├── QR Scan Records
└── 3D Model Storage
```

Multiple client devices interact with a single application layer, which in turn communicates with the Firebase backend. The architecture is designed to support up to 100 concurrent users.

---

## Functional Requirements

### Login System

The login system carries six functional requirements. It must comply with GDPR (REQ-LS-1). Users must be able to create new credentials (REQ-LS-2), view their own account details (REQ-LS-3), and change their own credentials (REQ-LS-4). Credentials must be stored in a database with appropriate security measures (REQ-LS-5), and each user's credentials must be stored separately (REQ-LS-6).

### Core Features

The AR Model Display renders static 3D models in augmented reality via ARCore. The Interactive Map displays pins for heritage sites across Albay and supports tap, zoom, and pan. The Quiz System is a gamified trivia feature with a per-question timer, scoring logic, and leaderboard synchronization. User Profile allows profile editing with input validation and save/cancel confirmation. Leaderboards provides two ranked boards with a per-user pop-up overview.

---

## Non-Functional Requirements

### Performance

If the application exceeds 1 GB in size, multimedia and 3D assets must migrate to cloud storage and load on demand (NFR-PR-1). Cloud asset fetching must complete within 2–4 seconds under a stable connection (NFR-PR-2). When no internet is available, the app must notify the user and fall back to locally stored models (NFR-PR-3). RAM usage during AR operations must not exceed 2 GB regardless of asset source (NFR-PR-4), and individual 3D models must not exceed 30 MB (NFR-PR-5).

The application supports 3–7 3D models in the initial release (NFR-PR-6). AR models must render within 2–4 seconds in offline mode (NFR-PR-7), and cloud-fetched models must complete rendering within 8 seconds total (NFR-PR-8). The interactive map must load with visible pins within 2 seconds (NFR-PR-9), and pin information must display within 1 second for local data or 1–6 seconds for cloud data (NFR-PR-10).

The quiz UI must have no interaction delays (NFR-PR-11), and the cloud database must sync local and cloud data correctly (NFR-PR-12). The application must maintain 99.6% availability in online mode, excluding third-party outages (NFR-PR-15), and must support up to 100 concurrent users (NFR-PR-16).

### Safety

- Warnings are displayed advising against using AR scanner or map while driving or in situations requiring full attention.
- Explicit device permissions are requested before accessing camera, storage, or location.
- Error messages and retry options are provided for compromised conditions (poor lighting, low performance).
- Cloud database performs validation checks during data retrieval to prevent corruption.
- Backup mechanisms are in place to prevent permanent data loss.
- All 3D models and cultural representations follow heritage guidelines.

### Quality Attributes

The UI is designed to be intuitive and easy to navigate, prioritizing ease of use over ease of learning (Usability). While the app is Android-only, it is compatible with most modern Android devices that support ARCore (Compatibility). The app is expected to have bug-free map usage, stable AR activation, and basic offline functionality via a local database (Reliability). AR content dynamically adjusts to varying lighting and device performance conditions (Adaptability). Database operations are kept independent of core functions, and the codebase is structured to allow features to be added without breaking existing ones (Maintainability). Battery and memory usage are optimized, and AR content is kept lightweight (Performance). Content is accurate and immersive to encourage knowledge retention (Educational Value). The trivia system and dual leaderboards are designed to motivate continued engagement (Gamification).

---

## Limitations

- All 3D models are static. Animations, audio narrations, and video are not supported in the current release.
- Coverage is limited to the province of Albay. Other Bicol provinces (Cam Sur, Cam Norte, Sorsogon, Masbate, Catanduanes) are not included.
- Location-based AR and image recognition are not implemented.
- The current release is limited to 4 cultural sites. Adding more requires additional 3D asset production and optimization.
- Gameplay is limited to trivia questions and basic area unlocking. Complex mechanics such as a playable character are not included.
- An active internet connection is required to sync user data and prevent exploitation of the currency/rewards system.
- The Forgot Password feature is not available in the current version.
- Email verification is not enforced at registration. Accounts can be created using non-existent but validly formatted email addresses.
- The application is exclusive to Android. iOS is not supported.

---

## Test Coverage Summary

The following feature areas have documented test cases:

**Interactive Map** (TC-IM-001 to TC-IM-006) covers map loading, pin display, pin tap interaction, zoom, pan, and behavior on unsupported OS versions.

**AR Model Display** (TC-SIMD-001 to TC-SIMD-004) covers model display on supported devices, error handling on unsupported devices, model interaction, and site information display.

**Quiz System** (TC-KSQ-001 to TC-KSQ-009) covers quiz initialization in both locked and unlocked states, behavior without internet, answer selection, timer countdown, timeout handling, multi-tap prevention, quiz completion, and score calculation.

**Login** (TC-LOGIN-001 to TC-LOGIN-005) covers valid login, incorrect password, unregistered email, empty field handling, and the absence of a forgot password feature.

**Registration** (TC-SIGN-001 to TC-SIGN-005) covers valid registration, duplicate email rejection, invalid email format, minimum password length enforcement, and the behavior of valid-format but non-existent emails.

**Profile Editing** (TC-PE-001 to TC-PE-009) covers screen navigation, invalid text input, dropdown validation, duplicate email rejection, invalid email format, password confirmation mismatch, saving changes, cancelling changes, and partial updates with blank field retention.

**Non-Functional** test cases cover cloud storage migration, AR performance limits (RAM, model size, render time), map load and interaction speed, and overall UI responsiveness.

Test data includes valid, invalid, and out-of-boundary values for each feature area.

---

## Security and Compliance

- **Philippine Privacy Act of 2012 (RA 10173)**: All personal data is encrypted in transit and at rest. Access to data is logged for auditability.
- **Intellectual Property Code of the Philippines (RA 8293)**: Open-source assets are credited. Proprietary content is not used without authorization.
- **Role-based access control**: Two roles are defined.
  - *Viewer*: Can access AR models and the interactive map.
  - *Administrator*: Has full CRUD access to the database and manages content, bugs, and updates.
- **GDPR compliance** is listed as a functional requirement (REQ-LS-1) for the login system.

---

## Stakeholders

The **BARABad Development Team** is the primary internal stakeholder, responsible for designing and developing the application. **Prof. Jennifer L. Llovido** of Bicol University serves as the academic supervisor and project evaluator for CS 117, also classified as primary internal. **End users** — tourists, students, and locals — are the primary external stakeholders and the intended audience of the application. The **Tourism Department of Albay (DOT Albay)** is a secondary external stakeholder and a potential partner for heritage site validation and endorsement. **Local communities in Albay** are secondary external stakeholders who may benefit indirectly through increased tourism and visibility.

---

## References

Roldan, S. J. (2023). *Adaptability to digital tourism by the travel and tour operators in Albay*. International Journal of Education, Business and Economics Research. https://ijeber.com/adaptability-to-digital-tourism-by-the-travel-and-tour-operators-in-albay/

---

## Team

**BARABad Development Team**
Bicol University — College of Science
CS 117: Software Engineering | BS Computer Science 3B

- Abion, Gerik Jed L.
- Acuña, Angelo James M.
- Balingbing, Raymond
- Balquin, John Jacob C.
- Rivera, Elyzel Jade P.

Academic Supervisor: Prof. Jennifer L. Llovido

---

*This README was written from the project's Software Requirements Specification document.*
