package com.example.projeto;
// Criado por Júlia Martins
// Data: 20/09/2022
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private Connection connection;
    private SQLiteDatabase banco;

    public ClientDAO(Context context) {
        connection = new Connection(context);
        banco = connection.getWritableDatabase();
    }

    //método do CRUD responsável por inserir o cliente
    public long insert (Client client) {
        ContentValues values = new ContentValues();
        values.put("name", client.getName());
        values.put("description", client.getDescription());
        values.put("date", client.getDate());
        values.put("amount", client.getAmount());
        return banco.insert("client", null, values);
    }

    //método do CRUD responsável por ver os clientes
    public List<Client>viewAll () {
        List<Client> client = new ArrayList<>();
        Cursor cursor = banco.query("client", new String[] {"id", "name", "description", "date", "amount"}, null, null, null, null, null );
        while(cursor.moveToNext()) {
            Client a = new Client();

            a.setId(cursor.getInt(0));
            a.setName(cursor.getString(1));
            a.setDescription(cursor.getString(2));
            a.setDate(cursor.getString(3));
            a.setAmount(cursor.getString(4));
            client.add(a);
        }
        return client;
    }

    //método do CRUD responsável por deletar os clientes
    public void delete(Client a) {
        banco.delete("client", "id = ?", new String[]{a.getId().toString()});
    }

    //método do CRUD responsável por criar clientes
    public void update(Client client) {
        ContentValues values = new ContentValues();
        values.put("name", client.getName());
        values.put("description", client.getDescription());
        values.put("date", client.getDate());
        values.put("amount", client.getAmount());
        banco.update("client", values, "id = ?", new String[]{client.getId().toString()});
    }
}
