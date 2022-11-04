package labfirstherostrategy;

public class Hero {
    public void moveHero(Move move) {
        System.out.println(move.move());
    }

    public static void main(String[] args) {
        Hero hero = new Hero();
        hero.moveHero(new Drive());
        hero.moveHero(new Fly());
        hero.moveHero(new Swim());
    }


}
