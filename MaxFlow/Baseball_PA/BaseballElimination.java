package MaxFlow.Baseball_PA;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Bag;
//imports for the max flow graph
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FlowEdge;

import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {
    private int[] wins, loss, left;
    private int maxWin;
    private int[][] games;
    private ST<String, Integer> teams;

    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        In input = new In(filename);
        int numTeams = input.readInt();
        wins = new int[numTeams];
        loss = new int[numTeams];
        left = new int[numTeams];
        maxWin = 0;
        games = new int[numTeams][numTeams];
        teams = new ST<String, Integer>();

        for (int i = 0; i < numTeams; i++) {
            teams.put(input.readString(), i);
            wins[i] = input.readInt();
            loss[i] = input.readInt();
            left[i] = input.readInt();
            if (maxWin < wins[i])
                maxWin = wins[i];
            for (int j = 0; j < numTeams; j++)
                games[i][j] = input.readInt();
        }
    }

    // number of teams
    public int numberOfTeams() {
        return teams.size();
    }

    // all teams
    public Iterable<String> teams() {
        return teams.keys();
    }

    // number of wins for given team
    public int wins(String team) {
        int t = getTeam(team);
        return wins[t];
    }

    // number of losses for given team
    public int losses(String team) {
        int t = getTeam(team);
        return loss[t];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        int t = getTeam(team);
        return left[t];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        int t1 = getTeam(team1), t2 = getTeam(team2);
        return games[t1][t2];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        int t = getTeam(team), totalGames = 0;
        for (int i = 0; i < games.length; i++)
            for (int j = 0; j < games[0].length; j++)
                totalGames += games[i][j];
        totalGames /= 2;
        return (eliminate(t).value() != totalGames) || (wins[t] + left[t] < maxWin);
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        if (!isEliminated(team))
            return null;
        int t = getTeam(team);
        Bag<String> coe = new Bag<String>();
        boolean isSimple = wins[t] + left[t] < maxWin;
        FordFulkerson maxFlow = eliminate(t);
        Iterable<String> keys = teams();
        for (String key : keys)
            if ((!isSimple && maxFlow.inCut(getTeam(key))) || (isSimple && wins[getTeam(key)] == maxWin))
                coe.add(key);
        return coe;
    }

    private FordFulkerson eliminate(int team) {
        int numTeams = numberOfTeams();
        FlowNetwork graph = new FlowNetwork(numTeams + 2 + numTeams * (numTeams - 1) / 2);
        // team nodes, source and end, games nodes
        int nodeNum = numTeams + 2, cap;
        for (int i = 0; i < numTeams; i++) {
            for (int j = i + 1; j < numTeams; j++) {
                graph.addEdge(new FlowEdge(nodeNum, i, Double.POSITIVE_INFINITY));
                graph.addEdge(new FlowEdge(nodeNum, j, Double.POSITIVE_INFINITY));
                if (games[i][j] > 0)
                    graph.addEdge(new FlowEdge(numTeams, nodeNum, games[i][j]));
                nodeNum++;
            }
            cap = wins[team] + left[team] - wins[i];
            if (cap > 0) // BTW: if it is less -> grantee of elimination
                graph.addEdge(new FlowEdge(i, numTeams + 1, cap));
        }
        return new FordFulkerson(graph, numTeams, numTeams + 1);
    }

    private int getTeam(String team) {
        if (team == null)
            throw new IllegalArgumentException();
        Integer ans = teams.get(team);
        if (ans == null)
            throw new IllegalArgumentException();
        return ans;
    }

    // test code given
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination(args[0]);
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}