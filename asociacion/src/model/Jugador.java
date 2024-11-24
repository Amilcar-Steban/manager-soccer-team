package model;

import java.util.ArrayList;

public class Jugador {
    private static int contador=1;
    private final int id;
    private String name;
    private Equipo team;

    public Jugador() {
        this.id = contador++;
    }

    public Jugador(String name, Equipo team) {
        this.name = name;
        this.team = team;
        this.id = contador++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Equipo getTeam() {
        return team;
    }

    public void setTeam(Equipo team) {
        this.team = team;
    }

    public int getId() {
        return id;
    }

    public static Jugador findById(int id, ArrayList<Jugador> jugadores){
        Jugador player = new Jugador();
        boolean foundPlayer = false;
        if (!jugadores.isEmpty()) {
            for (int i = 0; i < jugadores.size(); i++) {
                if (jugadores.get(i).id==id) {
                    player = jugadores.get(i);
                    foundPlayer = true;
                    break;
                }
            }
            if (!foundPlayer) {
                System.out.println("No se encontró el jugador");
            }
        }else{
            System.out.println("No hay jugadores");
        }
        return player;
    }

    public static void showPlayers(ArrayList<Jugador> jugadores){
        if (!jugadores.isEmpty()) {
            System.out.printf("%-10s %-20s %-20s%n", "ID", "Player Name", "Team Name");
            System.out.println("------------------------------------------");
            for (Jugador jugador : jugadores) {
                System.out.printf("%-10d %-20s %-20s%n", jugador.getId(), jugador.getName(), jugador.getTeam().getName());
            }
        }else{
            System.out.println("No hay jugadores");
        }
    }

    public static void showPlayers(Jugador jugador){
        if (jugador!=null) {
            System.out.printf("%-10s %-20s %-20s%n", "ID", "Player Name", "Team Name");
            System.out.println("------------------------------------------");
            System.out.printf("%-10d %-20s %-20s%n", jugador.getId(), jugador.getName(), jugador.getTeam().getName());
        }else{
            System.out.println("El jugador no existe");
        }
    }
}
