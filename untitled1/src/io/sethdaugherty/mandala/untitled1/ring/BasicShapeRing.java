package io.sethdaugherty.mandala.untitled1.ring;

import io.sethdaugherty.mandala.untitled1.shape.MandalaShape;
import processing.core.PApplet;

public class BasicShapeRing implements MandalaRing<MandalaShape, PApplet> {

    private final MandalaShape shape;
    private final int count;
    private final float radius;

    public BasicShapeRing(float radius, int count, MandalaShape shape) {
        this.radius = radius;
        this.count = count;
        this.shape = shape;
    }

    @Override
    public void draw(PApplet p) {

        p.pushMatrix();
        p.translate(p.width*0.5f, p.height*0.5f);

        p.strokeWeight(0);
        p.ellipse(0, 0, 2*radius, 2*radius);
        p.strokeWeight(1);
        for (int i=0; i < count; i++ ) {
            p.translate(0, radius*.95f);
            shape.draw(p);
            p.translate(0, -radius*.95f);
            p.rotate(p.TWO_PI / count);
        }

        p.popMatrix();
    }
}
