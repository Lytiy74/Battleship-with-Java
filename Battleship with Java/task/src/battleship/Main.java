package battleship;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Player current = new Player(new Battlefield(), "Player 1");
        Player opponent = new Player(new Battlefield(), "Player 2");

        Scanner scanner = new Scanner(System.in);
        placeShipsForPlayer(current, scanner);
        promptEnterKey(scanner);
        placeShipsForPlayer(opponent, scanner);
        promptEnterKey(scanner);

        ShootResult shootResult;
        do {
            System.out.println(opponent.getBattlefield().toString(true));

            System.out.println("---------------------");

            System.out.println(current.getBattlefield().toString(false));

            System.out.printf("%s, it's your turn:", current.getName());

            Coordinate cord = getCoordinate(scanner);
            shootResult = opponent.getBattlefield().shoot(cord);
            if (shootResult == ShootResult.MISS) {
                System.out.println("You missed!");
            } else if (shootResult == ShootResult.HIT) {
                System.out.println("You hit a ship!");
            } else if (shootResult == ShootResult.SUNK) {
                System.out.println("You sank a ship!");
            } else {
                System.out.println("You sank the last ship. You won. Congratulations!");
            }
            promptEnterKey(scanner);
            Player temp = current;
            current = opponent;
            opponent = temp;

        } while (shootResult != ShootResult.GAME_OVER);
    }

    private static void placeShipsForPlayer(Player player, Scanner scanner) {
        Battlefield battlefield = player.getBattlefield();
        System.out.printf("%s, place your ships on the game field\n", player.getName());
        System.out.println(battlefield.toString(false));
        for (ShipTypes ship : ShipTypes.values()) {
            System.out.printf("Enter the coordinates of the %s (%d cells)\n", ship.getName(), ship.getSize());

            while (true) {
                try {
                    String cor1 = scanner.next();
                    String cor2 = scanner.next();
                    scanner.nextLine();

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

    private static void promptEnterKey(Scanner scanner) {
        System.out.println("Press Enter and pass the move to another player");
        scanner.nextLine();
    }

    private static Coordinate getCoordinate(Scanner scanner) {
        while (true) {
            try {
                Coordinate coordinate = Coordinate.fromString(scanner.next());
                scanner.nextLine();
                return coordinate;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
