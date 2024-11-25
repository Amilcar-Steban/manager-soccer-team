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
                6. Eliminar jugador
                7. Eliminar equipo
                8. Seleccionar jugador
                9. Seleccionar equipo
                10. Salir
                """);
    }

    public static void options(){
        int option;
        do {
            menu();
            option = sc.nextInt();
            switch (option) {
                case 1 -> {clear();createPlayer();}
                case 2 -> {clear();createTeam();}
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
                case 6 -> {clear();deletePlayer();}
                case 7 -> {clear();deleteTeam();}
                case 8 -> {
                    clear();
                    menuSelect("player");
                    while (!sc.hasNextInt()) {
                        System.out.println("Por favor, ingresa un número válido.");
                        sc.next(); // Descartar el input no válido
                    }
                    int data = sc.nextInt();
                    switch (data) {
                        case 1 -> {
                            clear();
                            Jugador player = selectPlayer();
                            Jugador.showPlayers(player);
                        }
                        case 2 -> {
                            clear();System.out.println("Bye..");
                        }
                        case 3 -> {
                            clear();System.out.println("Bye..");
                        }
                        case 4 -> {clear();System.out.println("Bye..");}
                        default -> System.out.println("Opcion incorrecta");
                    }
                }
                case 9 -> {
                    clear();menuSelect("team");
                }
                case 10 -> {clear();System.out.println("Bye..");}
                default -> System.out.println("Opcion incorrecta");
            }
        } while (option!=10);
    }

    public static void clear(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();
    }

    public static void init(){
        //Informacion de prueba
        clear();
        Equipo team1 = new Equipo("Real Madrid");
        teams.add(team1);
        Equipo team2 = new Equipo("Barcelona");
        teams.add(team2);

        Jugador player1 = new Jugador("Amilcar Rodriguez", team1);
        players.add(player1);
        Jugador player2 = new Jugador("Cristiano Ronaldo", team2);
        players.add(player2);
        options();
        
    }

    public static void setPlayerInTeam(Jugador player, Equipo team){
        player.setTeam(team);
    }

    public static void createTeam(){
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

    public static void deletePlayer(){
        Jugador.showPlayers(players);
        System.out.print("Id de jugador a eliminar: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            sc.next(); // Descartar el input no válido
        }
        int data = sc.nextInt();
        boolean playerDeleted = false;

        for (int i = 0; i < players.size(); i++) {
            if (data == players.get(i).getId()) {
                players.remove(i);
                playerDeleted = true;
                break;
            }
        }

        if (playerDeleted) {
            System.out.println("\nJugador Eliminado exitosamente!"); 
            Jugador.showPlayers(players);
        }else{
            System.out.println("\nJugador no encontrado"); 
        }
    }

    public static void deleteTeam(){
        Equipo.showEquipos(teams);
        System.out.print("Id del equipo a eliminar: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            sc.next(); // Descartar el input no válido
        }
        int data = sc.nextInt();
        boolean playerDeleted = false;

        for (int i = 0; i < teams.size(); i++) {
            if (data == teams.get(i).getId()) {
                teams.remove(i);
                playerDeleted = true;
                break;
            }
        }

        if (playerDeleted) {
            System.out.println("\nEquipo Eliminado exitosamente!"); 
            Equipo.showEquipos(teams);
        }else{
            System.out.println("\nEquipo no encontrado"); 
        }
    }

    public static Jugador selectPlayer(){
        Jugador.showPlayers(players);
        System.out.print("Id de jugador: ");
        while (!sc.hasNextInt()) {
            System.out.println("Por favor, ingresa un número válido.");
            sc.next(); // Descartar el input no válido
        }
        int data = sc.nextInt();
        return Jugador.findById(data, players);
    }

    public static void menuSelect(String op){
        if (op.equalsIgnoreCase("team")) {
            System.out.println("""

                \tMenu Jugador
                1. Ver detalles
                2. Cambiar nombre
                3. Cambiar equipo
                4. Mostrar jugadores del equipo
                5. Regresar al menu anterior

                """);
        }else{
            System.out.println("""

                \tMenu Equipo 
                1. Ver detalles
                2. Cambiar nombre
                3. Cambiar equipo
                4. Regresar al menu anterior

                """);
        }
        
    }
}
