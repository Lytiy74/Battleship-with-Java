package battleship;

public class Main {

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();

        battlefield.placeShipOnField("A1", "A4");
        System.out.println(battlefield.toString());
    }
}
