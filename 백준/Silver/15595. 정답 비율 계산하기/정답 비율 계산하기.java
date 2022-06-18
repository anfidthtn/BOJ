import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	static class Info{
		String name;
		boolean pass;
		public Info(String name, int result) {
			this.name = name;
			this.pass = result == 4;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		List<Info> infos = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			String[] temp = br.readLine().split(" ");
			if (temp[1].equals("megalusion")) {
				continue;
			}
			infos.add(new Info(temp[1], Integer.parseInt(temp[2])));
		}
		
		Map<String, Integer> map = new TreeMap<>();
		for(int i = infos.size() - 1; i >= 0; i--) {
			if (infos.get(i).pass) {
				map.put(infos.get(i).name, 1);
			}
			else {
				if (map.containsKey(infos.get(i).name)) {
					map.put(infos.get(i).name, map.get(infos.get(i).name) + 1);
				}
			}
		}
		int total = 0;
		int count = 0;
		for (String key : map.keySet()) {
			count++;
			total += map.get(key);
		}
		if (count == 0) {
			System.out.println(0);
		}
		else {
			System.out.println(100 * (double) count / total);
		}
	}
}