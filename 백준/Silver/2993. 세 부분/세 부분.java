import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] word = br.readLine().toCharArray();
		final int size = word.length;
		List<char[]> words = new ArrayList<>();
		for(int a = 1; a < size; a++) {
			for(int b = a + 1; b < size; b++) {
				char[] newWord = new char[size];
				for(int aa = 0; aa <= a - 1; aa++) {
					newWord[aa] = word[a - 1 - aa];
				}
				for(int bb = a; bb <= b - 1; bb++) {
					newWord[bb] = word[b - 1 - bb + a];
				}
				for(int cc = b; cc <= size - 1; cc++) {
					newWord[cc] = word[size - 1 - cc + b];
				}
				words.add(newWord);
			}
		}
		words.sort(new Comparator<char[]>() {
			@Override
			public int compare(char[] o1, char[] o2) {
				for(int i = 0; i < size; i++) {
					if (o1[i] < o2[i]) {
						return -1;
					}
					if (o1[i] > o2[i]) {
						return 1;
					}
				}
				return 0;
			}
		});
		for(char c : words.get(0)) {
			System.out.print(c);
		}
	}
}