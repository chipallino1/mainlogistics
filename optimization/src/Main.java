import java.lang.reflect.Array;

public class Main {

    public static void main(String[] args) {

        RoutesMatrixImpl routesMatrix=new RoutesMatrixImpl();
        routesMatrix.setSize(5);
        routesMatrix.setRoutes();
        routesMatrix.showMatrix();
        routesMatrix.redRow();
        routesMatrix.redColumn();
        routesMatrix.showMatrix();
        int target=0;
        int[] rowConsts = routesMatrix.getRowConsts();
        int[] columnConsts = routesMatrix.getColumnConsts();
        for(int i=0;i<routesMatrix.getSize();i++){
            target=target+rowConsts[i]+columnConsts[i];
        }
        System.out.println("Target: "+target);

    }
}
