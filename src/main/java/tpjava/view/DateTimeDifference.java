package tpjava.view;

import tpjava.controller.DateTimeManager;

import java.time.*;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.out;

public class DateTimeDifference {
    private Scanner scanner;


    public DateTimeDifference(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menu() {
        String commande;
        scanner.nextLine();

        while (true) {
            out.println("Choix des categories a afficher :");
            out.println("(Tapez help pour afficher les options)");
            commande = scanner.nextLine();

            if (commande.contains("q")) {
                break;
            }

            if (commande.contains("help")) {
                out.println("---------------------------");
                out.println("Quitter : q");
                out.println("Options disponibles");
                out.println("Dates : d");
                out.println("Temps : t");
                out.println("DateTimes : D");
                out.println("---------------------------");
                continue;
            }

            if (commande.length() > 0 && (commande.charAt(0) == 'd' || commande.charAt(0) == 'D' || commande.charAt(0) == 't')) {
                break;
            }
            out.println("Commande inconnue...");
        }

        if (commande.contains("q")) {
            return;
        }
        switch (commande.charAt(0)) {
            case 'd':
                dateDifference();
                break;
            case 'D':
                dateTimeDifference();
                break;
            case 't':
                timeDifference();
                break;
        }
        scanner.nextLine();
    }

    private void dateDifference() {

        List<LocalDate> list = DateTimeManager.getDates();
        if (list.size() < 1) {
            out.println("Liste de dates vides");
            return;
        }

        list.stream()
                .forEach(localDate -> {
                    out.println(list.indexOf(localDate) + " : " + localDate);
                });

        int index1, index2;
        while (true) {
            out.println("Veuillez choisir l'index de la premiere date");
            index1 = scanner.nextInt();
            if (index1 < list.size() && index1 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        while (true) {
            out.println("Veuillez choisir l'index de la deuxieme date");
            index2 = scanner.nextInt();
            if (index2 < list.size() && index2 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        Period p = Period.between(list.get(index1), list.get(index2));
        out.println(p);
    }

    private void dateTimeDifference() {

        List<LocalDateTime> list = DateTimeManager.getDateTimes();
        if (list.size() < 1) {
            out.println("Liste de dates vides");
            return;
        }

        list.stream()
                .forEach(localDateTime -> {
                    out.println(list.indexOf(localDateTime) + " : " + localDateTime);
                });

        int index1, index2;
        while (true) {
            out.println("Veuillez choisir l'index du premier dateTime");
            index1 = scanner.nextInt();
            if (index1 < list.size() && index1 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        while (true) {
            out.println("Veuillez choisir l'index du deuxieme dateTime");
            index2 = scanner.nextInt();
            if (index2 < list.size() && index2 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        Duration d = Duration.between(list.get(index1), list.get(index2));
        out.println(d);
    }

    private void timeDifference() {

        List<LocalTime> list = DateTimeManager.getTimes();
        if (list.size() < 1) {
            out.println("Liste de dates vides");
            return;
        }

        list.stream()
                .forEach(localTime -> {
                    out.println(list.indexOf(localTime) + " : " + localTime);
                });

        int index1, index2;
        while (true) {
            out.println("Veuillez choisir l'index du premier temps");
            index1 = scanner.nextInt();
            if (index1 < list.size() && index1 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        while (true) {
            out.println("Veuillez choisir l'index du deuxieme temps");
            index2 = scanner.nextInt();
            if (index2 < list.size() && index2 >= 0) {
                break;
            }
            out.println("Valeur incorrecte");
        }

        Duration d = Duration.between(list.get(index1), list.get(index2));
        out.println(d);
    }
}
