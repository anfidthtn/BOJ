function solution(a, b, n) {
    var answer = 0;
    
    while (n >= a){
        var temp = parseInt(n / a) * b;
        answer += temp;
        n %= a;
        n += temp;
    }
    return answer;
}