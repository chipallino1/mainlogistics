import java.lang.reflect.Array;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RoutesMatrixImpl routesMatrix=new RoutesMatrixImpl();
        routesMatrix.setSize(5);
        routesMatrix.setRoutes();
        routesMatrix.showMatrix();
        routesMatrix.redRow();
        //routesMatrix.showMatrix();
        routesMatrix.redColumn();
        routesMatrix.showMatrix();
        int target=0;
        int[] rowConsts = routesMatrix.getRowConsts();
        int[] columnConsts = routesMatrix.getColumnConsts();
        for(int i=0;i<routesMatrix.getSize();i++){
            target=target+rowConsts[i]+columnConsts[i];
        }
        routesMatrix.setTarget(target);
        System.out.println("Target: "+target);
        System.out.println("Max mark of zero: "+MarkRoutes.getMaxMarkRoutes(routesMatrix.getMarksRoutes()).getMark());
        System.out.println("Row of zero: "+MarkRoutes.getMaxMarkRoutes(routesMatrix.getMarksRoutes()).getI());
        System.out.println("Col of zero: "+MarkRoutes.getMaxMarkRoutes(routesMatrix.getMarksRoutes()).getJ());
        MarkRoutes maxMarkRoutes = MarkRoutes.getMaxMarkRoutes(routesMatrix.getMarksRoutes());
        routesMatrix.chooseRoute(maxMarkRoutes.getI(),maxMarkRoutes.getJ());
        routesMatrix.showMatrix();
    }
}
