package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.orhanobut.hawk.Hawk;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();
    }

    public void fazerLogin(View view){

        if(Hawk.contains("usuario")){
            String usuario = Hawk.get("usuario");
            Log.d(TAG,"Existe um usuário chamado: "+usuario);
        }

        Intent intent = new Intent(this, ListaSenhasActivity.class);
        startActivity(intent);
    }

    public void novoCadastro(View view){
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
