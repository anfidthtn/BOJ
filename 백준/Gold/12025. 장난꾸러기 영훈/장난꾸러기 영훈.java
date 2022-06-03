import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String pass = br.readLine();
		
		long K = Long.parseLong(br.readLine()) - 1;
		List<Integer> idx = new ArrayList<>();
		for(int i = 0; i < pass.length(); i++) {
			switch(pass.charAt(i)) {
			case '1':
			case '2':
			case '6':
			case '7':
				idx.add(i);
				break;
			}
		}
		if(Long.toBinaryString(K).length() > idx.size()) {
			System.out.println(-1);
			return;
		}
		int kBit = idx.size() - 1;
		for(int i = 0; i < pass.length(); i++) {
			switch(pass.charAt(i)) {
			case '1':
			case '6':
				if ((K & ((long) 1 << kBit)) != 0) {
					System.out.print(6);
				}
				else {					
					System.out.print(1);
				}
				kBit--;
				break;
			case '2':
			case '7':
				if ((K & ((long) 1 << kBit)) != 0) {
					System.out.print(7);
				}
				else {					
					System.out.print(2);
				}
				kBit--;
				break;
			default:
				System.out.print(pass.charAt(i));
				break;
			}
		}
	}
}