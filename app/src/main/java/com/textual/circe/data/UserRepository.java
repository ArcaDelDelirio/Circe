package com.textual.circe.data;


/*Depends on:
-UserDao interface
 */
/*This class abstract access to multiple data sources.
Manage query threads and allow to use multiple backends.
 */

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    //Default constructor get The Room database and fetch the data
    UserRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application);
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsers();
    }
    //Wrap the Observer
    LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }
    //Use an AsyncTask for insert the Data, for possibles scalation with network task
    public void insert(User user) {
        new insertAsyncTask(mUserDao).execute(user);
    }

    //TODO: Create an insert Task for password too

    private static class insertAsyncTask extends AsyncTask<User,Void,Void> {
        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao mUserDao) {
            mAsyncTaskDao = mUserDao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            mAsyncTaskDao.insert(users[0]);
            return null;
        }
    }
}
