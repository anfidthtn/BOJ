import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	static class Student{
		int[] exam;
		public Student() {
			this.exam = new int[3];
		}
	}
	static int[] segTree;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Student[] students = new Student[N + 1];
		for(int i = 0; i <= N; i++) {
			students[i] = new Student();
		}
		for(int i = 0; i < 3; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				students[Integer.parseInt(st.nextToken())].exam[i] = j;
			}
		}
		Arrays.sort(students, new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				return o1.exam[0] - o2.exam[0];
			}
		});
		segTree = new int[4 * N];
		int count = 0;
		for(int i = 1; i <= N; i++) {
			int getmin = getMin(1, 1, N, 1, students[i].exam[1]);
			if (getmin > students[i].exam[2]) {
				count++;
			}
			update(1, 1, N, students[i].exam[1], students[i].exam[2]);
		}
		System.out.print(count);
	}
	public static int getMin(int nodeNum, int left, int right, int start, int end) {
		if (end < left || right < start) {
			return 1_000_000_000;
		}
		if (start <= left && right <= end) {
			if (segTree[nodeNum] == 0) {
				return 1_000_000_000;
			}
			return segTree[nodeNum];
		}
		int mid = (left + right) >> 1;
		return Math.min(getMin(nodeNum * 2, left, mid, start, end), getMin(nodeNum * 2 + 1, mid + 1, right, start, end));
	}
	public static void update(int nodeNum, int left, int right, int target, int value) {
		if (target < left || right < target) {
			return;
		}
		if (segTree[nodeNum] == 0) {
			segTree[nodeNum] = value;
		}
		else {
			segTree[nodeNum] = Math.min(segTree[nodeNum], value);
		}
		if (left == right) {
			return;
		}
		int mid = (left + right) >> 1;
		update(nodeNum * 2, left, mid, target, value);
		update(nodeNum * 2 + 1, mid + 1, right, target, value);
	}
}