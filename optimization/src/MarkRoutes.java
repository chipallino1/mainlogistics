import java.util.List;

public class MarkRoutes {
    private int i;
    private int j;
    private int mark;

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public static MarkRoutes getMinMarkRoutes(List<MarkRoutes> markRoutesList){
        int min=Integer.MAX_VALUE;
        MarkRoutes markRoutes=null;
        for(int i=0;i<markRoutesList.size();i++){
            if(markRoutesList.get(i).getMark()<min){
                min=markRoutesList.get(i).getMark();
                markRoutes=markRoutesList.get(i);
            }
        }
        return markRoutes;
    }

}
