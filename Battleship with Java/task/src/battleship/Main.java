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

        System.out.println("The game starts!\n");

        ShootResult shootResult = ShootResult.MISS;
        System.out.println(battlefield);
        System.out.println("Take a shot!\n");
        while (true) {
            try {
                shootResult = battlefield.shoot(Coordinate.fromString(scanner.next()));
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(battlefield);
        if (shootResult == ShootResult.MISS) {
            System.out.println("You missed!");
        } else {
            System.out.println("You hit a ship!");
        }

    }
}
