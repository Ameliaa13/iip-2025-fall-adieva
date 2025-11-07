import java.util.Scanner;

public class Main{
    public static void main(String[] args) {

        Basketball player1 =  new Basketball("Евгений", 1);
        Volleyball player2 = new Volleyball("Артем", 33);
        Football player3 = new Football("Михаил", 26);
        Tennis player4 = new Tennis("Алексей", 4);
        Chess player5 = new Chess("Анастасия", 15);
        Baseball player6 = new Baseball("Максим", 96);
        eSport player7 = new eSport("Ислам", 52);
        AquaAerobics player8 = new AquaAerobics("Элеонора", 81);
        Swimming player9 = new Swimming("Евдокия", 14);
        Golf player10 = new Golf("Егор", 10);

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите название турнира: "); //пример: "Отборочный турнир"
        String competition = sc.nextLine();

        player1.chooseYourTeam();
        player1.sayGoodLuck(competition);
        player1.Inventory();
        player1.Nutrition();

        player2.chooseYourTeam();
        player2.sayGoodLuck(competition);
        player2.Inventory();
        player2.Nutrition();

        player3.chooseYourTeam();
        player3.sayGoodLuck(competition);
        player3.Inventory();
        player3.Nutrition();

        player4.chooseYourTeam();
        player4.sayGoodLuck(competition);
        player4.Inventory();
        player4.Nutrition();

        player6.chooseYourTeam();
        player6.sayGoodLuck(competition);
        player6.Inventory();
        player6.Nutrition();

        player8.chooseYourTeam();
        player8.sayGoodLuck(competition);
        player8.Inventory();
        player8.Nutrition();

        player9.chooseYourTeam();
        player9.sayGoodLuck(competition);
        player9.Inventory();
        player9.Nutrition();

        player10.chooseYourTeam();
        player10.sayGoodLuck(competition);
        player10.Inventory();
        player10.Nutrition();

        player5.chooseYourTeam();
        player5.sayGoodLuck(competition);
        player5.Inventory();
        player5.Nutrition();

        player7.chooseYourTeam();
        player7.sayGoodLuck(competition);
        player7.Inventory();
        player7.Nutrition();


    }
}