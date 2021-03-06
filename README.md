<h1 align="center">Taste Me</h1>

### Creativity
  TasteMe offers an innovative way to get familiar with and enjoy the cusine from every region around the world.
  The users are given the opportunity to choose traditional meals directly from the map and check their authentic recipes.

### Value to the end user
  Users can save their favourite recipes in a local db and also to keep a shopping list.

### Use custom Views

### Use animations
 - Progress ring 
 - Adding rows

### Device APIs
  1. Connection API 
    -  Checks for internet connection. Notificate user to enable it when it is need.
  2. Google Maps API / Geolation API (to add image)
    - Users can search for recipes worldwide. When they open the map at first they see markers in  different colors:
      - Blue: Cold dishes
      - Red: Hold dishes
      - Green: Vegetarian dishes
      - Rose: Sweet dishes
    - They can click on marker and then an info window appears with image of the dishes, name and location (country/city/region)
    - On clicked info window the recipe is opened with more details
<p align="center"><a href="https://github.com/Android-Mobile-Applications/Taste-Me"><img src="https://raw.githubusercontent.com/Android-Mobile-Applications/Taste-Me/master/imgs/Screenshot_2016-01-18-09-43-03.png" /></a></p>
 
### Application Storage (SQLite)
  1. Saving all favourites recipe to SQLite
  2. Saving shopping list ingredients

### Remote data
  1. Parse.com
    - Used for sign up and sign in of each user
    - Saving recipes and ingredients (used relational data)

### Background jobs
  1. Implemented reminder with notification
    - Reminds to user to check the app and to try something new today from the recipes
    - Reminds to user to eat (with a lot of work sometimes it is hard :))
    - Interval - 24 hours

### Touch friendly UI
  1. Visually appealing colours have been used (white and orange)

### Notifications
  1. Used notification to reminds user
  2. Error notication when there isn't
    - Internet connection
    - Invalid username and password combination
    - Password and Confirm password doesn't match
    - Invalid username length on (sign up)
  3. Success notication on:
    - Successful sign up
    - Successful sign in
    - Added a new recipe to favourites

### Gestures
  1. Swipe
    - Used on favourites to swipe images
  2. Fling
    - Used on home screen
    - Used on favourites list
  3. Touch 
    - In every part of the application
  4. Long Press 
    - Zoom in a picture

### High-quality code and reusability
  1. The application code is separated into consistent and cohesive modules. 
    - auth
    - common
    - data
    - fragments
    - maps
    - services

### Validation and Error handling
  1. Validation of user input
    - On sign up
    - On sign in
    - On adding new product to shopping cart
  2. Network issues
    - If internet connection is disabled - message is shown
  3. Handling access denials
    - Show meesage when an error from Parse.com occurred 
