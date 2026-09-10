package battleship;

import java.util.Arrays;

public class Battlefield {

    private final char[][] field;
    private final int height;
    private final int width;
    private static final char FOG_SYMBOL = '~';
    private static final char PLAYER_SHIP_SYMBOL = 'O';


    public Battlefield() {
        this(10, 10);
    }

    private Battlefield(int height, int width) {
        this.height = height;
        this.width = width;
        this.field = new char[height][width];
        initField();
    }

    private void initField() {
        for (char[] chars : field) {
            Arrays.fill(chars, FOG_SYMBOL);
        }
    }

    public void placeShipOnField(String firstCoord, String secondCoord) {
        Coordinate c1 = new Coordinate(firstCoord);
        Coordinate c2 = new Coordinate(secondCoord);

        if (!isValidCoordinates(c1, c2)) {
            System.out.println("Error!");
            return;
        }

        int startRow = Math.min(c1.getRow(), c2.getRow());
        int endRow = Math.max(c1.getRow(), c2.getRow());
        int startCol = Math.min(c1.getCol(), c2.getCol());
        int endCol = Math.max(c1.getCol(), c2.getCol());


        int length = (endRow - startRow + endCol - startCol) + 1;
        System.out.printf("Length: %d\n", length);

        StringBuilder sb = new StringBuilder("Parts: ");
        for (int r = startRow; r <= endRow; r++) {
            char letter = (char) ('A' + r);
            for (int c = startCol; c <= endCol; c++) {
                sb.append(letter).append(c+1).append(" ");
                field[r][c] = PLAYER_SHIP_SYMBOL;
            }
        }

        System.out.println(sb);
    }

    private boolean isValidCoordinates(Coordinate c1, Coordinate c2) {
        if (c1.getRow() < 0 || c1.getCol() < 0 || c2.getRow() < 0 || c2.getCol() < 0) return false;
        if (c1.getRow() >= height || c1.getCol() >= width || c2.getRow() >= height || c2.getCol() >= width) return false;
        if(!(c1.getRow() == c2.getRow() || c1.getCol() == c2.getCol())) return false;
        return true;
    }

    @Override
    public String toString() {
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
                sb.append(field[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
