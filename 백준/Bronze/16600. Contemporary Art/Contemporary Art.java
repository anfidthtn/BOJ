import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Double d = Double.parseDouble(br.readLine());
		System.out.println(String.format("%.10f", 4 * Math.sqrt(d)));
	}
}