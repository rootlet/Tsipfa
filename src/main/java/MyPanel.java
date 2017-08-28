import javax.swing.*;
import java.awt.*;

/**
 * Created by rootlet on 29.06.2017.
 */
public class MyPanel extends JPanel {

 public void drawing(){
     repaint();
 }

    public void paintComponent(Graphics g){
        g.setColor(Color.CYAN);
        g.drawRect(0,0, 30,30);
        g.draw3DRect(50,50, 30,30, false);
        repaint();
    }
}
