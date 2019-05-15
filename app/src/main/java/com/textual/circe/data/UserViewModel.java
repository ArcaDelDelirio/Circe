package com.textual.circe.data;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

//TODO: Reference to later question
import com.textual.circe.data.User;
import com.textual.circe.data.UserRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private UserRepository mRepository;
    private LiveData<List<User>> mAllUsers;

    public UserViewModel(Application application){
        super(application);
        //TODO: Can't access the UserRepository class outside a package... Why?
        mRepository = new UserRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers(){return mAllUsers;}

    public void insert(User user){mRepository.insert(user);}
}
