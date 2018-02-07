package io.sethdaugherty.mandala.untitled1.quad.factory;

import io.sethdaugherty.mandala.untitled1.TriFunction;
import io.sethdaugherty.mandala.untitled1.quad.BasicShapeQuad;
import io.sethdaugherty.mandala.untitled1.quad.MandalaQuad;
import io.sethdaugherty.mandala.untitled1.ring.BasicShapeRing;
import io.sethdaugherty.mandala.untitled1.ring.CircleRing;
import io.sethdaugherty.mandala.untitled1.ring.MandalaRing;
import io.sethdaugherty.mandala.untitled1.ring.StrokedRing;
import io.sethdaugherty.mandala.untitled1.shape.*;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Function;

public class QuadFactory {

    private static final List<BiFunction<Float, MandalaShape, ? extends MandalaQuad>> quadFactories = setupQuadFactories();

    private static List<BiFunction<Float, MandalaShape,? extends MandalaQuad>> setupQuadFactories() {
        List<BiFunction<Float, MandalaShape, ? extends MandalaQuad>> quadFactories = new ArrayList<>();
        quadFactories.add((Float radius, MandalaShape shape) -> new BasicShapeQuad(radius, shape));
        //ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new CircleRing(radius));
        //ringFactories.add((Float radius, Integer count, MandalaShape shape) -> new StrokedRing(radius, new BasicShapeRing(radius, count, shape)));

        return quadFactories;
    }


    // This is intended to be used for testing a specific type of ring

    public static MandalaQuad<? extends MandalaShape, PApplet> createQuad(PApplet p, float radius, Function<Float, ? extends MandalaShape> factory) {
        float circumference = 2 * p.PI * radius;
        float shapeSize = circumference / 4;

        MandalaShape shape = factory.apply(shapeSize);

        float outerHypotenuse = (float) Math.sqrt( Math.pow(radius, 2) * 2f );
        float ratio = outerHypotenuse / radius;
        float translationRadius = radius / ratio;

        return new BasicShapeQuad(translationRadius, shape);
    }

    public static MandalaQuad<MandalaShape, PApplet> createRandomQuad(PApplet p, float radius) {
        float circumference = 2 * p.PI * radius;
        float shapeSize = circumference / 4;

        float outerHypotenuse = (float) Math.sqrt( Math.pow(radius, 2) * 2f );
        float ratio = outerHypotenuse / radius;
        float translationRadius = radius / ratio;
        MandalaShape shape = createRandomShape(shapeSize);
        return createRandomQuad(translationRadius, shape);
        //return new BasicShapeRing(radius, count, shape);

        //return new PetalRing(p, radius, count, shapeSize); // TODO: randomize this
    }

    public static MandalaQuad createRandomQuad(float radius, MandalaShape shape) {
        int randomElement = new Random().nextInt(quadFactories.size());
        return quadFactories.get(randomElement).apply(radius, shape);
    }

    private static MandalaShape createRandomShape(float shapeSize) {
        int randomElement = new Random().nextInt(ShapeFactories.getShapeFactories().size());
        return ShapeFactories.getShapeFactories().get(randomElement).apply(shapeSize);
    }
}
