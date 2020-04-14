package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

public class CadastroUsuarioActivity extends AppCompatActivity {

    private static final String TAG = "CadastroUsuarioActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private TextInputEditText pergunta;
    private TextInputEditText resposta;
    private RoundedImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);
        usuario = (TextInputEditText)findViewById(R.id.usuarioEditTextInput);
        senha = (TextInputEditText)findViewById(R.id.senhaEditTextInput);
        pergunta = (TextInputEditText)findViewById(R.id.perguntaEditTextInput);
        resposta = (TextInputEditText)findViewById(R.id.respostaEditTextInput);
        imagem = findViewById(R.id.userImage);
        Hawk.init(this).build();
    }

    public void cadastrarUsuario(View view){
        // TODO fazer o cadastro
        Log.d(TAG,"Clicou no fazer cadastro!");

        Hawk.put("usuario", usuario.getText().toString());
        Hawk.put("senha", senha.getText().toString());
        Hawk.put("pergunta", pergunta.getText().toString());
        Hawk.put("resposta", resposta.getText().toString());

        Toast.makeText(this,"Cadastro Realizado com Sucesso",Toast.LENGTH_LONG).show();

        finish();

    }


    public void capturarImagem(View view) {

        ImagePicker.Companion.with(this)
                .crop()
                .compress(1024)
                .maxResultSize(1080, 1080)
                .start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            Uri fileUri = data.getData();
            imagem.setImageURI(fileUri);

            Hawk.put("imagem",fileUri.getPath());


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, "Erro na captura da imagem", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Captura de imagem cancelada", Toast.LENGTH_SHORT).show();
        }


    }

}
