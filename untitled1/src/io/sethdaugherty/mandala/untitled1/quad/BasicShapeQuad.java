package io.sethdaugherty.mandala.untitled1.quad;

import io.sethdaugherty.mandala.untitled1.shape.MandalaShape;
import processing.core.PApplet;

public class BasicShapeQuad implements MandalaQuad {

    private final float radius;
    private final MandalaShape shape;

    public BasicShapeQuad(float radius, MandalaShape shape) {
        this.radius = radius;
        this.shape = shape;
    }

    @Override
    public void draw(PApplet p) {

        p.pushMatrix();
        p.translate(p.width*0.5f, p.height*0.5f);

        p.strokeWeight(0);
        p.rect(-radius, -radius, 2*radius, 2*radius);
        p.strokeWeight(1);
        for (int i=0; i < 4; i++ ) {
            p.translate(0, radius-1);
            shape.draw(p);
            p.translate(0, -radius+1);
            p.rotate(p.TWO_PI / 4);
        }

        p.popMatrix();
    }
}
