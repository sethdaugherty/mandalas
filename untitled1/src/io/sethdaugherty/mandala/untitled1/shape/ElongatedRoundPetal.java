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
public class ElongatedRoundPetal implements MandalaShape {
    private float petalSize;

    public ElongatedRoundPetal(float petalSize) {
        this.petalSize = petalSize/2;
    }

    @Override
    public void draw(PApplet p) {
        p.beginShape();
        p.vertex(petalSize, 0); // first point
        p.bezierVertex(petalSize+5, petalSize, petalSize, petalSize*2, 0, petalSize*2);
        p.bezierVertex(-petalSize, petalSize*2, -petalSize-5, petalSize, -petalSize, 0);
        p.endShape();
    }


}
