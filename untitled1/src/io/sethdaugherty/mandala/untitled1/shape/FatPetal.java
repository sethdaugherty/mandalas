package io.sethdaugherty.mandala.untitled1.shape;

import processing.core.PApplet;

//public class Thing {
//    PApplet p; //Reference to your Processing sketch
//
//    public Thing(PApplet p) {
//        p = p;
//    }
//
//    void doSomething() {
//        p.stroke(0);
//        p.fill(255, 255, 0);
//        p.rect(p.width / 3, p.height / 3, 100, 50);
//        //everything Processing-based, even width and height, needs a prefix
//    }
//}
public class FatPetal implements MandalaShape {
    private float petalSize;

    public FatPetal(float petalSize) {
        this.petalSize = petalSize/2;
    }

    @Override
    public void draw(PApplet p) {
        p.beginShape();
        p.vertex(petalSize, 0); // first point
        p.bezierVertex(petalSize, petalSize*1.5f, petalSize*.7f, petalSize*1.5f, 0, petalSize*2);
        p.bezierVertex(-petalSize*.7f, petalSize*1.5f, -petalSize, petalSize*1.5f, -petalSize, 0);

        //vertex(0, 100);
        //bezierVertex(50, 140, 75, 140, 120, 120);
        p.endShape();
    }


}
