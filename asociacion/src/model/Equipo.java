package model;

import java.util.ArrayList;

public class Equipo {
    private static int contador=1;
    private final int id;
    private String name;

    public Equipo(){
        this.id = contador++;
    }

    public Equipo(String name){
        this.name = name;
        this.id = contador++;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static Equipo findById(int id, ArrayList<Equipo> equipos){
        Equipo player = new Equipo();
        boolean foundTeam = false;
        if (!equipos.isEmpty()) {
            for (int i = 0; i < equipos.size(); i++) {
                if (equipos.get(i).id==id) {
                    player = equipos.get(i);
                    foundTeam = true;
                    break;
                }
            }
            if (!foundTeam) {
                System.out.println("No se encontró el equipo");
            }
        }else{
            System.out.println("No hay equipos");
        }
        return player;
    }

    public static void showEquipos(ArrayList<Equipo> equipos){
        if (!equipos.isEmpty()) {
            System.out.printf("%-10s %-20s%n", "ID", "Team Name");
            System.out.println("------------------------------");
            for (Equipo equipo : equipos) {
                System.out.printf("%-10d %-20s%n", equipo.getId(), equipo.getName());
            }
        }else{
            System.out.println("No hay equipos");
        }
    }

    public static void showEquipos(Equipo equipo){
        if (equipo!=null) {
            System.out.printf("%-10s %-20s%n", "ID", "Team Name");
            System.out.println("------------------------------");
            System.out.printf("%-10d %-20s%n", equipo.getId(), equipo.getName());
        }
        else{
            System.out.println("No hay equipos");
        }
    }
}
