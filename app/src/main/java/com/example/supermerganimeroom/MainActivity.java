package com.example.supermerganimeroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static UsuarioDAO usuarioDAO;

    EditText logUsuario, logSenha;

    ImageView imageInicial;

    public static SharedPreferences logadoOuNao, usuarioLogado;

    public static SharedPreferences.Editor logiEditor, usuarioEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Shared Preferences

        logadoOuNao = getSharedPreferences("Logado", Context.MODE_PRIVATE);
        logiEditor = logadoOuNao.edit();

        usuarioLogado = getSharedPreferences("UserLogged", Context.MODE_PRIVATE);
        usuarioEditor = usuarioLogado.edit();

        if(logadoOuNao.contains("Logado") && logadoOuNao.getBoolean("Logado", true)){



            startActivity(new Intent(this, DepoisDoLoginActivity.class));

        }


        //Edit texts

        logUsuario = (EditText)findViewById(R.id.userLogin);
        logSenha = (EditText)findViewById(R.id.passLogin);


        //ImageView

        imageInicial = (ImageView)findViewById(R.id.imageInicial);


        //DAO

        usuarioDAO = Room.databaseBuilder(this, UsuarioDatabase.class, "userDB")
                .allowMainThreadQueries()
                .build()
                .usuarioDAO();



        //Um escutador para cada vez que o usuário "clicar" em um editexts diferente (mudar o foco)

        logUsuario.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {

                int seHa = usuarioDAO.existeUsuario(logUsuario.getText().toString());

                if(seHa == 1){

                    String im = usuarioDAO.getFoto(logUsuario.getText().toString());

                    Bitmap imBm = Conversores.StringToBitMap(im);

                    imageInicial.setImageBitmap(imBm);

                } else {

                    //Caso usar imagens importadas, usar o código comentado abaixo
                    //imageInicial.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.id_de_imagens_importadas));

                    imageInicial.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher_foreground));
                }

            }
        });

    }

    public void irAoCadastro(View view) {

        startActivity(new Intent(getApplicationContext(), CadastroActivity.class));

    }

    public void checkDados(View view) {

        int codeSeTem = usuarioDAO.existeUsuarioComEssaSenha(

                logUsuario.getText().toString(),
                logSenha.getText().toString()

        );

        if(codeSeTem==0){

            Toast.makeText(this, "Nome de usuário e/ou senha incorretos", Toast.LENGTH_SHORT).show();

        } else {

            logiEditor.putBoolean("Logado", true);
            logiEditor.apply();

            usuarioEditor.putInt("UserLogged", usuarioDAO.idDoSelecionado(logUsuario.getText().toString()));
            usuarioEditor.apply();

            Intent intent = new Intent(this, DepoisDoLoginActivity.class);
            //intent.putExtra("nameUser", logUsuario.getText().toString());

            startActivity(intent);

        }

    }
}
