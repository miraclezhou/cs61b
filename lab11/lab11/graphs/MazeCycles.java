package lab11.graphs;


/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean cycleFound = false;
    private Maze maze;
    private int[] parent;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        s = maze.xyTo1D(1, 1);
        edgeTo[s] = s;
        parent = new int[maze.V()];
        parent[s] = s;
    }

    @Override
    public void solve() {
        detectCycles(s);
    }

    // Helper methods go here
    private void detectCycles(int v) {
        marked[v] = true;
        announce();
        if (cycleFound) {
            return;
        }
        for (int w : maze.adj(v)) {
            if (!marked[w]) {
                marked[w] = true;
                parent[w] = v;
                announce();
                detectCycles(w);
                /** If a cycle is found, stop searching other adjacencies. */
                if (cycleFound) {
                    return;
                }
            } else if (w != parent[v]) {
                /** Find a visited vertex from v's adj but not v's parent vertex.
                 *  Then find a cycle.
                 */
                int x = v;
                while (x != w) {
                    int p = parent[x];
                    edgeTo[x] = p;
                    x = p;
                    announce();
                }
                edgeTo[w] = v;
                announce();
                cycleFound = true;
                return;
            }
        }
    }
}

