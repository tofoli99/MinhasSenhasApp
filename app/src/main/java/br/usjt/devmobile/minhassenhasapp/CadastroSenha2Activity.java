package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

public class CadastroSenha2Activity extends AppCompatActivity {
    private static final String TAG = "CadastroSenhaActivity";
    private TextInputEditText nome;
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText url;
    private TextInputEditText observacao;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha2);
        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        nome = (TextInputEditText)findViewById(R.id.nomeEditTextInput);
        usuario = (TextInputEditText)findViewById(R.id.usuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.senhaEditTextInput);
        url = (TextInputEditText)findViewById(R.id.urlEditTextInput);
        observacao = (TextInputEditText)findViewById(R.id.obsEditTextInput);
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

        new SaveSenhaAsyncTask().execute(newSenha);

        finish();
    }

    private class SaveSenhaAsyncTask extends AsyncTask<Senha, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(Senha... senha) {
            db.senhaDao().insertAll(senha);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroSenha2Activity.this,"Senha criada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
