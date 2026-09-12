package battleship;

public class Coordinate {
    private final int row;
    private final int col;

    public Coordinate(String coord) {
        coord = coord.trim().toUpperCase();
        this.row = coord.charAt(0) - 'A';
        this.col = Integer.parseInt(coord.substring(1)) - 1;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public boolean isInBounds(int height, int width) {
        return row >= 0 && row <= height && col >= 0 && col <= width;
    }
}
