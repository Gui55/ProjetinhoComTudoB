package com.example.supermerganimeroom;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class CadastroActivity extends AppCompatActivity {

    private EditText[] campos;

    public ImageView fotoTirada;
    private static final int REQUEST_IMAGE_CAPTURE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        fotoTirada=(ImageView)findViewById(R.id.profilePhoto);


        campos = new EditText[]{

                findViewById(R.id.cadastroNome),
                findViewById(R.id.cadastroSobrenome),
                findViewById(R.id.cadastroUsuario),
                findViewById(R.id.cadastroEmail),
                findViewById(R.id.cadastroEndereco),
                findViewById(R.id.cadastroSenha),
                findViewById(R.id.confirmaSenha)

        };

    }

    public void tirarFoto(View view) {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(intent.resolveActivity(getPackageManager())!=null){

            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode==RESULT_OK){

            Bundle extras = data.getExtras();
            Bitmap foto = (Bitmap)extras.get("data");
            fotoTirada.setImageBitmap(foto);

        }

    }

    public void cadastrarUsuario(View view){

        int vazios = campos.length;

        for(int i=0;i<campos.length;i++){

            if(!campos[i].getText().toString().equals("")){

                vazios--;

            }

        }

        if(vazios==0 && fotoTirada.getDrawable() != null){ //Se nenhum campo estiver vazio e a imagem não estiver vazia

            if(campos[campos.length-1].getText().toString().equals(campos[campos.length-2].getText().toString())){

                //Se a senha e a confirmação baterem

                BitmapDrawable bd = (BitmapDrawable)fotoTirada.getDrawable();

                Bitmap imagem = bd.getBitmap();

                String imageSt = Conversores.BitMapParaString(imagem);

                MainActivity.usuarioDAO.insert(new Usuario(
                        campos[0].getText().toString(),
                        campos[1].getText().toString(),
                        campos[2].getText().toString(),
                        campos[3].getText().toString(),
                        campos[4].getText().toString(),
                        imageSt,
                        campos[5].getText().toString()
                ));

                Toast.makeText(this, "Você foi cadastrado com sucesso", Toast.LENGTH_SHORT).show();

                startActivity(new Intent(this, MainActivity.class));


            } else {

                Toast.makeText(this, "As senha e a confirmação não estão batendo", Toast.LENGTH_SHORT).show();

            }

        } else {
            Toast.makeText(this, "Faltam alguns dados", Toast.LENGTH_SHORT).show();
        }

    }
}
