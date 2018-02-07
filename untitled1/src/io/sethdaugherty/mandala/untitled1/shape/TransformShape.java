package io.sethdaugherty.mandala.untitled1.shape;

import processing.core.PApplet;

import java.util.function.Function;

public class TransformShape implements MandalaShape {

    private final MandalaShape shape;
    private final Function<PApplet, Void> transform;

    public TransformShape(MandalaShape shape, Function<PApplet, Void> transform) {
        this.shape = shape;
        this.transform = transform;
    }

    @Override
    public void draw(PApplet p) {
        p.pushMatrix();
        transform.apply(p);
        shape.draw(p);
        p.popMatrix();
    }
}
