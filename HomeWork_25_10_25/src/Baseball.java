import java.util.Scanner;

public class Baseball extends SportPlayer implements SportWithBall, SportNutrition{
    public Baseball(String name, int numberOfPlayer){
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
