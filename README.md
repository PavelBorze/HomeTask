# HomeTask
Home assignment for a job application at Mishloha
The application is built using the Model-View-ViewModel (MVVM) architecture pattern.  

# A brief overview of the architecture:
# Model:
The model layer is represented by the Repository classes. In this case the App
does not have any specific business logic except for the data fetching from the remote server or
local database.
The data is fetched from the remote server or local database using Datasource classes,
ReposPagingLocalDatasource and ReposPagingRemoteDatasource.
# View:
The view layer is represented by the Fragment classes, which is a Fragment. This layer is
responsible for displaying the data received from the ViewModel. It observes the ViewModel to get
the necessary data and updates the UI accordingly.  
# ViewModel:
The ViewModel layer is represented by the HomeViewModel and RepoViewModel classes. The ViewModel is
responsible for preparing and managing the data for the view. It communicates with the model and
exposes streams of data relevant to the view. The ViewModel does not know about the view, it only
exposes the data streams. The ViewModel also survives configuration changes.
# Database: 
The application uses Room database for local data storage. The AppDatabase class is the abstract
layer which provides DAO (Data Access Object) to handle the data operations.  
# View Binding:
The application uses View binding.
# Dependency Injection:
The application uses Dagger Hilt for dependency injection. 
# Coroutines: 
The application uses Kotlin Coroutines for handling asynchronous tasks.  
# Paging: 
The application uses the Paging library to load and display pages of data from larger dataset from
network or local storage.  
# Navigation: 
The application uses the Navigation component to navigate into, and back out from the different 
pieces of content within the app.  

# Reasoning for some of the choices made:
1. Most of the technologies used are an obvious default choice for an Android application.
2. The project is built with Views instead of Composables because the companies projects I decompiled
("com.mishloha.mishapp" and "com.sendiman.runner") were built with Views. I asked Yakir if I should
use Composables or Views But didn't received any answer.
3. The project uses Paging library to load and display pages of data from network or local storage. 
I've never used this library before and thought that it is a good idea to test it here. This proved 
to be a mistake as I found out that it is not possible to filter the data being fetched, in
the adapter, as it is done usually. The filtering should be done in the DataSource, which i did not
implement.
4. I used Glide for image loading. Glide has its own caching mechanism and it is easy to use. Also,
I've noticed that the Companies projects I decompiled previously were using Glide.
5. I didn't use DataBinding because it requires to use KAPT instead of KSP, so I've used ViewBinding
instead.

# Known issues:
1. The data is reloaded every time the user navigates back to the HomeFragment even if the data is
cached as per documentation. It might probably be fixed after gaining a deeper understanding of the
Paging library.
2. The application does not use API key for the GitHub API, which limits the requests to 60 per hour.