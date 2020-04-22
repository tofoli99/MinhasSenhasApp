package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class DetalhesSenhaActivity extends AppCompatActivity {

    private Senha senha;
    private TextView textViewSenha;
    private TextView textViewUsuarioValue;
    private TextView textViewSenhaValue;
    private TextView textViewUrlValue;
    private TextView textViewObsValue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe_senha);
        Intent intent = getIntent();
        senha = (Senha)intent.getSerializableExtra("senha");
        textViewSenha = findViewById(R.id.textViewNomeValue);
        textViewUsuarioValue = findViewById(R.id.textViewUsuarioValue);
        textViewSenhaValue = findViewById(R.id.textViewSenhaValue);
        textViewUrlValue = findViewById(R.id.textViewUrlValue);
        textViewObsValue = findViewById(R.id.textViewObsValue);

        textViewSenha.setText(senha.getNome());
        textViewUsuarioValue.setText(senha.getUsuario());
        textViewSenhaValue.setText(senha.getSenha());
        textViewUrlValue.setText(senha.getUrl());
        textViewObsValue.setText(senha.getObservacao());
    }


    public void fecharActivity(View view) {
        finish();
    }

    public void alterarSenha(View view) {

        senha.setNome(textViewSenha.getText().toString());
        senha.setUsuario(textViewUsuarioValue.getText().toString());
        senha.setSenha(textViewSenhaValue.getText().toString());
        senha.setUrl(textViewUrlValue.getText().toString());
        senha.setObservacao(textViewObsValue.getText().toString());

        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        intent.putExtra("senha",senha);
        startActivity(intent);
        finish();
    }

    public void deletarSenha(View view) {
        // Mostrar aqui um dialog e confirmar a deleção da senha
        finish();
    }
}
