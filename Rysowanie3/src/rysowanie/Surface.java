package rysowanie;

import java.awt.*;
import java.awt.event.*;
import java.awt.Shape;
import java.awt.Graphics.*;
import java.awt.Graphics2D.*;
import javax.swing.*;
import java.util.*;
import java.awt.geom.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

class ZEllipse extends Ellipse2D.Float{
    public ZEllipse(float x, float y, float width, float height) {
        setFrame(x, y, width, height);
    }
}

class ZRectangle extends Rectangle2D.Float{
        public ZRectangle(float x, float y, float width, float height) { 
            setRect(x, y, width, height);
        }
}

public class Surface extends JPanel{

    boolean rysujKolo;
    boolean rysujProstokat;
    boolean rysujTrojkat;
    int pktTrojkata;
    ArrayList<Figura> narysowaneFigury = new ArrayList();
    private int x1, x2, x3, y1, y2, y3, r;
    int j;
    static int pktX, pktY;
    int Xpocz, Ypocz;
    static int doZmiany;
    int aktywna = -1;
    boolean rysowanie = false;

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for(int i=0; i<=narysowaneFigury.size()-1; i++){
            int a = narysowaneFigury.get(i).x1;
            int b = narysowaneFigury.get(i).y1;
            int c = narysowaneFigury.get(i).x2;
            int d = narysowaneFigury.get(i).y2;
            int l = narysowaneFigury.get(i).x3;
            int h = narysowaneFigury.get(i).y3;
            int e = narysowaneFigury.get(i).typ;
            int f = narysowaneFigury.get(i).kolor;

            if(f == 0){
                g2d.setPaint(new Color(0, 0, 0));
            }
            else if(f == 1){
                g2d.setPaint(new Color(255, 100, 0));
            }
            else if(f == 2){
                g2d.setPaint(new Color(255, 0, 0));
            }
            else if(f == 3){
                g2d.setPaint(new Color(0, 100, 255));
            }
            else if(f == 4){
                g2d.setPaint(new Color(0, 200, 0));
            }
            else if(f == 5){
                g2d.setPaint(new Color(255, 255, 0));
            }
            else if(f == 6){
                g2d.setPaint(new Color(255, 255, 255));
            }
            
            if(e == 0){
                g2d.fillOval(Math.min(a, c), Math.min(b, d), Math.max(Math.abs(a-c), Math.abs(b-d)), Math.max(Math.abs(a-c), Math.abs(b-d)));
                }
    
            if(e == 1){
                g2d.fillRect(Math.min(a, c), Math.min(b, d), Math.abs(a-c), Math.abs(b-d));
                }
            
            if(e == 2){;
                int xp[] = {a, c, l};
                int yp[] = {b, d, h};
                g2d.fillPolygon(xp, yp, 3);
                }
            }
   } 

    @Override
    public void paintComponent(Graphics g) {       
        super.paintComponent(g);
        doDrawing(g);
    }
    
    Surface(){

        Font MojFont1  = new Font("Lucida Console", Font.BOLD, 20);
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);;

        rysujKolo = false;
        rysujProstokat = false;
        rysujTrojkat = false;

        JPopupMenu zmienKolor = new JPopupMenu("Zmień kolor");
        JMenuItem niebieski = new JMenuItem("Niebieski");
        zmienKolor.add(niebieski);
        JMenuItem czerwony = new JMenuItem("Czerwony");
        zmienKolor.add(czerwony);
        JMenuItem zielony = new JMenuItem("Zielony");
        zmienKolor.add(zielony);
        JMenuItem zolty = new JMenuItem("Żółty");
        zmienKolor.add(zolty);
        JMenuItem czarny = new JMenuItem("Czarny");
        zmienKolor.add(czarny);

        JMenuBar menu = new JMenuBar();

        JMenu ksztalty = new JMenu("Wybierz kształt");
        ksztalty.setFont(MojFont1);

        JMenuItem kolo = new JMenuItem("Koło");
        kolo.setFont(MojFont1);
        kolo.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rysujTrojkat = false;
                rysujKolo = true;
                rysujProstokat = false;
            }
        });
        ksztalty.add(kolo);

        JMenuItem prostokat = new JMenuItem("Prostokąt");
        prostokat.setFont(MojFont1);
        prostokat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rysujTrojkat = false;
                rysujKolo = false;
                rysujProstokat = true;
            }
        });
        ksztalty.add(prostokat);

        JMenuItem trojkat = new JMenuItem("Trójkąt");
        trojkat.setFont(MojFont1);
        trojkat.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rysujTrojkat = true;
                rysujKolo = false;
                rysujProstokat = false;
                pktTrojkata = 3;
            }
        });
        ksztalty.add(trojkat);

        menu.add(ksztalty);
        //POCZĄTEK OPCJI ZAPISU ---------------------------------------------------------------
        JMenu plik = new JMenu("Plik");
        plik.setFont(MojFont1);
        JMenuItem zapisz = new JMenuItem("Zapisz");
        zapisz.setFont(MojFont1);
        plik.add(zapisz);
        zapisz.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                String nazwa = JOptionPane.showInputDialog("Wprowadź nazwę pliku");
                File nowyPlik = new File(nazwa + ".csv");
                try{nowyPlik.createNewFile();}
                catch(IOException exc){}
                
                try{
                PrintWriter zapis = new PrintWriter(nazwa + ".csv");

                for(int j=0; j<=narysowaneFigury.size()-1; j++){

                    int a = narysowaneFigury.get(j).x1;
                    int b = narysowaneFigury.get(j).y1;
                    int c = narysowaneFigury.get(j).x2;
                    int d = narysowaneFigury.get(j).y2;
                    int l = narysowaneFigury.get(j).x3;
                    int h = narysowaneFigury.get(j).y3;
                    int t = narysowaneFigury.get(j).typ;
                    int f = narysowaneFigury.get(j).kolor;

                    zapis.append(Integer.toString(a));
                    zapis.append(",");
                    zapis.append(Integer.toString(b));
                    zapis.append(",");
                    zapis.append(Integer.toString(c));
                    zapis.append(",");
                    zapis.append(Integer.toString(d));
                    zapis.append(",");
                    zapis.append(Integer.toString(l));
                    zapis.append(",");
                    zapis.append(Integer.toString(h));
                    zapis.append(",");
                    zapis.append(Integer.toString(t));
                    zapis.append(",");
                    zapis.append(Integer.toString(f));
                    zapis.append("\n");

                 }
                 zapis.close();
                }
                catch(FileNotFoundException exc){}  
            }
    
        });

        JMenuItem wczytaj = new JMenuItem("Wczytaj");
        wczytaj.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                narysowaneFigury.clear();
                String nazwa = JOptionPane.showInputDialog("Wprowadź nazwę pliku");
                String wiersz;
                try{
                BufferedReader odczyt = new BufferedReader(new FileReader(nazwa + ".csv"));
                while ((wiersz = odczyt.readLine()) != null) {
                    String[] dane = wiersz.split(",");
                    try{
                        int a = Integer.parseInt(dane[0]);
                        int b = Integer.parseInt(dane[1]);
                        int c = Integer.parseInt(dane[2]);
                        int d = Integer.parseInt(dane[3]);
                        int l = Integer.parseInt(dane[4]);
                        int h = Integer.parseInt(dane[5]);
                        int t = Integer.parseInt(dane[6]);
                        int f = Integer.parseInt(dane[7]);

                        Figura wczytywana = new Figura(a, c, l, b, d, h, f, t);
                        narysowaneFigury.add(wczytywana);
                    }
                    catch(NumberFormatException exc){}

                    
                }
            }
                catch(FileNotFoundException exc){}
                catch(IOException exc){}

                repaint();
 
            }
    
        });
        wczytaj.setFont(MojFont1);
        plik.add(wczytaj);
        menu.add(plik);
        //KONIE OPCJI ZAPISU----------------------------------------------------------------
        JMenu opcje = new JMenu("Opcje");
        opcje.setFont(MojFont1);

        JMenuItem info = new JMenuItem("INFO");
        info.setFont(MojFont1);
        info.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Witamy w programie do rysowania kształtów.\n Należy wybrać z menu rodzaj kształtu i narysować go myszką na ekranie.\n Kolor kształtu można zmienić, klikając na niego prawym przyciskiem myszki.\n Kształty można również przesuwać, przeciągając je prawym przyciskiem myszki i skalować kółkiem myszki.\nAutor: Jakub Sokołowski", "INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        opcje.add(info);

        JMenuItem wyczysc = new JMenuItem("Wyczyść");
        wyczysc.setFont(MojFont1);
        wyczysc.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                narysowaneFigury.clear();
                repaint();
            }
        });
        opcje.add(wyczysc);
        menu.add(opcje);
        add(menu, BorderLayout.NORTH);
        //MOUSE WHEEL LISTENER - POWIĘKSZANIE I POMNIEJSZANIE KSZTAŁTÓW
        addMouseWheelListener( new MouseAdapter(){

            public void mouseWheelMoved(MouseWheelEvent e){
                pktX = e.getX();
                pktY = e.getY();

                if(e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL){
                        boolean wSrodku = false;
                        for(j = 0; j<=narysowaneFigury.size()-1; j++){
    
                            int a = narysowaneFigury.get(j).x1;
                            int b = narysowaneFigury.get(j).y1;
                            int c = narysowaneFigury.get(j).x2;
                            int d = narysowaneFigury.get(j).y2;
                            int l = narysowaneFigury.get(j).x3;
                            int h = narysowaneFigury.get(j).y3;
                            int t = narysowaneFigury.get(j).typ;
                            int f = narysowaneFigury.get(j).kolor;
    
                            if(t == 0){
                                ZEllipse e1 = new ZEllipse(Math.min(a, c), Math.min(b, d), Math.max(Math.abs(a-c), Math.abs(b-d)), Math.max(Math.abs(a-c), Math.abs(b-d)));
                                if(e1.contains(pktX, pktY)){
                                    doZmiany = j;
                                    wSrodku = true;
                                }
                            }
                            else if(t == 1){
                                ZRectangle r1 = new ZRectangle(Math.min(a, c), Math.min(b, d), Math.abs(a-c), Math.abs(b-d));
                                if(r1.contains(pktX, pktY)){
                                    doZmiany = j;
                                    wSrodku = true;
                                }
    
                            }
                            else if(t == 2){
                                Polygon p1 = new Polygon();
                                p1.addPoint(a, b);
                                p1.addPoint(c, d);
                                p1.addPoint(l, h);
                                if(p1.contains(pktX, pktY)){
                                    wSrodku = true;
                                    doZmiany = j;
                                }
    
                            }
    
                            if(wSrodku){
    
                                narysowaneFigury.get(doZmiany).powieksz(7*e.getWheelRotation());
                            }
    
                        }
                        repaint();
                }
                }
            } 
        );


        //MOUSE LISTENER - RYSOWANIE KSZTAŁTÓW LEWYM PRZYCISKIEM MYSZY I ZMIANA KOLORU PRAWYM
        addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {

                //RYSOWANIE TRÓJKĄTA
                if(e.getButton() == MouseEvent.BUTTON1){

                    if(pktTrojkata == 3){
                        x1 = e.getX();
                        y1 = e.getY();
                        pktTrojkata -= 1;
                    }
                    else if(pktTrojkata == 2){
                        x2 = e.getX();
                        y2 = e.getY();
                        pktTrojkata -= 1;
                    }
                    else if(pktTrojkata == 1){
                        x3 = e.getX();
                        y3 = e.getY();
                        narysowaneFigury.add(new Figura(x1, x2, x3, y1, y2, y3, 0, 2));
                        pktTrojkata -= 1;
                        rysujTrojkat = false;
                        repaint();
                    }
                }

                if(e.getButton() == MouseEvent.BUTTON3){
                    pktX = e.getX();
                    pktY = e.getY();
                    boolean wSrodku = false;
                    for(j = 0; j<=narysowaneFigury.size()-1; j++){

                        int a = narysowaneFigury.get(j).x1;
                        int b = narysowaneFigury.get(j).y1;
                        int c = narysowaneFigury.get(j).x2;
                        int d = narysowaneFigury.get(j).y2;
                        int l = narysowaneFigury.get(j).x3;
                        int h = narysowaneFigury.get(j).y3;
                        int t = narysowaneFigury.get(j).typ;
                        int f = narysowaneFigury.get(j).kolor;

                        if(t == 0){
                            ZEllipse e1 = new ZEllipse(Math.min(a, c), Math.min(b, d), Math.max(Math.abs(a-c), Math.abs(b-d)), Math.max(Math.abs(a-c), Math.abs(b-d)));
                            if(e1.contains(pktX, pktY)){
                                doZmiany = j;
                                wSrodku = true;
                            }
                        }
                        else if(t == 1){
                            ZRectangle r1 = new ZRectangle(Math.min(a, c), Math.min(b, d), Math.abs(a-c), Math.abs(b-d));
                            if(r1.contains(pktX, pktY)){
                                doZmiany = j;
                                wSrodku = true;
                            }

                        }
                        else if(t == 2){
                            Polygon p1 = new Polygon();
                            p1.addPoint(a, b);
                            p1.addPoint(c, d);
                            p1.addPoint(l, h);
                            if(p1.contains(pktX, pktY)){
                                wSrodku = true;
                                doZmiany = j;
                            }

                        }

                        if(wSrodku){

                            niebieski.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    narysowaneFigury.get(doZmiany).blue();
                                    repaint();
                                }
                            });
                            czerwony.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    narysowaneFigury.get(doZmiany).red();
                                    repaint();
                                }
                            });
                            zielony.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    narysowaneFigury.get(doZmiany).green();
                                    repaint();
                                }
                            });
                            zolty.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    narysowaneFigury.get(doZmiany).yellow();
                                    repaint();
                                }
                            });
                            czarny.addActionListener(new ActionListener(){
                                public void actionPerformed(ActionEvent e){
                                    narysowaneFigury.get(doZmiany).black();
                                    repaint();
                                }
                            });
                            zmienKolor.show(e.getComponent(), pktX, pktY);
                        }

                    }

                }
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {}
        
            @Override
            public void mouseExited(MouseEvent e) {}
            //POBIERANIE PUNKTÓW POCZĄTKOWYCH DLA PROATOKĄTA I KOŁA
            @Override
            public void mousePressed(MouseEvent e) {
                Xpocz = e.getX();
                Ypocz = e.getY();
                boolean wSrodku = false;
                if(e.getButton() == MouseEvent.BUTTON1){
                    if(!rysujProstokat && !rysujKolo){
                        for(j = narysowaneFigury.size()-1; j>=0 && !wSrodku; j--){   //TU BYŁA ZMIANA, ITERACJA MALEJĄCA

                            int a = narysowaneFigury.get(j).x1;
                            int b = narysowaneFigury.get(j).y1;
                            int c = narysowaneFigury.get(j).x2;
                            int d = narysowaneFigury.get(j).y2;
                            int l = narysowaneFigury.get(j).x3;
                            int h = narysowaneFigury.get(j).y3;
                            int t = narysowaneFigury.get(j).typ;
        
                            if(t == 0){
                                ZEllipse e1 = new ZEllipse(Math.min(a, c), Math.min(b, d), Math.max(Math.abs(a-c), Math.abs(b-d)), Math.max(Math.abs(a-c), Math.abs(b-d)));
                                if(e1.contains(Xpocz, Ypocz)){
                                    aktywna = j;
                                    wSrodku = true;
                                }
                            }
                            else if(t == 1){
                                ZRectangle r1 = new ZRectangle(Math.min(a, c), Math.min(b, d), Math.abs(a-c), Math.abs(b-d));
                                if(r1.contains(Xpocz, Ypocz)){
                                    aktywna = j;
                                    wSrodku = true;
                                }
        
                            }
                            else if(t == 2){
                                Polygon p1 = new Polygon();
                                p1.addPoint(a, b);
                                p1.addPoint(c, d);
                                p1.addPoint(l, h);
                                if(p1.contains(Xpocz, Ypocz)){
                                    wSrodku = true;
                                    aktywna = j;
                                }
        
                            }
        
                        }
                    }
                    else if(rysujProstokat || rysujKolo){
                        x1 = e.getX();
                        y1 = e.getY();
                    }
                }
        
            }
            //POBIERANIE PUNKTÓW KOŃCOWYCH DLA PROSTOKĄTA I KOŁA
            @Override
            public void mouseReleased(MouseEvent e) {

                if(aktywna != -1){
                    aktywna = -1;
                }
                
               if(e.getButton() == MouseEvent.BUTTON1){

                if(rysujProstokat || rysujKolo){
                    x2 = e.getX();
                    y2 = e.getY();
                }
                
                if(rysujProstokat){
                    narysowaneFigury.add(new Figura(x1, x2, 0, y1, y2, 0, 0, 1));
                    rysujProstokat = false;
                    repaint();
                }
                else if(rysujKolo){
                 narysowaneFigury.add(new Figura(x1, x2, 0, y1, y2, 0, 0, 0));
                 rysujKolo = false;
                 repaint();
                }

               }
            }
        });
        addMouseMotionListener(new MouseAdapter(){
            @Override
            public void mouseDragged(MouseEvent e){

                pktX = e.getX();
                pktY = e.getY();
                boolean wSrodku = false;

                if(aktywna != -1){
                    int dx = pktX - Xpocz;
                    int dy = pktY - Ypocz;
                    Xpocz += dx;
                    Ypocz += dy;
                    narysowaneFigury.get(aktywna).przesun(dx, dy);
                    repaint();
                }
            }
        });

        setVisible(true);
    }


}