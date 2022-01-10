# Top Reddits Reader

This reader was implemented using Kotlin, MVVM, Navigation with safe args, Koin, Room, Retrofit, Coroutines, Flow, and Coil.

The main page consists of a grid with the thumbnail of the top Reddits, which loads more Reddits when reaching the end of the list, and with pull to refresh.
The Reddits that are images are marked with a camera icon and you can click on them to see the details page.
It displays the title, image, votes, number of comments, author, and a button to open the Reddit on the browser.

The data is retrieved by a repository which shows first the cached Reddits and then gets the latest ones from the api, so if you have no internet you can still see the locally saved ones.
Internally there are entities for the dabatase, the network response, and the one used by the views, with methods for conversions, as to simplify and format the data.
The responses are encapsulated in a Resource class which reflects the state (loading, success, or error).

There are automated tests for the Fragments, ViewModels, and Database, implemented using Junit, Espresso, and Mockk.

Due to time constraints, everything was done on the main branch, which is definitely not the best practice.
