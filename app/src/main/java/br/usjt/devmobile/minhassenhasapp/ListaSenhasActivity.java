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

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListaSenhasActivity extends AppCompatActivity {

    private static final String TAG = "ListaSenhasActivity";
    private List<SenhaEntity> listaSenhas;
    private ListView senhasListView;
    private ArrayAdapter<SenhaEntity> adapter;
    private AppDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_senhas);
        db = Room.databaseBuilder(this.getApplicationContext(),
                AppDatabase.class, "database-name").build();
        listaSenhas = geraListaSenhas();
        senhasListView = findViewById(R.id.senhasListView);

        adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaSenhas);
        senhasListView.setAdapter(adapter);
        senhasListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SenhaEntity senhaSelecionada = listaSenhas.get(position);
                Toast.makeText(ListaSenhasActivity.this,"Senha selecionada: "
                        +senhaSelecionada.getNome(),Toast.LENGTH_SHORT).show();

//                Intent intent = new Intent(ListaSenhasActivity.this, DetalhesSenhaActivity.class);
//                intent.putExtra("senha",senhaSelecionada);
//                startActivity(intent);
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

    public void adicionarSenha(View v){
        Intent intent = new Intent(this,CadastroSenhaActivity.class);
        startActivity(intent);
    }

    public List<SenhaEntity> geraListaSenhas()  {

        List<SenhaEntity> lista = null;
        try {
            lista = new GetSenhasAsyncTask().execute().get();
            Log.d("TAMANHODALISTA","Tam: "+lista.size());
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }

    private class GetSenhasAsyncTask extends AsyncTask<Void, Void,List<SenhaEntity>>
    {
        @Override
        protected List<SenhaEntity> doInBackground(Void... url) {
            return db.senhaDao().getAll();
        }
    }
}