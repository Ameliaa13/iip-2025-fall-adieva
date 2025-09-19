import java.util.*;
public class n0754{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		int max1 = Math.max(a,b);
		int max2 = Math.max(max1, c);
		int min1 = Math.min(a,b);
		int min2 = Math.min(min1, c);
		if (94<= min2 && max2 <= 727 ){
			System.out.println(max2);
		}
		else{
			System.out.println("Error");
		}
	}
}