package io.sethdaugherty.mandala.untitled1.shape;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ShapeFactories {
    private static final List<Function<Float, ? extends MandalaShape>> shapeFactories = setupShapeFactories();

    private static List<Function<Float, ? extends MandalaShape>> setupShapeFactories() {
        List<Function<Float, ? extends MandalaShape>> shapeFactories = new ArrayList<>();
        shapeFactories.add((Float size) -> new Petal(size));
        shapeFactories.add((Float size) -> new FatPetal(size));
        shapeFactories.add((Float size) -> new FilledArc(size));
        shapeFactories.add((Float size) -> new NestedShape(new Petal(size), new FilledArc(size-10)));
        shapeFactories.add((Float size) -> new NestedShape(new FilledArc(size), new Petal(size*.8f)));
        shapeFactories.add((Float size) -> new NestedShape(new Petal(size), new NestedShape(new Petal(size*.9f), new FilledArc(size*.8f))));
        shapeFactories.add((Float size) -> new StripedArc(size));
        shapeFactories.add((Float size) -> new ElongatedRoundPetal(size));
        shapeFactories.add((Float size) -> new NestedShape(
                new Triangle(size * .35f, size * .4f, false),
                new NestedShape(
                        new Triangle(size * .25f, size * .3f, false),
                        new Triangle(size * .15f, size * .2f, true)
                ))
        );
        shapeFactories.add((Float size) -> new NestedShape(
                new ElongatedRoundPetal(size),
                new NestedShape(
                        new ElongatedRoundPetal(size*.95f),
                        new ElongatedRoundPetal(size*.9f))
        ));
        shapeFactories.add((Float size) -> new NestedShape(

                new FatPetal(size),
                new StripedArc(size)
        ));
        shapeFactories.add((Float size) -> new NestedShape(

                new FatPetal(size),
                new NestedShape(
                        new FilledArc(size),
                        new Triangle(size, size*.2f, false)
                )
        ));

        return shapeFactories;
    }

    public static List<Function<Float, ? extends MandalaShape>> getShapeFactories() {
        return shapeFactories;
    }
}
