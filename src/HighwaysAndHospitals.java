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
    public static long cost(int n, int hospitalCost, int highwayCost, int[][] cities) {
        // If hospital cost if less than or equal to hospital cost, return cost of placing hospital at every city
        if (hospitalCost <= highwayCost) {
            return (long) hospitalCost * n;
        }
        // Map for roots of each city in union find algorithm
        int[] roots = new int[n + 1];
        // Perform union find for each new edge
        for (int i = 0; i < cities.length; i++) {
            fastFind(cities[i][0], cities[i][1], roots);
        }
        // Count all of the disconnected subgraphs by counting negative or zero values in map
        int subgraphCount = 0;
        for (int i = 1; i < roots.length; i++) {
            if (roots[i] <= 0) {
                subgraphCount+= 1;
            }
        }
        // Calculate total cost and return
        return (long) subgraphCount * hospitalCost + (long) (n - subgraphCount) * highwayCost;
    }
    public static void fastFind(int cityOne, int cityTwo, int[] roots) {
        // Create copy of cityOne and cityTwo
        int i = cityOne;
        int j = cityTwo;
        int temp = 0;
        // Loop until root of city one is found
        while (roots[i] > 0) {
            i = roots[i];
        }
        // Loop until root of city two is found
        while (roots[j] > 0) {
            j = roots[j];
        }
        // Path compression - loop through each city that was seen and map it back to the component's root
        while (roots[cityOne] > 0) {
            temp = cityOne;
            cityOne = roots[cityOne];
            // Set the root of current city to the root of subgraph
            roots[temp] = i;
        }
        // Path compression - loop through each city that was seen and map it back to the component's root
        while (roots[cityTwo] > 0) {
            temp = cityTwo;
            cityTwo = roots[cityTwo];
            // Set the root of current city to the root of subgraph
            roots[temp] = j;

        }
        // Weight balancing - make city with more cities the root of the subgraph with less cities
        if (i != j) {
            // If the second subgraph is smaller than the first subgraph, make first subgraph the root of the second subgraph
            if (roots[j] < roots[i]) {
                roots[j] = roots[j] + roots[i] - 1;
                roots[i] = j;
            }
            // If the first subgraph is smaller than the second subgraph, make second subgraph the root of the first subgraph
            else {
                roots[i] = roots[i] + roots[j] - 1;
                roots[j] = i;
            }
        }
    }
}
