package com.example.projeto.model;
// Criado por Júlia Martins
// Data: 16/09/2022
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projeto.Client;
import com.example.projeto.ClientDAO;
import com.example.projeto.R;

import java.io.Serializable;
import java.util.Calendar;

public class MainClient extends AppCompatActivity implements Serializable {

    private EditText name;
    private EditText description;
    private EditText date;
    private EditText amount;
    private ClientDAO dao;
    private Client client = null;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_client);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());
        name = findViewById(R.id.editName);
        description = findViewById(R.id.editDescription);
        amount = findViewById(R.id.editAmount);
        dao = new ClientDAO(this);

        button = (Button) findViewById(R.id.btn_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClassView();
            }
        });

        Intent it = getIntent();
        if (it.hasExtra("client")) {
            client = (Client) it.getSerializableExtra("client");
            name.setText(client.getName());
            description.setText(client.getDescription());
            dateButton.setText(client.getDate());
            amount.setText(client.getAmount());
        }
    }

    public void openClassView() {
        Intent intent = new Intent(this, View.class);
        startActivity(intent);
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    // calendário
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);

    }

    // retorno do calendário quando o cliente selecionar a opção desejada
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    // função do calendário
    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        return "JAN";
    }

    // data
    public void openDatePicker(View view) {
        datePickerDialog.show();
    }

    // função salvar e mensagem retornada ao cliente
    public void Save(View view) {
        if (client == null) {
            Client client = new Client();
            client.setName(name.getText().toString());
            client.setDescription(description.getText().toString());
            initDatePicker();
            client.setAmount(amount.getText().toString());
            long id = dao.insert(client);
            Toast.makeText(this, "client insert from id: " + id, Toast.LENGTH_SHORT).show();
        } else {
            client.setName(name.getText().toString());
            client.setDescription(description.getText().toString());
            initDatePicker();
            client.setAmount(amount.getText().toString());
            dao.update(client);
            Toast.makeText(this, "Client updated: ", Toast.LENGTH_SHORT).show();
        }
    }
}