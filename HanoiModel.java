import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class HanoiModel {
    private int n;
    private ArrayList<Stack<Disk>> towers;
    private Disk[] disks;
    private ArrayList<Movement> movements;
    private Random rndColor;
    public HanoiModel(int n){
        this.n = n;
        towers = new ArrayList<Stack<Disk>>(3);
        for(int i = 0; i < 3; i++){
            towers.add(i, new Stack<Disk>());
        }
        initializeDisks(n);
    }

    private void initializeDisks(int n){
        disks = new Disk[n];
        rndColor = new Random();
        movements = new ArrayList<Movement>();
        for(int i = n; i > 0; i--){
            Color color = new Color(rndColor.nextInt(255), rndColor.nextInt(255), rndColor.nextInt(255));
            Disk disk = new Disk(i, color);
            towers.get(0).push(disk);
            disks[i-1] = disk;
            movements.add(new Movement(0, 0,disk));
        }
    }

    private void move(int origin, int destination){
        Disk disk = towers.get(origin).pop();
        towers.get(destination).push(disk);
        movements.add(new Movement(origin, destination, disk));
    }

    private void hanoi(int n, int origin, int aux, int destination){
        if(n==1){
            move(origin, destination);
        }else{
            hanoi(n-1, origin, destination, aux);
            move(origin, destination);
            hanoi(n-1, aux, origin, destination);
        }
    }

    public void startHanoi(){
        hanoi(n, 0, 1, 2);
    }
    public ArrayList<Movement> getMovements(){
        return movements;
    }
    public Disk[] getDisks(){
        return disks;
    }
    public void setN(int n){
        this.n = n;
        initializeDisks(n);
    }
}
