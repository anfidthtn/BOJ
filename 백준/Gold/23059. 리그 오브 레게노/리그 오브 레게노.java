import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Item {
		int need;
		List<String> child;

		public Item() {
			super();
			this.need = 0;
			this.child = new ArrayList<>();
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;

		Map<String, Item> itemInfos = new HashMap<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			String b = st.nextToken();
			if (!itemInfos.containsKey(a)) {
				itemInfos.put(a, new Item());
			}
			if (!itemInfos.containsKey(b)) {
				itemInfos.put(b, new Item());
			}
			itemInfos.get(a).child.add(b);
			itemInfos.get(b).need++;
		}

		int count = itemInfos.keySet().size();
		PriorityQueue<String> queue = new PriorityQueue<>();
		for (String name : itemInfos.keySet()) {
			if (itemInfos.get(name).need == 0) {
				queue.add(name);
				count--;
			}
		}

		StringBuilder sb = new StringBuilder();
		while (!queue.isEmpty()) {
			PriorityQueue<String> nextQueue = new PriorityQueue<>();
			while (!queue.isEmpty()) {
				String now = queue.poll();
				sb.append(now).append('\n');
				for (String child : itemInfos.get(now).child) {
					if (--itemInfos.get(child).need == 0) {
						nextQueue.add(child);
						count--;
					}
				}
			}
			queue = nextQueue;
		}

		if (count == 0) {
			System.out.print(sb.toString());
		} else {
			System.out.print(-1);
		}
	}
}