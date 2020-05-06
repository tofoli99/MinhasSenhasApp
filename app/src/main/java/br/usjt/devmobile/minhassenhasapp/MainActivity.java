package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.orhanobut.hawk.Hawk;
import com.rishabhharit.roundedimageview.RoundedImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity  {

    private static final String TAG = "MainActivity";
    private TextInputEditText usuario;
    private TextInputEditText senha;
    private RoundedImageView imagemMain;
    private LinearLayout layoutImagem;

    //facebook
    private static final int RESULT_PROFILE_ACTIVITY = 1;
    private static final int RESULT_POSTS_ACTIVITY = 2;
    private static final int RESULT_PERMISSIONS_ACTIVITY = 3;

    private static final String DEFAULT_FB_APP_ID = "ENTER_YOUR_FB_APP_ID_HERE";
    private CallbackManager callbackManager;
    private LoginButton facebook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Hawk.init(this).build();

        usuario = findViewById(R.id.emailEditTextInput);
        senha = findViewById(R.id.passwordEditTextInput);
        imagemMain = findViewById(R.id.userImageMain);
        layoutImagem = findViewById(R.id.layoutImagemMain);

        //Facebook Login
        callbackManager = CallbackManager.Factory.create();

        colocaImagem();

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

    }

    private void colocaImagem(){
        if(Hawk.contains("imagem")){
            String path = Hawk.get("imagem");
            imagemMain.setImageURI(Uri.fromFile(new File(path)));
            layoutImagem.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        colocaImagem();
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

    public void fazerLoginFacebook(View view) {
        signIn();
    }


    private void signIn() {
        // Launches the sign in flow, the result is returned in onActivityResult
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if(isLoggedIn)
        {
            Intent intent = new Intent(this, ListasSenhasActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);

    }

}
