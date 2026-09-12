# 🚢 Battleship (Java)

A clean, object-oriented console implementation of the classic **Battleship** game featuring hot-seat local 2-Player (PvP) mode, Fog of War, and comprehensive collision & boundary checks.

---

## 🎮 Game Rules & Overview

- **Fleet Composition**: Each player commands 5 standard ships:
  - Aircraft Carrier (5 cells)
  - Battleship (4 cells)
  - Submarine (3 cells)
  - Cruiser (3 cells)
  - Destroyer (2 cells)
- **Placement Rules**: Ships can be placed horizontally or vertically. Ships must not overlap and cannot touch each other (not even diagonally).
- **PvP Gameplay**: Players take turns entering target coordinates (e.g., `A1`, `F7`).
- **Fog of War**: During shooting, each player sees the opponent's board with ships concealed, alongside their own uncovered fleet view.
- **Victory Condition**: The first player to sink all 5 opponent ships wins the match!

---

## 🏛️ Architecture & Clean Code

The project is built following **SOLID**, **Domain-Driven Design (DDD)**, and **Clean Code** principles:

```
src/battleship/
├── Coordinate.java    # Immutable Value Object with regex validation & bounds checking
├── ShipTypes.java     # Enum defining ship specifications (sizes and display names)
├── Ship.java          # Domain Entity encapsulating coordinates and ship vitality
├── ShootResult.java   # Enum representing shot outcomes (HIT, MISS, SUNK, GAME_OVER)
├── Battlefield.java   # Aggregate Root managing the 10x10 grid, placement rules & shooting
├── Player.java        # Entity coupling a player identity with their battlefield
└── Main.java          # Game controller orchestrating game phases and console UI
```

### Key Design Highlights
- **Static Factory Methods**: `Coordinate.fromString()` and `Coordinate.fromRowAndCol()` separate instantiation from string parsing.
- **Value Object Contract**: `Coordinate` properly overrides `equals()` and `hashCode()`.
- **Command-Query Separation (CQS)**: Distinction between state-checking queries and state-altering commands.
- **Single Source of Truth**: The board rendering is parameterized (`toString(boolean hideShips)`), avoiding grid duplication.
- **Open-Closed Principle (OCP)**: Adding multiplayer required zero changes to existing core game classes.

---

## 🚀 Getting Started

### Prerequisites
- Java Development Kit (JDK) 17+ installed.

### Run
Compile and run directly using `javac` and `java`:

```bash
# Compile
javac battleship/*.java

# Run
java battleship.Main
```
