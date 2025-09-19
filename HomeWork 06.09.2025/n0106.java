import java.util.*;
public class n0106{
	public static void main(String args[]){
		Scanner sc= new Scanner(System.in);
		int n = sc.nextInt ();
		int sum = 0;
		for(int count = 0; count<n; count++){
			sum += sc.nextInt();
		}
		System.out.println(n-sum >sum ? sum : n-sum);
	}
}