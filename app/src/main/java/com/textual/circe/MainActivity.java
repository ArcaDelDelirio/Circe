package com.textual.circe;

import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
//Esta es la aplicacion principal
public class MainActivity extends AppCompatActivity {
    //Sobreescritura del constructor por defecto de la clase AppCompatActivity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Llamada a los parametros de diseño
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        //Construccion de la primera pantalla
        mPantalla = (LinearLayout) findViewById(R.id.Pantalla_perfil);
        mPerfil = (CardView) findViewById(R.id.Perfil);
        mAvatar = (ImageView) findViewById(R.id.Avatar);
        mPublicacion = (ScrollView) findViewById(R.id.Publicacion);
        mLista = (LinearLayout) findViewById(R.id.Lista_publicacion);
        melemento0 = (ImageView) findViewById(R.id.Elemento0);
        melemento1 = (ImageView) findViewById(R.id.Elemento1);
        melemento2 = (ImageView) findViewById(R.id.Elemento2);


        //Construccion del menu de navegacion
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    //Datos de la clase
    private TextView mTextMessage;
    private LinearLayout mPantalla;
    private CardView mPerfil;
    private ImageView mAvatar;
    private ScrollView mPublicacion;
    private LinearLayout mLista;
    private ImageView melemento0;
    private ImageView melemento1;
    private ImageView melemento2;

    //Metodos de la clase

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };


}
