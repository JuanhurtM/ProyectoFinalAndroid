package com.local.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button BtnLogin, BtnRegistrarse;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnLogin = findViewById(R.id.btnLogin);
        BtnRegistrarse = findViewById(R.id.btnRegistra);

    }

    public void Ir_A_Login(View v){
        Intent i = new Intent(MainActivity.this , LoginActivity.class);
        startActivity(i);
    }

    public void Ir_A_Registrarse(View v){
        Intent i = new Intent(MainActivity.this, RegistrarActivity.class);
        startActivity(i);
    }
}