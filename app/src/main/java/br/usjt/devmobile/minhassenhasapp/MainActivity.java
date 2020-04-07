package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextInputEditText usuario;
    private TextInputEditText senha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        usuario = findViewById(R.id.emailEditTextInput);
        senha = findViewById(R.id.passwordEditTextInput);

    }
// tem que usar o metodo equals porque o "==" é usado para comparar duas strings e como uma das variaveis que esta sendo comparada é um objeto, o método equals é o melhor para ser utilizado  este caso.
    public void fazerLogin(View view) {
        Log.d(TAG,"passou pelo login");
        if (usuario.getText().toString().equals(Hawk.get("usuario")) && senha.getText().toString().equals(Hawk.get("senha"))) {
            Intent intent = new Intent(this, ListaSenhasActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "senha ou usuario invalido", Toast.LENGTH_LONG).show();
        }

            }

    public void novoCadastro(View view){
        if(Hawk.contains("usuario")){
            Toast.makeText(this,"Usuário já cadastrado!",Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = new Intent(this, CadastroUsuarioActivity.class);
            startActivity(intent);
        }


    }


}
