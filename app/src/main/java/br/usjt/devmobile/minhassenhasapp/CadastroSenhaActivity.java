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

public class CadastroSenhaActivity extends AppCompatActivity {
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
        url = (TextInputEditText)findViewById(R.id.urlEditTextInput);
        observacao = (TextInputEditText)findViewById(R.id.observacaoEditTextInput);

    }

    public void cadastrarSenha(View view){
        // TODO fazer o cadastro
        Log.d(TAG,"Clicou no fazer cadastro de senha!");

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        final SenhaEntity senhaEntity = new SenhaEntity();
        senhaEntity.setNome(nome.getText().toString());
        senhaEntity.setUsuario(usuario.getText().toString());
        senhaEntity.setSenha(senha.getText().toString());
        senhaEntity.setUrl(url.getText().toString());
        senhaEntity.setObservacao(observacao.getText().toString());

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.senhaDao().insertAll(senhaEntity);
            }
        });

        Toast.makeText(this,"Cadastro Realizado com Sucesso",Toast.LENGTH_LONG).show();
        //Log.d(TAG,db.senhaDao().getAll().toString());
        finish();

    }

}
