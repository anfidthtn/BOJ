import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Integer> data = new ArrayList<>();
		int totalSum = 0;
		for (int i = 0; i < 9; i++) {
			data.add(Integer.parseInt(br.readLine()));
			totalSum += data.get(i);
		}
		data.sort((a, b) -> a < b ? -1 : 1);
		totalSum -= 100;
		outer : for (int i = 0; i < 9; i++) {
			for (int j = i + 1; j < 9; j++) {
				if (data.get(i) + data.get(j) == totalSum) {
					data.remove(j);
					data.remove(i);
					break outer;
				}
			}
		}
		
		for (int num : data) {
			System.out.println(num);
		}
	}
}