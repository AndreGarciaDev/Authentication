package com.example.authentication;

import android.content.Intent;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin;
    private Button btnCancel;
    private EditText edtEmail;
    private EditText edtPassword;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    //firebase
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Preencher campos obrigatórios", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (edtEmail.getText().toString().trim().matches(emailPattern)) {
                        loginUser(edtEmail.getText().toString(), edtPassword.getText().toString());
                    }
                    else {
                        Toast.makeText(MainActivity.this, "email inválido", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtEmail.setText("");
                edtPassword.setText("");
            }
        });


    }


    private void loginUser(String email, String password) {
        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(getApplicationContext(), "Login OK!", Toast.LENGTH_SHORT).show();

                            openPrincipalAcivity();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.INVISIBLE);
                        }

                    }
                });
    }


    private boolean userConnected() {
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser == null) {
            return false;
        } else {
            return true;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();

        if (userConnected()) {
            openPrincipalAcivity();
        }

    }

    private void openPrincipalAcivity() {

        Intent intent = new Intent(getApplicationContext(), PrincipalActivity.class);
        startActivity(intent);
        finish();
    }
}
