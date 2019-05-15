package com.textual.circe;

import android.os.Bundle;

import androidx.annotation.NonNull;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.textual.circe.Fragments.LoginFragment;
import com.textual.circe.data.User;
import com.textual.circe.data.UserViewModel;

import java.util.List;

public class FirebaseLoginActivity extends AppCompatActivity implements LoginFragment.OnFragmentInteractionListener {

    //Components members variables
    private FirebaseAuth mAuth;
    private NavController navController;
    private UserViewModel mUserViewModel;
    //Debugs members variables
    private static final String TAG = "EmailPassword";
    private static final int REQUEST_READ_CONTACTS = 0;
    private static final String TAG2 = "FireBaseLoginActivity";

    private TextView mStatusTextView;
    private TextView mDetailTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_activity);
        ActionBar mActionBar;
        mActionBar = getSupportActionBar();
        if(mActionBar != null)
            getSupportActionBar().hide();
        // Set up the login form.

        //Get the ViewModelProvider
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        //Create an observer for the LiveData
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                //TODO:Update the cached copy of users in the adapter
            }
        });
        //Get the Navigation Controller
        navController = Navigation.findNavController(findViewById(R.id.my_nav_host_fragment));
        mAuth = FirebaseAuth.getInstance();
        navController.navigateUp();

    }
    /*TODO:Get the Login Fragment and get/set data to UserViewModel an synchronize with NAvHostFragment and the MainActivity
    Remember, one ViewModel for two Activities, a ViewModel for user, autocomplete and check password and also for the rest
    of the fragments data needs
     */



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
/*        if(currentUser != null){
            updateUI(currentUser,findViewById(R.id.login_nav_host_fragment));
        }else{
            signIn(mEmailField.getText().toString(),mPasswordField.getText().toString(),findViewById(R.id.login_nav_host_fragment);
        }*/
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        Log.e(TAG2,"Fragment: "+fragment.getTag());
        //TODO : Change the fragment method for a navigation fragment host method
        //If get a NavHostFragment instance, Could we setInteractionListener ?
        if(fragment instanceof LoginFragment){
            LoginFragment loginFragment = (LoginFragment) fragment;
            loginFragment.setInteractionListener(this);
        }
    }

    //Implements the interface funtion
    public void signInPetition(String email,String password){
        Log.e(TAG2,email+password);
        signIn(email,password,this.findViewById(R.id.loginFragment));
    }

    private void updateUI(FirebaseUser currentUser,View view) {
        if(currentUser != null){
            Log.i(TAG,currentUser.toString());
            Navigation.findNavController(view).navigate(R.id.main_navigation);
        }
    }



    private void signIn(String email, final String password, final View view){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,view);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null,view);
                        }

                        // ...
                    }
                });
    }

    //TODO : Find the manner of get Private fields of View LoginFragment
/*    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        String password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }*/
    private void createAccount(String email, String password){
        Log.d(TAG, "createAccount:" + email);
/*        if (!validateForm()) {
            return;
        }*/

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,findViewById(R.id.my_nav_host_fragment));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(FirebaseLoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null,findViewById(R.id.my_nav_host_fragment));
                        }

                        // ...
                    }
                });
    }

}
