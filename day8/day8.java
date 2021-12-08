import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**Array
 * OVERVIEW: istanza di questa classe risolve il day8 dell'aoc 2021
 */
public class day8 {

	static final ArrayList<Line> LINE = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get("input.txt")));
		for (String l : lines) // converte stringhe in linee
			LINE.add(new Line(l));
		System.out.println("Part1: " + part1());
		System.out.println("Part2: " + part2());
	}

    /**
     * Post-condizioni: risoluzione parte1
     */
	static long part1() {
		int cnt = 0;
		for (Line line : LINE) {
			for (String s : line.output) {
				if (s.length() == 2 || s.length() == 3 || s.length() == 4 || s.length() == 7)
					cnt++;
			}
		}
		return cnt;
	}

    /**
     * Post-condizioni: risoluzione parte2
     */
	static long part2() {
		int sum = 0;
		for (Line line : LINE) {
			int temp = 0;
			for (String s : line.output)
				temp = temp * 10 + line.pattern.map.get(s); // aggiunge i nuovi digit.
			sum += temp;
		}
		return sum;
	}

}
/**
 * OVERVIEW: una istanza della classe rappresenta una linea del file di input
 */
class Line {
    //Attributi
	final ArrayList<String> input = new ArrayList<>();
	final ArrayList<String> output = new ArrayList<>();
	final Pattern pattern;

    /**
     * Costruttore: parsa input nel nuovo pattern
     */
	public Line(String line) {
		String[] lineSplit = line.split("\\|");

		for (String s : lineSplit[0].strip().split(" "))
			input.add(Line.sortString(s));
		for (String s : lineSplit[1].strip().split(" "))
			output.add(Line.sortString(s));

		pattern = new Pattern(input);
	}

    /**
     * Post-condizioni: ordina una stringa passata in input
     */
	private static String sortString(String s) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		return String.valueOf(charArray);
	}
}

/**
 * Ordina ogni linea per lunghezza della stringa e ricava il modello dagli indici 
 *indice   numero lunghezza
 *     0		1		  2
 *     1		7		  3
 *     2		4		  4
 *   3-5	2|3|5 		  5 
 *   6-8	0|6|9		  6 
 *     9		8		  7
 */
class Pattern {
    //Attributi
	final HashMap<String, Integer> map = new HashMap<>();
	final ArrayList<String> entries;

    /**
     * Costruttore: 
     */
	public Pattern(ArrayList<String> s) {
		this.entries = s;

		//Ordina per lunghezza della stringa
		entries.sort(Comparator.comparingInt(String::length));
		map.put(entries.get(0), 1);
		map.put(entries.get(1), 7);
		map.put(entries.get(2), 4);
		map.put(entries.get(9), 8);

		// Attributo per il sesto indice necessario per il 5.
		int sixIndex = 0;

		/**
		 * Controlla tutti i valori con lunghezza 6, ossia: 0, 9 e 6
		 * 9 è composto da 1, 7 e 4 			  //Indice 0, 1, 2
		 * 0 è composto solo da 1 e 7			  //Indice 0, 1
		 * 6 non è ne 1, ne 7 ne 4
		 */
		for (int i = 6; i <= 8; i++) {
			String d = entries.get(i);
			if (matches(i, 0) && matches(i, 1) && matches(i, 2))
				map.put(d, 9);
			else if (matches(i, 0) && matches(i, 1))
				map.put(d, 0);
			else {
				map.put(d, 6);
				sixIndex = i;
			}
		}

		/**
		 * Controlla i restanti valori: 3, 2 e 5
		 * 3 è composto da 1 e 7
		 * 5 è 6
		 * 2 non é ne 1, né 7, né 6
		 */
		for (int i = 3; i <= 5; i++) {
			String d = entries.get(i);
			if (matches(i, 0) && matches(i, 1))
				map.put(d, 3);
			else if (matches(sixIndex, i))
				map.put(d, 5);
			else
				map.put(d, 2);
		}
	}

	/** 
    * Post-condizioni: controlla se tutti i caratteri di entries(j) stanno in entries(i)
    */
	private boolean matches(int i, int j) {
		for (char c : entries.get(j).toCharArray()) {
			if (entries.get(i).indexOf(c) == -1)
				return false;
		}
		return true;
	}
}