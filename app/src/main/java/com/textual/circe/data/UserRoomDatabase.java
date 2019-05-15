package com.textual.circe.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* Depends on:
-User Data Model
-User Dao interface
 */

//Create an abstract RoomDatabase class because this object communicate by default with the Dao Object
@Database(entities = {User.class},version = 1)
public abstract class UserRoomDatabase extends RoomDatabase {
    //Provide and abstract getter for each Database
    //TODO:Will exist Network User and Cache User Databases
    public abstract UserDao userDao();

    //Make this a Singleton
    private static volatile UserRoomDatabase INSTANCE;
    //Get method
    static UserRoomDatabase getDatabase(final Context context){
        //Check if exist a Instance of Database yet
        if(INSTANCE == null){
            synchronized (UserRoomDatabase.class){
                //If not exist, create a Room Database with the Room.databaseBuilder
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserRoomDatabase.class,"user_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    //Populate Database Sample,
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final UserDao mDao;

        PopulateDbAsync(UserRoomDatabase db) {
            mDao = db.userDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            mDao.deleteAll();
            User user = new User("userdb@populatedatabase.com","userdb");
            mDao.insert(user);
            return null;
        }
    }


}


