package br.usjt.devmobile.minhassenhasapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Senha implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    private String nome;

    @ColumnInfo(name ="user")
    public String usuario;

    @ColumnInfo(name = "password")
    public String senha;

    @ColumnInfo (name ="url")
    public String url;

    @ColumnInfo (name ="obs")
    public String observacao;

    @Override
    public String toString() {
        return  id  +": " + nome + "Usuário: "+ usuario + "Senha: " + senha;
    }



    public Senha(String nome) {
        this.nome = nome;
    }

    public Senha() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
