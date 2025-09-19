import java.util.*;
public class n0021{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int max1 = Math.max(a,b);
		int max2 = Math.max(max1, c);
		int min1 = Math.min(a,b);
		int min2 = Math.min(min1, c);
		System.out.println(max2- min2);
	}
}