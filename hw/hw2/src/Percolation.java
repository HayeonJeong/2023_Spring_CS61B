import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private int size;
    private int openCount;
    private int virtualTop;
    private int virtualBottom;
    private WeightedQuickUnionUF checkFull;
    private WeightedQuickUnionUF checkPercolates;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        //Corner Case
        if (N <= 0) {
            throw new IllegalArgumentException();
        }

        size = N;
        grid = new boolean[size][size];
        openCount = 0;

        virtualTop = 0;
        virtualBottom = size * size + 1;

        checkFull = new WeightedQuickUnionUF(virtualBottom + 1);
        checkPercolates = new WeightedQuickUnionUF(virtualBottom + 2);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        //Corner Cases
        throwIfIsIndexOutOfBound(row, col);

        if (isOpen(row, col)) {
            return;
        }
        grid[row][col] = true;
        openCount++;

        // if grid[row][col] is in the top row
        if (row == 0) {
            checkFull.union(virtualTop, xyTo1D(row, col));
            checkPercolates.union(virtualTop, xyTo1D(row, col));
        }
        // if grid[row][col] is in the bottom row
        if (row == size - 1) {
            checkPercolates.union(virtualBottom, xyTo1D(row, col));
        }

        // if left box is already opened
        if (validate(row, col - 1) && isOpen(row, col - 1)) {
            checkFull.union(xyTo1D(row, col), xyTo1D(row, col - 1));
            checkPercolates.union(xyTo1D(row, col), xyTo1D(row, col - 1));
        }
        // if right box is already opened
        if (validate(row, col + 1) && isOpen(row, col + 1)) {
            checkFull.union(xyTo1D(row, col), xyTo1D(row, col + 1));
            checkPercolates.union(xyTo1D(row, col), xyTo1D(row, col + 1));
        }
        // if up box is already opened
        if (validate(row + 1, col) && isOpen(row + 1, col)) {
            checkFull.union(xyTo1D(row, col), xyTo1D(row + 1, col));
            checkPercolates.union(xyTo1D(row, col), xyTo1D(row + 1, col));
        }
        // if down box is already opened
        if (validate(row - 1, col) && isOpen(row - 1, col)) {
            checkFull.union(xyTo1D(row, col), xyTo1D(row - 1, col));
            checkPercolates.union(xyTo1D(row, col), xyTo1D(row - 1, col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        //Corner Case
        throwIfIsIndexOutOfBound(row, col);

        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        //must be constant time
        //cannot use a for loop to do some sort for manual checking in the isFull method
        //cannot do some sort of recursive exploration of the entire grid in the isFull method.

        //Corner Case
        throwIfIsIndexOutOfBound(row, col);

        //in the disjoint sets object, we do NOT want M, N, O, and P to be connected to the virtual bottom.
        //wquIsFull (not connected to virtualBottom when grid[row][col] is in the bottom row.
        return checkFull.connected(virtualTop, xyTo1D(row, col));
    }

    // number of open sites
    public int numberOfOpenSites() {
        //don't use for loop. when you open, openCount++
        return openCount;
    }

    // does the system percolate?
    public boolean percolates() {
        //in the disjoint sets object, we want M, N, O, and P to be connected to the virtual bottom.
        //wquPercolate (connected to virtualBottom when grid[row][col] is in the bottom row.
        return checkPercolates.connected(virtualTop, virtualBottom);
    }

    public int xyTo1D(int row, int col) {
        //HW2 Party OH - got answer
        return row * size + col + 1;
    }


    //Corner Cases
    public boolean validate(int row, int col) {
        //except xyTo1D method, we should consider the 2D array's index normally.
        //the first box's index is grid[0][0].
        return 0 <= row && row < size && 0 <= col && col < size;
    }

    //Corner Cases
    public void throwIfIsIndexOutOfBound(int row, int col) {
        if (!validate(row, col)) {
            throw new IndexOutOfBoundsException("Index out of bounds exception");
        }
    }
}
