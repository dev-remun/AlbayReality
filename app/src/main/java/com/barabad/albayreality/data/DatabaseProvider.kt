package com.barabad.albayreality.data

/*
* This is the actual object of the Database.
* This will serve the global database for the app.
* You can call this object anywhere in the app.
* To call, use this syntax: DatabaseProvider.database.<functionalities>
* To know the functionalities and attributes of this database, please refer to the Database class.
*
* This database is populated in the MainActivity since that is the entry point of the app. Refer to the MainActivity.
*
* */

object DatabaseProvider {
    val database = Database()
}
