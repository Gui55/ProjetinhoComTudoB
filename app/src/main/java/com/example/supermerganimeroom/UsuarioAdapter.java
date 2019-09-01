package com.example.supermerganimeroom;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UsuarioAdapter extends RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder> {

    private List<Usuario> usuarios;

    public UsuarioAdapter(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }


    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_list_row, parent, false);

        return new UsuarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {

        Usuario usuario = usuarios.get(position);

        //Definição do id e do nome de usuário

        holder.id.setText(String.valueOf(usuario.getId()));

        holder.usuario.setText(usuario.getNomeDeUsuario());


        //Definição da foto

        String im = usuario.getFotoPerfil();

        Bitmap imBm = Conversores.StringToBitMap(im);

        holder.foto.setImageBitmap(imBm);

    }

    @Override
    public int getItemCount() {
        return usuarios.size();
    }

    protected class UsuarioViewHolder extends RecyclerView.ViewHolder {

        TextView id, usuario;
        ImageView foto;

        public UsuarioViewHolder(@NonNull View itemView) {
            super(itemView);

            id = (TextView)itemView.findViewById(R.id.userIdView);
            usuario = (TextView)itemView.findViewById(R.id.usernameView);

            foto = (ImageView)itemView.findViewById(R.id.userIcon);

        }
    }


}
