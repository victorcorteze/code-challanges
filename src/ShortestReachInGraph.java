import java.io.*;
import java.util.*;

public class ShortestReachInGraph {
    public static class Graph{
        private LinkedList<Integer> [] neighborsList;
        private int size;
        public Graph(int size){
            neighborsList = new LinkedList[size];
            this.size = size;
            for(int v = 0; v < size; v++)
                neighborsList[v] = new LinkedList<>();
        }
        public void addEdge(int first, int second){
            neighborsList[first].add(second);
            neighborsList[second].add(first);
        }
        public int[] shortestReach(int startId){
            LinkedList<Integer> queue = new LinkedList<Integer>();
            queue.add(startId);

            int[] distances = new int[this.size];
            Arrays.fill(distances, -1);
            distances[startId] = 0;

            while (!queue.isEmpty()){
                int node = queue.poll();
                for (int neighbor : neighborsList[node]){
                    if(distances[neighbor] == -1){
                        distances[neighbor] = distances[node] + 6;
                        queue.add(neighbor);
                    }
                }
            }
            return distances;
        }

    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int queries = scanner.nextInt();

        for (int t = 0; t < queries; t++) {

            Graph graph = new Graph(scanner.nextInt());
            int m = scanner.nextInt();

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt() - 1;
                int v = scanner.nextInt() - 1;

                graph.addEdge(u, v);
            }

            int startId = scanner.nextInt() - 1;
            int[] distances = graph.shortestReach(startId);

            for (int i = 0; i < distances.length; i++) {
                if (i != startId) {
                    System.out.print(distances[i]);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        scanner.close();
    }

}
