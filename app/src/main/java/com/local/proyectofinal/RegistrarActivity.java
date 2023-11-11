package com.local.proyectofinal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import java.util.Map;
import java.util.HashMap;
import android.util.Log;
import androidx.annotation.NonNull;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarActivity extends AppCompatActivity {

    EditText EdtNombre, EdtCedula, EdtCelular, EdtCorreo, EdtContrasena;
    Button btnRegistrar, btnVolver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //Edit Text
        EdtNombre = findViewById(R.id.editTextNombre);
        EdtCedula = findViewById(R.id.editTextCedula);
        EdtCelular = findViewById(R.id.editTextCelular);
        EdtCorreo = findViewById(R.id.editTextCorreo);
        EdtContrasena = findViewById(R.id.editTextPassword);

        //Botones
        btnRegistrar = findViewById(R.id.btnRegUsuario);
        btnVolver = findViewById(R.id.btnVolver);

        btnRegistrar.setOnClickListener(v -> registrarUsuario());


    }

   public void registrarUsuario(){

       String Nombre, Cedula, Celular, Correo, Contraseña;

       Nombre = EdtNombre.getText().toString();
       Cedula = EdtCedula.getText().toString();
       Celular = EdtCelular.getText().toString();
       Correo = EdtCorreo.getText().toString();
       Contraseña = EdtContrasena.getText().toString();

       FirebaseFirestore db = FirebaseFirestore.getInstance();

       if (Nombre.isEmpty() || Cedula.isEmpty() || Celular.isEmpty() || Correo.isEmpty() || Contraseña.isEmpty()) {
           Toast.makeText(this, "Ingrese todos los datos para el registro", Toast.LENGTH_SHORT).show();
           return;
       }

       Map<String, Object> datos = new HashMap<>();

       datos.put("carril", "0");
       datos.put("nombre", Nombre);
       datos.put("cedula", Cedula);
       datos.put("contrasena", Contraseña);
       datos.put("correo", Correo);
       datos.put("telefono", Celular);


       // Create a new user with a first and last name

// Add a new document with a generated ID
       db.collection("padres")
               .add(datos)
               .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                   @Override
                   public void onSuccess(DocumentReference documentReference) {
                       Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                       Intent intent = new Intent(RegistrarActivity.this, MainActivity.class);
                       startActivity(intent);
                       finish();
                   }
               })
               .addOnFailureListener(new OnFailureListener() {
                   @Override
                   public void onFailure(@NonNull Exception e) {
                       Log.w(TAG, "Error adding document", e);
                   }
               });

   }

    public void Ir_A_Main(View v){
        Intent i = new Intent(RegistrarActivity.this , MainActivity.class);
        startActivity(i);
    }

}