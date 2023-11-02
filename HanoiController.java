import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HanoiController implements ActionListener {
    HanoiModel model;
    HanoiView view;
    private int nextMovement;
    private Timer t;
    private ArrayList<Movement> movements;
    public HanoiController(HanoiModel model, HanoiView view){
        this.view = view;
        this.model = model;
        this.view.setDisks(model.getDisks());
        this.movements = model.getMovements();
        this.view.makeListeners(this);
        t = new Timer(1, this);
        nextMovement = 0;
    }
    public void updateModelDisks(int n){
        if(n > 14){ //Visual limit of disks is 14
            n = 14;
            view.setN(n);
        }
        model.setN(n);
        view.setDisks(model.getDisks());
        movements = model.getMovements();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == view.getButton()){
            updateModelDisks(view.getN());
            model.startHanoi();
            t.start();
            view.disableButton();
            return;
        }
        if(nextMovement == movements.size()){
            t.stop();
            return;
        }
        view.makeMovement(movements.get(nextMovement));
        if(nextMovement > model.getDisks().length - 1){
            view.setMovements(nextMovement - model.getDisks().length + 1);
        }
        nextMovement++;
    }
}
