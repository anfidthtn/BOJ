import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int V = Integer.parseInt(br.readLine());
		String str = br.readLine();
		int[] counts = new int[2];
		for (int i = 0; i < V; i++) {
			counts[str.charAt(i) - 'A']++;
		}
		if(counts[0] > counts[1]) {
			System.out.println("A");
		}
		else if(counts[1] > counts[0]) {
			System.out.println("B");
		}
		else {
			System.out.println("Tie");
		}
	}
}