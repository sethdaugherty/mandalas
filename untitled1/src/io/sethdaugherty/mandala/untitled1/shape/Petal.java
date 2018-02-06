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
public class Petal implements MandalaShape {
    private float petalSize;

    public Petal(float petalSize) {
        this.petalSize = petalSize/2;
    }

    @Override
    public void draw(PApplet p) {
        p.beginShape();
        p.vertex(petalSize, 0); // first point
        p.bezierVertex(petalSize, petalSize, 5, petalSize, 0, petalSize*2);
        p.bezierVertex(-5, petalSize, -petalSize, petalSize, -petalSize, 0);

        //vertex(0, 100);
        //bezierVertex(50, 140, 75, 140, 120, 120);
        p.endShape();
    }


}
