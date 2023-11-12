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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class IndexActivity extends AppCompatActivity {

    Button BtnAlumno, BtnSalir, BtnListo, BtnLlegue, BtnCarril1, BtnCarril2, BtnCarril3;

    TextView TextArea;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        Padre padre = (Padre) getIntent().getSerializableExtra("padre");
        Alumno alumno = (Alumno) getIntent().getSerializableExtra("alumno");
        String idAlumno = getIntent().getStringExtra("idAlumno"); //El id ya lo esta trayendo de la otra vista


        //Buttons
        BtnAlumno = findViewById(R.id.btnAlumnos);
        BtnSalir  = findViewById(R.id.btnSingOut);
        BtnListo = findViewById(R.id.btnListo);
        BtnLlegue = findViewById(R.id.btnLlegue);
        BtnCarril1 = findViewById(R.id.carril1);
        BtnCarril2 = findViewById(R.id.carril2);
        BtnCarril3 = findViewById(R.id.carril3);
        TextArea = findViewById(R.id.textView3);


        BtnAlumno.setOnClickListener(v -> Ir_A_Alumno(padre));
        BtnLlegue.setOnClickListener(view -> llegue(alumno));
        BtnCarril1.setOnClickListener(view -> carril_1(idAlumno));
        BtnCarril2.setOnClickListener(view -> carril_2(idAlumno));
        BtnCarril3.setOnClickListener(view -> carril_3(idAlumno));




    }
    public void listo(){
        //Logica para eliminar documentos
    }

    public void llegue(Alumno alumno){
        TextArea.setText("Nombre: "+ alumno.getNombre() +" Grado: "+alumno.getGrado());

        //Hacer mas logica que en ves de traer los nombres del obj consulte la coleccion con los criterios de padre y id
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

    public void Ir_A_Alumno(Padre padre){
        Intent intent = new Intent(IndexActivity.this, RegistrarAlumnoActivity.class);
        intent.putExtra("carrilPadre",padre.getCarril());
        intent.putExtra("nombrePadre", padre.getNombre());
        //intent.putExtra("padre", padre);
        startActivity(intent);
        finish();
    }

    public void Ir_A_Main(View v){
        Intent i = new Intent(IndexActivity.this , MainActivity.class);
        startActivity(i);
        finish();
    }
}