# Arrow for GitHub

[![CircleCI](https://circleci.com/gh/adam-lincoln/android.arrowforgithub/tree/develop.svg?style=svg)](https://circleci.com/gh/adam-lincoln/android.arrowforgithub/tree/develop)
[![CircleCI](https://circleci.com/gh/adam-lincoln/android.arrowforgithub/tree/develop.svg?style=shield)](https://circleci.com/gh/adam-lincoln/android.arrowforgithub/tree/develop)
[![codecov](https://codecov.io/gh/adam-lincoln/android.arrowforgithub/branch/develop/graph/badge.svg)](https://codecov.io/gh/adam-lincoln/android.arrowforgithub)

A demostration app with a recycler view, displaying public repositories for the Shopify organisation using the Github Developer API.

![](intro.gif)

The recycler view displays the following information:

* Name
* If it is a fork or not (Y/N)
* Number of stargazers
* Time since it was created
* An interaction on the row which opens the user's browser to the repository's page

Goals:

* Solve the problem using Android (Kotlin preferred)
* Retrofit
* Back the recycler view with some type of cache or persistence layer
* Use an architecture pattern

# Architecture Pattern

The architectural pattern chosen was Model-View-Presenter (MVP) as a balance between separation of concerns, existing knowledge and speed to a minimum viable product.

User takes action -> View talks to Presenter -> Presenter talks to Data -> Presenter tells View what to do.

# Libraries

3rd party libraries:

* [okhttp3](http://square.github.io/okhttp)
    * An HTTP & HTTP/2 client for Android and Java applications.
* [retrofit](https://square.github.io/retrofit)
    * Retrofit turns your HTTP API into a Java interface.
* [joda-time](https://github.com/JodaOrg/joda-time)
    * Joda-Time provides a quality replacement for the Java date and time classes.
    * https://www.joda.org/joda-time

Google:

* [gson](https://github.com/google/gson)
    * A Java serialization/deserialization library to convert Java Objects into JSON and back
* [recyclerview](https://developer.android.com/guide/topics/ui/layout/recyclerview)
* [room](https://developer.android.com/topic/libraries/architecture/room)
* [Dagger 2](https://github.com/google/dagger)

# Done

* Choose any colour via a Settings menu, store in sharedprefs
* [Stetho](http://facebook.github.io/stetho)
    * A debug bridge for Android applications
    * chrome://inspect/#devices
* [Timber](https://github.com/JakeWharton/timber)
    * A logger with a small, extensible API which provides utility on top of Android's normal Log class.
* [Calligraphy](https://github.com/InflationX/Calligraphy)
    * Custom fonts in Android the easy way.
* [LeakCanary](https://github.com/square/leakcanary)
    * A memory leak detection library for Android and Java.

# To Do

* RecyclerView - sticky headers - a through z - requires a sortable / filterable repository
* Filter - who doesn't love a magnifying glass in their toolbar?
* Sort - Sort by fields displayed
* Navigation Drawer
* [Kotlin – Sort List of Objects with Comparator Example](http://kotlination.com/kotlin/kotlin-sort-list-of-objects-with-comparator-example)
* [Android - RecyclerView - Sticky Header](https://gist.github.com/saber-solooki/edeb57be63d2a60ef551676067c66c71)
* [Searchable-RecyclerView-Demo](https://github.com/Wrdlbrnft/Searchable-RecyclerView-Demo)
* Dagger - at the moment injection is handled by an object that can be override for unit tests via product flavours
* Add a script that clicks through the app and records a video, stops and saves it to file.
* Implement loading / empty with retry / no network with retry
* List items - chips are clickable?  Setting foreground=transparent / clickabled = false / focusable = false didn't help.
* Rx - too much work is being done on the UI thread leading to some UI jank on the emulator when loading items
* Clean - implement use cases and separate layers into data / domain / presentation
* Unit tests
* Developed for Pixel 2 API 27 - test for more devices / screen sizes / API levels
* Tablet mode
* Configuration changes (orientation)
* Review Lint report
* Provide support for signing keys
* Event bus for highlighting source of data with a snackbar - `Loaded from <source> | OK`
* Separate memory / disk (room) / web layers with a status indicator of where the data is and it's size
* Add thread executor for background tasks like mapping - might be solved with Rx
* Free vs Paid - ad supported
* Palette to extract colour from the Orgs icon?
* Coordinator layout
* Share sheet with text, email, QR Code and Nearby API support
* Authentication via Login with Google / Facebook - Share with "my devices"
* Dependant API calls
    * https://api.github.com/orgs/shopify/repos
    * For each repo where fork: true -> https://api.github.com/repos/Shopify/delayed_job and return parent.full_name: "tobi/delayed_job"
* To Stateful Presenter or not
* [pidcat](https://github.com/JakeWharton/pidcat)
    * on Windows? bash, Windows Subsystem for Linux, Python - yay!
    * C:\Users\User\AppData\Local\Android\Sdk\platform-tools
    * C:\git\pidcat
    * user / user
    * pidcat.py adamlincoln.android.arrowforgithub
    * echo $PATH
    * export PATH=$PATH:/mnt/c/Users/User/AppData/Local/Android/Sdk/platform-tools
    * export PATH=$PATH:/mnt/c/git/pidcat
* Dashboard with Cast API
* Smart watch ... listen for incoming connections / shares and open app on phone?  Small dashboard with notifications?
* Raspi Tv dashboard to monitor app dev / build / agile / deployments / installs
* [Palette API](https://developer.android.com/training/material/palette-colors)
* [Debounce](https://stackoverflow.com/questions/16534369/avoid-button-multiple-rapid-clicks)
* [CircleCI Unit test meta data](https://circleci.com/docs/2.0/collect-test-data/?mkt_tok=eyJpIjoiTUdKaE1EZzBNalUwWVROaCIsInQiOiJmS3dOSmd0S2QrVkpVbFlQbGdjb2MzV2tTaEg0U1FiTGxCNHhDNFR4OGlLbklYRnF0T3NtNEg1b1FaYVVcL2RyRWExZkZPOE5OelN5amx4UkpEbDBzNVZFUGo4NUlFY1FybXJjVmpHUzdteHY5UUVNWWNOdEN1RnFEMnk0bW83bXIifQ%3D%3D#gradle-junit-test-results)
* [spotless](https://github.com/diffplug/spotless)
* [MPAndroidChart](https://github.com/PhilJay/MPAndroidChart)
* [Konfetti](https://github.com/DanielMartinus/Konfetti)
* [iosched](https://github.com/google/iosched)
* [Indicator Fast Scroll](https://github.com/reddit/IndicatorFastScroll)

# Resources

* [Shopify on GitHub](https://github.com/Shopify)
* [GitHub Developer](https://developer.github.com)
* [Shopify Repositories via GitHub Developer API](https://api.github.com/orgs/shopify/repos)
* [Postman](https://www.getpostman.com/)
* [Android Jetpack](https://developer.android.com/jetpack/)
* [Android Jetpack - Guide to app architecture](https://developer.android.com/jetpack/docs/guide)
* [Android Architecture Blueprints](https://github.com/googlesamples/android-architecture)
* [Android Sunflower](https://github.com/googlesamples/android-sunflower)
* [Cheesesquare Sample](https://github.com/chrisbanes/cheesesquare)
* [Best practices in Android development](https://github.com/futurice/android-best-practices)
* [Complete Android Fragment & Activity Lifecycle](https://github.com/xxv/android-lifecycle)
* [Material Design - The development documentation](https://materialdoc.com/)
* [Kotlin style guide](https://developer.android.com/kotlin/style-guide)
* [apiwave](http://apiwave.com/java/api/junit.framework.Assert)
* [Build local unit tests](https://developer.android.com/training/testing/unit-testing/local-unit-tests)
* [Google Unsplash](https://github.com/googlesamples/android-unsplash)
* [nickbutcher/plaid](https://github.com/nickbutcher/plaid)
* [Star Wars API](https://swapi.co/)
* [CodePath](https://guides.codepath.com/android)
