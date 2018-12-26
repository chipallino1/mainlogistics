import java.lang.reflect.Array;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RoutesMatrixImpl routesMatrix=new RoutesMatrixImpl();
        routesMatrix.setSize(5);
        routesMatrix.setRoutes();
        routesMatrix.showMatrix();
        routesMatrix.redRow();
        routesMatrix.showMatrix();
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
        List<MarkRoutes> markRoutesList = routesMatrix.getMarksRoutes();
        for (int i=0;i<markRoutesList.size();i++){
            System.out.println(markRoutesList.get(i).getMark());
        }
        //System.out.println("Min mark of zero: "+MarkRoutes.getMinMarkRoutes().getMark());


    }
}
