package org.example;
import java.util.ArrayList;

public class Ronda {

    private int id; // atributo
    private ArrayList<Partido> listaDePartidos; //atributo

    public Ronda(int id, ArrayList<Partido> listadepartidos) { // constructor
        this.id = id;
        this.listaDePartidos = listadepartidos;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Partido> getListaDePartidos() {
        return listaDePartidos;
    }

    public void agregarPartido (Partido partido){

        this.listaDePartidos.add(partido);
    }
}
