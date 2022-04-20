import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] words = br.readLine().split(" ");
		int row = -1;
		int col = -1;
		search : for (int i = 0; i < words[0].length(); i++){
			for (int j = 0; j < words[1].length(); j++) {
				if (words[0].charAt(i) == words[1].charAt(j)) {
					row = j;
					col = i;
					break search;
				}
			}
		}
		
		for(int i = 0; i < words[1].length(); i++) {
			if (i == row) {
				System.out.println(words[0]);
				continue;
			}
			for(int j = 0; j < words[0].length(); j++) {
				if (j == col) System.out.print(words[1].charAt(i));
				else System.out.print('.');
			}
			System.out.println();
		}
	}
}