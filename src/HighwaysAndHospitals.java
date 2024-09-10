import java.util.ArrayList;

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
        ArrayList[] cityConnections = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            cityConnections[i] = new ArrayList<Integer>();
        }
        for (int[] connection : cities) {
            cityConnections[connection[0]].add(connection[1]);
        }


        return 0;
    }
    public static int tryCity(int n, int hospitalCost, int highwayCost, int cities[][], boolean hospitals[], int totalCost, int currentCity, ArrayList[] cityConnections) {
        if (currentCity > n) {
            return 0;
        }
        hospitals[currentCity] = true;
        return tryCity(n, hospitalCost, highwayCost, cities[][], hospitals, totalCost + hospitalCost, currentCity, cityConnections);
        hospitals[currentCity] = false;
        int numConnections = cityConnections[currentCity].size();
        for (int i = 0; i < numConnections; i++) {
            // Somehow set the highway to true
            // Call recursive method with new highway added and cost changed
            return tryCity(n, hospitalCost, highwayCost, cities[][],
            hospitals, totalCost + highwayCost, currentCity, cityConnections);

        }
    }
}
