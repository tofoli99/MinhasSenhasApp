package br.usjt.devmobile.minhassenhasapp;

import java.io.Serializable;

public class Senha implements Serializable {
    private String nome;

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

    @Override
    public String toString() {
        return nome;
    }
}
