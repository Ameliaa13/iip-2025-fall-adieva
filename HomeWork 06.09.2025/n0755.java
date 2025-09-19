import java.util.*;
public class n0755{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		if(a+b-c >=0){
			System.out.println(a+b-c);
		}
		else{
			System.out.println("Impossible");
		}
	}
}