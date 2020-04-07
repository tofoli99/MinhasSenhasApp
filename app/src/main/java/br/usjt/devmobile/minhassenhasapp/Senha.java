package br.usjt.devmobile.minhassenhasapp;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "senha")
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
