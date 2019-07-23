# DTI - Code challenge Android

#### Stack

- Kotlin
- RxKotlin 
- Retrofit
- OkHttp
- Realm database

#### Patterns

- MVVM
- Repository


### Setup project

 1 - Clone this repository

 2 - Open in Android studio

 3 -  If you are running android N and above, change the computer's IP in the `network_security.xml`. 

The file is located in: `Network module > src > main > res > xml > network_security.xml`

4 - Find the API Constants file and replace the api url `internal const val BASE_URL = "http://192.168.25.7:8080"` to your server matching IP.
The file is located in: 

`Network module > src > main > java > com.therealdroid.github.network.config > Constants.kt`

5 - Run project.


### Credentials
You can find the credemtials in the server [documentation](https://github.com/therealandroid/dti-server/blob/master/README.md#login-credentials)

### Project Decisions

**Activities replication instead reusable fragments**
	
It is a fact that login and registration are very similar in terms of functionalities, form fields, and server responses. I could make reusable fragments, repository and datasources but I choose to create another activity for it (register and login). The reason is because in a real situation it will not be similar and going deeper it will have different logic etc.

**Rx instead LiveData plus Coroutine.**
	
I’ve followed the same guideline LiveData uses with ViewModel, but I’m more familiar using Rx with MVVM. 

**Realm database instead Room database.**

 The reason I choose realm is because I’m not familiar with room and because I did not used google components architecture as well. Realm is used in this project to persist the logged in user.

**MVVM**

- Reusable
- Centralize application business logic

#### To improve 

- My tests are not working and I didn't had enough time to figure out the problem.  This [challenge](https://github.com/therealandroid/SkyScannerTest/tree/master/app/src/test/java/br/com/skyscannerapplication) have tests running.

T_T
