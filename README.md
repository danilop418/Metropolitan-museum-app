# Metropolitan Museum App

Is an Android application developed with **Kotlin** and **Android Studio**, designed to provide users with detailed information about works of art from the Metropolitan Museum. The app follows a **clean MVVM architecture** and is structured for scalability and maintainability.

The project currently includes:

- **Fetching and displaying art objects** using a remote API.
- **Separation of concerns** with `data`, `domain`, and `presentation` layers.

## Project Structure

```text
│       src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/.../features/museum/
│       │   │   ├── data/
│       │   │   │   ├── MuseumDataRepository.kt
│       │   │   │   └── remote/
│       │   │   │       ├── MuseumApiClient.kt
│       │   │   │       ├── MuseumApiRemoteDataSource.kt
│       │   │   │       ├── MuseumApiService.kt
│       │   │   │       └── ObjectsIdResponse.kt
│       │   │   ├── domain/
│       │   │   │   ├── ErrorApp.kt
│       │   │   │   ├── MuseumFetchUseCase.kt
│       │   │   │   ├── MuseumRepository.kt
│       │   │   │   └── WorkOfArt.kt
│       │   │   └── presentation/
│       │   │       ├── MuseumActivity.kt
│       │   │       └── MuseumViewModel.kt
│       │   └── res/
│       │       ├── drawable/
│       │       ├── layout/
│       │       ├── mipmap-*/
│       │       └── values/
│       └── test/
│           
└── gradle
