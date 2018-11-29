import java.util.Random;
import java.util.Scanner;
public class Graph {
    public int[][] mat;
    public boolean[][] matB;
    public int size, edgeNum;
    
    
    public Graph(int num,int edges){//generates a graph
        Random rand = new Random();
        int n;
        if(num==-1)
            n=rand.nextInt(9)+2;
        else
            n=num;
        this.mat=new int[n][n];
        this.matB=new boolean[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matB[i][j]=false;
            }
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                mat[i][j]=Integer.MAX_VALUE;
            }
        }
        if(edges==-1)
            this.edgeNum=rand.nextInt(((n*n-n)/2)-(n-1)+1)+(n-1);
        else
            this.edgeNum=edges;
        int nE=0;
        //System.out.println(nE+" "+edgeNum);
        for(int j=0;j<n;j++){
            matB[j][j]=true;
            mat[j][j]=0;
        }
        while(nE<edgeNum){
            for(int i=0;i<n;i++){
                for(int j=i+1;j<n;j++){
                    if(rand.nextInt(2)==1&&mat[i][j]==Integer.MAX_VALUE&&nE<edgeNum){
                        //System.out.println(nE+" "+i+" "+j);
                        matB[i][j]=true;
                        mat[i][j]= rand.nextInt(10)+1;
                        matB[j][i]=true;
                        mat[j][i]= mat[i][j];
                        nE++;
                    }
                }
            }
        }
        size=n;
    }
    public void printMat(){
        //int count=0;
        System.out.print("x\\y\t");
        for(int i=0;i<size;i++){
                System.out.print((i+1)+"\t");
        }
        System.out.println();
        for(int i=0;i<size;i++){
            System.out.print((i+1)+"\t");
            for(int j=0;j<size;j++){
                System.out.print(((mat[i][j]==Integer.MAX_VALUE)?"inf":mat[i][j])+"\t");
                //if(mat[i][j]!=0&&mat[i][j]!=Integer.MAX_VALUE)
                //    count++;
            }
            System.out.println();
        }
        //String s=(edgeNum>=size-1&&edgeNum<=(size*size-size)/2)?" True":" False";
        //System.out.println(size+" "+edgeNum+" "+count/2+s);
    }
    public void clear(){
       for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                matB[i][j]=false;
            }
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                mat[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int j=0;j<size;j++){
            matB[j][j]=true;
            mat[j][j]=0;
        }
    }
    public void createEdge(int i,int j,int w){
        mat[i][j]=w;
        mat[j][i]=w;
        matB[i][j]=true;
        matB[j][i]=true;
    }
     public boolean connected(){
        boolean[] columns=new boolean[size];
        for(int i=0;i<columns.length;i++){
            columns[i]=false;
        }
        for(int i=0;i<size;i++){
            for(int j=i+1;j<size;j++){
                if(mat[i][j]!=0&&mat[i][j]!=Integer.MAX_VALUE){
                    columns[j]=true;
                }
            }
        }
        for(int i=0;i<size;i++){
        	
        	if(mat[i][0] !=0&& mat[i][0]!=Integer.MAX_VALUE)
        	{
        		columns[0] = true; // check the first one and if you see a normal number put it to true
        	}
        	
        }
        if(containsOnlyTrue(columns))
            return true;
        else
            return false;
    }
    public boolean containsOnlyTrue(boolean[] col){
        for(int i=0;i<col.length;i++){
            if(!col[i])
                return false;
        }
        return true;
    }
}
//8 6 5=19 /11/4 3 1 2 1