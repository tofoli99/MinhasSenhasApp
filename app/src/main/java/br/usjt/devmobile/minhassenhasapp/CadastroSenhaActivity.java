package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;


public class CadastroSenhaActivity extends AppCompatActivity {

    private static final String TAG = "CadastroSenhaActivity";
    private TextInputEditText nome;
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText url;
    private TextInputEditText observacao;
    private AppDatabase db;
    private Senha senhaCorrente;
    private boolean alteracao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_senha);
        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        nome = (TextInputEditText)findViewById(R.id.cadNomeSenhaEditTextInput);
        usuario = (TextInputEditText)findViewById(R.id.cadUsuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.cadSenhaEditTextInput);
        url = (TextInputEditText)findViewById(R.id.cadUrlEditTextInput);
        observacao = (TextInputEditText)findViewById(R.id.cadObservacaoEditTextInput);

        Intent intent = getIntent();
        if(intent !=null && intent.hasExtra("senha")){
            alteracao = true;
            senhaCorrente = (Senha)intent.getSerializableExtra("senha");
            preencherCampos();
        }
    }

    private void preencherCampos(){
        nome.setText(senhaCorrente.getNome());
        usuario.setText(senhaCorrente.getUsuario());
        this.senha.setText(senhaCorrente.getSenha());
        url.setText(senhaCorrente.getUrl());
        observacao.setText(senhaCorrente.getObservacao());
    }

    public void cadastrarSenha(View view) {

        if(alteracao){
            senhaCorrente.setNome(nome.getText().toString());
            senhaCorrente.setUsuario(usuario.getText().toString());
            senhaCorrente.setSenha(this.senha.getText().toString());
            senhaCorrente.setUrl(url.getText().toString());
            senhaCorrente.setObservacao(observacao.getText().toString());
            new AlterarSenhaAsyncTask().execute(senhaCorrente);
        }else{
            senhaCorrente = new Senha(
                    nome.getText().toString(),
                    usuario.getText().toString(),
                    this.senha.getText().toString(),
                    url.getText().toString(),
                    observacao.getText().toString()
            );
            new SaveSenhaAsyncTask().execute(senhaCorrente);
        }


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
            Toast.makeText(CadastroSenhaActivity.this,"Senha criada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class AlterarSenhaAsyncTask extends AsyncTask<Senha, Void, Boolean>
    {
        @Override
        protected Boolean doInBackground(Senha... senha) {
            db.senhaDao().updateSenha(senha[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroSenhaActivity.this,"Senha criada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
