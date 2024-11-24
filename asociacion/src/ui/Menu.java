package ui;

import java.util.ArrayList;
import java.util.Scanner;
import model.Equipo;
import model.Jugador;

public class Menu {
    private static final Scanner sc = new Scanner(System.in);
    private static final ArrayList<Equipo> teams = new ArrayList<>();
    private static final ArrayList<Jugador> players = new ArrayList<>();

    public static void menu(){
        System.out.println("""
                \tMenu
                1. Crear jugador
                2. Crear equipo
                3. Asignar jugador a equipo
                4. Mostrar lista de jugadores
                5. Mostrar lista de equipos
                6. Salir
                """);
    }

    public static void options(){
        int option;
        do {
            menu();
            option = sc.nextInt();
            switch (option) {
                case 1 -> createPlayer();
                case 2 -> createTeam();
                case 3 -> {
                    clear();
                    Jugador.showPlayers(players);
                    int opPlayer = sc.nextInt();
                    Jugador newPlayer = Jugador.findById(opPlayer, players);
                    if(newPlayer!= null){
                        clear();
                        Equipo.showEquipos(teams);
                        int opTeam = sc.nextInt();
                        Equipo newTeam = Equipo.findById(opTeam, teams);
                        if(newTeam!= null){
                            setPlayerInTeam(newPlayer,newTeam);
                            clear();
                            Jugador.showPlayers(players);
                        }else{
                            System.out.println("No existe el Equipo");
                        }
                    }else{
                        System.out.println("No existe el jugador");
                    }
                }
                case 4 -> {clear();Jugador.showPlayers(players);}
                case 5 -> {clear();Equipo.showEquipos(teams);}
                case 6 -> {clear();System.out.println("Bye..");}
                default -> System.out.println("Opcion incorrecta");
            }
        } while (option!=6);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void init(){
        clear();
        Equipo team1 = new Equipo("Real Madrid");
        teams.add(team1);
        Equipo team2 = new Equipo("Barcelona");
        teams.add(team2);
        options();
        
    }

    public static void setPlayerInTeam(Jugador player, Equipo team){
        player.setTeam(team);
    }

    public static void createTeam(){
        clear();
        Equipo newTeam = new Equipo();
        System.out.print("Nombre del equipo: ");
        String nameTeam = sc.next();

        newTeam.setName(nameTeam);
        teams.add(newTeam);
        clear();
        System.out.println("Equipo creado correctamente.\n");
        Equipo.showEquipos(teams);
    }

    public static void createPlayer(){
        clear();
        Jugador newPlayer = new Jugador();
        System.out.print("Nombre de jugador: ");
        sc.nextLine(); 
        String name = sc.nextLine();
    
        if (!teams.isEmpty()) {
            Equipo.showEquipos(teams);
            System.out.print("\nIngresa el id del team: ");
            while (!sc.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                sc.next(); // Descartar el input no válido
            }
            int data = sc.nextInt();
            Equipo foundTeam = Equipo.findById(data, teams); 
            if (foundTeam == null) {
                newPlayer.setName(name);
                newPlayer.setTeam(null);
                players.add(newPlayer);
                clear();
                Jugador.showPlayers(players);
            } else {
                newPlayer.setName(name);
                newPlayer.setTeam(foundTeam);
                players.add(newPlayer);
                clear();
                Jugador.showPlayers(players);
                System.out.println("Jugador creado exitosamente!"); 
            }
        } else {
            newPlayer.setName(name);
            newPlayer.setTeam(null);
            players.add(newPlayer);
            clear();
            Jugador.showPlayers(players);
        }
    }
}
