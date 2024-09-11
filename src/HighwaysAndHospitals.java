import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Highways & Hospitals
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *
 * Completed by: Cody Kletter
 *
 */

public class HighwaysAndHospitals {

    /**
     * TODO: Complete this function, cost(), to return the minimum cost to provide
     *  hospital access for all citizens in Menlo County.
     */
    public static long cost(int n, int hospitalCost, int highwayCost, int cities[][]) {
        int currentCity = 0;
        // If hospital cost if less than or equal to hospital cost, return cost of placing hospital at every city
        if (hospitalCost <= highwayCost) {
            return (long) hospitalCost * n;
        }
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        ArrayList[] cityConnections = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            cityConnections[i] = new ArrayList<Integer>();
        }
        for (int[] connection : cities) {
            cityConnections[connection[0]].add(connection[1]);
            cityConnections[connection[1]].add(connection[0]);

        }
        int totalCost = 0;
        boolean[] citiesExplored = new boolean[n + 1];
        while (currentCity != n) {
            // Add cost for hospital being built at current city
            totalCost += hospitalCost;

        }
        return totalCost;
    }

    public static long cityBFS(int n, int totalCost, int highwayCost, int cities[][], int currentCity, ArrayList cityConnections[], Queue<Integer> bfsQueue, boolean[] citiesExplored) {

        while (!bfsQueue.isEmpty()) {
            // Remove current city from list of cities to be searched
            bfsQueue.remove();
            ArrayList<Integer> connections = cityConnections[currentCity];
            int numConnections = connections.size();
            for (int i = 0; i < numConnections; i++) {
                int city = connections.get(i);
                if (citiesExplored[city] == false) {
                    bfsQueue.add(city);
                }
            }
            currentCity = bfsQueue.peek();
        }
    }
}
