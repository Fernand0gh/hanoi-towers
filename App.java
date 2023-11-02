import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public static void main(String[] args){
        HanoiView view = new HanoiView();
        HanoiModel model = new HanoiModel(1);
        new HanoiController(model, view);
    }
}
