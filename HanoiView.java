import javax.swing.*;
import java.awt.*;

public class HanoiView extends JFrame{
    private Disk[] disks;
    private final int[] TOWER_X;
    private final int TOWER_Y;
    private final int CEILING; //techo hasta donde suben los discos
    private int[] tower_floors;
    private Graphics g;
    private Image img = null; //screen off
    private JButton start;
    private JTextField textField;
    private JLabel movements;
    public HanoiView(){
        super("Hanoi Towers");
        tower_floors = new int[]{500, 500, 500};
        TOWER_X = new int[]{150, 550, 950};
        TOWER_Y = 100;
        CEILING = 50;
        makeGUI();
        img = createImage(getWidth(), getHeight());
        g = img.getGraphics();
        repaint();
    }
    private void makeGUI(){
        setSize(1150, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        JLabel label = new JLabel("Number of disks: ");
        label.setBounds(280, 500, 200, 50);
        label.setFont(new Font("Arial", Font.PLAIN, 20));
        add(label);

        textField = new JTextField("3");
        textField.setBounds(450, 500, 100, 50);
        add(textField);

        start = new JButton("Start");
        start.setBounds(550, 500, 100, 50);
        start.grabFocus();
        add(start);

        movements = new JLabel("Movements: ");
        movements.setBounds(700, 500, 200, 50);
        movements.setFont(new Font("Arial", Font.PLAIN, 20));
        add(movements);

        setVisible(true);
    }

    public void paint(Graphics gl){
        draw();
        gl.drawImage(img, 0, 0, getWidth(), getHeight(), this);
    }

    private void draw(){
        if(g == null){
            return;
        }
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(TOWER_X[0], TOWER_Y, 30, 400);
        g.fillRect(TOWER_X[1], TOWER_Y, 30, 400);
        g.fillRect(TOWER_X[2], TOWER_Y, 30, 400);

        for(Disk d: disks){
            g.setColor(d.getColor());
            g.fillRoundRect(d.getXInt(), d.getYInt(), d.getWidthInt(), d.getHeightInt(), 10, 10);
        }
    }

    public void makeMovement(Movement movement){
        Disk disk = disks[movement.getDisk().getN()];
        int destinationTower = movement.getDestination();
        int originTower = movement.getOrigin();

        int x = 0, y = 0;

        x = TOWER_X[destinationTower] - (disk.getWidthInt()/2) + 15;
        y = tower_floors[destinationTower] -= disk.getHeightInt();

        if(originTower != destinationTower){
            tower_floors[originTower] += disk.getHeightInt();
        }
        while(disk.getYInt() > CEILING){
            disk.setY(disk.getYInt() - 1);
            paint(getGraphics());
        }

        while(disk.getXInt() != x){
            if(disk.getXInt() < x){
                disk.setX(disk.getXInt() + 1);
            }else{
                disk.setX(disk.getXInt() - 1);
            }
            paint(getGraphics());
        }

        while(disk.getYInt() < y){
            disk.setY(disk.getYInt() + 1);
            paint(getGraphics());
        }
    }

    public void setDisks(Disk[] disks){
        this.disks = disks;
    }
    public JButton getButton(){
        return start;
    }
    public void disableButton(){
        start.setEnabled(false);
    }
    public int getN(){
        return Integer.parseInt(textField.getText());
    }
    public void setN(int n){
        textField.setText("" + n);
    }
    public void makeListeners(HanoiController controller){
        start.addActionListener(controller);
    }
    public void setMovements(int movements){
        this.movements.setText("Movements: " + movements);
    }
}
