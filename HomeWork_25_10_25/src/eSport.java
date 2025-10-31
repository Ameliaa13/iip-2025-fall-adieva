import java.util.Scanner;

public class eSport extends SportPlayer implements SportNutrition, eSportInventory{
    public eSport(String name, int numberOfPlayer){
        super(name, numberOfPlayer);
    }

    @Override
    public void chooseYourTeam(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите команду: ");
        String team = sc.nextLine();
        System.out.println("Ваша команда: "+ team);
    }
}
