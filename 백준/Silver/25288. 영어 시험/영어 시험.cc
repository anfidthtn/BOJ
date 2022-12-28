#include<iostream>
#include<algorithm>

using namespace std;

int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);
	int N;
	string s;
	cin >> N;
	cin >> s;
	bool arr[26] = { false, };
	
	for (int i = 0; i < s.length(); i++) {
		arr[s[i] - 'a'] = true;
	}
	for (int n = 0; n < N; n++) {
		for (int i = 0; i < 26; i++) {
			if (arr[i]) {
				cout << (char)(i + 'a');
			}
		}
	}

	return 0;
}