class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (int i = 0; i < s.length(); i++){
            if (first){
                if ('a' <= s.charAt(i) && s.charAt(i) <= 'z'){
                    sb.append((char) (s.charAt(i) - 'a' + 'A'));
                }
                else{
                    sb.append(s.charAt(i));
                }
                first = false;
            }
            else{
                if ('A' <= s.charAt(i) && s.charAt(i) <= 'Z'){
                    sb.append((char) (s.charAt(i) - 'A' + 'a'));
                }
                else{
                    sb.append(s.charAt(i));
                }
            }
            if (s.charAt(i) == ' '){
                first = true;
            }
            
        }
        return sb.toString();
    }
}