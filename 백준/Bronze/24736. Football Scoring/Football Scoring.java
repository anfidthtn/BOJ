import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int score1 = Integer.parseInt(st.nextToken()) * 6 + Integer.parseInt(st.nextToken()) * 3 + Integer.parseInt(st.nextToken()) * 2 + Integer.parseInt(st.nextToken()) * 1 + Integer.parseInt(st.nextToken()) * 2;
		st = new StringTokenizer(br.readLine());
		int score2 = Integer.parseInt(st.nextToken()) * 6 + Integer.parseInt(st.nextToken()) * 3 + Integer.parseInt(st.nextToken()) * 2 + Integer.parseInt(st.nextToken()) * 1 + Integer.parseInt(st.nextToken()) * 2;
		System.out.print(score1 + " " + score2);
	}
}