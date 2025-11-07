import java.util.Scanner;

public class Chess extends SportPlayer implements SportNutrition, ChessInventory{
    public Chess(String name, int numberOfPlayer){
        super(name, numberOfPlayer);
    }

    @Override
    public void chooseYourTeam(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите цвет фигуры: ");
        String team = sc.nextLine();
        System.out.println("Ваш цвет фигуры: "+ team);
    }
}
