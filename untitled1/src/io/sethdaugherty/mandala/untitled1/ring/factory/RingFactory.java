package io.sethdaugherty.mandala.untitled1.ring.factory;

import io.sethdaugherty.mandala.untitled1.ring.*;
import io.sethdaugherty.mandala.untitled1.shape.*;
import io.sethdaugherty.mandala.untitled1.TriFunction;
import processing.core.PApplet;

import java.util.*;
import java.util.function.Function;

public class RingFactory {

    private static final List<TriFunction<Float, Integer, MandalaShape, ? extends MandalaRing>> ringFactories = setupRingFactories();

    private static List<TriFunction<Float,Integer,MandalaShape,? extends MandalaRing>> setupRingFactories() {
        List<TriFunction<Float, Integer, MandalaShape, ? extends MandalaRing>> ringFactories = new ArrayList<>();
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new BasicShapeRing(radius, count, shape));
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new BasicShapeRing(radius, count, shape)); // We want lots of these, so double up
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new CircleRing(radius));
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new DotRing(radius, (radius/count)*.9f, count));
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new DotRing(radius, (radius/count)*.7f, count*2));
        ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new StrokedRing(radius, new BasicShapeRing(radius, count, shape)));


        return ringFactories;
    }




    // This is intended to be used for testing a specific type of ring

    public static MandalaRing<? extends MandalaShape, PApplet> createRing(PApplet p, float radius, int count, Function<Float, ? extends MandalaShape> factory) {
        float circumference = 2 * p.PI * radius;
        float shapeSize = circumference / count;

        MandalaShape shape = factory.apply(shapeSize);

        return new BasicShapeRing(radius, count, shape);
    }

    public static MandalaRing<MandalaShape, PApplet> createRandomRing(PApplet p, float radius, int count) {

        float circumference = 2 * p.PI * radius;
        float shapeSize = circumference / count;

        MandalaShape shape = createRandomShape(shapeSize);
        return createRandomRing(radius, count, shape);
        //return new BasicShapeRing(radius, count, shape);

        //return new PetalRing(p, radius, count, shapeSize); // TODO: randomize this
    }

    public static MandalaRing createRandomRing(float radius, int count, MandalaShape shape) {
        int randomElement = new Random().nextInt(ringFactories.size());
        return ringFactories.get(randomElement).apply(radius, count, shape);
    }

    private static MandalaShape createRandomShape(float shapeSize) {
        int randomElement = new Random().nextInt(ShapeFactories.getShapeFactories().size());
        return ShapeFactories.getShapeFactories().get(randomElement).apply(shapeSize);
    }
}
