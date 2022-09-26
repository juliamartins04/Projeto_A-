package com.example.projeto;
// Criado por Júlia Martins
// Data: 16/09/2022
import java.io.Serializable;

public class Client implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private String date;
    private String amount;

// Classe cliente com gets e sets
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return name;
    }

}
