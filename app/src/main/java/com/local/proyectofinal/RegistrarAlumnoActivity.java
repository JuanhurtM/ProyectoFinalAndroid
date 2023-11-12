package com.local.proyectofinal;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistrarAlumnoActivity extends AppCompatActivity {

    TextView Text, TexArea;
    EditText EdtNombre, EdtGrado;
    Button BtnRegistrar, BtnVolver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_alumno);

        String carrilPadre = getIntent().getStringExtra("carrilPadre");
        String nombrePadre = getIntent().getStringExtra("nombrePadre");

        EdtNombre = findViewById(R.id.editTextNombreAlumno);
        EdtGrado = findViewById(R.id.editTextGrado);

        Text = findViewById(R.id.Test);
        TexArea = findViewById(R.id.textView2);

        BtnRegistrar = findViewById(R.id.btnRegUsuario);
        BtnVolver = findViewById(R.id.btnVolver);

        BtnRegistrar.setOnClickListener(view -> registrarAlumno(carrilPadre, nombrePadre));
        BtnVolver.setOnClickListener(view -> volver());


    }
    public void volver(){

        Intent i = new Intent(RegistrarAlumnoActivity.this , IndexActivity.class);
        startActivity(i);
        finish();

    }

    public void registrarAlumno(String Carril, String NombrePadre) {
        String Nombre, Grado, carril, padre;

        Nombre = EdtNombre.getText().toString();
        Grado = EdtGrado.getText().toString();
        carril = Carril;
        padre = NombrePadre;

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        if (Nombre.isEmpty() || carril.isEmpty() || padre.isEmpty() || Grado.isEmpty()) {
            Toast.makeText(this, "Ingrese todos los datos para el registro", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> datos = new HashMap<>();

        datos.put("carril", carril);
        datos.put("grado", Grado);
        datos.put("nombre", Nombre);
        datos.put("padre", padre);

        db.collection("alumnos")
                .add(datos)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // En el éxito, puedes obtener el ID del documento recién creado
                        String documentId = documentReference.getId();
                        DocumentReference docRef = db.collection("alumnos").document(documentId);
                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                if (task.isSuccessful()) {
                                    DocumentSnapshot document = task.getResult();
                                    if (document.exists()) {
                                        Alumno alumno = document.toObject(Alumno.class);
                                        String id = document.getId();
                                        Intent i = new Intent(RegistrarAlumnoActivity.this , IndexActivity.class);
                                        i.putExtra("idAlumno",id);
                                        i.putExtra("alumno", alumno);
                                        startActivity(i);
                                        finish();
                                    } else {
                                        Log.d(TAG, "No such document");
                                    }
                                } else {
                                    Log.d(TAG, "get failed with ", task.getException());
                                }
                            }
                        });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                        TexArea.setText("Error al agregar el documento");
                    }
                });
    }



}

