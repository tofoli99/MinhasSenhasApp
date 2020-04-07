package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class ListaSenhasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_senhas);
    }
    public void CadastrarSenhaNova(View view){
        Log.d(null,"Clicou no fazer cadastro de senha deu certo!");

        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        startActivity(intent);
    }
}
