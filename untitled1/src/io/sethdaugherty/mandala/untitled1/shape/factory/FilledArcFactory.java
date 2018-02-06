package io.sethdaugherty.mandala.untitled1.shape.factory;

import io.sethdaugherty.mandala.untitled1.shape.FilledArc;
import processing.core.PApplet;

import java.util.function.BiFunction;
import java.util.function.Function;

public class FilledArcFactory implements Function<Float, FilledArc> {

    @Override
    public FilledArc apply(Float shapeSize) {
        return new FilledArc(shapeSize);
    }
}
