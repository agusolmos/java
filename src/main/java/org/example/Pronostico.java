package org.example;


public class Pronostico {

    private int id;

    private int idEquipo1;

    private int idGanador;

    private int idEquipo2;

    private Partido partido;

    public Pronostico(Partido partido, int ganador){
        this.id = partido.getId();
        this.partido = partido;
        this.idEquipo1 = partido.getIdEquipo1();
        this.idEquipo2 = partido.getIdEquipo2();
        this.idGanador = ganador;
    }

    //suma un punto por cada pronostico acertado
    public int Puntos(){
        int puntos = 0;
        if (partido.Ganador() == this.idGanador) {
            puntos += 1;
        }
        return puntos;
    }

}
