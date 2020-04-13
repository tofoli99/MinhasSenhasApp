package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private RoundedImageView imagem;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        usuario = findViewById(R.id.emailEditTextInput);
        senha = findViewById(R.id.passwordEditTextInput);
        imagem = findViewById(R.id.userImageMain);
        layout = findViewById(R.id.layoutImage);
        if(Hawk.contains("imagem")){
            String filePath = Hawk.get("imagem",null);
            Log.d(TAG,"Chegou aqui: "+ filePath);
            layout.setVisibility(View.VISIBLE);
            imagem.setImageURI(Uri.fromFile(new File(filePath)));
        }

    }

    public void fazerLogin(View view){



        if(usuario.getText().toString().equals(Hawk.get("usuario")) &&
            senha.getText().toString().equals(Hawk.get("senha"))){

            Intent intent = new Intent(this, ListasSenhasActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this,"Usuário ou senha incorretos!",Toast.LENGTH_SHORT).show();
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

    @Override
    public void onResume() {
        super.onResume();
        if(Hawk.contains("imagem")){
            String filePath = Hawk.get("imagem",null);
            Log.d(TAG,"Chegou aqui: "+ filePath);
            layout.setVisibility(View.VISIBLE);
            imagem.setImageURI(Uri.fromFile(new File(filePath)));
        }
    }
}
