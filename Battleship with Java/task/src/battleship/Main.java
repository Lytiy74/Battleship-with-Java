package battleship;

import java.util.Scanner;

public class Main {
    private static boolean hideShips = false;

    public static void main(String[] args) {
        Battlefield battlefield = new Battlefield();
        Scanner scanner = new Scanner(System.in);
        System.out.println(battlefield.toString(hideShips));

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
        hideShips = true;

        ShootResult shootResult;
        System.out.println(battlefield.toString(hideShips));
        System.out.println("Take a shot!\n");
        do {
            Coordinate cord = getCoordinate(scanner);
            shootResult = battlefield.shoot(cord);
            System.out.println(battlefield.toString(hideShips));
            if (shootResult == ShootResult.MISS) {
                System.out.println("You missed! Try again:");
            } else if (shootResult == ShootResult.HIT) {
                System.out.println("You hit a ship! Try again:");
            } else if (shootResult == ShootResult.SUNK) {
                System.out.println("You sank a ship! Specify a new target:");
            } else {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
        } while (shootResult != ShootResult.GAME_OVER);
    }

    private static Coordinate getCoordinate(Scanner scanner) {
        while (true) {
            try {
                return Coordinate.fromString(scanner.next());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
