package lab11.graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Queue<Integer> q = new ArrayDeque<>();
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
        marked[s] = true;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        q.add(s);
        while (!q.isEmpty() && !targetFound) {
            int x = q.remove();
            for (int w : maze.adj(x)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = x;
                    distTo[w] = distTo[x] + 1;
                    announce();
                    if (w == t) {
                        targetFound = true;
                        break;
                    }
                    q.add(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

