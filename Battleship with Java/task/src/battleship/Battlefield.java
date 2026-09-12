package battleship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Battlefield {

    private final char[][] field;
    private final List<Ship> ships;
    private final int height;
    private final int width;
    private static final char FOG_SYMBOL = '~';
    private static final char SHIP_SYMBOL = 'O';
    private static final char HIT_SYMBOL = 'X';
    private static final char MISS_SYMBOL = 'M';


    public Battlefield() {
        this(10, 10);
    }

    private Battlefield(int height, int width) {
        this.height = height;
        this.width = width;
        this.field = new char[height][width];
        this.ships = new ArrayList<>();
        initField();
    }

    private void initField() {
        for (char[] chars : field) {
            Arrays.fill(chars, FOG_SYMBOL);
        }
    }

    public void placeShipOnField(String firstCord, String secondCord, ShipTypes shipType) {
        Coordinate c1 = Coordinate.fromString(firstCord);
        Coordinate c2 = Coordinate.fromString(secondCord);


        boolean isHorizontal = c1.getRow() == c2.getRow() && c1.getCol() != c2.getCol();
        boolean isVertical = c1.getRow() != c2.getRow() && c1.getCol() == c2.getCol();

        if ((!c1.isInBounds(height, width) || !c2.isInBounds(height, width))
                || (!isHorizontal && !isVertical)) {
            throw new IllegalArgumentException("Error! Wrong ship location! Try again:");
        }


        int startRow = Math.min(c1.getRow(), c2.getRow());
        int endRow = Math.max(c1.getRow(), c2.getRow());
        int startCol = Math.min(c1.getCol(), c2.getCol());
        int endCol = Math.max(c1.getCol(), c2.getCol());


        int actualLength = (endRow - startRow + endCol - startCol) + 1;

        if (actualLength != shipType.getSize()) {
            throw new IllegalArgumentException(
                    String.format("Error! Wrong length of the %s! Try again:", shipType.getName())
            );
        }

        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                if (hasAdjacentShip(r, c)) {
                    throw new IllegalArgumentException(
                            "Error! You placed it too close to another one. Try again:"
                    );
                }
            }
        }

        List<Coordinate> shipCoordinates = new ArrayList<>();
        for (int r = startRow; r <= endRow; r++) {
            for (int c = startCol; c <= endCol; c++) {
                field[r][c] = SHIP_SYMBOL;
                shipCoordinates.add(Coordinate.fromRowAndCol(r, c));
            }
        }
        ships.add(new Ship(shipCoordinates, shipType));
    }

    private boolean hasAdjacentShip(int row, int col) {

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int checkRow = row + i;
                int checkCol = col + j;

                if (checkRow >= 0 && checkRow < height && checkCol >= 0 && checkCol < width
                        && field[checkRow][checkCol] == SHIP_SYMBOL) return true;


            }

        }
        return false;
    }

    public ShootResult shoot(Coordinate cord) {
        if (!cord.isInBounds(height, width)) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }


        int row = cord.getRow();
        int col = cord.getCol();
        char cell = field[row][col];

        if (cell == FOG_SYMBOL || cell == MISS_SYMBOL) {
            field[row][col] = MISS_SYMBOL;
            return ShootResult.MISS;
        }

        boolean wasIntact = cell == SHIP_SYMBOL;
        field[row][col] = HIT_SYMBOL;

        if (wasIntact) {
            Ship hitShip = findShipAt(cord);
            if (hitShip != null && isShipSunk(hitShip)) {
                hitShip.setSunk(true);
                if (allShipSunk()) {
                    return ShootResult.GAME_OVER;
                }
                return ShootResult.SUNK;
            }
        }
        return ShootResult.HIT;
    }

    private boolean isShipSunk(Ship hitShip) {
        for (Coordinate coordinate : hitShip.getCoordinates()) {
            if (field[coordinate.getRow()][coordinate.getCol()] == SHIP_SYMBOL) {
                return false;
            }
        }
        return true;
    }

    private boolean allShipSunk() {
        for (Ship ship : ships) {
            if (!ship.isSunk()) return false;
        }
        return true;
    }

    private Ship findShipAt(Coordinate cord) {
        for (Ship ship : ships) {
            if (ship.contains(cord)) return ship;
        }
        return null;
    }

    @Override
    public String toString() {
        return toString(false);
    }

    public String toString(boolean hideShips) {
        StringBuilder sb = new StringBuilder();

        sb.append(" ");

        for (int i = 0; i < width; i++) {
            sb.append(i + 1)
                    .append(" ");
        }

        sb.append("\n");

        for (int i = 0; i < height; i++) {
            char letter = (char) ('A' + i);
            sb.append(letter).append(" ");
            for (int j = 0; j < width; j++) {
                char fieldSymbol = field[i][j];
                if (hideShips && fieldSymbol == SHIP_SYMBOL) {
                    fieldSymbol = FOG_SYMBOL;
                }
                sb.append(fieldSymbol).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
