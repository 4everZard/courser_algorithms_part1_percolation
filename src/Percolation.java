import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private final int n;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int numberOfOpenSites;

    /* create n-by-n grid, with all sites blocked */
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.grid = new boolean[n][n]; // false == blocked

        weightedQuickUnionUF = new WeightedQuickUnionUF(n * n + 2); // two more added, one to the top, one to the button
    }

    public static void main(String[] args) {
        System.out.println("Hello, World");
        Percolation p = new Percolation(10);
        System.out.println(p.isOpen(1, 1));
        System.out.println(p.numberOfOpenSites());

        p.open(1, 1);
        p.open(2, 1);
        p.open(1, 2);
        System.out.println(p.isFull(1, 2));
        System.out.println(p.isFull(2, 1));

        System.out.println(p.percolates());
    }

    /* number of open sites */
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    /* is site (row, col) full? */
    public boolean isFull(int row, int col) {
        // A full site is an open site that can be connected to an open site in the top row
        // via a chain of neighboring (left, right, up, down) open sites.
        return isOpen(row, col) && weightedQuickUnionUF.connected(0, rowColTo1D(row, col));
    }

    /* does the system percolate? */
    public boolean percolates() {
        // We say the system percolates if there is a full site in the bottom row.
        return weightedQuickUnionUF.connected(0, n * n + 1);
    }

    /* open site (row, col) if it is not open already */
    public void open(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }

        if (!this.grid[row - 1][col - 1]) {
            this.grid[row - 1][col - 1] = true;
            this.numberOfOpenSites++;

            if (row == 1) { // top
                weightedQuickUnionUF.union(0, rowColTo1D(row, col));
            }
            if (row == this.n) { // button
                weightedQuickUnionUF.union(n * n + 1, rowColTo1D(row, col));
            }


            if (col - 1 > 0) { // left
                if (isOpen(row, col - 1)) weightedQuickUnionUF.union(rowColTo1D(row, col - 1), rowColTo1D(row, col));
            }

            if (col + 1 <= n) { // right
                if (isOpen(row, col + 1)) weightedQuickUnionUF.union(rowColTo1D(row, col + 1), rowColTo1D(row, col));
            }

            if (row - 1 > 0) { // up
                if (isOpen(row - 1, col)) weightedQuickUnionUF.union(rowColTo1D(row - 1, col), rowColTo1D(row, col));
            }

            if (row + 1 <= n) { // down
                if (isOpen(row + 1, col)) weightedQuickUnionUF.union(rowColTo1D(row + 1, col), rowColTo1D(row, col));
            }
        }
    }

    /* is site (row, col) open? */
    public boolean isOpen(int row, int col) {
        // Can my Percolation data type assume the row and column indices are between 0 and n−1?
        // No. The API specifies that valid row and column indices are between 1 and n.
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }
        return this.grid[row - 1][col - 1];
    }

    private int rowColTo1D(int row, int col) {
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IllegalArgumentException();
        }

        row--;
        col--;

        return row * n + col;
    }
}