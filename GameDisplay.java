package View;

import Controller.*;
import Model.*;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.*;



public class GameDisplay {

    static final int EMPTY = 0;
    static final int FRUIT = 1;
    static final int SNAKE = 2;

    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT = 3;

    private Display display = new Display();
    private Shell shell = new Shell(display, SWT.SHELL_TRIM | SWT.CENTER);
    private Interaction interaction;
    private GC gc;
    private Thread game;

    private Snake snake = new Snake();
    private Field field = new Field();

    public int Delay = 1000;
    Image Head = new Image(display, "C:\\Users\\User\\Desktop\\Snake_PPvIS_Term_5_Tsimashenko\\Head.png");
    Image Body = new Image(display, "C:\\Users\\User\\Desktop\\Snake_PPvIS_Term_5_Tsimashenko\\body.png");
    Image Cell = new Image(display, "C:\\Users\\User\\Desktop\\Snake_PPvIS_Term_5_Tsimashenko\\Cell.png");
    Image Fruit = new Image(display, "C:\\Users\\User\\Desktop\\Snake_PPvIS_Term_5_Tsimashenko\\Fruit.png");

    public void goHardcore(){
    Body = new Image(display, "C:\\Users\\User\\Desktop\\Snake_PPvIS_Term_5_Tsimashenko\\Cell.png");}

    public void goFast(){
    Delay = Delay/4;}

    public class MoveRun implements Runnable {


        public void run(){
                while(true) {
                    try {
                        Thread.sleep(Delay);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(snake.getHeadH()==-1){
                        break;
                    }
                    int snsz=snake.Size()-2;
                    interaction.Move();
                    if(Delay > 100)
                    if(snsz<snake.Size()-2){
                        Delay = Delay - 100;
                        //snsz+=1;
                    }
                    draw(gc);
                    System.out.println(snake.Size());
                    for(int X=0; X<field.getLines().size(); X++) {
                        System.out.println(' ');
                        for (int Y = 0; Y < field.getLines().get(0).size(); Y++) {
                            System.out.print(field.getObject(Y, X).getTypeOfObject());
                        }
                    }
                }
                display.syncExec(new Runnable() {
                    @Override
                    public void run() {
                        MessageBox gameOver = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                        gameOver.setMessage("GAME OVER! YOUR SCORE IS:" + (snake.Size()- 2));
                        gameOver.open();
                        shell.dispose();
                    }
                });
        }
    }

    public GameDisplay(){
        Color gray = display.getSystemColor(SWT.COLOR_DARK_GRAY);
        shell.setBackground(gray);
        shell.setText("Snake F-game by Tsimashenko D.A. 621701");
        shell.setSize(500, 500);
        interaction = new Interaction(field, snake);
        centerWindow();
        initGameWindow();
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }

    private void centerWindow() {

        Rectangle rectangle = shell.getDisplay().getBounds();

        Point p = shell.getSize();
        int nLeft = (rectangle.width - p.x) / 2;
        int nTop = (rectangle.height - p.y) / 2;

        shell.setBounds(nLeft, nTop, p.x, p.y);

    }

    public synchronized void draw( GC gc){

        gc.drawImage(Head,snake.getHeadH() * 25,snake.getHeadV() * 25);
        for(int XAxis = 0; XAxis < field.getLines().size() ; XAxis++)
            for (int YAxis = 0 ; YAxis < field.getLines().get(0).size(); YAxis ++ )
                switch (field.getObject(XAxis,YAxis).getTypeOfObject()){
                    case SNAKE:
                        if(XAxis == snake.getHeadH() && YAxis == snake.getHeadV())
                            break;
                        gc.drawImage(Body,XAxis * 25, YAxis * 25);
                        break;
                    case FRUIT:
                        gc.drawImage(Fruit, XAxis * 25, YAxis * 25);
                        break;
                    case EMPTY:
                        gc.drawImage(Cell, XAxis * 25, YAxis * 25);
                        break;
                }

    };

    public void initGameWindow(){

        Canvas canvas = new Canvas(shell,SWT.NONE);
        canvas.setLocation(10,50);

        Button play = new Button(shell, SWT.PUSH);
        play.setBounds(10,10,35,25);
        play.setText("Play");

        Button left = new Button(shell, SWT.PUSH);
        left.setBounds(300,10,35,25);
        left.setText("Left");

        Button right = new Button(shell, SWT.PUSH);
        right.setBounds(350,10,35,25);
        right.setText("Right");

        Button Hardcore = new Button(shell, SWT.PUSH);
        Hardcore.setBounds(400,10,35,25);
        Hardcore.setText("White");

        Button Pizdec = new Button(shell, SWT.PUSH);
        Pizdec.setBounds(440,10,35,25);
        Pizdec.setText("Fast");

        Text FieldSizeX = new Text(shell, SWT.CHECK);
        FieldSizeX.setBounds(55, 10, 35, 25);
        FieldSizeX.setText("10");

        Label FieldSizeXText = new Label(shell, SWT.NONE);
        FieldSizeXText.setText("Field width");
        FieldSizeXText.setBounds(100, 10, 70, 25);

        Text FieldSizeY = new Text(shell, SWT.CHECK);
        FieldSizeY.setBounds(180, 10, 35, 25);
        FieldSizeY.setText("10");

        Label FieldSizeYText = new Label(shell, SWT.NONE);
        FieldSizeYText.setText("Field heigth");
        FieldSizeYText.setBounds(225, 10, 70, 25);

        play.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if ( keyEvent.character == 'd'){interaction.TurnLeft();}
                if ( keyEvent.character == 'a'){interaction.TurnRight();}
            }
        });
//30
        play.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent arg0){
                canvas.setSize(Integer.parseInt(FieldSizeX.getText()) * 25,Integer.parseInt(FieldSizeY.getText()) * 25);
                gc = new GC(canvas);
                field.setField(Integer.parseInt(FieldSizeX.getText()), Integer.parseInt(FieldSizeY.getText()));
                snake.setHeadV(2);
                snake.setHeadH(1);
                field.setObject(snake.getHeadH(),snake.getHeadV(),SNAKE);
                field.setObject(snake.getHeadH(),snake.getHeadV()-1,SNAKE);
                field.setObject(snake.getHeadH(),snake.getHeadV()-2,SNAKE);
                snake.addSegment(snake.getHeadH(),snake.getHeadV()-1);
                snake.addSegment(snake.getHeadH(),snake.getHeadV()-2);
                snake.setDirection(UP/*RIGHT*/);
                interaction.RandFood();
                draw(gc);
                game = new Thread (new MoveRun());
                game.start();

            }
            });

        shell.addKeyListener(new KeyAdapter()  {

            public void KeyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {

                    case 'd':
                        interaction.TurnRight();
                        break;

                    case 'a':
                        interaction.TurnLeft();
                        break;

                    default:
                }
            }
        });

        play.addKeyListener(new KeyAdapter()  {

            public void KeyPressed(java.awt.event.KeyEvent evt) {
                switch (evt.getKeyCode()) {

                    case 'd':
                        interaction.TurnRight();
                        break;

                    case 'a':
                        interaction.TurnLeft();
                        break;

                    default:
                }
            }
        });

        left.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0){
                System.out.println('a');
                interaction.TurnRight();

            }
        });

        right.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0){
                System.out.println('d');
                interaction.TurnLeft();
            }
        });

        Hardcore.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0){
                System.out.println('h');
                goHardcore();
            }
        });

        Pizdec.addSelectionListener(new SelectionAdapter() {
            @Override
            public void widgetSelected(SelectionEvent arg0){
                System.out.println('p');
                goFast();
            }
        });
    }
    //SWT.ARROW_LEFT
    //SWT.ARROW_RIGHT
}
