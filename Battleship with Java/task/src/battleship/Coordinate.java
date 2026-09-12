package battleship;

public class Coordinate {
    private final int row;
    private final int col;

    private Coordinate(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public static Coordinate fromString(String raw) {
        if (raw == null) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }

        String cord = raw.trim().toUpperCase();

        if (!cord.matches("^[A-Z]\\d+$")) {
            throw new IllegalArgumentException("Error! You entered the wrong coordinates! Try again:");
        }
        int row = cord.charAt(0) - 'A';
        int col = Integer.parseInt(cord.substring(1)) - 1;

        return new Coordinate(row, col);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isInBounds(int height, int width) {
        return row >= 0 && row < height && col >= 0 && col < width;
    }
}
