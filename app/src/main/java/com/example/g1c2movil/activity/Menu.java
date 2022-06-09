package com.example.g1c2movil.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.g1c2movil.R;
import com.example.g1c2movil.fragmentos.ContactosFragment;
import com.example.g1c2movil.fragmentos.ConvocatoriaFragment;
import com.example.g1c2movil.fragmentos.InstructivoFragment;
import com.example.g1c2movil.fragmentos.ProcesosFragment;
import com.google.android.material.navigation.NavigationView;


public class Menu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private Bundle bundle;
    private int control_fragmento=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();


        bundle = new Bundle();
        //bundle.putString("cedula",getIntent().getExtras().get("cedula")+"");

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        switch (item.getItemId()){
            case R.id.nav_convocatoria:
                onNavigation(new ConvocatoriaFragment());
                break;
            case R.id.nav_instructivo:
                onNavigation(new InstructivoFragment());
                break;
            case R.id.nav_procesos:
                onNavigation(new ProcesosFragment());
                break;

            case R.id.nav_contactos:
                onNavigation(new ContactosFragment());
                break;

            case R.id.nav_sesion:
                cierredeSecion();
                break;


        }
        setTitle(item.getTitle());
        control_fragmento=item.getItemId();
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(control_fragmento==-1 || control_fragmento==R.id.nav_convocatoria){
            onResumenCorrect(new ConvocatoriaFragment());
        }else if(control_fragmento==R.id.nav_instructivo){
            onResumenCorrect(new ContactosFragment());
        }else if(control_fragmento==R.id.nav_procesos){
            onResumenCorrect(new ProcesosFragment());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        for(int i = 0; i < count; ++i) {
            fm.popBackStackImmediate();
        }
    }

    private void onResumenCorrect(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_fragment,fragment)
                .commit();
    }

    private void onNavigation(Fragment fragment){
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.container_fragment,
                fragment).commit();
    }

    private void cierredeSecion(){
            Intent mainIntent = new Intent(Menu.this, MainActivity.class);
            finish();
            startActivity(mainIntent);
        Toast.makeText(this,"Cierre sesion con exito", Toast.LENGTH_SHORT).show();
    }

}
