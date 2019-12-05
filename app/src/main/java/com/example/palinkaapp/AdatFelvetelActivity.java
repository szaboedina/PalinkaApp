package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdatFelvetelActivity extends AppCompatActivity {

    private AdatbazisSegito adatbazisSegito;
    private EditText editTextFozo, editTextGyumolcs, editTextAlkohol;
    private Button buttonFelvetel, buttonVissza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adat_felvetel);init();
        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatRogzites();
                Intent intent=new Intent(AdatFelvetelActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonVissza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vissza=new Intent(AdatFelvetelActivity.this,MainActivity.class);
                startActivity(vissza);
                finish();
            }
        });

    }
    public void init(){
        adatbazisSegito=new AdatbazisSegito(this);
        editTextFozo=findViewById(R.id.idFozo);
        editTextGyumolcs=findViewById(R.id.idGyumolcs);
        editTextAlkohol=findViewById(R.id.idAlkohol);
        buttonFelvetel=findViewById(R.id.buttonFelvetel);
        buttonVissza=findViewById(R.id.buttonVissza);
    }
    public void adatRogzites(){
        String fozo=editTextFozo.getText().toString();
        String gyumolcs=editTextGyumolcs.getText().toString();
        String alkohol=editTextAlkohol.getText().toString();
        Boolean eredmeny=adatbazisSegito.adatRogzites(fozo,gyumolcs,alkohol);
        if (eredmeny)
            Toast.makeText(this, "Adatrögzítés sikeres!", Toast.LENGTH_SHORT).show();

        else
            Toast.makeText(this, "Adatrögzítés sikertelen!", Toast.LENGTH_SHORT).show();


    }
}
