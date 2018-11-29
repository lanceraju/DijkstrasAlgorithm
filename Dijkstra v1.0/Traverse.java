import java.util.ArrayList;
public class Traverse {
    //public ArrayList<Integer> unvisited=new ArrayList<Integer>();
    //public ArrayList<Integer> visited=new ArrayList<Integer>();
    public class Node{
        int from,weight;
        boolean visited,origin;
        
        public Node(){
            from = 0;
            weight=Integer.MAX_VALUE;
            visited=false;
            origin=false;
        }
    }

    public Node[] nodes;
    public int currN,currW,nextN,nextW;
    public Traverse(int n){
        nodes=new Node[n];
        for(int i=0;i<nodes.length;i++){
            nodes[i]=new Node();
        }
    }
    public void printStep(){
        System.out.print((currN+1)+"\t");
        for(int i=0;i<nodes.length;i++){
            //System.out.print(""+((nodes[i].visited)?"T":"F"));
            System.out.print(((nodes[i].weight==Integer.MAX_VALUE)?"Inf":nodes[i].weight)+"\t");
        }
        System.out.println();
    }
}
