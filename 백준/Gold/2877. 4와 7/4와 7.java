import java.util.*;class Main{public static void main(String[]a){int K=new Scanner(System.in).nextInt(),x=1;for(;K>x*2;K-=x*=2);K--;for(;x>0;x/=2)System.out.print((K&x)>0?7:4);}}