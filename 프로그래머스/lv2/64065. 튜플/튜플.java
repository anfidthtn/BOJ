import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public int[] solution(String s) {
        List<Set<Integer>> list = new ArrayList<>();
        list.add(new HashSet<>());
        int num = 0;
        for(int i = 1; i < s.length() - 1; i++) {
        	switch(s.charAt(i)) {
        	case '{':
        		list.add(new HashSet<>());
        		break;
        	case ',':
        		if (s.charAt(i + 1) == '{') {
        			break;
        		}
        	case '}':
        		list.get(list.size() - 1).add(num);
        		num = 0;
        		break;
        	default:
        		num *= 10;
        		num += s.charAt(i) - '0';
        		break;
        	}
        }
        list.sort(new Comparator<Set<Integer>>() {
			@Override
			public int compare(Set<Integer> o1, Set<Integer> o2) {
				return o1.size() - o2.size();
			}
		});
        List<Integer> answerList = new ArrayList<>();
        for(int i = 1; i < list.size(); i++) {
        	if (list.get(i).size() > list.get(i - 1).size()) {
        		for(int next : list.get(i)) {
        			if (!list.get(i - 1).contains(next)) {
        				answerList.add(next);
        				break;
        			}
        		}
        	}
        }
        
        int[] answer = new int[answerList.size()];
        for(int i = 0; i < answerList.size(); i++) {
        	answer[i] = answerList.get(i);
        }
        return answer;
    }
}