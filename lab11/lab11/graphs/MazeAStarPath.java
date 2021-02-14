package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private MinPQ<Integer> pq;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new MinPQ(new AStarComparator());
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int vx = maze.toX(v);
        int vy = maze.toY(v);
        int tx = maze.toX(t);
        int ty = maze.toY(t);
        return Math.abs(tx - vx) + Math.abs(ty - vy);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex x. */
    private void astar(int x) {
        marked[x] = true;
        announce();
        pq.insert(x);
        while (!pq.isEmpty() && !targetFound) {
            int v = pq.delMin();
            for (int w : maze.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    if (w == t) {
                        targetFound = true;
                        break;
                    }
                    pq.insert(w);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

    private class AStarComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return h(o1) - h(o2);
        }
    }
}

