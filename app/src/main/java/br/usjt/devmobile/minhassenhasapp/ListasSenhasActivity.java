package br.usjt.devmobile.minhassenhasapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ListasSenhasActivity extends AppCompatActivity {

    private static final String TAG = "ListaSenhasActivity";
    private List<Senha> listaSenhas;
    private ListView senhasListView;
    private ArrayAdapter <Senha> adapter;
    private AppDatabase db;
    private static String pesquisa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_senhas);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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
                Senha senhaSelecionada = listaSenhas.get(position);
                Toast.makeText(ListasSenhasActivity.this,"Senha selecionada: "
                        +senhaSelecionada.getNome(),Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(ListasSenhasActivity.this, DetalhesSenhaActivity.class);
                intent.putExtra("senha",senhaSelecionada);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista_senhas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_crescente:
                geraListaSenhasOrdemCrescente();
                break;
            case R.id.action_decrescente:
                geraListaSenhasOrdemDecrescente();
                break;
            case R.id.action_busca:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Title");

                final EditText input = new EditText(this);

                input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                builder.setView(input);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        pesquisa = input.getText().toString();
                        geraListaSenhaPesquisa();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                break;
            case R.id.action_original:
                geraListaOriginal();
                break;
            default:
                break;
        }

        return true;
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

    private void geraListaOriginal(){
        listaSenhas = geraListaSenhas();
        if(adapter != null) {
            adapter.clear();
            adapter.addAll(listaSenhas);
            adapter.notifyDataSetChanged();
        }
    }

    private void geraListaSenhasOrdemCrescente(){
        listaSenhas = geraListaSenhasAsc();
        if(adapter != null) {
            adapter.clear();
            adapter.addAll(listaSenhas);
            adapter.notifyDataSetChanged();
        }
    }

    private void geraListaSenhasOrdemDecrescente(){
        listaSenhas = geraListaSenhasDesc();
        if(adapter != null){
            adapter.clear();
            adapter.addAll(listaSenhas);
            adapter.notifyDataSetChanged();
        }
    }

    private void geraListaSenhaPesquisa(){
        listaSenhas = geraListaSenhaPesq();
        if(adapter != null){
            adapter.clear();
            adapter.addAll(listaSenhas);
            adapter.notifyDataSetChanged();
        }
    }

    public void adicionarSenha(View v){
        Intent intent = new Intent(this,CadastroSenhaActivity.class);
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

    public List<Senha> geraListaSenhasAsc()  {

        List<Senha> lista = null;
        try {
            lista = new GetSenhasAsyncTaskAsc().execute().get();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }

    public List<Senha> geraListaSenhasDesc() {

        List<Senha> lista = null;
        try {
            lista = new GetSenhasAsyncTaskDesc().execute().get();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }

    public List<Senha> geraListaSenhaPesq(){
        List<Senha> lista = null;
        try {
            lista = new GetSenhasAsyncTaskSearch().execute().get();
        }catch (ExecutionException e1){
            e1.printStackTrace();
        }catch (InterruptedException e2){
            e2.printStackTrace();
        }
        return lista;
    }

    private class GetSenhasAsyncTaskAsc extends AsyncTask<Void, Void,List<Senha>>
    {
        @Override
        protected List<Senha> doInBackground(Void... url) {
            return db.senhaDao().getAllAsc();
        }
    }

    private class GetSenhasAsyncTaskDesc extends AsyncTask<Void, Void,List<Senha>>
    {
        @Override
        protected List<Senha> doInBackground(Void... url) {
            return db.senhaDao().getAllDesc();
        }
    }

    private class GetSenhasAsyncTaskSearch  extends AsyncTask<Void, Void,List<Senha>>
    {
        @Override
        protected List<Senha> doInBackground(Void... url) {
            return db.senhaDao().getFiltroNome(pesquisa);
        }
    }
}
