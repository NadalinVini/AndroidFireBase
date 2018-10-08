package com.example.opet.i7;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.opet.i7.model.Partida;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HistoricoTrucoActivity extends Activity {

    private TextView textEmail;
    private Button btnAtualizar;
    private Button btnMenu;
    private ProgressBar progressPartidas;
    private ListView mList;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    private String uid;

    private List<Partida> partidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_truco);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        textEmail = findViewById(R.id.textEmail);

        if (currentUser != null) {
            uid = currentUser.getUid().toString();
            String email = currentUser.getEmail();

            textEmail.setText("Bem vindo " + email);
        }

        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnAtualizar = findViewById(R.id.btnMenu);
        mList = findViewById(R.id.mList);
        progressPartidas = findViewById(R.id.progressPartidas);
        partidas = new ArrayList<>();
    }

    @Override
    protected void onStart(){
        super.onStart();
        carregarPartidas();
    }

    public void carregarPartidas(){
        Query mQuery = mDatabase.child("users").child(uid).child("partida").orderByChild("id")
                .limitToFirst(50);

        mQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!partidas.isEmpty())
                    partidas.clear();

                progressPartidas.setVisibility(ProgressBar.VISIBLE);

                for(DataSnapshot livroSnapshot : dataSnapshot.getChildren()){
                    partidas.add(livroSnapshot.getValue(Partida.class));
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(HistoricoTrucoActivity.this,android.R.layout.simple_list_item_1,android.R.id.text1);

                for(Partida l: partidas){
                    adapter.add(l.getId());
                }

                mList.setAdapter(adapter);
                progressPartidas.setVisibility(ProgressBar.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }




}
