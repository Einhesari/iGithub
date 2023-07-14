# iTollHub
## Overview
iTollHub is a simple app implemented for iToll Co. technical assignments. It uses GitHub rest API to search for users and get their profiles detail.

## Build

The straightforward way to build the project is to:

- Install Android Studio or its commandline tools
- Run the project by the AS or `./gradlew assembleDebug` to get a debug `.apk`

 ## Preview
 
 You can access to latest `.apk` of each PR or commit on `main` branch:

- Go to [actions tab](https://github.com/Einhesari/iTollHub/actions).
- Open the suited workflow-run that has been completed
- Download `release-apk` in the artifacts section

 ## Technical Overview

1. Project tends to use cutting-edge technologies that might be experimental in the Android
   ecosystem (such as `version catalog` and `jetpack compose`).
   
2. This project also tends to represent the following skills: Git, Git flow, automation, code style
4. The Project combines Google's recommended architecture and `Clean` arch in a `modular` way.
5. `data` module contains `domain` repositories but domain models have moved into `model` module.
6. `core` module that contains application core logic is not dependent on any other module.
7. `network` module contains the app's online data source which is powered by [Github API v3](https://developer.github.com/v3/). It's the app's only data source.
8. `feature` module that is separated into `search` and `details` features hosts the presentation layer of the architecture and it's based on  the `UDF` pattern. the UI is implemented using `jetpack compose`.

## Further Developments
1. Write UI, Unit, and Integration tests
2. Implement some linting tools like `detekt`
