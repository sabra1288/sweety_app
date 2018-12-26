package firstapp.om.sweety.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import firstapp.om.sweety.MapsActivity;
import firstapp.om.sweety.R;
import firstapp.om.sweety.home.User;
import firstapp.om.sweety.language.SweetyLangActivity;
import firstapp.om.sweety.language.SweetyLangConstans;
import firstapp.om.sweety.navigation.NavigationPage;
import io.paperdb.Paper;

public class SplashScreen extends SweetyLangActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        android.support.v7.app.ActionBar actionBar=getSupportActionBar(); //to hide the actionBar in the splash page
        actionBar.hide();


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); // to hide the notefitication bar

        if (getSharedPreferences(SweetyLangConstans.LANG_FILE,MODE_PRIVATE).getString(SweetyLangConstans.LANG,null)!=null
                && getSharedPreferences(SweetyLangConstans.LANG_FILE, MODE_PRIVATE).getString((SweetyLangConstans.LANG), null).equalsIgnoreCase(SweetyLangConstans.EN)) {
            chageLang(SweetyLangConstans.EN);


        }else {
            chageLang((SweetyLangConstans.AR));

        }

        Paper.init(this);


        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i=new Intent (SplashScreen.this,LoginActivity.class);
                startActivity(i);
                finish();

            }
        }, 5000);

        String user =Paper.book().read(LoginActivity.EMAIL);
        String password=Paper.book().read(LoginActivity.PASSWORD);
        if (user !=null && password !=null)
        {
            if (!user.isEmpty() &&!password.isEmpty())

            login(user,password);
        }

    }

    private void login(String user, String password) {


        FirebaseAuth mAuth=FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference("User");



        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);

                    if (user.equals("user")){
                        Intent intent=new Intent(SplashScreen.this,MapsActivity.class);
                        startActivity(intent);

                    }else {
                        Toast.makeText(SplashScreen.this, "Please enter correct user name and password", Toast.LENGTH_LONG).show();
                    }



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



//        //authenticate user
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(SplashScreen.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//
//                        if (task.isSuccessful()) {
//
//
//                            Toast.makeText(SplashScreen.this, "SignIn successful", Toast.LENGTH_LONG).show();
//                            Intent intent=new Intent(SplashScreen.this,NavigationPage.class);
//                            startActivity(intent);
//                            finish();
//
//                        }
//
//
//                    }
//
//
//
//                });
//


    }


}

