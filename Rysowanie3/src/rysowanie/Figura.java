package rysowanie;

public class Figura {

    int x1, x2, x3, y1, y2, y3;
    int kolor;
    int typ;

    Figura(int x1, int x2, int x3, int y1, int y2, int y3, int kolor, int typ){
        
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
        this.kolor = kolor;
        this.typ = typ;
    }

    void red(){
        kolor = 2;
    }

    void blue(){
        kolor = 3;
    }

    void black(){
        kolor = 0;
    }

    void green(){
        kolor = 4;
    }

    void yellow(){
        kolor = 5;
    }

    void powieksz(int p){

        if(typ == 0 /*|| typ == 1*/){

            if(x1 < x2){
                x2 = x2 + p;
            }
            else if(x1 > x2){
                x1 = x1 + p;
            }
    
            if(y1 < y2){
                y2 = y2 + p;
            }
            else if(y1 > y2){
                y1 = y1 + p;
            }
            
        }

        else if(typ == 1){

            if(x1 < x2){
                x2 = x2 + p*Math.abs(x1-x2)/500;
            }
            else if(x1 > x2){
                x1 = x1 + p*Math.abs(x1-x2)/500;
            }
    
            if(y1 < y2){
                y2 = y2 + p*Math.abs(y1-y2)/500;
            }
            else if(y1 > y2){
                y1 = y1 + p*Math.abs(y1-y2)/500;
            }
        }

        else if(typ == 2){

            int sx = (x1+x2+x3)/3;
            int sy = (y1+y2+y3)/3;

            if(x1 > sx){
                x1 = x1 + p;
            }
            else{
                x1 = x1 - p;
            }

            if(x2 > sx){
                x2 = x2 + p;
            }
            else{
                x2 = x2 - p;
            }

            if(x3 > sx){
                x3 = x3 + p;
            }
            else{
                x3 = x3 - p;
            }



            if(y1 > sy){
                y1 = y1 + p;
            }
            else{
                y1 = y1 - p;
            }

            if(y2 > sy){
                y2 = y2 + p;
            }
            else{
                y2 = y2 - p;
            }

            if(y3 > sy){
                y3 = y3 + p;
            }
            else{
                y3 = y3 - p;
            }

        }


    }

    void przesun(int dx, int dy){

        x1 = x1 + dx;
        x2 = x2 + dx;
        x3 = x3 + dx;

        y1 = y1 + dy;
        y2 = y2 + dy;
        y3 = y3 + dy;


    }
}