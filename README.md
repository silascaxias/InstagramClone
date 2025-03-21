# Instagram Clone

This is an Instagram clone project, built using Jetpack Compose, Koin, Navigation Compose, and other modern Android development technologies. The goal is to replicate the interface and some functionalities of the Instagram application, serving as a study and portfolio project.

## Implemented Screens

This project already has the following screens implemented:

-   **Home Screen (Feed):**
    -   Displays posts in a feed format.
    -   Each post contains an image, the author's username, caption, and comments.
    -   Ability to like and comment on a post.
    -   Displays Stories at the top.
-   **Login Screen:**
    -   Fields for entering username and password.
    -   Login button.
    -   Option to create a new account if the user doesn't have one.
-   **Register Screen:**
    -   Form for registering a new user.
    -   Fields for user information input.
    -   Register button.
-   **Profile Screen**
    -   Display profile photo.
    -   Display user's Posts.
    -   Possibility to edit profile.
    -   View of the number of posts, followers, and following.

## Main Dependencies

The project utilizes the following libraries and tools:

-   **Jetpack Compose:**
    -   `androidx.compose.ui:ui`
    -   `androidx.compose.material3:material3`
    -   `androidx.compose.ui:ui-tooling-preview`
    -   `androidx.compose.runtime:runtime-livedata`
    -   `androidx.activity:activity-compose`
    -   `androidx.compose.ui:ui-tooling`
    -   To build the user interface in a declarative and reactive way.

-   **Navigation Compose:**
    -   `androidx.navigation:navigation-compose`
    -   For navigation between different screens of the application.

-   **Koin (Dependency Injection):**
    -   `implementation libs.koin.androidx.compose`
    -   For dependency injection, facilitating code organization and testing.

-   **Coil (Image Loading):**
    -   `io.coil-kt:coil-compose`
    -   For efficient image loading.

-   **Room:**
    - `androidx.room:room-runtime`
    - `androidx.room:room-compiler`
    - `androidx.room:room-ktx`
      
-   **Accompanist System UI Controller**:
    -   `"io.insert-koin:koin-androidx-compose`
    -   Responsible for changing the status bar color.

-   **Other Dependencies:**
    -   `androidx.core:core-ktx`
    -   `androidx.lifecycle:lifecycle-runtime-ktx`
    -   `junit:junit`
    -   `androidx.test.ext:junit`
    -   `androidx.test.espresso:espresso-core`
 
## Project Structure (MVVM)

```bash
│── data/                # Data layer
│   ├── local/           # Local database (Room)
│   │   ├── dao/         # DAOs (Data Access Objects)
│   │   ├── AppDatabase.kt
│   ├── repository/      # Repositories (interface between data sources and ViewModel)
│   ├── model/           # Data models (DTOs or structures separate from Room entities)
│
│── di/                  # Dependency Injection (Koin)
│   ├── AppModule.kt     # Koin modules (ViewModels, Repositories, etc.)
│
│── ui/                  # UI layer
│   ├── components/      # Reusable Jetpack Compose components
│   ├── screens/         # Application screens
│   │   ├── home/        # Example screen
│   │   ├── login/       # Another screen
│   │   ├── profile/
│   ├── navigation/      # Jetpack Navigation Compose configuration
│
│── viewmodel/           # ViewModels (intermediary between UI and data)
│
│── utils/               # Utility functions and extensions
│── MainActivity.kt
│── MyApp.kt             # Root Composable of the application
```

## Setup

1.  **Clone the repository:**

2.  **Open the project in Android Studio.**

3.  **Sync the project with the Gradle files.**

4.  **Set up Firebase:**
    -   Create a Firebase project in the Firebase console ([https://console.firebase.google.com/](https://console.firebase.google.com/)).
    -   Add an Android app to your Firebase project.
    -   Download the `google-services.json` file and place it in the `app` directory of your Android project.
    -   Enable Firebase Authentication (e.g., Email/Password) in the Firebase console.
    -   Enable Firebase Storage and Firestore in the Firebase console.

5.  **Build and run the project on an emulator or physical device.**

## Future Implementations

-   **Implementation of other instagram screens:** Explore, stories, Direct, etc.
-   **Integration with a real backend:** for real requests.
-   **Data persistence:** Storage of information and user authentication.
-   **Implementation of unit tests:** To ensure the stability of the application.
-   **UI Improvement:** Refine the user interface design.

## Contribution

Contributions are welcome! Feel free to open issues and pull requests.

## License

This project is licensed under the [License Name, e.g., MIT License] - see the [LICENSE.md](LICENSE.md) file for details.
