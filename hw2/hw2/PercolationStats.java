package hw2;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private int times;
    private int num;
    private double[] fraction;
    private double stddev;
    private double mean;
    private PercolationFactory pf;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T, PercolationFactory pf) {
        // Check argument N and T.
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N or T must be positive!");
        }
        num = N;
        times = T;
        fraction = new double[times];
        this.pf = pf;
        doExperiments();
    }

    private void doExperiments() {
        double sum = 0.0;
        for (int i = 0; i < times; i++) {
            Percolation perco = pf.make(num);
            while (!perco.percolates()) {
                int rowIndex = StdRandom.uniform(num);
                int colIndex = StdRandom.uniform(num);
                if (!perco.isOpen(rowIndex, colIndex)) {
                    perco.open(rowIndex, colIndex);
                }
            }
            fraction[i] = (double) perco.numberOfOpenSites() / (num * num);
            sum += fraction[i];
        }
        mean = sum / times;
    }
    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (times == 1) {
            return Double.NaN;
        }
        double sumStddev = 0.0;
        for (int i = 0; i < times; i++) {
            sumStddev += (fraction[i] - mean) * (fraction[i] - mean);
        }
        stddev = Math.sqrt(sumStddev / (times - 1));
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(times);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(times);
    }

    /**
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats p = new PercolationStats(50, 20, pf);
        System.out.println(p.mean());
        System.out.println(p.stddev());
        System.out.println("( " + p.confidenceLow() + " , " + p.confidenceHigh() + " )");
    }
     */




}
