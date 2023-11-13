package com.local.proyectofinal;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    Button BtnAlumno, BtnSalir, BtnListo, BtnLlegue, BtnCarril1, BtnCarril2, BtnCarril3;
    String nombrePadreLogin;
    TextView TextArea;

    ArrayList<String> nombresArray;
    ArrayList<String> gradosArray;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String padre = getIntent().getStringExtra("padre");
        //Alumno alumno = (Alumno) getIntent().getSerializableExtra("alumno");
        String idAlumno = getIntent().getStringExtra("idAlumno"); //El id ya lo esta trayendo de la otra vista
        //String NombrePadre = getIntent().getStringExtra("NombrePadre");


        db.collection("padres")
                .whereEqualTo("nombre", padre)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                nombrePadreLogin = document.getString("nombre"); // Reemplaza "nombreCampo" con el nombre real del campo
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });



        //Buttons
        BtnAlumno = findViewById(R.id.btnAlumnos);
        BtnSalir  = findViewById(R.id.btnSingOut);
        BtnListo = findViewById(R.id.btnListo);
        BtnLlegue = findViewById(R.id.btnLlegue);
        BtnCarril1 = findViewById(R.id.carril1);
        BtnCarril2 = findViewById(R.id.carril2);
        BtnCarril3 = findViewById(R.id.carril3);
        TextArea = findViewById(R.id.textView3);

        //Buttons Listener
        BtnAlumno.setOnClickListener(view -> Ir_A_Alumno(padre));
        BtnLlegue.setOnClickListener(view -> llegue(padre));
        BtnListo.setOnClickListener(view -> listo(padre));
        //BtnLlegue.setOnClickListener(view -> llegue(alumno));
        BtnCarril1.setOnClickListener(view -> carril_1(idAlumno));
        BtnCarril2.setOnClickListener(view -> carril_2(idAlumno));
        BtnCarril3.setOnClickListener(view -> carril_3(idAlumno));




    }
    public void listo(String padre){
        //Logica para eliminar documentos
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Obtén la referencia de la colección
        CollectionReference miColeccionRef = db.collection("alumnos");

        // Realiza una consulta para obtener todos los documentos que tienen el campo "padre"
        miColeccionRef.whereEqualTo("padre", padre).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Itera sobre los documentos y elimínalos uno por uno
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        db.collection("alumnos").document(document.getId()).delete()
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> deleteTask) {
                                        if (deleteTask.isSuccessful()) {
                                            TextArea.setText("");
                                            Toast.makeText(IndexActivity.this, "Has recogido a tus hijos!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Log.w(TAG, "Error al eliminar documento", deleteTask.getException());
                                        }
                                    }
                                });
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.getException());
                }
            }
        });

    }


    public void llegue(String padre){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        nombresArray = new ArrayList<>();
        gradosArray = new ArrayList<>();

        db.collection("alumnos")
                .whereEqualTo("padre", padre)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String nombre = document.getString("nombre");
                                String grado = document.getString("grado");

                                nombresArray.add(nombre);
                                gradosArray.add(grado);
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            for (int i = 0; i < nombresArray.size(); i++) {
                                stringBuilder.append("Nombre: ").append(nombresArray.get(i)).append(",  Grado: ").append(gradosArray.get(i)).append("\n");
                            }

                            TextArea.setText(stringBuilder.toString());
                        } else {
                            TextArea.setText("Porfavor registra a un alumno!");
                        }
                    }
                });
    }

    public void carril_1(String id){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Carril;
        Carril = "1";
        DocumentReference DocRef = db.collection("alumnos").document(id);
        DocRef.update("carril", Carril)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(IndexActivity.this, "Carril Seleccionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void carril_2(String id){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Carril;
        Carril = "2";
        DocumentReference DocRef = db.collection("alumnos").document(id);
        DocRef.update("carril", Carril)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(IndexActivity.this, "Carril Seleccionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void carril_3(String id){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Carril;
        Carril = "3";
        DocumentReference DocRef = db.collection("alumnos").document(id);
        DocRef.update("carril", Carril)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(IndexActivity.this, "Carril Seleccionado", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
    }

    public void Ir_A_Alumno(String padre){
        Intent intent = new Intent(IndexActivity.this, RegistrarAlumnoActivity.class);
        intent.putExtra("nombrePadre", padre);
        startActivity(intent);
        finish();
    }

    public void Ir_A_Main(View v){
        Intent i = new Intent(IndexActivity.this , MainActivity.class);
        startActivity(i);
        finish();
    }
}