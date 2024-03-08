import edu.princeton.cs.algs4.MinPQ;

public class StreamMedian {
    private MinPQ<Integer> bigger;  // min-heap for the larger half
    private MinPQ<Integer> smaller; // simulated max-heap for the smaller half by negating the integers

    public StreamMedian() {
        // Initialize the heaps
        bigger = new MinPQ<Integer>();
        smaller = new MinPQ<Integer>();
    }

    public void insert(Integer i) {
        if (smaller.isEmpty() || i <= -smaller.min()) {
            // Negate the number to simulate max-heap behavior
            smaller.insert(-i);
        } else {
            bigger.insert(i);
        }

        // Balance the heaps
        if (smaller.size() > bigger.size() + 1) {
            // Re-negate the number to restore its original value before moving it
            bigger.insert(-smaller.delMin());
        } else if (bigger.size() > smaller.size()) {
            // Negate the number to maintain max-heap simulation when moving to smaller
            smaller.insert(-bigger.delMin());
        }
    }

    public double getMedian() {
        if (smaller.size() > bigger.size()) {
            // Re-negate to get the original value
            return -smaller.min();
        } else if (bigger.size() > smaller.size()) {
            return bigger.min();
        } else {
            // Re-negate the smaller heap's top element and average it with the bigger heap's top element
            return (-smaller.min() + bigger.min()) / 2.0;
        }
    }
}
