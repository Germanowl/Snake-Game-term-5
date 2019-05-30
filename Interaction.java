package Controller;

import Model.*;

public class Interaction {

    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    static final int EMPTY = 0;
    static final int FRUIT = 1;
    static final int SNAKE = 2;

    private Field field;
    private Snake snake;

    public Interaction(Field field, Snake snake) {
        this.field = field;
        this.snake = snake;
    }

    public void CutTail (){
        //field.getObject( snake.getHorizontal(index),snake.getVertical(index)).setTypeOfObject(EMPTY);
        //field.setObject(snake.getHorizontal(snake.Size() - 2),snake.getVertical(snake.Size() - 2),EMPTY);
        field.setObject(snake.getHorizontal(snake.Size() - 1),snake.getVertical(snake.Size() - 1),EMPTY);
        snake.getVerticals().remove(snake.Size() - 1);
        snake.getHorizontals().remove(snake.Size() - 1);
    }

    public void KusZaZhopu (){
    snake.setHeadH(-1);
    }

    public void RandFood(){
        while(true) {
            int XRand = (int)(Math.random() * field.getLines().size());
            int YRand = (int)(Math.random() * field.getLines().get(0).size());

            if( field.getObject( XRand , YRand ).getTypeOfObject() == EMPTY){
                field.getObject( XRand , YRand ).setTypeOfObject(FRUIT);
                break;
            }
        }
    }

    public void TurnLeft() {
        switch(snake.getDirection()){
            case UP:
                snake.setDirection(LEFT);
                break;
            case RIGHT:
                snake.setDirection(UP);
                break;
            case DOWN:
                snake.setDirection(RIGHT);
                break;
            case LEFT:
                snake.setDirection(DOWN);
                break;
        }
    }

    public void TurnRight() {
        switch(snake.getDirection()){
            case UP:
                snake.setDirection(RIGHT);
                break;
            case RIGHT:
                snake.setDirection(DOWN);
                break;
            case DOWN:
                snake.setDirection(LEFT);
                break;
            case LEFT:
                snake.setDirection(UP);
                break;
        }
    }

    public synchronized void Move(){
        switch(snake.getDirection()){
            case UP:

                if(snake.getHeadV()+1 >= field.getLines().get(0).size()){
                    KusZaZhopu();
                    break;
                }


                switch(field.getObject(snake.getHeadH(), snake.getHeadV() + 1 ).getTypeOfObject()) {
                    case EMPTY:
                        snake.setHeadV(snake.getHeadV() + 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        CutTail();
                        snake.addSegment(snake.getHeadH(), snake.getHeadV() - 1, 0);
                        field.setObject(snake.getHeadH(), snake.getHeadV() - 1, SNAKE);
                        break;
                    case FRUIT:
                        snake.setHeadV(snake.getHeadV() + 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        snake.addSegment(snake.getHeadH(), snake.getHeadV() - 1, 0);
                        field.setObject(snake.getHeadH(), snake.getHeadV() - 1,SNAKE);
                        RandFood();

                        break;
                    case SNAKE:
                        KusZaZhopu();
                        break;
                }
                break;

            case RIGHT:

                if(snake.getHeadH()+1 >= field.getLines().size()){
                    KusZaZhopu();
                    break;
                }
                switch(field.getObject(snake.getHeadH() + 1, snake.getHeadV()).getTypeOfObject()) {
                    case EMPTY:
                        snake.setHeadH( snake.getHeadH() + 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        CutTail();
                        snake.addSegment( snake.getHeadH() -1 , snake.getHeadV(),0);
                        field.setObject(snake.getHeadH()-1, snake.getHeadV(), SNAKE);
                        break;
                    case FRUIT:
                        snake.setHeadH( snake.getHeadH() + 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        snake.addSegment( snake.getHeadH() -1 , snake.getHeadV(),0);
                        field.setObject(snake.getHeadH()-1, snake.getHeadV(), SNAKE);
                        RandFood();

                        break;
                    case SNAKE:
                        KusZaZhopu();
                        break;
                }
                break;

            case DOWN:

                if(snake.getHeadV()-1 < 0){
                    KusZaZhopu();
                    break;
                }

                switch(field.getObject(snake.getHeadH(), snake.getHeadV() - 1 ).getTypeOfObject()) {
                    case EMPTY:
                        snake.setHeadV( snake.getHeadV() - 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        CutTail();
                        snake.addSegment( snake.getHeadH() , snake.getHeadV() + 1,0);
                        field.setObject(snake.getHeadH(), snake.getHeadV() + 1, SNAKE);
                        break;
                    case FRUIT:
                        snake.setHeadV( snake.getHeadV() - 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        snake.addSegment( snake.getHeadH() , snake.getHeadV()+1,0);
                        field.setObject(snake.getHeadH(), snake.getHeadV()+1, SNAKE);
                        RandFood();
                        break;
                    case SNAKE:
                        KusZaZhopu();
                        break;
                }
                break;

            case LEFT:

                if(snake.getHeadH()-1 < 0){
                    KusZaZhopu();
                    break;
                }

                switch(field.getObject(snake.getHeadH() - 1, snake.getHeadV() ).getTypeOfObject()) {
                    case EMPTY:
                        snake.setHeadH( snake.getHeadH() - 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        CutTail();
                        snake.addSegment( snake.getHeadH() +1 , snake.getHeadV(),0);
                        field.setObject(snake.getHeadH()+1, snake.getHeadV(), SNAKE);
                        break;
                    case FRUIT:
                        snake.setHeadH( snake.getHeadH() - 1);
                        field.setObject(snake.getHeadH(), snake.getHeadV(), SNAKE);
                        snake.addSegment( snake.getHeadH() +1 , snake.getHeadV(),0);
                        field.setObject(snake.getHeadH()+1, snake.getHeadV(), SNAKE);
                        RandFood();
                        break;
                    case SNAKE:
                        KusZaZhopu();
                        break;
                }
                break;
        }
    }
}
