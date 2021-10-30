package nl.hendriks.eindproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(MainActivity.this, "Firebase connection succesful",
                Toast.LENGTH_LONG).show();

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        btnSingIn = findViewById(R.id.SignIn);
        btnSingIn.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick (View v){
            String email = emailId.getText().toString();
            String pwd = password.getText().toString();
            if (email.isEmpty()) {
                emailId.setError("Please enter a valid e-mailadress");
                emailId.requestFocus();
            } else if (pwd.isEmpty()) {
                password.setError("please enter a valid password");
                password.requestFocus();

            } else if (email.isEmpty() && pwd.isEmpty()) {
                Toast.makeText(MainActivity.this, "Fields are emty", Toast.LENGTH_SHORT).show();
            }else if (!(email.isEmpty() && pwd.isEmpty())){
                mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "SignUp Unsuccesfull", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            startActivity(new Intent(MainActivity.this, ));
                        }
                    }
                });
            }
            else {
                Toast.makeText(MainActivity.this, "Error Occurred", Toast.LENGTH_SHORT).show();
            }

        }
        });


    }

    public void loadRegisterScreen(View y){
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);                           // laad pagina i
    }

    public void loadtest(View x){
        Intent i = new Intent(this, AddGoalActivity.class);
        startActivity(i);
    }
}