import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		for(int i = 0; i < N; i++) {
			for(int j = N - i - 1; j > 0; j--) {
				System.out.print(" ");
			}
			for(int j = 0; j < i * 2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();
		}
		for(int i = N - 2; i >= 0; i--) {
			for(int j = N - i - 1; j > 0; j--) {
				System.out.print(" ");
			}
			for(int j = 0; j < i * 2 + 1; j++) {
				System.out.print("*");
			}
			System.out.println();			
		}
	}
}