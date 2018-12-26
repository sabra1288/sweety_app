package firstapp.om.sweety.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rey.material.widget.CheckBox;

import firstapp.om.sweety.MapsActivity;
import firstapp.om.sweety.R;
import firstapp.om.sweety.home.User;
import firstapp.om.sweety.home.cakes.CakeItem;
import firstapp.om.sweety.navigation.NavigationPage;
import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity {
  private   TextView signUp,forgotPass;
  private EditText userName,pass;
  private FirebaseAuth mAuth;
  private String email,password;
  private Button login;
  public static final String PREFS_NAME = "LoginPrefs";
  public static final String EMAIL = "email";
  public static final String PASSWORD = "password";
//  private String login_id="sabra1288@gmail.com";
//  private String password_code="123s456";
  private FirebaseDatabase firebaseDatabase;
  private DatabaseReference databaseReference;
  private User user;
  private CheckBox chRememberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setTitle("Sign In");


        mAuth=FirebaseAuth.getInstance();

        userName=findViewById(R.id.userName);
        pass=findViewById(R.id.password);
        forgotPass=findViewById(R.id.forgot);
        login=findViewById(R.id.login);
        chRememberMe=findViewById(R.id.rememberMe);

        Paper.init(this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("User");


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                             user = snapshot.getValue(User.class);
//                             user.setName(userName.getText().toString());
//                             user.setPassword(pass.getText().toString());

                            if (user.getName().equals(userName.getText().toString()) && user.getPassword().equals(pass.getText().toString())){

                                Toast.makeText(LoginActivity.this, "Sign In successfully", Toast.LENGTH_LONG).show();
                                Intent intent=new Intent(LoginActivity.this,MapsActivity.class);
                                startActivity(intent);


                            }else {
                                Toast.makeText(LoginActivity.this, "Please enter correct user name and password", Toast.LENGTH_LONG).show();
                            }



                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


//                userName.setText(login_id);
//                pass.setText(password_code);


                email = userName.getText().toString();
                 final String password =pass.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Please enter email id", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Please Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }





                //authenticate user
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                if (task.isSuccessful()) {

                                    Toast.makeText(LoginActivity.this, "SignIn successful", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(LoginActivity.this,NavigationPage.class);
                                    startActivity(intent);
                                    finish();

                                }
                                if (chRememberMe.isChecked()){
                                    Paper.book().write(LoginActivity.EMAIL,userName.getText().toString());
                                    Paper.book().write(LoginActivity.PASSWORD,pass.getText().toString());
                                }


                            }



                        });

            }
        });


        signUp=findViewById(R.id.signUp);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        forgotPass.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage("verifying..");
        progressDialog.show();


        mAuth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Reset password instructions has sent to your email",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Email don't exist", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Please Enter the Email", Toast.LENGTH_SHORT).show();
            }
        });

    }
});

    }

}
