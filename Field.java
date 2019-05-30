package Model;
import java.util.ArrayList;

public class Field {

    public ArrayList<Line> Objects = new ArrayList<>();

    public Field (){}

    public void setField (int LineAmount, int SizeOfLine){
        for (int LineTracker = 0; LineTracker < LineAmount; LineTracker++){
            Line line = new Line (SizeOfLine);
            this.Objects.add(line);
        }
    }

    public ArrayList<Line> getLines() {
        return Objects;
    }

    public WorkObj getObject(int X, int Y) {
        return this.Objects.get(X).get(Y);
    }//По горизонтали Line, по вертикали WorkObj

    public void setObjects(ArrayList<Line> Objects) {
        this.Objects = Objects;
    }

    public void setObject(int X, int Y, int Type) {
        Objects.get(X).get(Y).setTypeOfObject(Type);
    }


}
