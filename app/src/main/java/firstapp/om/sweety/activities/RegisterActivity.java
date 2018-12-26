package firstapp.om.sweety.activities;

import android.content.Intent;
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

import firstapp.om.sweety.R;

public class RegisterActivity extends AppCompatActivity {
    private Button singUp;
    private TextView signIn;

    private EditText email,password;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Sign Up");

        email=findViewById(R.id.r_email);
        password=findViewById(R.id.r_password);
        signIn=findViewById(R.id.signin);

        firebaseAuth=FirebaseAuth.getInstance();

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        singUp=findViewById(R.id.register);

        singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mUser=email.getText().toString().trim();
                String mPassword=password.getText().toString().trim();

                if (TextUtils.isEmpty(mUser)){
                    email.setError("please provide a vaild mail ID");
                    return;

                }
                if (!isEmail(mUser)){
                    Toast.makeText(RegisterActivity.this,"Mail id is not valid",Toast.LENGTH_LONG).show();
                    email.setError("please provide a vaild mail ID");
                    return;
                }
                if (TextUtils.isEmpty(mPassword)|| mPassword.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password filed is empty or"+"Its should have six or more characters",Toast.LENGTH_LONG).show();
                    password.setError("Pleas provide a valid Password should have at least six character");

                }
                firebaseAuth.createUserWithEmailAndPassword(mUser,mPassword).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful());
                        Toast.makeText(RegisterActivity.this,"Registration Successful",Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });

            }
        });

    }

    public static boolean isEmail(String email){
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }
}
