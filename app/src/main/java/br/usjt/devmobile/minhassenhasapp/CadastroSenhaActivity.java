package br.usjt.devmobile.minhassenhasapp;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.Random;
class CadastroSenhaActivity extends AppCompatActivity {
    private static final String TAG = "CadastroSenhaActivity";
    private TextInputEditText nome;
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText url;
    private TextInputEditText observacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);
        nome = (TextInputEditText)findViewById(R.id.nomeEditTextInput);
        usuario = (TextInputEditText)findViewById(R.id.usuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.senhaEditTextInput);
        url = (TextInputEditText)findViewById(R.id.urlEditTextLayout);
        observacao = (TextInputEditText)findViewById(R.id.obsEditTextLayout);
        Hawk.init(this).build();
    }
    public void cadastrarSenha(View view) {
        // TODO fazer o cadastro da senha
        Log.d(TAG,"Clicou em cadastrar senha!!!!!!!");
        Senha newSenha = new Senha();
        newSenha.setNome(nome.getText().toString());
        newSenha.setUsuario(usuario.getText().toString());
        newSenha.setSenha(senha.getText().toString());
        newSenha.setUrl(url.getText().toString());
        newSenha.setObservacao(observacao.getText().toString());

        SenhaDao senhaDao = null;

        senhaDao.insertAll(newSenha);

        Toast.makeText(this,"Senha cadastrada com Sucesso",Toast.LENGTH_LONG).show();

        finish();
    }
}
