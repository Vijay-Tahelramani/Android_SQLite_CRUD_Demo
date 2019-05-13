# Android_SQLite_CRUD_Demo
This android application tutorial performs basic database operations with appropriate validation using SQLITE database.

In android, we have a class ```SQLiteOpenHelper``` and ```SQLiteDatabase``` using which we can create database in our application.
To do that, I have created a class named ```DatabaseHandler```. It extends ```SQLiteOpenHelper``` class. when we extend any class we also need to implement its methods.
Here we have two methods.
1. ```onCreate()```
2. ```onUpgrade()```

Here in ```onCreate()``` method, perform the create table query and when you change something in database like table has been changed then ```onUpgrade()``` method is called.

Here we also need a constructer of class ```DatabaseHandler```. So when we create object of this class and call the method of database class it will automatically create the database file in your application.
See below code:
```
mDatabase = new DatabaseHandler();
mDatabase.getReadableDatabase();
```
Here ```mDatabase``` is the object name of my ```DatabaseHandler``` class.<br/>

See the code of <a href="https://github.com/Vijay-Tahelramani/Android_SQLite_CRUD_Demo/blob/master/SQLITE_CRUD/app/src/main/java/com/example/sqlite_crud/DatabaseHandler.java">```DatabaseHandler.java```</a> class.

Below methods have created for the CRUD operation.
1. ```addUser()``` for insertion.<br/>
2. ```deleteUser()``` for deletion.<br/>
3. ```updateUserData()``` for updating records.<br/>
4. ```getAllUsers()``` for retrieving all the records from Table.<br/>
5. ```getSpecificUser()``` for retrieving the data of specific user.<br/>
6. ```checkUserExist()``` - for checking that the user already exist or not.<br/>

When we store or retrieve data we need to store information somewhere, here I have created a class ```Users```. Using Constructor, getter and setter methods I have stored data into database and retrieved data in the form of List.

Below 4 class shows the code for performing CRUD operations.
1. <a href="https://github.com/Vijay-Tahelramani/Android_SQLite_CRUD_Demo/blob/master/SQLITE_CRUD/app/src/main/java/com/example/sqlite_crud/InsertFragment.java">```InsertFragment.java```</a><br/>
2. <a href="https://github.com/Vijay-Tahelramani/Android_SQLite_CRUD_Demo/blob/master/SQLITE_CRUD/app/src/main/java/com/example/sqlite_crud/DeleteFragment.java">```DeleteFragment.java```</a><br/>
3. <a href="https://github.com/Vijay-Tahelramani/Android_SQLite_CRUD_Demo/blob/master/SQLITE_CRUD/app/src/main/java/com/example/sqlite_crud/UpdateFragment.java">```UpdateFragment.java```</a><br/>
4. <a href="https://github.com/Vijay-Tahelramani/Android_SQLite_CRUD_Demo/blob/master/SQLITE_CRUD/app/src/main/java/com/example/sqlite_crud/seeDataFragment.java">```seeDataFragment.java```</a><br/>

Screen Preview:
