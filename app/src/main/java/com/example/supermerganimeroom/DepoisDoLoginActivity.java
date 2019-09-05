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

        //boasVindas.setText("Seja bem-vindo, "+getIntent().getExtras().get("nameUser").toString());

        boasVindas.setText("Seja bem-vindo, "+MainActivity.usuarioDAO.usernameDoSelecionado(MainActivity.usuarioLogado.getInt("UserLogged", 0)));

    }

    public void irAosUsuarios(View view) {

        startActivity(new Intent(this, UsuariosListActivity.class));

    }

    public void alterarDados(View view) {



    }

    public void irAosAnimes(View view) {

        startActivity(new Intent(this, AnimeListActivity.class));

    }

    public void metodoLogout(View view) {

        MainActivity.logiEditor.putBoolean("Logado", false);
        MainActivity.logiEditor.apply();

        MainActivity.usuarioEditor.putInt("UserLogged", 0);
        MainActivity.usuarioEditor.apply();

        startActivity(new Intent(this, MainActivity.class));

    }
}
