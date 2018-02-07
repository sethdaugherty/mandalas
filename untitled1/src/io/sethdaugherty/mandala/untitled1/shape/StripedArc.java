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
public class StripedArc implements MandalaShape {
    private float size;

    public StripedArc(float size) {
        this.size = size;
    }

    @Override
    public void draw(PApplet p) {
        p.arc(0, 0, size/2, size/2, 0, p.PI, p.OPEN);
        for (float i=.9f; i > 0; i= i-.1f) {
            p.arc( 0, 0, size * i, size * i, 0, p.PI, p.OPEN);
        }
    }
}
