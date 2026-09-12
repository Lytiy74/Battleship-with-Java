package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        Scanner scanner = new Scanner(System.in);
        System.out.println(battlefield);

        for (ShipTypes ship : ShipTypes.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells)\n", ship.getName(), ship.getSize());

            while (true) {
                try {
                    String cor1 = scanner.next();
                    String cor2 = scanner.next();

                    battlefield.placeShipOnField(cor1, cor2, ship);
                    System.out.println();
                    System.out.println(battlefield);
                    break;
                } catch (Exception e) {
                    System.out.println();
                    System.out.println(e.getMessage());
                }
            }
        }

    }
}
