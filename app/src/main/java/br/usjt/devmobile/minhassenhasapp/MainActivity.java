package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    List contas = new ArrayList();
    private TextInputEditText loginUsuario;
    private TextInputEditText loginSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginUsuario = (TextInputEditText)findViewById(R.id.emailEditTextInput);
        loginSenha = (TextInputEditText)findViewById(R.id.passwordEditTextInput);
        Hawk.init(this).build();
    }

    public void fazerLogin(View view){
        Hawk.put("loginUsuario", loginUsuario.getText().toString());
        Hawk.put("loginSenha", loginSenha.getText().toString());
        if(Hawk.contains("usuario")){
            String usuario = Hawk.get("usuario");
            String senha = Hawk.get("senha");
            String pergunta = Hawk.get("pergunta");
            String resposta = Hawk.get("resposta");
            Log.d(TAG,"usuario " + usuario);
            Log.d(TAG,"senha " + senha);
            Log.d(TAG,"loginUsuario " + Hawk.get("loginUsuario"));
            Log.d(TAG,"loginSenha " + Hawk.get("loginSenha"));
            if(usuario.equals(Hawk.get("loginUsuario")) && senha.equals(Hawk.get("loginSenha")) && Hawk.get("loginUsuario") != null && Hawk.get("loginSenha") != null){
                Intent intent = new Intent(this, ListaSenhasActivity.class);
                startActivity(intent);
            } else {
                DialogFragment newFragment = new Alertando();
                newFragment.show(getSupportFragmentManager(), "alert");
            }
        }
    }

    public void novoCadastro(View view){
        Intent intent = new Intent(this, CadastroUsuarioActivity.class);
        startActivity(intent);
    }
}
