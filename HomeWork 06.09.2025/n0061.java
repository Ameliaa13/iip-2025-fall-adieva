import java.util.*;
public class n0061{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c= sc.nextInt();
		int d = sc.nextInt();
		int e= sc.nextInt();
		int f = sc.nextInt();
		int g = sc.nextInt();
		int h = sc.nextInt();
		if(a+c+e+g > b+d+f+h){
			System.out.println("1");
		}
		else if(a+c+e+g == b+d+f+h){
			System.out.println("DRAW");
		}
		else{
			System.out.println("2");
		}
	}
}