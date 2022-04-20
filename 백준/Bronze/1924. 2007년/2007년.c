#include<stdio.h>

int main() {
	int month, day;
	int dayarr[12] = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30 };
	int count = 0;
	char week[7][5] = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };

	scanf("%d %d", &month, &day);

	while (month > 1) {
		count += dayarr[--month];
	}
	count += day;
	count %= 7;

	printf("%s", week[count]);

	return 0;
}