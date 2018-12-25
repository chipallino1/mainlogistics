import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RoutesMatrixImpl implements RoutesMatrix {

    private int[][] routes;
    private int size;
    private int target;
    private int[] rowConsts;
    private int[] columnConsts;

    public int[][] getRoutes() {
        return routes;
    }

    public int getTarget() {
        return target;
    }
    public void setTarget(int target){
        this.target = target;
    }

    public void setRoutes(int[][] routes) {
        this.routes = routes;
    }

    public void setRoutes() {
        Random random=new Random(System.currentTimeMillis());
        for (int i=0;i<this.size;i++){
            for(int j=0;j<this.size;j++){
                if(i==j) {
                    routes[i][j]=Integer.MAX_VALUE;
                    continue;
                }
                routes[i][j]=Math.abs(random.nextInt()%1000000);
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize() {
        Random random=new Random(System.currentTimeMillis());
        int size = Math.abs(random.nextInt()%1000);
        this.routes = new int[size][size];
        this.size = size;
    }

    public void setSize(int size) {
        this.routes = new int[size][size];
        this.size = size;
    }

    public void redRow() {
        int min;
        this.rowConsts=new int[this.size];
        for (int rowNum = 0; rowNum < this.size; rowNum=rowNum+1) {
            min  = Integer.MAX_VALUE;
            for (int j = 0; j < this.size; j++) {
                if (this.routes[rowNum][j] < min) {
                    min = this.routes[rowNum][j];
                    this.rowConsts[j]=min;
                }
            }
            for (int j = 0; j < this.size; j++) {
                this.routes[rowNum][j] = this.routes[rowNum][j] - min;
            }
        }
    }

    public void redColumn() {
        int min;
        this.columnConsts=new int[this.size];
        for (int columnNum = 0; columnNum < this.size; columnNum = columnNum + 1) {
            min = Integer.MAX_VALUE;
            for (int i = 0; i < this.size; i++) {
                if (this.routes[i][columnNum] < min) {
                    min = this.routes[i][columnNum];
                    this.columnConsts[i]=min;
                }
            }
            for (int i = 0; i < this.size; i++) {
                this.routes[i][columnNum] = this.routes[i][columnNum] - min;
            }
        }
    }

    public int[] getRowConsts() {
        return rowConsts;
    }

    public int[] getColumnConsts() {
        return columnConsts;
    }

    public List<MarkRoutes> getMarksRoutes(){
        List<MarkRoutes> markRoutesList=new ArrayList<>();
        MarkRoutes markRoutes;
        for (int i=0;i<this.size;i++){
            for(int j=0;j<this.size;i++){
                if(this.routes[i][j]==0){
                    markRoutes=new MarkRoutes();
                    markRoutes.setI(i);
                    markRoutes.setJ(j);
                    markRoutes.setMark(getMinRow(i,i,j)+getMinCol(j,i,j));
                    markRoutesList.add(markRoutes);
                }
            }
        }
        return markRoutesList;
    }

    public int getMinRow(int rowNum,int excludeI, int excludeJ){
        int min = Integer.MAX_VALUE;
        for(int j=0;j<this.size;j++){
            if(rowNum==excludeI && j==excludeJ){
                continue;
            }
            if(this.routes[rowNum][j]<min){
                min=this.routes[rowNum][j];
            }
        }
        return min;
    }

    public int getMinCol(int colNum,int excludeI, int excludeJ){
        int min = Integer.MAX_VALUE;
        for(int i=0;i<this.size;i++){
            if(colNum==excludeI && i==excludeJ){
                continue;
            }
            if(this.routes[i][colNum]<min){
                min=this.routes[i][colNum];
            }
        }
        return min;
    }

    @Override
    public void showMatrix() {
        for (int i=0;i<this.size;i++){
            System.out.print(i+": ");
            for(int j=0;j<this.size;j++){
                System.out.print(routes[i][j]+"\t");
            }
            System.out.println();
        }
    }
}
