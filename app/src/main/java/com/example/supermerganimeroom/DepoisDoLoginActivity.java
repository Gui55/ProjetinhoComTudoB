package com.example.supermerganimeroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DepoisDoLoginActivity extends AppCompatActivity {

    TextView boasVindas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depois_do_login);


        boasVindas = (TextView)findViewById(R.id.boasVindas);

        boasVindas.setText("Seja bem-vindo, "+getIntent().getExtras().get("nameUser").toString());

    }

    public void irAosUsuarios(View view) {

        startActivity(new Intent(this, UsuariosListActivity.class));

    }
}
