# Welcome!

This repository contains the code that was shown in the intro presentation, as well as the samples we have worked
through in the hands-on session. Feel free to look up anything you found interesting during the talk, and if you have
additional questions, don't hesitate to contact me!

### Contents of this Repository

Here's a quick guide to the different files and folders in this repository

- `/gradle` folder: In here you will find the gradle wrapper jar as well as `gradle-wrapper.properties`. This file is
  very important, as it is the location where you can define the gradle version.
- `/src` folder: This is where you find all the code that was shown in the presentation and the hands-on session.
    - `/main/java` folder: In the intro presentation, a comparison between Java and Kotlin was demonstrated. The
      corresponding Java code can be found in here.
    - `/main/kotlin` folder: This is the source root for any Kotlin code in this repository.
        - `com.rtarita.presentation` package: In here, you will find all the code that was shown in the intro
          presentation, including the Ktor samples.
        - `com.rtarita.samples` package: All the code that we went through in the hands-on session can be found in here.
          The samples are sorted by their file name, `s00`,`s01`, etc.
    - `/main/resources` folder: The `logback.xml` config file is located here, it is necessary to configure logging for
      the Ktor samples.
- `.gitignore` file: Gradle and IntelliJ generate some files and folders that should not be pushed to VCS (under normal
  circumstances). Have a look at the file contents for more info.
- `build.gradle.kts` file: This is the main Gradle buildscript. In here you will find the definition of the Kotlin
  version as well as all included libraries and frameworks.
- `gradle.properties` file: Additional configurations for the build system
- `gradlew` file: For machines with `bash` installed (for example, most Linux distros), this is the bash script you
  invoke when you want to run Gradle tasks manually.
- `gradlew.bat` file: This is a batch script that is otherwise equivalent to `gradlew`. Its main purpose is to be
  a `gradlew` surrogate for Windows users.
- `README.md` file: This file.
- `settings.gradle.kts` file: An additional Gradle buildscript for certain settings.