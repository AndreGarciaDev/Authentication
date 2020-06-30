package com.example.authentication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class PrincipalActivity extends AppCompatActivity {

    private Button btnLogout, bt1, bt2, bt3, bt4, bt5, bt6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disconnect();
            }
        });

        bt1 = (Button) findViewById (R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("https://www.voacap.com/11m/");
            }
        });

        bt2 = (Button) findViewById (R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("https://www.voacap.com/hf/");
            }
        });

        bt3 = (Button) findViewById (R.id.bt3);
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("https://www.voacap.com/dx/");
            }
        });

        bt4 = (Button) findViewById (R.id.bt4);
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("https://www.voacap.com/greyline/");
            }
        });

        bt5 = (Button) findViewById (R.id.bt5);
        bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("https://www.voacap.com/qth.html");
            }
        });

        bt6 = (Button) findViewById (R.id.bt6);
        bt6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoPage("http://www.hamqsl.com/solar101vhfpic.php");
            }
        });
    }

    private void gotoPage(String url){
        Intent intent = new Intent(getApplicationContext(), WebViewVoacap.class);
        intent.setData(Uri.parse(url));
        startActivity(intent);
        //finish();
    }

    private void disconnect() {
        FirebaseAuth.getInstance().signOut();
        closePrincipal();
    }


    private void closePrincipal() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

}
