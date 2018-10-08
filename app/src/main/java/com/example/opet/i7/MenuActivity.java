package com.example.opet.i7;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void telaTruco(View view){
        Toast.makeText(MenuActivity.this, "Partida iniciada", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MenuActivity.this, TrucoActivity.class);
        startActivity(intent);
    }

    public void telaBemVindo(View view){
        Intent intent = new Intent(MenuActivity.this, BemVindo.class);
        startActivity(intent);
    }

    public void telaHistorico(View view){
        Intent intent = new Intent(MenuActivity.this, HistoricoTrucoActivity.class);
        startActivity(intent);
    }
}
