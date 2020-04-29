package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetalhesSenhaActivity extends AppCompatActivity {

    private Senha senha;
    private TextView textViewNome;
    private TextView textViewUsuario;
    private TextView textViewSenha;
    private TextView textViewUrl;
    private TextView textViewObservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        senha = (Senha)intent.getSerializableExtra("senha");
        textViewNome = findViewById(R.id.textViewNomeValue);
        textViewUsuario = findViewById(R.id.textViewUsuarioValue);
        textViewSenha = findViewById(R.id.textViewSenhaValue);
        textViewUrl = findViewById(R.id.textViewUrlValue);
        textViewObservacao = findViewById(R.id.textViewObsValue);

        textViewNome.setText(senha.getNome());
        textViewUsuario.setText(senha.getUsuario());
        textViewSenha.setText(senha.getSenha());
        textViewUrl.setText(senha.getUrl());
        textViewObservacao.setText(senha.getObservacao());
    }

    public void deletarSenha(View view) {
        // TODO Os alunos devem implementar isso como trabalho da semana
        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        intent.putExtra("excluir",senha);
        startActivity(intent);
        finish();
    }

    public void alterarSenha(View view) {
        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        intent.putExtra("senha",senha);
        startActivity(intent);
        finish();
    }

    public void fecharTela(View view) {
        finish();
    }
}
