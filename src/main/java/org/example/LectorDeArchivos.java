package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class LectorDeArchivos {

    private String resultado; // atributo
    private String pronostico; //atributo

    public LectorDeArchivos(String resultado, String pronostico) { //constructor
        this.resultado = resultado;
        this.pronostico = pronostico;
    }

    private static Ronda buscarRondaPorId(ArrayList<Ronda> rondas, int id) { // método que busca ronda
        for (Ronda ronda : rondas) { // Ronda = clase , ronda = elemento : rondas = lista -> por cada ronda de rondas hacer...
            if (ronda.getId() == id) {
                return ronda;
            }
        }
        return null; // si no encuentra la ronda...
    }

    // Agrega un partido a una ronda. Si la ronda no existe, la crea y la agrega a la lista de rondas.
    private static void agregarRondas(ArrayList<Ronda> listaDeRondas, int ronda, Partido partido) {
        if (buscarRondaPorId(listaDeRondas, ronda) == null) {
            ArrayList<Partido> listaDePartidos = new ArrayList<>();
            Ronda unaronda = new Ronda(ronda, listaDePartidos);
            unaronda.agregarPartido(partido);
            listaDeRondas.add(unaronda);
        } else {
            buscarRondaPorId(listaDeRondas, ronda).agregarPartido(partido);
        }
    }

    public Persona buscarPersonaPorId(ArrayList<Persona> personas, int id) { // método que busca persona
        for (Persona persona : personas) { // Persona = clase , persona = elemento : personas = lista -> por cada persona de personas hacer...
            if (persona.getId() == id) {
                return persona;
            }
        }
        return null;
    }

    // Agrega un pronostico a la persona. Si la persona no existe, la crea y la agrega a la lista de personas.
    private void agregarPersonas(ArrayList<Persona> listaDePersonas, int idpersona, String nombrepersona, Pronostico pronostico) {
        if (buscarPersonaPorId(listaDePersonas, idpersona) == null) {
            ArrayList<Pronostico> listaDePronosticos = new ArrayList<>();
            Persona persona = new Persona(idpersona, nombrepersona, listaDePronosticos);
            persona.agregarPronostico(pronostico);
            listaDePersonas.add(persona);
        } else {
            buscarPersonaPorId(listaDePersonas, idpersona).agregarPronostico(pronostico);
        }
    }

    private static Partido buscarPartidoPorId(Collection<Partido> partidos, int id) { // metodo que busca partido por id
        for (Partido partido : partidos) { // Partido = clase , partido = elemento : partidos = lista -> por cada partido de partidos hacer
            if (partido.getId() == id) {
                return partido;
            }
        }
        return null; // si no encuentra el partido
    }

    // Busca el equipo con el id dado
    private static Equipo buscarEquipoPorId(ArrayList<Equipo> listaDeEquipos, int id) {
        for (Equipo equipo : listaDeEquipos
        ) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // Crea un equipo con los parametros dados solo si no existe, y lo agrega a la lista dada. Devuelve el equipo.
    private static Equipo agregarEquipos(ArrayList<Equipo> listaDeEquipos, int id, String nombre) {
        if (buscarEquipoPorId(listaDeEquipos, id) == null) {
            Equipo equipo = new Equipo(id, nombre);
            listaDeEquipos.add(equipo);
            return equipo;
        } else {
            return buscarEquipoPorId(listaDeEquipos, id);
        }
    }

    public ArrayList<Ronda> leerResultados() throws IOException { //método para leer el archivo de resultados

        ArrayList<Ronda> listaDeRondas = new ArrayList<>(); // crea lista de rondas vacía
        ArrayList<Equipo> listaDeEquipos = new ArrayList<>();

        // Leer el archivo de resultados
        BufferedReader reader1 = new BufferedReader(new FileReader(resultado));
        reader1.readLine(); // ignora el encabezado
        String linea;

        while ((linea = reader1.readLine()) != null) {
            String[] partes = linea.split(",");
            int ronda = Integer.parseInt(partes[0]);
            int id = Integer.parseInt(partes[1]); // se usa integer pq lo que se lee es string, transforma el string en un int
            int idEquipo1 = Integer.parseInt(partes[2]);
            String nombreEquipo1 = partes[3];
            int golesEquipo1 = Integer.parseInt(partes[4]);
            int golesEquipo2 = Integer.parseInt(partes[5]);
            int idEquipo2 = Integer.parseInt(partes[6]);
            String nombreEquipo2 = partes[7];

            Equipo equipo1 = agregarEquipos(listaDeEquipos, idEquipo1, nombreEquipo1);
            Equipo equipo2 = agregarEquipos(listaDeEquipos, idEquipo2, nombreEquipo2);

            Partido partido = new Partido(id, equipo1, equipo2, golesEquipo1, golesEquipo2);

            agregarRondas(listaDeRondas, ronda, partido);
        }
        reader1.close();
        return listaDeRondas;
    }


    public ArrayList<Persona> leerPronostico(ArrayList<Partido> listaDePartidos) throws IOException {
        ArrayList<Persona> listaDePersonas = new ArrayList<>(); // crea lista de personas vacía

        BufferedReader reader = new BufferedReader(new FileReader(pronostico));
        reader.readLine();
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int idpersona = Integer.parseInt(parts[0]);
            String nombrepersona = parts[1];
            int idpartido = Integer.parseInt(parts[2]);
            int idGanador = Integer.parseInt(parts[4]);

            //si se encuentra el partido, crear el pronostico
            if (buscarPartidoPorId(listaDePartidos, idpartido) != null) {
                Partido partido = buscarPartidoPorId(listaDePartidos, idpartido);
                Pronostico pronostico = new Pronostico(partido, idGanador);
                agregarPersonas(listaDePersonas, idpersona, nombrepersona, pronostico);
            }
        }
        reader.close();
        return listaDePersonas;
    }
}