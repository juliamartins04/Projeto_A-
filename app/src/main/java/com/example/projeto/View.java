package com.example.projeto;
// Criado por Júlia Martins
// Data: 20/09/2022
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.projeto.model.MainClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class View extends AppCompatActivity {

    private ListView listView;
    private ClientDAO dao;
    private List<Client> client;
    private List<Client> clientFilter = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        getSupportActionBar().show();

        listView =  findViewById(R.id.list_clients);
        dao = new ClientDAO(this);
        client = dao.viewAll();
        clientFilter.addAll(client);
        ArrayAdapter<Client> adapter = new ArrayAdapter<Client>(this, android.R.layout.simple_list_item_1, clientFilter);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
    }

    public void Register(MenuItem item) {
        Intent intent = new Intent(this, MainClient.class);
        startActivity(intent);
    }

    public void delete(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Client clientDelete = clientFilter.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atention!")
                .setMessage("You want delete the client?")
                .setNegativeButton("NO", null)
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clientFilter.remove(clientDelete);
                        client.remove(clientDelete);
                        dao.delete(clientDelete);
                        listView.invalidateViews();
                    }
                }).create();
        dialog.show();
    }

    public void Update(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        final Client clientUpdate = clientFilter.get(menuInfo.position);
        Intent it = new Intent(this, MainClient.class);
        it.putExtra("client", clientUpdate);
        startActivity(it);
    }

    @Override
    public void onResume() {
        super.onResume();
        client = dao.viewAll();
        clientFilter.clear();
        clientFilter.addAll(client);
        listView.invalidateViews();
    }

    // menu de opções
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_main, menu);

        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                searchClient(s);
                return false;
            }
        });
        return true;
    }

    public void onCreateContextMenu(ContextMenu menu, android.view.View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v ,menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_context, menu);
    }

    // Procurar cliente no menu
    public void searchClient(String name) {
        clientFilter.clear();
        for(Client a : client) {
            if (a.getName().toLowerCase().contains(name.toLowerCase())) {
                clientFilter.add(a);
            }
        }
        listView.invalidateViews();
    }
}