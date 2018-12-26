package firstapp.om.sweety.navigation;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Locale;

import firstapp.om.sweety.MapsActivity;
import firstapp.om.sweety.R;
import firstapp.om.sweety.activities.LoginActivity;
import firstapp.om.sweety.home.cakes.cakegallery.CakeDetail;
import firstapp.om.sweety.home.cakes.cakegallery.CakeGallery;
import firstapp.om.sweety.home.HomeFragment;
import firstapp.om.sweety.home.database.CartFragment;
import firstapp.om.sweety.home.database.OrderDetail;
import firstapp.om.sweety.home.database.OrderInfo;
import firstapp.om.sweety.language.SweetyLangActivity;
import firstapp.om.sweety.language.SweetyLangConstans;

public class NavigationPage extends SweetyLangActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeFragment.OnFragmentInteractionListener,CakeGallery.OnFragmentInteractionListener
        ,CakeDetail.OnFragmentInteractionListener,CartFragment.OnFragmentInteractionListener,OrderDetail.OnFragmentInteractionListener,OrderInfo.OnFragmentInteractionListener {
private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                CartFragment cartFragment =new CartFragment();
                fragmentTransaction.replace(R.id.main_continer,cartFragment);
                fragmentTransaction.commit();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        HomeFragment homeFragment=HomeFragment.newInstance(null,null);
        fragmentTransaction.replace(R.id.main_continer,homeFragment);
        fragmentTransaction.commit();

        View headerView=navigationView.getHeaderView(0);
        textView=findViewById(R.id.nav_userName);




    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_page, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        android.support.v4.app.Fragment fragment=null;


        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {


       fragment=HomeFragment.newInstance(null,null);

            // Handle the camera action
        } else if (id == R.id.nav_order) {


        } else if (id == R.id.nav_language) {

            Locale current =getResources().getConfiguration().locale;
            if (current.getLanguage().equalsIgnoreCase(SweetyLangConstans.EN)){
                chageLang(SweetyLangConstans.AR);

            }else  {
                chageLang(SweetyLangConstans.EN);

            }

            Intent intent =getIntent();
            finish();
            startActivity(intent);

        } else if (id == R.id.nav_contactUs) {

            fragment=new ContactUsFragment();


        } else if (id == R.id.nav_about) {

            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            String shareApp="Share Applation";
            intent.putExtra(Intent.EXTRA_SUBJECT, shareApp);
            intent.setType("application/vnd.android.package-archive");
            startActivity(Intent.createChooser(intent,"Share App"));


        } else if (id == R.id.nav_logout) {
            SharedPreferences settings = getSharedPreferences(LoginActivity.PREFS_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = settings.edit();
            editor.clear();
            editor.commit();
            Intent intent=new Intent(this,HomeFragment.class);
            startActivity(intent);




        }
        if (fragment!=null){
            fragmentTransaction.replace(R.id.main_continer,fragment);
            fragmentTransaction.commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

            public void backButtonPressed(View view) {
        onBackPressed();

            }
        }
