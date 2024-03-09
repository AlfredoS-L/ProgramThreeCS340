import edu.princeton.cs.algs4.MinPQ;

/**
 * The {@code StreamMedian} class allows for the continuous insertion of integers
 * and the retrieval of the current median of the inserted numbers.
 * <p>
 *     It utilizes two priority queues (min heaps) to partition the stream of numbers into
 *     two halves, where one heap stores the larger half and the other stores the smaller half.
 *     This organization enables efficient median calculation for dynamically changing data sets.
 * </p>
 *
 * authors: Alfredo Sandoval-Luis and Charles Ray
 * edu.uwp.cs.340.course CSCI 340 - Data Structures/Algotrithm Design
 * edu.uwp.cs.340.section 001
 * edu.uwp.cs.340.assignment 3
 * bugs: none
 */

public class StreamMedian {
    private MinPQ<Integer> bigger;  // min heap for the larger half
    private MinPQ<Integer> smaller; // simulated max heap for the smaller half

    /**
     * Initializes an empty {@code StreamMedian} instance with no elements.
     */
    public StreamMedian() {
        bigger = new MinPQ<Integer>();
        smaller = new MinPQ<Integer>();
    }

    /**
     * Inserts a new integer into the stream. The method ensures the
     * two halves remain balanced or have at most a difference of one
     * element between them after each insertion.
     *
     * @param i the integer to insert into the stream
     */
    public void insert(Integer i) {
        if (smaller.isEmpty() || i <= -smaller.min()) {
            // negating the number to replicate max heap
            smaller.insert(-i);
        } else {
            bigger.insert(i);
        }

        // balancing heaps
        if (smaller.size() > bigger.size() + 1) {
            // negate the number again to restore its original value
            bigger.insert(-smaller.delMin());
        } else if (bigger.size() > smaller.size()) {
            // negate the number to maintain max heap simulation when moving to smaller
            smaller.insert(-bigger.delMin());
        }
    }

    /**
     * Returns the median of the currently inserted integers. If the total number
     * of elements is odd, the median is the middle element. If the number is even,
     * the median is the average of the two middle elements.
     *
     * @return the median of the inserted integers as a double
     */
    public double getMedian() {
        if (smaller.size() > bigger.size()) {
            // negate again for original value
            return -smaller.min();
        } else if (bigger.size() > smaller.size()) {
            return bigger.min();
        } else {
            // averaging the two values
            return (-smaller.min() + bigger.min()) / 2.0;
        }
    }
}
