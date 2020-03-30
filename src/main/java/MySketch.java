import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PFont;
import sma.ObjetATrier;
import sma.Systeme;
import sma.environnement.Case;
import sma.environnement.Grille;

public class MySketch extends PApplet{

    private Systeme systeme;
    private int iterationNumber=0;
    private int iterationPerDraw=10;
    private int frameNumber=0;

    public void settings(){
        systeme=new Systeme(10000,15000,15000,540,540);
        systeme.printGrille();
        size(540, 540);
    }

    public void draw(){
        background(20);
        for(int k=0; k<iterationPerDraw;k++){
            systeme.faireUneIteration();
            iterationNumber++;
        }
        if(iterationPerDraw<1000){
            iterationPerDraw+=5;
        }
        loadPixels();
        Grille grille = systeme.env.grille;
        for(int x=0;x<540;++x){
            for(int y=0;y<540;++y){
                Case aCase = grille.getCase(x, y);
                if(aCase.isOccupiedByObject()){
                    ObjetATrier obj = aCase.objetSurCase;
                    pixels[x+y*540]=obj.type%2==0?
                            color(150,150,255)
                            :
                            color(150,255,150);
                }
            }
        }
        updatePixels();

        System.out.println(iterationNumber);
        save("gifFrames/"+frameNumber+".png");
        frameNumber++;
//        PFont font = createFont("font.ttf",32);
//        textFont(font, 32);
//        text(iterationNumber, 10, 50);
    }

    public void mouseDragged(){

    }

    public static void main(String[] args){
        String[] processingArgs = {"MySketch"};
        MySketch mySketch = new MySketch();
        PApplet.runSketch(processingArgs, mySketch);
    }
}
