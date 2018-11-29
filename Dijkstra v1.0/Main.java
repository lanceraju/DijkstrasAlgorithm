import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String args[]){
        System.out.print("Would you like to generate a random-undirected and fully-connected graph?[y/n]:");
        Graph g;
        Scanner in =new Scanner(System.in);
        String input=in.nextLine();
        if(input.contains("y"))
            do{
                g=new Graph(-1,-1);
            }while(!g.connected());
        else{
            System.out.print("To create a custom undirected graph, enter the number of verticies you want:");
            int num =in.nextInt();
            System.out.print("Enter the number of edges you want (-1 to randomize):");
            int edges=in.nextInt();
            do{
                g=new Graph(num,edges);
            }while(!g.connected());         
            if(edges!=-1){  //this is to rewrite the edges
                g.clear();
                System.out.println("Enter the transitions as follows (vertex vertex weight):");
                for(int i=0;i<g.edgeNum;i++){
                    int v1=in.nextInt(),v2=in.nextInt(),w=in.nextInt();
                    g.createEdge(v1-1,v2-1,w);
                }
            }
        }
        System.out.println();
        g.printMat();
        System.out.print("Enter 2 verticies to find the shortest path (vertex vertex):");
        int from=in.nextInt(),to=in.nextInt();
        System.out.println("\nDijkstra:");
        dijkstra(from-1,to-1,g);
    }
    public static void dijkstra(int from, int to,Graph g){
        ArrayList<Traverse> steps =new ArrayList<Traverse>();
        Traverse step =new Traverse(g.size),prev;
        step.currN=-1;
        step.currW=0;
        for(int i=0;i<g.size;i++){
            step.nodes[i].weight=Integer.MAX_VALUE;
            step.nodes[i].visited=false;
            step.nodes[i].from=from;
        }
        step.nextN=from;
        step.nextW=0;
        steps.add(step);
        System.out.print("Steps");
        for(int i=0;i<g.size;i++){
            System.out.print("\t"+(i+1));
        }
        System.out.println();
        
        while(to!=step.currN){
            step =new Traverse(g.size);
            prev=steps.get(steps.size()-1);
            step.currN=prev.nextN;
            //prev.printStep();
            step.currW=prev.nextW;
            for(int i=0;i<g.size;i++){
                if(i==step.currN){
                    step.nodes[i].weight=step.currW;
                    step.nodes[i].from=prev.nodes[step.currN].from;
                    step.nodes[i].visited=true;
                    step.nodes[i].origin=true;
                    
                }
                else if(prev.nodes[i].visited){
                    step.nodes[i].weight=prev.nodes[i].weight;
                    step.nodes[i].from=prev.nodes[i].from;
                    step.nodes[i].visited=true;
                    step.nodes[i].origin=false;
                }
                else if(prev.nodes[i].weight>(long)step.currW+g.mat[step.currN][i]){
                    step.nodes[i].weight=step.currW+g.mat[step.currN][i];
                    step.nodes[i].from=step.currN;
                    step.nodes[i].visited=false;
                    step.nodes[i].origin=false;
                }
                else{
                    step.nodes[i].weight=prev.nodes[i].weight;
                    step.nodes[i].from=prev.nodes[i].from;
                    step.nodes[i].visited=false;
                    step.nodes[i].origin=false;
                }
            }
            int minW=Integer.MAX_VALUE,minN=0;
            boolean allVisited=true;
            for(int i=0;i<g.size;i++){
                if(!step.nodes[i].visited&&step.nodes[i].weight<minW){
                    allVisited=false;
                    minW=step.nodes[i].weight;minN=i;
                    //System.out.println("\n"+step.nodes[i].weight+i);
                }
            }
            steps.add(step);
            if(allVisited)
                break;
            step.nextN=minN;
            step.nextW=minW;
            steps.remove(steps.size()-1);
            steps.add(step);
        }
        for(int i=0;i<steps.size();i++){
            steps.get(i).printStep();
        }
        System.out.println();
        //prints the path assuming it exists
        ArrayList<Integer> path =new ArrayList<Integer>();
        path.add(steps.get(steps.size()-1).currN);
        int prevN=steps.get(steps.size()-1).nodes[steps.get(steps.size()-1).currN].from;
        if(steps.get(steps.size()-1).nodes[steps.get(steps.size()-1).currN].weight!=Integer.MAX_VALUE){
            for(int i=steps.size()-2;i>0;i--){
                if(prevN==steps.get(i).currN){
                    path.add(prevN);
                    prevN=steps.get(i).nodes[steps.get(i).currN].from;
                }
            }
            System.out.print("The shortest path from Node:("+(from+1)+" to "+(to+1)+") is ");
            for(int i=path.size()-1;i<path.size()&&i>=0;i--){
                if(i!=0)
                    System.out.print((path.get(i)+1)+"-");
                else
                    System.out.print(path.get(i)+1);
            }
            System.out.println(" and weight = "+steps.get(steps.size()-1).nodes[steps.get(steps.size()-1).currN].weight);
        }
        else{    //just in case the path from a to b is non existent
            System.out.println("Node:"+(from+1)+" is not connected to the graph containing Node:"+(to+1));
        }
            
    }
    
}
