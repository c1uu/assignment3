import java.io.*;
import java.util.*;

public class driver {
    private int vertices; // Number of vertices in the graph


    private int[][] adjacencyMatrix; // Adjacency matrix representation


    private boolean[] visited; // Array to track visited nodes


    // Constructor to initialize the graph from a file
    public driver(String filename) 
    
    throws IOException {
        readGraphFromFile(filename);
    }

    // Reads the adjacency matrix from the given file
    private void readGraphFromFile(String filename) 
    
    throws IOException {
        
        BufferedReader br = new BufferedReader(new FileReader(filename));
        
        //read the number of vertices
        vertices = Integer.parseInt(br.readLine().trim());

        //create matrix and read the matrix rows
        adjacencyMatrix = new int[vertices][vertices];
        for (int i = 0; i < vertices; i++) {
            String[] row = br.readLine().trim().split(" "); // Read matrix row
            for (int j = 0; j < vertices; j++) {

                //filling the matrix
                adjacencyMatrix[i][j] = Integer.parseInt(row[j]);
            }
        }
        br.close(); // Close the file reader
    }

    //method to start DFS traversal
    public void performDFS() {

        //must make sure all the vertices are cleared to ensure proper counting
        visited = new boolean[vertices];
        System.out.print("DFS: ");
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) { // Start DFS if node hasn't been visited
                executeDFS(i);
            }
        }
        System.out.println();
    }

    // Depth-First Search using recursion
    private void executeDFS(int node) {

        //create stack
        Stack<Integer> stack = new Stack<>();
        
        stack.push(node);
        while (!stack.isEmpty()) {

            //last added node
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");


                for (int neighbor = 0; neighbor < vertices; neighbor++) {
                    if (adjacencyMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                        stack.push(neighbor); // Push all unvisited neighbors
                    }
                }
            }
        }
    }

    //method to start BFS traversal
    public void performBFS() {

        visited = new boolean[vertices]; // clear visited array
        
        System.out.print("BFS: ");
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) { // Start BFS if node hasn't been visited
                executeBFS(i);
            }
        }
        System.out.println();
    }


    // Breadth-First Search 
    private void executeBFS(int node) {


        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS traversal

        queue.add(node);
        
        visited[node] = true;
        
        while (!queue.isEmpty()) {
            
            int current = queue.poll(); // Process first added node
            
            System.out.print(current + " ");
            
            for (int neighbor = 0; neighbor < vertices; neighbor++) {

                if (adjacencyMatrix[current][neighbor] == 1 && !visited[neighbor]) {
                    queue.add(neighbor); // Add unvisited neighbors
                    visited[neighbor] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter file name: "); //user for filename
        String filename = scanner.nextLine().trim(); //Read user input
        scanner.close();
        try {
            driver graph = new driver(filename); //Load graph from file
            graph.performDFS(); // Execute DFS traversal
            graph.performBFS(); // Execute BFS traversal
        } catch (IOException e) 
        {
            System.err.println("Error reading file: " + e.getMessage()); // Handle file errors
        }
    }
}
