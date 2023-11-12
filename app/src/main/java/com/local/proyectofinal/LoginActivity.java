package com.local.proyectofinal;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import android.content.Intent;
import android.util.Log;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    EditText EdtEmail, EdtPassword;
    Button btnLogin, btnLoginVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EdtEmail = findViewById(R.id.editTextLoginCorreo);
        EdtPassword = findViewById(R.id.editTextLoginPassword);

        btnLogin = findViewById(R.id.btnSingIn);
        btnLoginVolver = findViewById(R.id.btnLoginVolver);

        btnLogin.setOnClickListener(v -> singIn());

    }

    public void singIn(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String Email, Password;

        Email = EdtEmail.getText().toString().trim();
        Password = EdtPassword.getText().toString().trim();

        if(Email.isEmpty() || Password.isEmpty()){
            Toast.makeText(this, "Ingrese todos los datos para el ingreso", Toast.LENGTH_SHORT).show();
            return;
        }

        db.collection("padres")
                .whereEqualTo("correo", Email)
                .whereEqualTo("contrasena", Password)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            // Existe un documento que coincide con cedula y email
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                DocumentReference docRef = db.collection("padres").document(document.getId());
                                docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        Padre padre = documentSnapshot.toObject(Padre.class);
                                        String id = documentSnapshot.getId();
                                        // Envia el objeto Padre a IndexActivity
                                        Intent intent = new Intent(LoginActivity.this, IndexActivity.class);
                                        intent.putExtra("padre", padre);
                                        intent.putExtra("id", id);
                                        startActivity(intent);

                                        Toast.makeText(LoginActivity.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            // No existe un documento que coincida con cedula y email
                            // Navegar hacia la actividad Carril
                            Toast.makeText(LoginActivity.this, "Usuario no registrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e(TAG, "Error al realizar la consulta: ", task.getException());
                    }
                });

    }


    public void Ir_A_Main(View v){
        Intent i = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(i);
        finish();
    }
}