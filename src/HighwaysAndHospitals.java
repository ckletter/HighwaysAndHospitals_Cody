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
        // If hospital cost if less than or equal to hospital cost, return cost of placing hospital at every city
        if (hospitalCost <= highwayCost) {
            return (long) hospitalCost * n;
        }

        int currentCity = 1;
        // Create array of array lists for each connection that a city has
        ArrayList[] cityConnections = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            cityConnections[i] = new ArrayList<Integer>();
        }
        // For each connection, add to both cities the other city as a connection in its arraylist
        for (int[] connection : cities) {
            cityConnections[connection[0]].add(connection[1]);
            cityConnections[connection[1]].add(connection[0]);

        }
        long totalCost = 0;
        // array of booleans to keep track of each city being explored or not
        boolean[] citiesExplored = new boolean[n + 1];
        // Loop until all cities have been explored
        while (true) {
            // Add cost for hospital being built at current city and mark as explored
            totalCost += hospitalCost;
            citiesExplored[currentCity] = true;
            // Calculate the total cost of all highways built at the subgraph of the current city
            totalCost += subgraphBFS(0, highwayCost, currentCity, cityConnections, citiesExplored);
            // Adjust current city to the next unsearched city
            boolean citiesUnsearched = false;
            for (int i = 2; i < n + 1; i++) {
                if (!citiesExplored[i]) {
                    currentCity = i;
                    citiesUnsearched= true;
                    break;
                }
            }
            if (!citiesUnsearched) {
                break;
            }
        }
        return totalCost;
    }

    /**
     * Calculates the total cost of all highways being built at the subgraph of the current city being passed in
     * Performs BFS to search through each city in the subgraph until all cities have been explored
     */
    public static long subgraphBFS(long subgraphCost, int highwayCost, int currentCity, ArrayList cityConnections[], boolean[] citiesExplored) {
        // Creates a queue of adjacent cities to be added in BFS algorithm
        Queue<Integer> bfsQueue = new LinkedList<Integer>();
        bfsQueue.add(currentCity);
        // Keep looping until all cities in the subgraph have been explored
        while (!bfsQueue.isEmpty()) {
            // Remove current city from list of cities to be searched
            bfsQueue.remove();
            // Add the cost of building the next highway
            subgraphCost += highwayCost;
            ArrayList<Integer> connections = cityConnections[currentCity];
            // Loop through each of the current city's neighbors
            int numConnections = connections.size();
            for (int i = 0; i < numConnections; i++) {
                int city = connections.get(i);
                // If the city has not yet been explored, adds it to cities to be searched and marks it as explored
                if (citiesExplored[city] == false) {
                    bfsQueue.add(city);
                    citiesExplored[city] = true;
                }
            }
            // If there are still cities left to be searched, sets current city to next city to be searched
            if (bfsQueue.peek() != null) {
                currentCity = bfsQueue.peek();
            }
        }
        // Subtract the cost of the first highway being built (there are one less highways than cities in the subgraph)
        // Return the final cost of the subgraph
        return subgraphCost - highwayCost;
    }
}
