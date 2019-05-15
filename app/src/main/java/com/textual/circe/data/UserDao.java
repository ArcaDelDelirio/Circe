package com.textual.circe.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/* Depends on:
-User Data Model
-Possible SQLite Database, in this case we create the SQLite with Room Database Object
 */

@Dao
public interface UserDao {

    //Here declare the CRUD operation for the Database
    //TODO: Declare and implement the Update method, but it's possible that the LiveData object have an implementation for that ... ??
    //Create
    @Insert
    void insert(User mUser);

    //Show the users
    @Query("SELECT * from user_table ORDER BY user DESC")
    LiveData<List<User>> getAllUsers();
    //Delete
    @Query("DELETE FROM user_table")
    void deleteAll();



}
