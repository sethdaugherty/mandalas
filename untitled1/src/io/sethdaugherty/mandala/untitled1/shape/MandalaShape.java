package io.sethdaugherty.mandala.untitled1.shape;

import processing.core.PApplet;

public interface MandalaShape<P extends PApplet> {
    void draw(P p);
}
