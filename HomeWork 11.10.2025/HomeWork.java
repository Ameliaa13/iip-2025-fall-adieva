import java.util.*;
public class HomeWork{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		String pattern1 = "((\\d{2}(0[48]|[2468][048]|[13579][26])\\-((0[13578]|10|12)\\-([0-2]\\d|3[01])|(0[469]|11)\\-([0-2]\\d|30)|02\\-([012]\\d)))|"+
			"(((0[48]|[2468][048]|[13579][26])00)\\-((0[13578]|10|12)\\-([0-2]\\d|3[01])|(0[469]|11)\\-([0-2]\\d|30)|02\\-([012]\\d)))|"+
			"(0000|\\d{4})\\-((0[13578]|10|12)\\-([0-2]\\d|3[01])|(0[469]|11)\\-([0-2]\\d|30)|02\\-([01]\\d|2[0-8])))"+
			"T([0-1]\\d|2[0-3]):[0-5]\\d:[0-5]\\d"+
			"\\.\\d+Z";
	

		while(true){
			String toTest = sc.nextLine();
			
			boolean matches = toTest.matches(pattern1);
			System.out.println(matches ? "Yes" : "No");
					
		}
		
	}
}