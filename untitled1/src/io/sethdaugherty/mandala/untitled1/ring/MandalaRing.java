package io.sethdaugherty.mandala.untitled1.ring;

import io.sethdaugherty.mandala.untitled1.shape.MandalaShape;
import processing.core.PApplet;

public interface MandalaRing<T extends MandalaShape, P extends PApplet> {
    void draw(P p);
}
