package Model;
import java.util.ArrayList;

public class Line {

    private ArrayList<WorkObj> Struct = new ArrayList<WorkObj>();

    public Line(){}

    public Line(int amount){
        for (int i = 0; i < amount; i++) {
            WorkObj Inside = new WorkObj();
            Inside.setTypeOfObject(0);
            this.Struct.add(Inside);
        }
    }

    public void add(WorkObj Inside){
        this.Struct.add(Inside);
    }

    public void add(int Num, WorkObj Inside){
        this.Struct.add(Num, Inside);
    }

    public int size() { return this.Struct.size();}

    public WorkObj get(int Number){return this.Struct.get(Number); }

    public void clear(){this.Struct.clear();}
}
