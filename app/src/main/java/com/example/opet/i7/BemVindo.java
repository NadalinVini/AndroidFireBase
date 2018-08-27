package com.example.opet.i7;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class BemVindo extends Activity {

    private Button buttonB;
    private TextView textView;
    private Spinner spinner1;
    private CalendarView Calendar;
    private EditText TextNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bem_vindo);

        textView = findViewById(R.id.textView);
        spinner1 = findViewById(R.id.spinner1);
        Calendar = findViewById(R.id.Calendar);
        TextNome = findViewById(R.id.TextNome);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            textView.setText("Bem vindo, " + user.getEmail());
        }
    }
    public void RegistrarDados(View view)
    {
        TextNome.getText().toString();
        spinner1.getSelectedItem();
    }
}
