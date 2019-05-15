package com.textual.circe.Fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.fragment.app.Fragment;

import androidx.navigation.NavAction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.navigation.NavigationView;
import com.textual.circe.FirebaseLoginActivity;
import com.textual.circe.R;

public class SplashFragment extends Fragment {

    private static final String TAG = "Splash Fragment";

    public SplashFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_layout,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Animation anim =  view.getAnimation();
        if(anim != null){
            if(anim.hasEnded()){
                Toast.makeText(this.getContext(), "Anim :" + anim.toString() + "has ended", Toast.LENGTH_SHORT).show();
            }

        }
        AppCompatImageButton next = view.findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_splashFragment_to_loginFragment);
            }
        });


    }

}
