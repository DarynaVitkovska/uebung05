package ueb05;

import java.util.*;

class CorpusAnalyzer {
    private List<String> theses = new LinkedList<String>();

    CorpusAnalyzer(Iterator<String> thesesIterator) {
        // TODO Alle Titel in die this.theses Liste übernehmen

        while (thesesIterator.hasNext()) {
            theses.add(thesesIterator.next());
        }
    }

    /**
     * Gibt die Anzahl der angefertigten Theses zurück
     */
    int countTheses() {
        return theses.size();
    }

    /**
     * Gibt die durchschnittliche Länge von Titeln in Worten zurück
     */
    int averageThesisTitleLength() {
        int sum = 0;//
        for (int i = 0; i < theses.size(); i++) {
            String t = theses.get(i);// hier haben i Zeile
            String[] woerter = t.split(" ");// mit leerzeichen durchteilen
            System.out.println(woerter);
            sum += woerter.length;// hier wir berechnen wie viele Wörter wir haben
        }
        return sum / theses.size();
    }

    /**
     * Gibt eine alphabetisch absteigend sortierte und duplikatfreie
     * Liste der ersten Wörter der Titel zurück.
     */
    List<String> uniqueFirstWords() {
        Set<String> str = new HashSet<>();// erstellen ein Set, da hier keine Duplikate sein darf
        for (int i = 0; i < theses.size(); i++) {// laufen durch unsere Thesis
            String t = theses.get(i); // jede Thesis nehmen und in den String rein schieben
            String[] woerter = t.split(" ");// dieses String teilen wir in die Wörter und schieben sie in den Array
            str.add(woerter[0]);// hier fügen wir von jedem Thesis erstes Element in unser List hinzu
        }
        List<String> list = new LinkedList<>();//hier erstellen wir eine Liste, die unsere Elemente ausgeben wird
        list.addAll(str);// in diese Liste fügen wir alle erste Wörter von unseres Thesis hinzu
        list.sort(new Comparator<String>() {// Der Comperator definier, welche Element der kleinste ist
            @Override
            public int compare(String o1, String o2) {// hier haben wir gesagt, dass zuerst kommen die Letzte Buchstaben und dann erste
                return o2.compareTo(o1);
            }
        });

        return list; // hier geben wir die sortierte Liste zurück
    }

    /**
     * Gibt einen Iterator auf Titel zurück; dabei werden alle Woerter, welche
     * in `blackList` vorkommen durch Sternchen ersetzt (so viele * wie Buchstaben).
     */
    Iterator<String> censoredIterator(Set<String> blackList) {
        return new Iterator<String>() {
            Iterator<String> it = theses.iterator();

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public String next() {
                String s = it.next();
                for (String c : blackList)
                    s = s.replaceAll(c, repeat("*", c.length()));
                return s;
            }
        };
    }

    // Hilfsfunktion
    private static String repeat(String s, int n) {
        StringBuilder sb = new StringBuilder();
        while (n-- > 0)
            sb.append(s);
        return sb.toString();
    }

    /**
     * Gibt eine Liste von allen Titeln zurueck, wobei Woerter so ersetzt werden,
     * wie sie in der Map abgebildet werden.
     */
    List<String> normalizedTheses(Map<String, String> replace) {
        List<String> normalizedT = new LinkedList<String>();
        for (String str : theses) {
            for (Map.Entry<String, String> m : replace.entrySet()) {
                str = str.replace(m.getKey(), m.getValue());
            }
            normalizedT.add(str);
        }

        normalizedT.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o2.length(), o1.length());
            }
        });
        return normalizedT;
    }
}
