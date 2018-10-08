package com.example.opet.i7.model;

public class Truco {

    private String id;
    private String vitoria;

    public Truco() {
    }

    public Truco(String id, String vitoria) {
        this.id = id;
        this.vitoria = vitoria;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVitoria() {
        return vitoria;
    }

    public void setVitoria(String vitoria) {
        this.vitoria = vitoria;
    }
}
