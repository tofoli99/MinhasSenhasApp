package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
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
    private boolean alterar = false;
    private Button botaoAlterar;
    private Button botaoExcluir;

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
        botaoAlterar = findViewById(R.id.buttonAltararCadastro);
        botaoExcluir = findViewById(R.id.buttonDeletar);

        Intent intent = getIntent();
        if(intent != null && intent.hasExtra("senha")){
            senhaCorrente = (Senha)intent.getSerializableExtra("senha");
            alterar = true;
            botaoAlterar.setText("Alterar Registro de Senha");
            preecherCampos();
        }else{
            senhaCorrente = (Senha)intent.getSerializableExtra("excluir");
            botaoExcluir.setText("Excluir Registro de Senha");
            preecherCampos();
            deletarSenha();
        }

    }

    private void preecherCampos(){

        nome.setText(senhaCorrente.getNome());
        usuario.setText(senhaCorrente.getUsuario());
        senha.setText(senhaCorrente.getSenha());
        url.setText(senhaCorrente.getUrl());
        observacao.setText(senhaCorrente.getObservacao());

    }

    public boolean validaNome(){
        if(TextUtils.isEmpty(nome.getText())){
            nome.setError("Campo é obrigatório");
            return false;
        }
        else{
            nome.setError(null);
            return true;
        }
    }

    public boolean validaUsuario(){
        if(TextUtils.isEmpty(usuario.getText())){
            usuario.setError("Campo é obrigatório");
            return false;
        }
        else{
            usuario.setError(null);
            return true;
        }
    }

    public boolean validaSenha(){
        if(TextUtils.isEmpty(senha.getText())){
            senha.setError("Campo é obrigatorio");
            return false;
        }
        else{
            usuario.setError(null);
            return true;
        }
    }

    public void cadastrarSenha(View view) {

        if(alterar){

            senhaCorrente.setNome(nome.getText().toString());
            senhaCorrente.setUsuario(usuario.getText().toString());
            senhaCorrente.setSenha(senha.getText().toString());
            senhaCorrente.setUrl(url.getText().toString());
            senhaCorrente.setObservacao(observacao.getText().toString());

            if(!validaNome() | !validaUsuario() | !validaSenha())
                return;

            new AlterarSenhaAsyncTask().execute(senhaCorrente);
        }else{

            senhaCorrente = new Senha(
                    nome.getText().toString(),
                    usuario.getText().toString(),
                    this.senha.getText().toString(),
                    url.getText().toString(),
                    observacao.getText().toString()
            );

            new CriarSenhaAsyncTask().execute(senhaCorrente);

        }
    }

    public void deletarSenha(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Excluir")
                .setMessage("Deseja deletar esse registro")
                .setNegativeButton("Nao", null)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeletarSenhaAsyncTask().execute(senhaCorrente);
                    }
                });

    }


    private class CriarSenhaAsyncTask extends AsyncTask<Senha, Void, Boolean>
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
        protected Boolean doInBackground(Senha... senhas) {
            db.senhaDao().updateSenha(senhas[0]);
            return true;
        }

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroSenhaActivity.this,"Senha foi alterada com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private class DeletarSenhaAsyncTask extends  AsyncTask<Senha, Void, Boolean>
    {
        @Override
        protected  Boolean doInBackground(Senha... senhas){ db.senhaDao().delete(senhas[0]);return true;}

        @Override
        protected void onPostExecute(Boolean sucess){
            Toast.makeText(CadastroSenhaActivity.this,"Senha foi excluida com sucesso!",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}
