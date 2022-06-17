import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String str = br.readLine();
		if (str.equals("F")) {
			System.out.println("0.0");
		}
		else {
			double score = 'A' - str.charAt(0) + 4;
			switch(str.charAt(1)) {
			case '+':
				score += 0.3;
				break;
			case '-':
				score -= 0.3;
				break;
			}
			System.out.println(String.format("%.1f", score));
		}
	}
}