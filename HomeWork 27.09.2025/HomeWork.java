import java.util.*;
public class HomeWork{
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);
		System.out.println("Введите название операции:");
		String comand = sc.nextLine();

		if(!(comand.equals("encode") || comand.equals("decode"))){
			System.out.println("Invalid command");
		} else{

			System.out.println("Введите слово:");
			String slovo = sc.nextLine();
			String glasnye1 = "аеёиоуыэюя";
			String glasnye2 = "АЕЁИОУЫЭЮЯ";
			String res = "";

			if( comand.equals("encode")){
				for (int i =0; i < slovo.length(); i++){
					char letter = slovo.charAt(i);
					if(glasnye1.contains(letter + "")){
						res += letter + "с" + letter;
					} 
					else if(glasnye2.contains(letter + "")){
						int index = glasnye2.indexOf(letter);
						res += letter + "с" + glasnye1.charAt(index);
					}
					else{
						res += letter;
					}
				}
			} else{
				for (int i = 0, j=1, k=2; i < slovo.length() && j < slovo.length() && k < slovo.length(); i++, j++, k++){
					char letter_i = slovo.charAt(i);
					char letter_j = slovo.charAt(j);
					char letter_k = slovo.charAt(k);
					if (letter_j == 'с' && letter_i == letter_k && glasnye1.contains(letter_i + "")){
						res += letter_i;
						i +=2;
						j+=2;
						k+=2;
					} 
					else if (letter_j == 'с' && glasnye2.contains(letter_i + "") && glasnye1.contains(letter_k + "")) {
						int index = glasnye2.indexOf(letter_i);
						if(letter_k == glasnye1.charAt(index)){
							res += letter_i;
							i+=2;
							j+=2;
							k+=2;
						}
					} else{
						
						if(k == slovo.length()-1){
							res += letter_i;
							res += letter_j;
							res += letter_k;
						} else{
							res += letter_i;
						}
					}
				}
			} 
			if(!slovo.equals(res)){
					System.out.println(res);
			} else{
				System.out.println("Invalid sausage string");
			}
		}
	}
}