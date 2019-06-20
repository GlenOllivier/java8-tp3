package tpjava.view;

import tpjava.controller.DateTimeManager;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Scanner;

public abstract class Menu {
    private static Scanner scanner;
    private static DateTimeSelector dateTimeSelector;
    private static DateTimeDifference dateTimeDifference;

    public static void menu() {
        scanner = new Scanner(System.in);
        dateTimeSelector = new DateTimeSelector(scanner);
        dateTimeDifference = new DateTimeDifference(scanner);
        int choix = 0;

        while (choix != 9) {
            System.out.println("-------------- Menu --------------");
            System.out.println("1 - Saisir une date");
            System.out.println("2 - Saisir un temps");
            System.out.println("3 - Saisir un DateTime");
            System.out.println("4 - Afficher les elements");
            System.out.println("5 - Afficher une difference");
            System.out.println("6 - Afficher le temps autre part");
            System.out.println("7 - Afficher la DateTime modifiee");
            System.out.println("8 - Minijeu");
            System.out.println("9 - Quitter");
            System.out.println("----------------------------------");
            choix = scanner.nextInt();
            switch (choix) {
                case 1:
                    addDate();
                    break;
                case 2:
                    addTime();
                    break;
                case 3:
                    addDateTime();
                    break;
                case 4:
                    dateTimeSelector.menu();
                    break;
                case 5:
                    dateTimeDifference.menu();
                    break;
                case 6:
                    String timezone = getTimezone();
                    ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of(timezone));
                    System.out.println(zdt);
                    break;
                case 7:
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Option invalide");
                    break;
            }
        }
        scanner.close();
    }

    private static void addDate() {
        int annee, mois, jour;
        System.out.println("Ajout d'une date (annee, mois, jour) :");
        annee = getAnnee();
        mois = getMois();
        jour = getJour(annee, mois);
        DateTimeManager.addDate(annee, mois, jour);
    }

    private static void addTime() {
        int heure, minute, seconde;
        System.out.println("Ajout d'un temps (heure, minute, seconde) :");
        heure = getHeure();
        minute = getMinute();
        seconde = getSeconde();
        DateTimeManager.addTime(heure, minute, seconde);
    }

    private static void addDateTime() {
        int annee, mois, jour, heure, minute, seconde;
        System.out.println("Ajout d'un DateTime (annee, mois, jour, heure, minute, seconde) :");
        annee = getAnnee();
        mois = getMois();
        jour = getJour(annee, mois);
        heure = getHeure();
        minute = getMinute();
        seconde = getSeconde();
        DateTimeManager.addDateTime(annee, mois, jour, heure, minute, seconde);
    }

    public static int getAnnee() {
        int annee;
        while (true) {
            System.out.println("Veuillez entrer une annee : ");
            annee = scanner.nextInt();
            if (annee > -999999999 && annee < 999999999) {
                break;
            }
            System.out.println("Annee saisie incorrect !");
        }
        return annee;
    }

    public static int getMois() {
        int mois;
        while (true) {
            System.out.println("Veuillez entrer un mois : ");
            mois = scanner.nextInt();
            if (mois >= 1 && mois <= 12) {
                break;
            }
            System.out.println("Mois saisie incorrect !");
        }
        return mois;
    }

    public static int getJour(int annee, int mois) {
        int jour;
        while (true) {
            System.out.println("Veuillez entrer un jour : ");
            jour = scanner.nextInt();
            try {
                LocalDate ldt = LocalDate.of(annee, mois, jour);
                break;
            } catch (DateTimeException e) {
                System.out.println("Jour saisi incorrect !");
            }
        }
        return jour;
    }

    public static int getHeure() {
        int heure;
        while (true) {
            System.out.println("Veuillez entrer une heure : ");
            heure = scanner.nextInt();
            if (heure >= 0 && heure <= 23) {
                break;
            }
            System.out.println("Heure saisie incorrect !");
        }
        return heure;
    }

    public static int getMinute() {
        int minute;
        while (true) {
            System.out.println("Veuillez entrer une minute : ");
            minute = scanner.nextInt();
            if (minute >= 0 && minute <= 59) {
                break;
            }
            System.out.println("Minute saisie incorrect !");
        }
        return minute;
    }

    public static int getSeconde() {
        int seconde;
        while (true) {
            System.out.println("Veuillez entrer une seconde : ");
            seconde = scanner.nextInt();
            if (seconde >= 0 && seconde <= 59) {
                break;
            }
            System.out.println("Seconde saisie incorrect !");
        }
        return seconde;
    }

    public static String getTimezone() {
        String timezone;
        scanner.nextLine();
        while (true) {
            System.out.println("Veuillez entrer une timezone : ");
            timezone = scanner.nextLine();
            if (ZoneId.getAvailableZoneIds().contains(timezone)) {
                break;
            }
            System.out.println("Timezone saisie incorrect !");
        }
        return timezone;
    }
}
