# android.github.shopify

A demostration app with a single recycler view, displays public repositories for the Shopify organisation using the Github Developer API.

[![android.github.shopify.intro](http://img.youtube.com/vi/Hhfyuh0fflE/0.jpg)](https://youtu.be/Hhfyuh0fflE "android.github.shopify.intro")

The recycler view displays the following information:

* Name
* If it is a fork or not (Y/N)
* Number of stargazers
* Time since it was created
* An interaction on the row which opens the user's browser to the repository's page

Additional goals:

* Solve the problem using Android (Kotlin preferred)
* Retrofit
* Back the recycler view with some type of cache or persistence layer
* Architecture pattern

# Architecture Pattern

The architectural pattern chosen was Model-View-Presenter (MVP) as a balance between existing knowledge and speed to a minimum viable product.

# Libraries

3rd party libraries:

* okhttp
* retrofit
* joda-time

Google:

* gson
* recyclerview
* room

# Resources

* Shopify on GitHub <https://github.com/Shopify>
* GitHub Developer <https://developer.github.com>
* Shopify Repositories via GitHub Developer API <https://api.github.com/orgs/shopify/repos>
* Postman <https://www.getpostman.com/>
    * See `.\docs\postman.request.json` and `.\docs\postman.response.json`
* https://github.com/googlesamples/android-architecture
* https://github.com/googlesamples/android-sunflower
* https://github.com/chrisbanes/cheesesquare
* https://github.com/futurice/android-best-practices
* https://github.com/xxv/android-lifecycle
* https://blog.github.com/2018-06-29-GIF-that-keeps-on-GIFing/
