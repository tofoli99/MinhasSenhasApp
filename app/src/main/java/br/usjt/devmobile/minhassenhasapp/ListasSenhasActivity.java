package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ListasSenhasActivity extends AppCompatActivity {

    private static final String TAG = "ListaSenhasActivity";
    private List<Senha> listaSenhas;
    private ListView senhasListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_senhas);
        listaSenhas = geraListaSenhas();
        senhasListView = findViewById(R.id.senhasListView);

        ArrayAdapter <Senha> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaSenhas);
        senhasListView.setAdapter(adapter);
        senhasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Senha senhaSelecionada = listaSenhas.get(position);
                Toast.makeText(ListasSenhasActivity.this,"Senha selecionada: "+senhaSelecionada.getNome(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListasSenhasActivity.this, DetalhesSenhaActivity.class);
                intent.putExtra("senha",senhaSelecionada);
                startActivity(intent);
            }
        });
    }


    public void adicionarSenha(View v){

        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();



        final User user = new User();
        Random random = new Random();

        user.firstName = "elcio "+random.nextInt(1000);
        user.lastName = "abrahao "+random.nextInt(1000);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.userDao().insertAll(user);
                for(User u: db.userDao().getAll()){
                    Log.d("DB","User: "+u.toString());
                }
            }
        });


    }

    public List<Senha> geraListaSenhas(){
        ArrayList<Senha> lista = new ArrayList<>();
        lista.add(new Senha("Senha 1"));
        lista.add(new Senha("Senha 2"));
        lista.add(new Senha("Senha 3"));
        lista.add(new Senha("Senha 4"));
        lista.add(new Senha("Senha 5"));
        lista.add(new Senha("Senha 6"));
        lista.add(new Senha("Senha 7"));
        lista.add(new Senha("Senha 8"));
        lista.add(new Senha("Senha 9"));
        lista.add(new Senha("Senha 10"));
        lista.add(new Senha("Senha 11"));
        lista.add(new Senha("Senha 12"));
        lista.add(new Senha("Senha 13"));
        lista.add(new Senha("Senha 14"));
        lista.add(new Senha("Senha 15"));
        lista.add(new Senha("Senha 16"));
        lista.add(new Senha("Senha 17"));
        return lista;
    }
}
