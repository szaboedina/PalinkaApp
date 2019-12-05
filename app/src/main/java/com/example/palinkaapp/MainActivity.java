package com.example.palinkaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private AdatbazisSegito adatbazisSegito;
    private TextView textViewListazas;
    private Button buttonFelvetel, buttonKereses, buttonListazas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        textViewListazas.setMovementMethod(new ScrollingMovementMethod());
        buttonListazas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adatLekerdezes();
            }
        });

        buttonFelvetel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AdatFelvetelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        buttonKereses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AdatKeresesActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    public void init(){
        adatbazisSegito= new AdatbazisSegito(this);
        textViewListazas=findViewById(R.id.idResult);
        buttonFelvetel=findViewById(R.id.buttonFelvetel);
        buttonKereses=findViewById(R.id.buttonKereses);
        buttonListazas=findViewById(R.id.buttonListazas);
    }
    public void adatLekerdezes(){
        Cursor eredmeny= adatbazisSegito.adatLekerdezes();
        StringBuffer stringBuffer=new StringBuffer();
        if (eredmeny!= null&&eredmeny.getCount()>0){
            while (eredmeny.moveToNext()){
                stringBuffer.append("ID: "+eredmeny.getString(0)+"\n");
                stringBuffer.append("Főző: "+eredmeny.getString(1)+"\n");
                stringBuffer.append("Gyümölcs: "+eredmeny.getString(2)+"\n");
                stringBuffer.append("Alkohol: "+eredmeny.getString(3)+"\n\n");
            }
            textViewListazas.setText(stringBuffer.toString());
            Toast.makeText(this,"Adat sikeresen lekérve!",Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Adatlekérés sikertelen!", Toast.LENGTH_SHORT).show();

    }

}
