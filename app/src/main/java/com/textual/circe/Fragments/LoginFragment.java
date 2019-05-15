package com.textual.circe.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import com.google.android.material.textfield.TextInputEditText;
import com.textual.circe.FirebaseLoginActivity;
import com.textual.circe.R;


public class LoginFragment extends Fragment{

    private AutoCompleteTextView mEmailField;
    private TextInputEditText mPasswordField;
    private OnFragmentInteractionListener mCallback;
    private static final String TAG = "LoginFragment";


    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mEmailField = (AutoCompleteTextView) view.findViewById(R.id.email);

        mPasswordField = (TextInputEditText) view.findViewById(R.id.password);

        Button mEmailSignInButton = (Button) view.findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailField.getText().toString();
                String passwd = mPasswordField.getText().toString();
                Log.e(TAG,email + passwd);
                mCallback.signInPetition(email,passwd);
            }
        });
    }

    //This method provides a simple interface to allow communicate the fragment with the activity

    public void setInteractionListener(OnFragmentInteractionListener callback){
        this.mCallback = callback;
    }
    public interface OnFragmentInteractionListener {
        void signInPetition(String email,String password);
    }
}
