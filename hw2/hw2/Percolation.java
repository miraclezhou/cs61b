package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF connectedSites;

    // Another wf to check if the site is full, avoid backwash.
    private WeightedQuickUnionUF checkFull;

    private int rows;
    private boolean[] openSites;
    private int openNums;
    private int top;
    private int bottom;

    /** create N-by-N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be positive!");
        }
        connectedSites = new WeightedQuickUnionUF(N * N + 2);
        checkFull = new WeightedQuickUnionUF(N * N + 1);
        openSites = new boolean[N * N];
        rows = N;
        openNums = 0;
        top = rows * rows;
        bottom = rows * rows + 1;
    }

    /** open the site (row, col) if it is not open already. */
    public void open(int row, int col) {
        int index = xyToIndex(row, col);

        if (!openSites[index]) {
            openSites[index] = true;
        }
        // Connect to the virtual top.
        if (row == 0) {
            connectedSites.union(index, top);
            checkFull.union(index, top);
        }
        // Connect to the virtual bottom.
        if (row == rows - 1) {
            connectedSites.union(index, bottom);
        }
        connectNeighbors(row, col);
        openSites[xyToIndex(row, col)] = true;
        openNums += 1;
    }

    /** Connect the neighbor sites if they are open. */
    private void connectNeighbors(int row, int col) {
        // Check left site.
        if (col > 0) {
            if (isOpen(row, col - 1)) {
                connectedSites.union(xyToIndex(row, col), xyToIndex(row, col - 1));
                checkFull.union(xyToIndex(row, col), xyToIndex(row, col - 1));
            }
        }
        // Check right site.
        if (col < rows - 1) {
            if (isOpen(row, col + 1)) {
                connectedSites.union(xyToIndex(row, col), xyToIndex(row, col + 1));
                checkFull.union(xyToIndex(row, col), xyToIndex(row, col + 1));
            }
        }
        // Check upper site.
        if (row > 0) {
            if (isOpen(row - 1, col)) {
                connectedSites.union(xyToIndex(row, col), xyToIndex(row - 1, col));
                checkFull.union(xyToIndex(row, col), xyToIndex(row - 1, col));
            }
        }
        // Check under site.
        if (row < rows - 1) {
            if (isOpen(row + 1, col)) {
                connectedSites.union(xyToIndex(row, col), xyToIndex(row + 1, col));
                checkFull.union(xyToIndex(row, col), xyToIndex(row + 1, col));
            }
        }
    }

    /** is the site (row, col) open? */
    public boolean isOpen(int row, int col) {
        return openSites[xyToIndex(row, col)];
    }

    /** is the site (row, col) full? */
    public boolean isFull(int row, int col) {
        // Check if this site is open.
        if (!isOpen(row, col)) {
            return false;
        }
        int index = xyToIndex(row, col);
        // Check if this site is connected to virtual top.
        return checkFull.connected(index, top);
    }

    /** Get number of open sites. */
    public int numberOfOpenSites() {
        return openNums;
    }

    /**  does the system percolate? */
    public boolean percolates() {
        return connectedSites.connected(top, bottom);
    }

    private int xyToIndex(int row, int col) {
        return rows * row + col;
    }

    /** use for unit testing (not required). */
    public static void main(String[] args) {
        Percolation p = new Percolation(5);
        p.open(0, 1);
        p.open(1, 1);
        p.open(1, 2);
        p.open(2, 2);
        p.open(2, 3);
        System.out.println(p.isFull(2, 4));
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());

        System.out.println(p.xyToIndex(2, 3));
    }

}
