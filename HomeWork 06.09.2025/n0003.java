import java.util.*;
public class n0003{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n%10 ==5 && n< 4*100000){
			System.out.println((n/10 *(n/10 +1))*100 + 25);
		}
	}
}