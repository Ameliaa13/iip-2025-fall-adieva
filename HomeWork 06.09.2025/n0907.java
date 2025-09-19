import java.util.*;
public class n0907{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int w = sc.nextInt();
		int h = sc.nextInt();
		int r = sc.nextInt();
		if(2*r <= w && 2*r <=h){
			System.out.println("YES");
		}
		else{
			System.out.println("NO");
		}
	}
}