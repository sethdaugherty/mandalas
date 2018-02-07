package io.sethdaugherty.mandala.untitled1.quad;

import io.sethdaugherty.mandala.untitled1.shape.MandalaShape;
import processing.core.PApplet;

public interface MandalaQuad <T extends MandalaShape, P extends PApplet> {
    void draw(P p);
}
