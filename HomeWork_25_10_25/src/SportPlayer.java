public abstract class SportPlayer  {
    String name;
    int numberOfPlayer;

    public SportPlayer(String name, int numberOfPlayer){
        this.numberOfPlayer =numberOfPlayer;
        this.name = name;
    }

    public abstract void chooseYourTeam();

    public void sayGoodLuck(){
        System.out.printf("Здравствуйте, %s! Ваш номер: %d\n", this.name, this.numberOfPlayer);
    }
    // тут у нас overload
    public void sayGoodLuck(String competition){
        System.out.printf("Вы записались на %s. Хорошей игры, %s! ", competition, this.name);
    }
}
