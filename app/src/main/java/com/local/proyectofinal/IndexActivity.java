package com.local.proyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class IndexActivity extends AppCompatActivity {

    TextView Text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Padre padre = (Padre) getIntent().getSerializableExtra("padre");

        Text = findViewById(R.id.textView);

        Text.setText(padre.getNombre());

    }
}