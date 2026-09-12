package battleship;

import java.util.List;
import java.util.Objects;

public class Ship {
    private final ShipTypes type;
    private final List<Coordinate> coordinates;
    private boolean isSunk;

    public Ship(List<Coordinate> coordinates, ShipTypes type) {
        this.coordinates = coordinates;
        this.type = type;
        this.isSunk = false;
    }

    public boolean contains(Coordinate cord) {
        return coordinates.contains(cord);
    }

    public List<Coordinate> getCoordinates() {
        return coordinates;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public ShipTypes getType() {
        return type;
    }

    public void setSunk(boolean sunk) {
        isSunk = sunk;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ship ship)) return false;
        return isSunk == ship.isSunk && type == ship.type && Objects.equals(coordinates, ship.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, coordinates, isSunk);
    }
}
