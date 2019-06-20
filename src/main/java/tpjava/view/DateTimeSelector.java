package tpjava.view;

import tpjava.controller.DateTimeManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class DateTimeSelector {

    private Scanner scanner;

    public DateTimeSelector(Scanner scanner) {
        this.scanner = scanner;
    }

    public void menu() {
        String commande;
        scanner.nextLine();

        while (true) {
            out.println("Choix de la categorie a afficher :");
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
                out.println("Tri : -T");
                out.println("Filtre : -F");
                out.println("Options de Tri/Filtrage :");
                out.println("Annee : a");
                out.println("Mois : M");
                out.println("Jour : j");
                out.println("Heures : h");
                out.println("Minutes : m");
                out.println("Secondes : s");
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
                List<LocalDate> dates = DateTimeManager.getDates();
                dates = filtreDate(dates, commande);
                dates = triDate(dates, commande);
                dates.forEach(out::println);
                break;
            case 'D':
                List<LocalDateTime> dateTimes = DateTimeManager.getDateTimes();
                dateTimes = filtreDateTime(dateTimes, commande);
                dateTimes = triDateTime(dateTimes, commande);
                dateTimes.forEach(out::println);
                break;
            case 't':
                List<LocalTime> times = DateTimeManager.getTimes();
                times = filtreTime(times, commande);
                times = triTime(times, commande);
                times.forEach(out::println);
                break;
        }
        scanner.nextLine();
    }


    private List<LocalTime> triTime(List<LocalTime> list, String commande) {
        Stream<LocalTime> stream = list.stream();
        Pattern p = Pattern.compile("-T(.)");
        Matcher m = p.matcher(commande);

        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'h':
                    return stream.sorted(Comparator.comparing(LocalTime::getHour))
                            .collect(Collectors.toList());
                case 'm':
                    return stream.sorted(Comparator.comparing(LocalTime::getMinute))
                            .collect(Collectors.toList());
                case 's':
                    return stream.sorted(Comparator.comparing(LocalTime::getSecond))
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }

    private List<LocalDate> triDate(List<LocalDate> list, String commande) {
        Stream<LocalDate> stream = list.stream();
        Pattern p = Pattern.compile("-T(.)");
        Matcher m = p.matcher(commande);
        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'a':
                    return stream.sorted(Comparator.comparing(LocalDate::getYear))
                            .collect(Collectors.toList());
                case 'M':
                    return stream.sorted(Comparator.comparing(LocalDate::getMonth))
                            .collect(Collectors.toList());
                case 'j':
                    return stream.sorted(Comparator.comparing(LocalDate::getDayOfMonth))
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }

    private List<LocalDateTime> triDateTime(List<LocalDateTime> list, String commande) {
        Stream<LocalDateTime> stream = list.stream();
        Pattern p = Pattern.compile("-T(.)");
        Matcher m = p.matcher(commande);
        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'a':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getYear))
                            .collect(Collectors.toList());
                case 'M':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getMonth))
                            .collect(Collectors.toList());
                case 'j':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getDayOfMonth))
                            .collect(Collectors.toList());
                case 'h':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getHour))
                            .collect(Collectors.toList());
                case 'm':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getMinute))
                            .collect(Collectors.toList());
                case 's':
                    return stream.sorted(Comparator.comparing(LocalDateTime::getSecond))
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }

    private List<LocalTime> filtreTime(List<LocalTime> list, String commande) {
        Stream<LocalTime> stream = list.stream();
        Pattern p = Pattern.compile("-F(.)");
        Matcher m = p.matcher(commande);
        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'h':
                    out.println("Entrez une valeur pour le filtre d'heure :");
                    int heure = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getHour() == heure)
                            .collect(Collectors.toList());
                case 'm':
                    out.println("Entrez une valeur pour le filtre de minutes :");
                    int minute = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getMinute() == minute)
                            .collect(Collectors.toList());
                case 's':
                    out.println("Entrez une valeur pour le filtre de secondes :");
                    int second = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getSecond() == second)
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }

    private List<LocalDate> filtreDate(List<LocalDate> list, String commande) {
        Stream<LocalDate> stream = list.stream();
        Pattern p = Pattern.compile("-F(.)");
        Matcher m = p.matcher(commande);
        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'a':
                    out.println("Entrez une valeur pour le filtre d'annee :");
                    int annee = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getYear() == annee)
                            .collect(Collectors.toList());
                case 'M':
                    out.println("Entrez une valeur pour le filtre de mois :");
                    int mois = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getMonthValue() == mois)
                            .collect(Collectors.toList());
                case 'j':
                    out.println("Entrez une valeur pour le filtre de jour :");
                    int jour = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDate -> localDate.getDayOfMonth() == jour)
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }

    private List<LocalDateTime> filtreDateTime(List<LocalDateTime> list, String commande) {
        Stream<LocalDateTime> stream = list.stream();
        Pattern p = Pattern.compile("-F(.)");
        Matcher m = p.matcher(commande);
        if (m.find()) {
            String result = m.group(1);
            switch (result.charAt(0)) {
                case 'a':
                    out.println("Entrez une valeur pour le filtre d'annee :");
                    int annee = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getYear() == annee)
                            .collect(Collectors.toList());
                case 'M':
                    out.println("Entrez une valeur pour le filtre de mois :");
                    int mois = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getMonthValue() == mois)
                            .collect(Collectors.toList());
                case 'j':
                    out.println("Entrez une valeur pour le filtre de jour :");
                    int jour = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getDayOfMonth() == jour)
                            .collect(Collectors.toList());
                case 'h':
                    out.println("Entrez une valeur pour le filtre d'heure :");
                    int heure = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getHour() == heure)
                            .collect(Collectors.toList());
                case 'm':
                    out.println("Entrez une valeur pour le filtre de minutes :");
                    int minute = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getMinute() == minute)
                            .collect(Collectors.toList());
                case 's':
                    out.println("Entrez une valeur pour le filtre de secondes :");
                    int second = scanner.nextInt();
                    scanner.nextLine();
                    return stream.filter(localDateTime -> localDateTime.getSecond() == second)
                            .collect(Collectors.toList());
            }
        }
        return stream.collect(Collectors.toList());
    }
}
