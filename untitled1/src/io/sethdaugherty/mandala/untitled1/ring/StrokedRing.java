package io.sethdaugherty.mandala.untitled1.ring;

import io.sethdaugherty.mandala.untitled1.shape.MandalaShape;
import processing.core.PApplet;

public class StrokedRing implements MandalaRing<MandalaShape, PApplet> {

    private final MandalaRing outerRing;
    private final CircleRing circle;

    public StrokedRing(float radius, MandalaRing outerRing) {
        this.circle = new CircleRing(radius);
        this.outerRing = outerRing;
    }

    @Override
    public void draw(PApplet p) {

        outerRing.draw(p);
        circle.draw(p);
    }
}
