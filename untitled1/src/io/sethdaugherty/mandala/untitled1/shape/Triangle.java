package io.sethdaugherty.mandala.untitled1.shape;

import io.sethdaugherty.mandala.untitled1.AppletHelpers;
import io.sethdaugherty.mandala.untitled1.Config;
import processing.core.PApplet;

public class Triangle implements MandalaShape {

    private float baseLength;
    private float height;
    private boolean shouldFill;

    public Triangle(float baseLength, float height, boolean shouldFill) {
        this.baseLength = baseLength;
        this.height = height;
        this.shouldFill = shouldFill;
    }

    @Override
    public void draw(PApplet p) {
        if (shouldFill) {
            AppletHelpers.setFill(Config.getStroke(), p);
        }
        p.beginShape();
        p.vertex(baseLength/2, 0);
        p.vertex(0, height);
        p.vertex(-baseLength/2, 0);
        p.endShape();
        // reset the fill
        if (shouldFill) {
            AppletHelpers.setFill(Config.getFill(), p);
        }
    }
}
