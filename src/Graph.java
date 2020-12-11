import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Graph {
    private int capacity;
    private int size;
    private int[][] adjMatrix;
    private Stack stack;
    private Vertex[] verticesArray;

    public Graph(int capacity){
        this.capacity=capacity;
        this.size=0;
        this.adjMatrix=new int[capacity][capacity];
        stack=new Stack();
        verticesArray=new Vertex[capacity];
        for(int i=0; i<capacity; i++){
            for(int j=0; j<capacity; j++){
                adjMatrix[i][j]=0;
            }
        }
    }

    public void addVertex(char label){
        if(size>=capacity){
            System.out.println("The graph is full!");
            return;
        }
        Vertex vertex=new Vertex(label);
        verticesArray[size]=vertex;
        size++;
    }

    public void addEdge(int startIndex, int endIndex){
        if(startIndex>=size || startIndex<0 || endIndex>=size || endIndex<0){
            System.out.println("Invalid index");
            return;
        }
        adjMatrix[startIndex][endIndex]=1;
        adjMatrix[endIndex][startIndex]=1;
    }

    public void displayVertex(int index){
        if(index>=size || index<0){
            System.out.println("Invalid index");
            return;
        }
        System.out.print(verticesArray[index].getLabel());
    }

    //start with an index, apply DFS to it.
    //Print all nodes traversed
    // Return the number of searched nodes
    public int DisplayDFS(int start){
        if(size==0){
            System.out.println("The graph is empty");
            return 0;
        }
        int count=1;
        int currentIndex=start;
        Vertex currentVertex=verticesArray[currentIndex];

        currentVertex.isVisited=true;
        displayVertex(currentIndex);
        stack.push(currentIndex);

        while(!stack.isEmpty()){
            currentIndex=findUnvisitedNeighbour(stack.peek());
            if(currentIndex==-1){ //no unvisited neighbours
                stack.pop();
            }else{
                currentVertex=verticesArray[currentIndex];
                currentVertex.isVisited=true;
                displayVertex(currentIndex);
                stack.push(currentIndex);
                count++;
            }
        }

        for(int i=0; i<size ; i++){
            verticesArray[i].isVisited=false;
        }
        System.out.println();
        //reset stack
        stack=new Stack();
        return count;
    }

    //DFS without display; return the number of traversed vertices
    private int DFS(int start){
        if(size==0){
            return 0;
        }
        int count=1;
        int currentIndex=start;
        Vertex currentVertex=verticesArray[currentIndex];

        currentVertex.isVisited=true;
        stack.push(currentIndex);

        while(!stack.isEmpty()){
            currentIndex=findUnvisitedNeighbour(stack.peek());
            if(currentIndex==-1){ //no unvisited neighbours
                stack.pop();
            }else{
                currentVertex=verticesArray[currentIndex];
                currentVertex.isVisited=true;
                stack.push(currentIndex);
                count++;
            }
        }

        for(int i=0; i<size ; i++){
            verticesArray[i].isVisited=false;
        }
        //reset stack
        stack=new Stack();
        return count;
    }


    public void findPath(int start, int end){
        if(start<0 || start>=size || end<0 || end>=size){
            System.out.println("Invalid index");
            return;
        }

        int currentIndex=start;
        Vertex currentVertex=verticesArray[currentIndex];

        boolean found=false;

        currentVertex.isVisited=true;
        stack.push(currentIndex);

        while(!stack.isEmpty() && !found){
            if(currentIndex==end){
                found=true;
            }else{
                currentIndex=findUnvisitedNeighbour(stack.peek());
                if(currentIndex==-1){ //no unvisited neighbours
                    stack.pop();
                }else{
                    currentVertex=verticesArray[currentIndex];
                    currentVertex.isVisited=true;
                    stack.push(currentIndex);
                }
            }

        }
        for(int i=0; i<size ; i++){
            verticesArray[i].isVisited=false;
        }
        System.out.println("Found: "+found);
        //reset stack
        stack=new Stack();
    }

    public void checkConnectivity(){
        if(size==0){
            System.out.println("The graph is empty!");
            return;
        }
        int count=DFS(0);
        if(count!=size){
            System.out.println("The graph is disconnected");
        }else{
            System.out.println("The graph is connected");
        }
        //reset stack
        stack=new Stack();
    }

    //print all connected components, and return the number of pieces
    public int findConnectedComponents(){
        if(size==0){
            System.out.println("The graph is empty");
            return 0;
        }

        int count=0;
        int piece=0;

        while(count < size){
            piece++;
            count++;
            Random random=new Random();
            int currentIndex=0;

            //randomly pick a vertex with no owner
            while(verticesArray[currentIndex].ownedBy!=-1){
                currentIndex=random.nextInt(size);
            }

            //mark it as articulation point
            Vertex currentVertex=verticesArray[currentIndex];
            currentVertex.isVisited=true;
            currentVertex.isArticulation=true;
            System.out.print("Piece "+piece+": "+currentVertex.getLabel());

            int owner=currentIndex;
            currentVertex.ownedBy=owner;

            stack.push(currentIndex);
            //DFS
            while(!stack.isEmpty()){
                currentIndex=findUnvisitedNeighbour(stack.peek());
                if(currentIndex==-1){ //no unvisited neighbours
                    stack.pop();
                }else{
                    currentVertex=verticesArray[currentIndex];
                    currentVertex.isVisited=true;
                    currentVertex.ownedBy=owner;
                    displayVertex(currentIndex);
                    stack.push(currentIndex);
                    count++;
                }
            }

            System.out.println();
            //reset stack
            stack=new Stack();
        }

        for(int i=0; i<size ; i++){
            verticesArray[i].isVisited=false;
            verticesArray[i].ownedBy=-1;
            verticesArray[i].isArticulation=false;
        }
        return piece;
    }

    private int findUnvisitedNeighbour(int index){
        for(int i=0; i<size; i++){
            if(index!=i && adjMatrix[index][i]==1 && !verticesArray[i].isVisited){
                return i;
            }
        }
        return -1;
    }

    public void printMatrix(){
        for(int i=0; i<adjMatrix.length; i++){
            System.out.println(Arrays.toString(adjMatrix[i]));
        }
    }

}
