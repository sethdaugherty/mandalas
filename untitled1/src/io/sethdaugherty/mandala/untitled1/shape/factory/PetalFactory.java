package io.sethdaugherty.mandala.untitled1.shape.factory;

import io.sethdaugherty.mandala.untitled1.shape.Petal;

import java.util.function.Function;

public class PetalFactory implements Function<Float, Petal> {

    @Override
    public Petal apply(Float shapeSize) {
        return new Petal(shapeSize);
    }
}
