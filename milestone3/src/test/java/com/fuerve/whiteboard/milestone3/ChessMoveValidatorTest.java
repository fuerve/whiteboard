package com.fuerve.whiteboard.milestone3;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fuerve.whiteboard.milestone3.ChessMoveValidator.Coordinate;
import com.fuerve.whiteboard.milestone3.ChessMoveValidator.Piece;
import com.fuerve.whiteboard.milestone3.ChessMoveValidator.Player;

/**
 * Tests for the ChessMoveValidator class.
 */
public class ChessMoveValidatorTest {

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.ChessMoveValidator#validateMove(com.fuerve.whiteboard.milestone3.ChessMoveValidator.Piece, com.fuerve.whiteboard.milestone3.ChessMoveValidator.Coordinate, com.fuerve.whiteboard.milestone3.ChessMoveValidator.Coordinate, boolean)}.
     */
    @Test
    public void testValidateMovePawn() {
        assertTrue(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 0), new Coordinate(0, 1), Player.WHITE));
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 1), new Coordinate(0, 0), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 1), new Coordinate(0, 0), Player.BLACK));
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 0), new Coordinate(0, 1), Player.BLACK));
        
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 0), new Coordinate(1, 0), Player.WHITE));
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(0, 0), new Coordinate(1, 0), Player.BLACK));
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(1, 0), new Coordinate(0, 0), Player.WHITE));
        assertFalse(ChessMoveValidator.validateMove(Piece.PAWN, new Coordinate(1, 0), new Coordinate(0, 0), Player.BLACK));
    }
    
    /***/
    @Test
    public void testValidateMoveRook() {
        assertTrue(ChessMoveValidator.validateMove(Piece.ROOK, new Coordinate(0, 0), new Coordinate(0, 7), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.ROOK, new Coordinate(0, 0), new Coordinate(0, 7), Player.BLACK));
        assertFalse(ChessMoveValidator.validateMove(Piece.ROOK, new Coordinate(0, 0), new Coordinate(7, 7), Player.WHITE));
        assertFalse(ChessMoveValidator.validateMove(Piece.ROOK, new Coordinate(0, 0), new Coordinate(7, 7), Player.BLACK));
    }
    
    /***/
    @Test
    public void testValidateMoveBishop() {
        assertFalse(ChessMoveValidator.validateMove(Piece.BISHOP, new Coordinate(0, 0), new Coordinate(0, 7), Player.WHITE));
        assertFalse(ChessMoveValidator.validateMove(Piece.BISHOP, new Coordinate(0, 0), new Coordinate(0, 7), Player.BLACK));
        assertTrue(ChessMoveValidator.validateMove(Piece.BISHOP, new Coordinate(0, 0), new Coordinate(7, 7), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.BISHOP, new Coordinate(0, 0), new Coordinate(7, 7), Player.BLACK));
    }
    
    /***/
    @Test
    public void testValidateMoveKnight() {
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(1, 4), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(1, 4), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(1, 2), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(1, 2), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(2, 5), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(2, 1), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(4, 5), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(4, 1), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(5, 4), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(5, 2), Player.BLACK));
        
        assertFalse(ChessMoveValidator.validateMove(Piece.KNIGHT, new Coordinate(3, 3), new Coordinate(2, 2), Player.BLACK));
    }
    
    /***/
    @Test
    public void testValidateMoveQueen() {
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(0, 0), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(0, 0), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(7, 7), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(7, 7), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(0, 6), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(0, 6), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(6, 0), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(6, 0), Player.BLACK));
        
        assertFalse(ChessMoveValidator.validateMove(Piece.QUEEN, new Coordinate(3, 3), new Coordinate(1, 0), Player.BLACK));
    }
    
    /***/
    @Test
    public void testValidateMoveKing() {
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 2), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 2), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 3), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 3), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 4), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(2, 4), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(3, 2), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(3, 2), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(3, 4), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(3, 4), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 2), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 2), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 3), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 3), Player.BLACK));
        
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 4), Player.WHITE));
        assertTrue(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(4, 4), Player.BLACK));
        
        assertFalse(ChessMoveValidator.validateMove(Piece.KING, new Coordinate(3, 3), new Coordinate(0, 0), Player.BLACK));
    }

    /**
     * Test method for {@link com.fuerve.whiteboard.milestone3.ChessMoveValidator#possibleMoves(com.fuerve.whiteboard.milestone3.ChessMoveValidator.Piece, com.fuerve.whiteboard.milestone3.ChessMoveValidator.Coordinate, boolean)}.
     */
    @Test
    public void testPossibleMovesWhitePawn() {
        final Coordinate start = new Coordinate(0, 0);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        expected.add(new Coordinate(0, 1));
        
        final List<Coordinate> actual = ChessMoveValidator.possibleMoves(Piece.PAWN, start, Player.WHITE);
        assertTrue(actual.containsAll(expected));
    }
    
    /***/
    @Test
    public void testPossibleMovesBlackPawn() {
        final Coordinate start = new Coordinate(0, 1);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        expected.add(new Coordinate(0, 0));
        
        final List<Coordinate> actual = ChessMoveValidator.possibleMoves(Piece.PAWN, start, Player.BLACK);
        assertTrue(actual.containsAll(expected));
    }
    
    /***/
    @Test
    public void testPossibleMovesRook() {
        final Coordinate start = new Coordinate(3, 3);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        for (int x = 0; x < 8; x++) {
            if (x == start.getX()) {
                continue;
            }
            expected.add(new Coordinate(x, start.getY()));
        }
        for (int y = 0; y < 8; y++) {
            if (y == start.getY()) {
                continue;
            }
            expected.add(new Coordinate(start.getX(), y));
        }
        
        assertTrue(ChessMoveValidator.possibleMoves(Piece.ROOK, start, Player.WHITE).containsAll(expected));
        assertTrue(ChessMoveValidator.possibleMoves(Piece.ROOK, start, Player.BLACK).containsAll(expected));
    }

    /***/
    @Test
    public void testPossibleMovesBishop() {
        final Coordinate start = new Coordinate(3, 3);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // The piece can move diagonally from (3, 3) to any spot that divides to 1 (e.g. 4/4)
                // or any spot with coordinates that add to 6 (e.g. 3+3, 2+4, 1+5).
                if (x == start.getX() && y == start.getY()) {
                    continue;
                } else if (x + y == 6 || (x == 0 && y == 0) || (double)x / (double)y == 1.0) {
                    expected.add(new Coordinate(x, y));
                }
            }
        }
        
        assertTrue(ChessMoveValidator.possibleMoves(Piece.BISHOP, start, Player.WHITE).containsAll(expected));
        assertTrue(ChessMoveValidator.possibleMoves(Piece.BISHOP, start, Player.BLACK).containsAll(expected));
    }
    
    /***/
    @Test
    public void testPossibleMovesKnight() {
        final Coordinate start = new Coordinate(3, 3);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        expected.add(new Coordinate(start.getX() - 2, start.getY() - 1));
        expected.add(new Coordinate(start.getX() - 2, start.getY() + 1));
        expected.add(new Coordinate(start.getX() + 2, start.getY() - 1));
        expected.add(new Coordinate(start.getX() + 2, start.getY() + 1));
        expected.add(new Coordinate(start.getX() - 1, start.getY() - 2));
        expected.add(new Coordinate(start.getX() - 1, start.getY() + 2));
        expected.add(new Coordinate(start.getX() + 1, start.getY() - 2));
        expected.add(new Coordinate(start.getX() + 1, start.getY() + 2));

        assertTrue(ChessMoveValidator.possibleMoves(Piece.KNIGHT, start, Player.WHITE).containsAll(expected));
        assertTrue(ChessMoveValidator.possibleMoves(Piece.KNIGHT, start, Player.BLACK).containsAll(expected));
    }
    
    /***/
    @Test
    public void testPossibleMovesQueen() {
        final Coordinate start = new Coordinate(3, 3);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                // The piece can move diagonally from (3, 3) to any spot that divides to 1 (e.g. 4/4)
                // or any spot with coordinates that add to 6 (e.g. 3+3, 2+4, 1+5), or any piece along
                // a horizontal or vertical line.
                if (y == 0 && x != 0) {
                    continue;
                } else if (x == start.getX() && y == start.getY()) {
                    continue;
                }
                if (x + y == 6 || (x == 0 && y == 0) || (double)x / (double)y == 1.0 || x == start.getX() || y == start.getY()) {
                    expected.add(new Coordinate(x, y));
                }
            }
        }
        
        
        assertTrue(ChessMoveValidator.possibleMoves(Piece.QUEEN, start, Player.WHITE).containsAll(expected));
        assertTrue(ChessMoveValidator.possibleMoves(Piece.QUEEN, start, Player.BLACK).containsAll(expected));
    }
    
    /***/
    @Test
    public void testPossibleMovesKing() {
        final Coordinate start = new Coordinate(3, 3);
        final List<Coordinate> expected = new ArrayList<Coordinate>();
        for (int x = start.getX() - 1; x <= start.getX() + 1; x++) {
            for (int y = start.getX() - 1; y <= start.getY() + 1; y++) {
                if (x == start.getX() && y == start.getY()) {
                    continue;
                }
                // The piece can move diagonally from (3, 3) to any spot that divides to 1 (e.g. 4/4)
                // or any spot with coordinates that add to 6 (e.g. 2+4, 4+2), or any piece along
                // a horizontal or vertical line, within one space of its starting square.
                if (x / y == 1 || x + y == 6 || x == start.getX() || y == start.getY()) {
                    expected.add(new Coordinate(x, y));
                }
            }
        }
        
        
        assertTrue(ChessMoveValidator.possibleMoves(Piece.KING, start, Player.WHITE).containsAll(expected));
        assertTrue(ChessMoveValidator.possibleMoves(Piece.KING, start, Player.BLACK).containsAll(expected));
    }
}
