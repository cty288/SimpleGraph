public class Main {
    public static void main(String[] args) {
        Graph graph=new Graph(10);
        graph.addVertex('A');//0
        graph.addVertex('B');//1
        graph.addVertex('C');//2
        graph.addVertex('D');//3
        graph.addVertex('E');//4
        graph.addVertex('F');//5
        graph.addVertex('G');//6
        graph.addVertex('H');//7
        graph.DisplayDFS(1);
        graph.checkConnectivity();
        graph.findPath(0,4);

        graph.addEdge(0,1);
        graph.addEdge(0,7);
        graph.addEdge(1,7);
        graph.addEdge(7,2);
        graph.addEdge(7,3);
        graph.addEdge(5,6);
        graph.addEdge(6,4);
        //graph.printMatrix();

        graph.DisplayDFS(3);
        graph.findPath(3,1);
        graph.findPath(5,3);
        graph.DisplayDFS(5);

        graph.checkConnectivity();

        graph.findConnectedComponents();

        graph.addVertex('I'); //8
        graph.addVertex('J'); //9
        graph.findConnectedComponents();

        graph.addEdge(3,8);
        graph.addEdge(6,9);
        graph.addEdge(1,6);

        graph.checkConnectivity();
        graph.findConnectedComponents();

    }
}
