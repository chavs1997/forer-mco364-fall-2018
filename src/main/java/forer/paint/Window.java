package forer.paint;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame{
    public Window(){
        setTitle("Paint");
        setSize(800,600);
        Canvas paintCanvas = new Canvas();
        setLayout(new BorderLayout());
        setBackground(Color.BLUE);
        add(paintCanvas);
    }

    public static void main(String [] args){
        new Window().setVisible(true);
    }

}