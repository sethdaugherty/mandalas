package io.sethdaugherty.mandala.untitled1.ring;

import io.sethdaugherty.mandala.untitled1.shape.NullShape;
import processing.core.PApplet;

public class CircleRing implements MandalaRing<NullShape, PApplet> {

    private final float radius;

    public CircleRing(float radius) {
        this.radius = radius;
    }

    @Override
    public void draw(PApplet p) {
        p.pushMatrix();
        p.translate(p.width*0.5f, p.height*0.5f);

        p.ellipse(0, 0, radius*2, radius*2);

        p.popMatrix();

    }
}
