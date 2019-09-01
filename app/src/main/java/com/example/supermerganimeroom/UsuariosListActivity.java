package com.example.supermerganimeroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UsuariosListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private UsuarioAdapter usuarioAdapter;
    private RecyclerView.LayoutManager recycleLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);

        configurarRecyclerView();
    }

    private void configurarRecyclerView() {

        recyclerView=(RecyclerView)findViewById(R.id.recycleDosUsuarios);

        recycleLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recycleLayoutManager);

        usuarioAdapter = new UsuarioAdapter(MainActivity.usuarioDAO.getUsuarios());

        recyclerView.setAdapter(usuarioAdapter);

    }
}
