package io.sethdaugherty.mandala.untitled1.shape;

import processing.core.PApplet;

public class NestedShape implements MandalaShape {

    private final MandalaShape baseShape;
    private final MandalaShape foregroundShape;

    public NestedShape(MandalaShape baseShape, MandalaShape foregroundShape) {
        this.baseShape = baseShape;
        this.foregroundShape = foregroundShape;
    }

    @Override
    public void draw(PApplet p) {
        baseShape.draw(p);
        foregroundShape.draw(p);
    }
}
