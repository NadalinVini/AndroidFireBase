package com.example.opet.i7;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opet.i7.model.Partida;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.Timestamp;
import java.util.Random;

public class TrucoActivity extends Activity {

    private TextView textJogador1;
    private EditText textJogador2;
    private TextView textPonto1;
    private TextView textPonto2;
    private Button btnPonto1;
    private Button btnPonto2;
    private TextView textResultado;
    private Button btnAbandonar;
    private Button btnJogar;
    private Button btnTruco;

    private String uid;
    private int valorPartida;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_truco);

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        textJogador1 = findViewById(R.id.textJogador1);
        textJogador2 = findViewById(R.id.textJogador2);
        textPonto1 = findViewById(R.id.textPonto1);
        textPonto2 = findViewById(R.id.textPonto2);
        btnPonto1 = findViewById(R.id.btnPonto1);
        btnPonto2 = findViewById(R.id.btnPonto2);
        textResultado = findViewById(R.id.textResultado);
        btnAbandonar = findViewById(R.id.btnAbandonar);
        btnJogar = findViewById(R.id.btnJogar);
        btnTruco = findViewById(R.id.btnTruco);

        valorPartida = 1;

        if (currentUser != null) {
            String email = currentUser.getEmail();

            textJogador1.setText(email);
            uid = currentUser.getUid().toString();
        }
    }

    public void ponto1(View view){
        int pontoAntigo = Integer.parseInt(textPonto1.getText().toString());
        int pontoAntigo2 = Integer.parseInt(textPonto2.getText().toString());

        if(pontoAntigo < 12 && pontoAntigo2 < 12) {
            String ponto = "0";

            if(valorPartida == 1) {
                int novoPonto = pontoAntigo + 1;
                ponto = String.valueOf(novoPonto);
                textPonto1.setText(ponto);
            } else if(valorPartida == 3){
                int novoPonto = pontoAntigo + 3;
                ponto = String.valueOf(novoPonto);
                textPonto1.setText(ponto);
            } else if(valorPartida == 6){
                int novoPonto = pontoAntigo + 6;
                ponto = String.valueOf(novoPonto);
                textPonto1.setText(ponto);
            } else if(valorPartida == 9){
                int novoPonto = pontoAntigo + 9;
                ponto = String.valueOf(novoPonto);
                textPonto1.setText(ponto);
            } else if(valorPartida == 12){
                int novoPonto = pontoAntigo + 12;
                ponto = String.valueOf(novoPonto);
                textPonto1.setText(ponto);
            }

            if(Integer.parseInt(ponto) >= 12){
                textResultado.setText("Jogador 1 Venceu!!!");

                /* Salvar no banco
                    variáveis:
                        Nome do jogador 1 (que está logado) = textJogador1
                        Nome do jogador 2 (que foi editado o nome durante a partida) = textJogador2
                        Vitória (Boolean true porque o vencedor foi o é dono do login) = true
                        Identificador da partida (definido abaixo) = timeStamp
                */
                long timeStamp = new Timestamp(System.currentTimeMillis()).getTime();

                Random gerador = new Random();
                Integer numero = gerador.nextInt();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Partida partida = new Partida();
                partida.setId(user.getUid());
                partida.setJogador1(textJogador1.getText().toString());
                partida.setJogador2(textJogador2.getText().toString());
                partida.setVitoria(true);

                salvarPartida(partida, timeStamp);

                // Teste do timestamp
                Toast.makeText(TrucoActivity.this, String.valueOf(timeStamp), Toast.LENGTH_SHORT).show();
            }

            valorPartida = 1;
            btnPonto1.setText("+" + valorPartida + " jogador 1");
            btnPonto2.setText("+" + valorPartida + " jogador 2");
            btnTruco.setText("Truco");
        }
    }

    public void ponto2(View view) {
        int pontoAntigo = Integer.parseInt(textPonto2.getText().toString());
        int pontoAntigo1 = Integer.parseInt(textPonto1.getText().toString());

        if(pontoAntigo < 12 && pontoAntigo1 < 12) {
            String ponto = "0";

            if(valorPartida == 1) {
                int novoPonto = pontoAntigo + 1;
                ponto = String.valueOf(novoPonto);
                textPonto2.setText(ponto);
            } else if(valorPartida == 3){
                int novoPonto = pontoAntigo + 3;
                ponto = String.valueOf(novoPonto);
                textPonto2.setText(ponto);
            } else if(valorPartida == 6){
                int novoPonto = pontoAntigo + 6;
                ponto = String.valueOf(novoPonto);
                textPonto2.setText(ponto);
            } else if(valorPartida == 9){
                int novoPonto = pontoAntigo + 9;
                ponto = String.valueOf(novoPonto);
                textPonto2.setText(ponto);
            } else if(valorPartida == 12){
                int novoPonto = pontoAntigo + 12;
                ponto = String.valueOf(novoPonto);
                textPonto2.setText(ponto);
            }

            if(Integer.parseInt(ponto) >= 12){
                textResultado.setText("Jogador 2 Venceu!!!");

                /* Salvar no banco
                    variáveis:
                        Nome do jogador 1 (que está logado) = textJogador1
                        Nome do jogador 2 (que foi editado o nome durante a partida) = textJogador2
                        Vitória (Boolean false porque o vencedor foi o que não é dono do login) = false
                        Identificador da partida (definido abaixo) = timeStamp
                */
                long timeStamp = new Timestamp(System.currentTimeMillis()).getTime();

                Random gerador = new Random();
                Integer numero = gerador.nextInt();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                Partida partida = new Partida();
                partida.setId(user.getUid());
                partida.setJogador1(textJogador1.getText().toString());
                partida.setJogador2(textJogador2.getText().toString());
                partida.setVitoria(true);

                salvarPartida(partida, timeStamp);

                // Teste do timestamp
                Toast.makeText(TrucoActivity.this, String.valueOf(timeStamp), Toast.LENGTH_SHORT).show();
            }

            valorPartida = 1;
            btnPonto1.setText("+" + valorPartida + " jogador 1");
            btnPonto2.setText("+" + valorPartida + " jogador 2");
            btnTruco.setText("Truco");
        }
    }

    public void truco(View view) {
        if(valorPartida == 1) {
            valorPartida = valorPartida + 2;
            btnTruco.setText("6");
        } else if(valorPartida == 3 || valorPartida == 6){
            valorPartida = valorPartida + 3;
            btnTruco.setText(String.valueOf(valorPartida + 3));
        } else if(valorPartida == 9){
            valorPartida = valorPartida + 3;
            btnTruco.setText("Brinca não!!!");
        } else{
            Toast.makeText(TrucoActivity.this, "Impossível aumentar o valor da partida.", Toast.LENGTH_SHORT).show();
        }

        btnPonto1.setText("+" + valorPartida + " jogador 1");
        btnPonto2.setText("+" + valorPartida + " jogador 2");
    }

    public void jogarNovamente(View view) {
        valorPartida = 1;
        btnPonto1.setText("+" + valorPartida + " jogador 1");
        btnPonto2.setText("+" + valorPartida + " jogador 2");
        btnTruco.setText("Truco");
        textPonto1.setText("0");
        textPonto2.setText("0");
        textResultado.setText("Quem vencerá?!?");
    }

    public void voltarMenu(View view) {
        Intent intent = new Intent(TrucoActivity.this, MenuActivity.class);
        startActivity(intent);
    }

    private void salvarPartida(Partida partida, Long timeStamp) {
        mDatabase.child("partidas").child(String.valueOf(timeStamp)).setValue(partida)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(TrucoActivity.this, "Partida Salva!", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(TrucoActivity.this, "Erro ao Salvar a partida ", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
