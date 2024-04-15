package com.example.agendaapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText txtNombres, txtTelf, txtEmail;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombres = findViewById(R.id.txtNombres);
        txtTelf = findViewById(R.id.txtTelf);
        txtEmail = findViewById(R.id.txtEmail);

        preferences = getSharedPreferences("contactos", Context.MODE_PRIVATE);
    }

    public void btnGuardarContacto(View v){
        String nombres = txtNombres.getText().toString().trim();
        String telf = txtTelf.getText().toString();
        String email = txtEmail.getText().toString().trim();

        if(nombres.isEmpty() || telf.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Campos Incompletos", Toast.LENGTH_SHORT).show();
        } else {
            if (!contactoExiste(nombres)) { // Verificar si el contacto ya existe
                guardarContacto(nombres, telf, email);
                Toast.makeText(this, "Contacto guardado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Ya existe un contacto con el mismo nombre", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void btnBuscarContacto(View v){
        String nombreBusqueda = txtNombres.getText().toString().trim();
        if(!nombreBusqueda.isEmpty()) {
            if (contactoExiste(nombreBusqueda)) { // Verificar si el contacto existe
                llenarCamposContacto(nombreBusqueda);
                Toast.makeText(this, "Contacto encontrado!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Contacto no encontrado!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Campo Nombres incompleto", Toast.LENGTH_SHORT).show();
        }
    }

    public void btnLimpiarCampos(View v){
        txtNombres.setText("");
        txtEmail.setText("");
        txtTelf.setText("");

        Toast.makeText(this, "Campos Limpiados!", Toast.LENGTH_SHORT).show();
    }

    private boolean contactoExiste(String nombre) {
        return preferences.contains(nombre);
    }

    private void guardarContacto(String nombres, String telf, String email) {
        Contacto contacto = new Contacto(nombres, telf, email);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(nombres, contacto.toString());
        editor.apply();
    }

    private void llenarCamposContacto(String nombre) {
        String contactoString = preferences.getString(nombre, null);
        if (contactoString != null) {
            String[] contactoInfo = contactoString.split(",");
            txtNombres.setText(contactoInfo[0]);
            txtTelf.setText(contactoInfo[1]);
            txtEmail.setText(contactoInfo[2]);
        }
    }
}
