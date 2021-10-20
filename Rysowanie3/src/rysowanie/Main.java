package rysowanie;

//import java.awt.*;
import java.awt.event.*;
//import java.awt.Graphics.*;
//import java.awt.Graphics2D.*;
import javax.swing.*;
//import java.util.*;
//import java.awt.geom.*;

class MojWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent e){
        System.exit(0);
    }
}

public class Main {
    
    public static void main(String args []){
    	
        JFrame okno = new JFrame("Program do rysowania");
        Surface Tlo = new Surface();
        okno.setBounds(450, 100, 1000, 800);
        okno.add(Tlo);
        okno.setResizable(false);
        okno.addWindowListener(new MojWindowAdapter());
        okno.setVisible(true);
    }

}