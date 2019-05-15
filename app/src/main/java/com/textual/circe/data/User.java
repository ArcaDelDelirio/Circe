package com.textual.circe.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//This is the Data Model for the FirebaseLoginActivity

@Entity(tableName = "user_table")
public class User {

    //Encapsulate data of class
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "user")
    private String mUser;
    @NonNull
    @ColumnInfo(name = "password")
    private String mPassword;

    //Setters and Getters methods
    //Setters

    public User(@NonNull String mUser,@NonNull String mPassword) {
        this.mUser = mUser;
        this.mPassword = mPassword;
    }
    //Getters
    @NonNull
    public String getPassword() {
        return this.mPassword;
    }

    @NonNull
    public String getUser() {
        return this.mUser;
    }




}
