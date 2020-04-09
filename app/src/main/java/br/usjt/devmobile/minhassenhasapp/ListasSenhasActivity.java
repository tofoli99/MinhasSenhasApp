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
import java.util.concurrent.ExecutionException;

public class ListasSenhasActivity extends AppCompatActivity {

    private static final String TAG = "ListaSenhasActivity";
    private List<Senha> listaSenhas;
    private ListView senhasListView;
    private AppDatabase db;
    ArrayAdapter <Senha> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_senhas);
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();
        listaSenhas = geraListaSenhas();
        Log.d("ACTIVITY", "tam: "+listaSenhas.size());

        senhasListView = findViewById(R.id.senhasListView);


        adapter =
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

    @Override
    protected void onResume(){
        super.onResume();
        listaSenhas = geraListaSenhas();
        if(adapter != null) {
            adapter.clear();
            adapter.addAll(listaSenhas);
            adapter.notifyDataSetChanged();
        }
    }

//    public void adicionarSenha(View v){
//
//        final AppDatabase db = Room.databaseBuilder(getApplicationContext(),
//                AppDatabase.class, "database-name").build();
//
//        final Senha senha = new Senha();
//        Random random = new Random();
//
//        senha.setNome("senha "+random.nextInt(1000));
//        senha.setUsuario("usuario "+random.nextInt(1000));
//        senha.setObservacao("observacao "+random.nextInt(1000));
//        senha.setUrl("url "+random.nextInt(1000));
//        senha.setSenha("senha "+random.nextInt(1000));
//
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                db.senhaDao().insertAll(senha);
//                for(Senha s: db.senhaDao().getAll()){
//                    listaSenhas.add(s);
//                    Log.d("DB","Senha: "+s.toString());
//                }
//            }
//        });
//
//
//    }

    public void telaAdicionarSenha(View view){
        Intent intent = new Intent(this, CadastroSenha2Activity.class);
        startActivity(intent);
    }

    public List<Senha> geraListaSenhas()  {

        List<Senha> lista = null;
        try {
            lista = new GetSenhasAsyncTask().execute().get();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }

    private class GetSenhasAsyncTask extends AsyncTask<Void, Void,List<Senha>>
    {
        @Override
        protected List<Senha> doInBackground(Void... url) {
            return db.senhaDao().getAll();
        }
    }
}
