import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		while((line = br.readLine()) != null) {
			while(!line.equals(line.replaceAll("BUG", ""))) {
				line = line.replaceAll("BUG", "");
			}
			System.out.println(line);
		}
	}
}