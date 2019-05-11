package com.textual.circe;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;


import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.textual.circe.Fragments.AntologiaFragment;
import com.textual.circe.Fragments.CreateFragment;
import com.textual.circe.Fragments.PerfilFragment;

import android.view.MenuItem;

//Esta es la aplicacion principal
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        BottomNavigationView bottomNav = findViewById(R.id.navigation_bottom);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    Fragment container = getSupportFragmentManager().findFragmentById(R.id.Container);


                    switch (menuItem.getItemId()){
                        case R.id.nav_perfil:
                            selectedFragment = new PerfilFragment();
                            break;
                        case R.id.nav_crear:
                            selectedFragment = new CreateFragment();
                            break;
                        case R.id.nav_antologia:
                            selectedFragment = new AntologiaFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(container.getId(),selectedFragment).commit();
                    return true;
                }
            };

}
