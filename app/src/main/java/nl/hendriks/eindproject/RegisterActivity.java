package nl.hendriks.eindproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    Button btnRegister;
    TextView etRegEmail;
    TextView etRegPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        btnRegister = findViewById(R.id.button3);
        etRegEmail = findViewById(R.id.editTextTextEmailAddress);
        etRegPassword = findViewById(R.id.editTextTextPassword2);


        btnRegister.setOnClickListener(view -> {
            createUser();
        });
    }

    private void createUser() {
        String email = etRegEmail.getText().toString();
        String password = etRegPassword.getText().toString();

        if (TextUtils.isEmpty(email)) {
            etRegEmail.setError("email cannot be empty");
            etRegEmail.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            etRegPassword.setError("password cannot be empty");
            etRegPassword.requestFocus();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(RegisterActivity.this, "user registered succesful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, test.class));
                    } else {
                        Toast.makeText(RegisterActivity.this, "user registered unsuccesful", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


}