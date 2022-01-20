package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    public static final String TAG = "YOUR-TAG-NAME";

    Button btnRegister;
    TextView etLoginEmail;
    TextView etLoginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLoginEmail = findViewById(R.id.editTextTextPersonName);
        etLoginPassword = findViewById(R.id.editTextTextPassword);
        btnRegister = findViewById(R.id.button);

        mAuth = FirebaseAuth.getInstance();
        btnRegister.setOnClickListener(view -> {
            loginUser();
        });


    }
    public void onStart() {
        super.onStart();

        FirebaseUser user = mAuth.getCurrentUser();
        if(user == null){

        }
    }


    private void loginUser(){
        String email = etLoginEmail.getText().toString();
        String password = etLoginPassword.getText().toString();

        if (TextUtils.isEmpty(email)){
            etLoginEmail.setError("email cannot be empty");
            etLoginEmail.requestFocus();
        }else if(TextUtils.isEmpty(password)){
            etLoginPassword.setError("password cannot be empty");
            etLoginPassword.requestFocus();
        }else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(loginActivity.this, "user logged in succesful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(loginActivity.this, AddGoalActivity.class));

                    }else{
                        Toast.makeText(loginActivity.this, "user loggin failed", Toast.LENGTH_SHORT).show();

                    }
                }
            });
        }
    }

    private void updateUI(FirebaseUser user) {

    }

    public void loadRegisterScreen(View y){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);                           // laad pagina i
    }

    public void LoadAddGoalActivity(View x){
        Intent i = new Intent(this, AddGoalActivity.class);
        startActivity(i);
    }


}