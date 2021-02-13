package hw4.puzzle;


import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayDeque;
import java.util.Comparator;

public class Solver {

    private ArrayDeque<WorldState> solutionDeque = new ArrayDeque<>();

    /** The minimum number of moves to solve the puzzle starting
     *  at the initial WorldState. */
    private int movesOfSolver;

    private MinPQ<SearchNode> myPQ = new MinPQ<>(new WsComparator());

    private class SearchNode {
        private WorldState myWS;
        private int numberOfMoves;
        private SearchNode prevNode;
        private int distanceToGoal;

        public SearchNode(WorldState ws, int moves, SearchNode prev) {
            myWS = ws;
            numberOfMoves = moves;
            prevNode = prev;
            distanceToGoal = myWS.estimatedDistanceToGoal();
        }
    }
    /** Constructor which solves the puzzle, computing everything necessary
     *  for moves() and solution() to not have to solve the problem again.
     *  Solves the puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        SearchNode initNode = new SearchNode(initial, 0, null);
        myPQ.insert(initNode);
        SearchNode x = myPQ.delMin();
        while (!x.myWS.isGoal()) {
            for (WorldState ws : x.myWS.neighbors()) {
                if (x.prevNode != null) {
                    if (ws.equals(x.prevNode.myWS)) {
                        continue;
                    }
                }
                SearchNode childNode = new SearchNode(ws, x.numberOfMoves + 1, x);
                myPQ.insert(childNode);
            }
            x = myPQ.delMin();
        }
        movesOfSolver = x.numberOfMoves;
        while (x != null) {
            solutionDeque.addFirst(x.myWS);
            x = x.prevNode;
        }
    }

    /** Returns the minimum number of moves to solve the puzzle starting
     *  at the initial WorldState.
     */
    public int moves() {
        return movesOfSolver;
    }

    /**
     * @return a sequence of WorldStates from the initial WorldState to the solution.
     */
    public Iterable<WorldState> solution() {
        return solutionDeque;
    }


    private class WsComparator<SearchNode> implements Comparator<Solver.SearchNode> {

        @Override
        public int compare(Solver.SearchNode o1, Solver.SearchNode o2) {
            int d1 = o1.distanceToGoal;
            int d2 = o2.distanceToGoal;
            int p1 = d1 + o1.numberOfMoves;
            int p2 = d2 + o2.numberOfMoves;
            return p1 - p2;
        }
    }
}
