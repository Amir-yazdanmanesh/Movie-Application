# Movie App

This Android project follows Clean Architecture principles for building a movie app. It is structured with modular layers, including data, domain, and presentation.

## Project Structure

The project is organized into the following modules:

- **app**: Android application module.
  - **data**: Data layer with data sources and repositories.
  - **domain**: Domain layer with use cases and domain models.
  - **presentation**: Presentation layer with UI components, ViewModels, and navigation.
  - **di**: Dependency injection module using Dagger Hilt.
  - **common**: Common code or components shared across modules.
   
## Dependencies

- **Hilt**: Dependency injection for Android.
- **Jetpack Navigation (with Safe Args)**: Navigation framework for seamless navigation between destinations.
- **Coroutine Flow**: Leveraging Kotlin Coroutines for asynchronous programming using Flow.
- **Unit Testing**: Ensuring code reliability with unit tests.
- **Mockito**: Mocking framework for unit testing.
- **Retrofit 2 / Okhttp3**: Networking libraries for efficient API communication.
- **Room**: Persistence library for local data storage.
- **View Binding**: Type-safe and efficient view access in Android.
- **LeakCanary**: Detecting memory leaks in the application.
- **Chucker**: An in-app HTTP inspector for monitoring network calls.
