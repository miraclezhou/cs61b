package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {
    private int size;
    private int[][] tiles;
    private final int BLANK = 0;

    /** Constructs a board from an N-by-N array of tiles where
        tiles[i][j] = tile at row i, column j. */
    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        // Check illegal index.
        if (i < 0 || j < 0 || i > (size - 1) || j > (size - 1)) {
            throw new IllegalArgumentException("i or j must be between 0 and N - 1");
        }
        if (size == 0) {
            return 0;
        }
        return tiles[i][j];
    }

    /** Returns the board size N. */
    public int size() {
        return size;
    }

    /** Returns the neighbors of the current board. */
    /**
     * Returns neighbors of this board.
     * SPOILERZ: This is the answer.
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    /** Return the right number at row i and column j. */
    private int rightNumAt(int i, int j) {
        return i * size + j + 1;
    }

    /** Hamming estimate described below. */
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) == 0) {
                    continue;
                }
                if (tileAt(i, j) != rightNumAt(i, j)) {
                    hamming += 1;
                }
            }
        }
        return hamming;
    }

    /**
     * Given a num, return its right num of row.
     */
    private int getRightIndexOfRow(int num) {
        return (num - 1) / size;
    }

    /**
     * Given a num, return its right num of column.
     */
    private int getRightIndexOfcolumn(int num) {
        int iCol = (num % size) - 1;
        if (iCol < 0) {
            iCol = size - 1;
        }
        return iCol;
    }

    /** Manhattan estimate described below. */
    public int manhattan() {
        int manhattan = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int num = tileAt(i, j);
                if (num == 0) {
                    continue;
                }
                int iRow = getRightIndexOfRow(num);
                int iCol = getRightIndexOfcolumn(num);
                int vDistance = Math.abs(i - iRow);
                int hDistance = Math.abs(j - iCol);
                manhattan += (vDistance + hDistance);
            }
        }
        return manhattan;
    }

    /** Estimated distance to goal. This method should simply return the
     *  results of manhattan() when submitted to Grade scope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /** Returns true if this board's tile values are the same position as y's. */
    public boolean equals(Object y) {
        Board o = (Board) y;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tileAt(i, j) != o.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }


    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
