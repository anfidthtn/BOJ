import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long total = 0;
		String str = br.readLine();
		switch(str) {
		case "white":
			total += 1;
		case "grey":
			total += 1;
		case "violet":
			total += 1;
		case "blue":
			total += 1;
		case "green":
			total += 1;
		case "yellow":
			total += 1;
		case "orange":
			total += 1;
		case "red":
			total += 1;
		case "brown":
			total += 1;
		}
		total *= 10;
		str = br.readLine();
		switch(str) {
		case "white":
			total += 1;
		case "grey":
			total += 1;
		case "violet":
			total += 1;
		case "blue":
			total += 1;
		case "green":
			total += 1;
		case "yellow":
			total += 1;
		case "orange":
			total += 1;
		case "red":
			total += 1;
		case "brown":
			total += 1;
		}
		str = br.readLine();
		switch(str) {
		case "white":
			total *= 10;
		case "grey":
			total *= 10;
		case "violet":
			total *= 10;
		case "blue":
			total *= 10;
		case "green":
			total *= 10;
		case "yellow":
			total *= 10;
		case "orange":
			total *= 10;
		case "red":
			total *= 10;
		case "brown":
			total *= 10;
		}
		System.out.println(total);
	}
}