import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void numberOfOpenSites() {
        Percolation p = new Percolation(10);

        assertEquals(p.numberOfOpenSites(), 0);
        p.open(1,1);
        assertEquals(p.numberOfOpenSites(), 1);
        p.open(10,10);
        assertEquals(p.numberOfOpenSites(), 2);
    }

    @org.junit.jupiter.api.Test
    void isFull() {
        Percolation p = new Percolation(10);

        p.open(1,1);
        p.open(2,1);
        p.open(10,10);

        assertEquals(p.isFull(1,1), true);
        assertEquals(p.isFull(2,1), true);
        assertEquals(p.isFull(10,10), false);
    }

    @org.junit.jupiter.api.Test
    void percolates() {
        Percolation p = new Percolation(10);

        p.open(1,1);
        p.open(2,1);
        p.open(10,10);
        assertEquals(p.percolates(), false);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        p.open(4,1);
        p.open(5,1);
        p.open(6,1);
        p.open(7,1);
        p.open(8,1);
        p.open(9,1);
        p.open(10,1);
        assertEquals(p.percolates(), true);

        Percolation p2 = new Percolation(1);
        p2.open(1,1);
        assertEquals(p2.percolates(), true);

    }

    @org.junit.jupiter.api.Test
    void open() {
        Percolation p = new Percolation(10);

        p.open(1,1);
        assertEquals(p.isOpen(1,1), true );
    }

    @org.junit.jupiter.api.Test
    void isOpen() {
        Percolation p = new Percolation(10);

        p.open(1,1);
        assertEquals(p.isOpen(1,1), true );
        assertEquals(p.isOpen(10,10), false );
    }

//    @org.junit.jupiter.api.Test
//    void main() {
//    }
}