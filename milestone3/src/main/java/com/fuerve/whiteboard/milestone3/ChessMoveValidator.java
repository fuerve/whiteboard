package com.fuerve.whiteboard.milestone3;

import java.util.ArrayList;
import java.util.List;

/**
 * Chess move validator.  Will validate that a piece is being moved correctly.  Doesn't necessarily
 * take into account the full state of the board.
 */
public class ChessMoveValidator {
    /**
     * Validates a possible move of a piece.
     * @param piece The piece being moved.
     * @param start The starting coordinate on the board.
     * @param end The ending coordinate on the board.
     * @param player The player making the move.
     * @return True if the move is valid.
     */
    public static boolean validateMove(final Piece piece, final Coordinate start, final Coordinate end, final Player player) {
        return possibleMoves(piece, start, player).contains(end);
    }

    /**
     * Builds a list of all possible moves for a piece.
     * @param piece The piece being moved.
     * @param start The starting coordinate on the board.
     * @param player The player making the move.
     * @return A list of all possible moves for the piece.
     */
    public static List<Coordinate> possibleMoves(final Piece piece, final Coordinate start, final Player player) {
        switch (piece) {
        case PAWN:
            return pawnMoves(start, player);
        case ROOK:
            return rookMoves(start, player);
        case BISHOP:
            return bishopMoves(start, player);
        case KNIGHT:
            return knightMoves(start, player);
        case QUEEN:
            return queenMoves(start, player);
        case KING:
            return kingMoves(start, player);
        default:
            return new ArrayList<Coordinate>();
        }
    }

    /**
     * Moves for the pawn.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> pawnMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();
        final int startX = start.getX();
        final int startY = start.getY();
        final int direction = player == Player.BLACK ? -1 : 1;
        
        result.add(new Coordinate(startX, startY + direction));
        return result;
    }
    
    /**
     * Moves for the rook.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> rookMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();

        final int startX = start.getX();
        final int startY = start.getY();
        
        for (int x = 0; x < 8; x++) {
            if (x == startX) {
                continue;
            } else {
                result.add(new Coordinate(x, startY));
            }
        }
        
        for (int y = 0; y < 8; y++) {
            if (y == startY) {
                continue;
            } else {
                result.add(new Coordinate(startX, y));
            }
        }

        return result;
    }
    
    /**
     * Moves for the bishop.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> bishopMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();

        final int startX = start.getX();
        final int startY = start.getY();
        
        // The number of spaces between each valid point in discrete space on a perfectly
        // diagonal line is proportional to the nth odd number, where n is the number of
        // spaces distant a point along a given axis is from the starting point.
        // That is, if you imagine drawing a box around the bishop at a single space away,
        // the four corners would have a single space between them.  At two spaces distance,
        // each of the four corners has three spaces between them.  Then five, then seven,
        // though at that point, at least one space is going to be off the board.
        for (int i = 1; i < 8; i++) {
            // First coordinate.
            int topLeftX = startX - i;
            int topLeftY = startY + i;
            
            // Second coordinate.
            int topRightX = startX + i;
            int topRightY = startX + i;
            
            // Third coordinate.
            int bottomRightX = startX + i;
            int bottomRightY = startY - i;
            
            // Fourth coordinate.
            int bottomLeftX = startX - i;
            int bottomLeftY = startY - i;
            
            addIfOnBoard(result, topLeftX, topLeftY);
            addIfOnBoard(result, topRightX, topRightY);
            addIfOnBoard(result, bottomRightX, bottomRightY);
            addIfOnBoard(result, bottomLeftX, bottomLeftY);
        }

        return result;
    }
    
    /**
     * Moves for the knight.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> knightMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();

        addIfOnBoard(result, start.getX() - 2, start.getY() - 1);
        addIfOnBoard(result, start.getX() - 2, start.getY() + 1);
        addIfOnBoard(result, start.getX() + 2, start.getY() - 1);
        addIfOnBoard(result, start.getX() + 2, start.getY() + 1);
        addIfOnBoard(result, start.getX() - 1, start.getY() - 2);
        addIfOnBoard(result, start.getX() - 1, start.getY() + 2);
        addIfOnBoard(result, start.getX() + 1, start.getY() - 2);
        addIfOnBoard(result, start.getX() + 1, start.getY() + 2);

        return result;
    }
    
    /**
     * Moves for the queen.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> queenMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();

        // A queen can do everything that both a rook and a bishop can do.
        result.addAll(rookMoves(start, player));
        result.addAll(bishopMoves(start, player));

        return result;
    }
    
    /**
     * Moves for the king.
     * @param start The starting point.
     * @param player The player.
     * @return A list of all possible moves.
     */
    private static List<Coordinate> kingMoves(final Coordinate start, final Player player) {
        final List<Coordinate> result = new ArrayList<Coordinate>();

        final int startX = start.getX();
        final int startY = start.getY();
        final int minX = Math.max(0, startX - 1);
        final int maxX = Math.min(7, startX + 1);
        final int minY = Math.max(0, startY - 1);
        final int maxY = Math.min(7, startY + 1);
        
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                if (x == startX && y == startY) {
                    continue;
                } else {
                    result.add(new Coordinate(x, y));
                }
            }
        }

        return result;
    }

    
    /**
     * Determines whether a set of x and y coordinates are on the board.
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @return True if the point is on the board.
     */
    private static boolean isOnBoard(final int x, final int y) {
        return (x >= 0 && x < 8 && y >= 0 && y < 8);
    }
    
    /**
     * Adds a coordinate to the result list if that coordinate is on the board.
     * @param result The result list.
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    private static void addIfOnBoard(final List<Coordinate> result, final int x, final int y) {
        if (isOnBoard(x, y)) {
            result.add(new Coordinate(x, y));
        }
    }

    /**
     * The different pieces in chess.
     */
    public enum Piece {
        PAWN,
        ROOK,
        BISHOP,
        KNIGHT,
        QUEEN,
        KING
    }

    /**
     * The two possible players.
     */
    public enum Player {
        BLACK,
        WHITE
    }

    /**
     * Contains a 2D coordinate in quadrant I.
     */
    public static class Coordinate {
        private int x, y;

        /**
         * Sets the x coordinate.
         * @param xx The x coordinate.
         */
        public void setX(final int xx) {
            if (xx < 0) {
                x = 0;
            } else if (xx > 7) {
                x = 7;
            } else {
                x = xx;
            }
        }

        /**
         * Gets the x coordinate.
         * @return The x coordinate.
         */
        public int getX() {
            return x;
        }

        /**
         * Sets the y coordinate.
         * @param yy The y coordinate.
         */
        public void setY(final int yy) {
            if (yy < 0) {
                y = 0;
            } else if (yy > 7) {
                y = 7;
            } else {
                y = yy;
            }
        }

        /**
         * Gets the y coordinate.
         * @return The y coordinate.
         */
        public int getY() {
            return y;
        }

        /**
         * Initializes a new instance of Coordinate.
         * @param xx The x coordinate.
         * @param yy The y coordinate.
         */
        public Coordinate(final int xx, final int yy) {
            x = xx;
            y = yy;

            if (x < 0) {
                x = 0;
            } else if (x > 7) {
                x = 7;
            }

            if (y < 0) {
                y = 0;
            } else if (y > 7) {
                y = 7;
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Coordinate) {
                if (obj == this) {
                    return true;
                } else {
                    return ((Coordinate) obj).x == x && ((Coordinate) obj).y == y;
                }
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }
}
