package io.sethdaugherty.mandala.untitled1;

import io.sethdaugherty.mandala.untitled1.quad.factory.QuadFactory;
import io.sethdaugherty.mandala.untitled1.shape.*;
import io.sethdaugherty.mandala.untitled1.ring.factory.RingFactory;
import processing.core.PApplet;

import java.util.Random;
import java.util.function.Function;

public class Main extends PApplet {
    int[] palette;
    int[][] paletteLibrary;

    boolean shouldRedraw = true;

    public static void main(String[] args) {
        PApplet.main("io.sethdaugherty.mandala.untitled1.Main", args);
    }

    public void settings() {
        size(1000, 1000);
    }

    public void setup() {
        background(102);

        paletteLibrary = new int[5][5];
        // http://colorpalettes.net/color-palette-3609/
        paletteLibrary[0] = new int[]{AppletHelpers.color("#b2b3b2"), AppletHelpers.color("#eed1e2"), AppletHelpers.color("#ae84a0"), AppletHelpers.color("#7f2852"), AppletHelpers.color("#0a0a18")};
        // http://colorpalettes.net/color-palette-2754/
        paletteLibrary[1] = new int[]{AppletHelpers.color("#6b635b"), AppletHelpers.color("#a9c695"), AppletHelpers.color("#b1bb39"), AppletHelpers.color("#e3e79f"), AppletHelpers.color("#ededef")};
        // http://colorpalettes.net/color-palette-3674/
        paletteLibrary[2] = new int[]{AppletHelpers.color("#222628"), AppletHelpers.color("#555860"), AppletHelpers.color("#a2adc2"), AppletHelpers.color("#e9e8eb"), AppletHelpers.color("#fdfdff")};
        // http://colorpalettes.net/color-palette-3670/
        paletteLibrary[3] = new int[]{AppletHelpers.color("#0c090b"), AppletHelpers.color("#312b2f"), AppletHelpers.color("#767f88"), AppletHelpers.color("#e4e9eb"), AppletHelpers.color("#5178ba")};
        // http://colorpalettes.net/color-palette-3606/
        paletteLibrary[4] = new int[]{AppletHelpers.color("#b4d7d4"), AppletHelpers.color("#314701"), AppletHelpers.color("#6d8008"), AppletHelpers.color("#fbcc38"), AppletHelpers.color("#f5880d")};
    }

    public void draw() {
        if (shouldRedraw) {
            println("drawing mandala");
            drawMandala();
            shouldRedraw = false;
        }

        float petalSize = 50;
//        pushMatrix();
//        translate(width*0.5f, height*0.5f);
////noFill();
//        beginShape();
//        vertex(petalSize, 0); // first point
//        float c1_x = petalSize*1.5f;
//        float c1_y = petalSize;
//        float c2_x = petalSize/10;
//        float c2_y = petalSize + petalSize*.6f;
//        println(c1_x + " " + c1_y + " " + c2_x + " " + c2_y + " ");
//        bezierVertex(c1_x, c1_y, c2_x, c2_y, 0, petalSize*2);
//        bezierVertex(-c2_x, c2_y, -c1_x, c1_y, -petalSize, 0);
//
//        //vertex(0, 100);
//        //bezierVertex(50, 140, 75, 140, 120, 120);
//        endShape();
//
//        popMatrix();
    }

    public void mouseClicked() {
        shouldRedraw = true;
    }

    private void drawMandala() {
        palette = paletteLibrary[(int) (random(paletteLibrary.length))];

        background(56);

        //RingFactory.createRandomRing(this, 280f, 30).draw();
        //RingFactory.createRandomRing(this, 280f, 60).draw();
        //RingFactory.createRandomRing(this, 200f, 30).draw();

        int count = (int) random(10, 20);
        float radius = width / 2 - 200;

        int option = 3;

        if (option == 1) {

            // Option 1: draw a specific shape
            randomFillAndStroke();
            Function<Float, ? extends MandalaShape> factory = (Float size) -> new Triangle(size * .2f, size * .3f, false);
            factory = (Float size) -> new NestedShape(

                    new FatPetal(size),
                    new StripedArc(size)
            );
            factory = (Float size) -> new NestedShape(

                    new FatPetal(size),
                    new NestedShape(
                        new FilledArc(size),
                        new Triangle(size, size*.2f, false)
                    )
            );

//            factory = (Float size) -> new TransformShape(
//                    new InwardTriangle(size*.2f, size*.3f, false),
//                    (PApplet p) -> { p.rotate()} ???? how do you pass the count, shapesize and radius parameters into this factory?
//                    );

            RingFactory.createRing(this, radius, count, factory).draw(this);

        } else if (option == 2) {

            // Option 2: draw a random ring
            randomFillAndStroke();
            RingFactory.createRandomRing(this, radius, count).draw(this);
        } else if (option == 3) {
            // Option 3: draw the full mandala

            int baseRingCount = 8;
            for (int i = 0; i < baseRingCount; i++) {
                randomFillAndStroke();
                int duplications = new Random().nextInt(3)+1;
                RingFactory.createRandomRing(this, radius, count*duplications).draw(this);
                radius = radius * .9f;
            }

            int quadCount = 4;
            radius = 100;
            for (int i=0; i < quadCount; i++) {
                randomFillAndStroke();
                QuadFactory.createRandomQuad(this, radius).draw(this);
                radius = radius * .85f;
            }
        } else if (option == 4) {
            randomFillAndStroke();
            Function<Float, ? extends MandalaShape> factory = (Float size) -> new NestedShape(
                    new Petal(size),
                    new FilledArc(size)

                );
            QuadFactory.createQuad(this, 100f, factory).draw(this);
        }

    }

    private void drawMandala2() {
        palette = paletteLibrary[(int) (random(paletteLibrary.length))];

        background(56);

        // draw some "background" rings first
        int count = (int) random(10, 20);
        float radius = width / 2 - 300;
        float shapeSize = 60;

        int baseRingCount = 3;
        for (int i = 0; i < baseRingCount; i++) {
            randomFillAndStroke();
            radius = randomBaseRing(count * (int) random(1, 2.9f), radius, shapeSize * random(.5f, 2));
        }


        count = (int) random(10, 20);
        radius = width / 2 - 200;
        shapeSize = 20;

        int ringCount = 10;

        for (int i = 0; i < ringCount; i++) {
            randomFillAndStroke();
            radius = randomRing(count * (int) random(1, 2.9f), radius, shapeSize * random(.5f, 2));
        }
    }

    float randomRing(float count, float radius, float shapeSize) {

        int shape = (int) random(9);
//shape=7;
        if (shape == 0) {
            // circle
            println("drawing circle ring" + radius + " " + count + " " + shapeSize);
            circleRing(count, radius, shapeSize);
            return radius - shapeSize * 2;
        } else if (shape == 1) {
            // triangle
            triangleRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 2) {
            // inner triangle
            inwardTriangleRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 3) {
            // rect
            rectRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 4) {
            // empty ring
            emptyRing(radius);
        } else if (shape == 5) {
            lineRing(count, radius, shapeSize);
        } else if (shape == 6) {
            roundPetalRing(count, radius, shapeSize);
        } else if (shape == 7) {
            pointedPetalRing(count, radius, shapeSize);
        } else if (shape == 8) {
            sharpPetalRing(count, radius, shapeSize);
        }

        return radius - shapeSize;
    }

    float randomBaseRing(float count, float radius, float shapeSize) {

        int shape = (int) random(3);
//shape=7;
        if (shape == 0) {
            linedArcRing(count, radius, shapeSize);
        } else if (shape == 1) {
            linedArcRing(count, radius, shapeSize);
        } else if (shape == 2) {
            linedArcRing(count, radius, shapeSize);
        }

        return radius - shapeSize;
    }

    void circleRing(float count, float ringRadius, float circleRadius) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            ellipse(0, 0, circleRadius, circleRadius);
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void roundPetalRing(float count, float ringRadius, float petalSize) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            beginShape();
            vertex(petalSize, 0); // first point
            float c1_x = petalSize * 1.5f;
            float c1_y = petalSize;
            float c2_x = petalSize / 10;
            float c2_y = petalSize + petalSize * .6f;
            //println(c1_x + " " + c1_y + " " + c2_x + " " + c2_y + " ");
            bezierVertex(c1_x, c1_y, c2_x, c2_y, 0, petalSize * 2);
            bezierVertex(-c2_x, c2_y, -c1_x, c1_y, -petalSize, 0);

            //vertex(0, 100);
            //bezierVertex(50, 140, 75, 140, 120, 120);
            endShape();            //ellipse(0, 0, circleRadius, circleRadius);
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void sharpPetalRing(float count, float ringRadius, float petalSize) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            beginShape();
            vertex(petalSize, 0); // first point
            bezierVertex(petalSize, petalSize, 5, petalSize, 0, petalSize * 2);
            bezierVertex(-5, petalSize, -petalSize, petalSize, -petalSize, 0);

            //vertex(0, 100);
            //bezierVertex(50, 140, 75, 140, 120, 120);
            endShape();
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void pointedPetalRing(float count, float ringRadius, float petalSize) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            beginShape();
            vertex(petalSize, 0); // first point
            bezierVertex(petalSize, petalSize, 5, petalSize, 0, petalSize * 2);
            bezierVertex(-5, petalSize, -petalSize, petalSize, -petalSize, 0);

            //vertex(0, 100);
            //bezierVertex(50, 140, 75, 140, 120, 120);
            endShape();
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void linedArcRing(float count, float ringRadius, float petalSize) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            arc(0, 0, petalSize, petalSize, 0, PI, PIE);
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }

        pushMatrix();
        for (int i = 0; i < count; i++) {
            translate(0, ringRadius);
            line(0, 0, petalSize, 0);
            translate(0, -ringRadius);
            rotate(TWO_PI / count);
        }
        popMatrix();

        popMatrix();
    }

    void rectRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            rect(-baseLen / 2, 0, baseLen, sideLen);
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void inwardTriangleRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            pushMatrix();
            rotate(PI);
            translate(0, -sideLen);
            isoTriangle(baseLen, sideLen);
            popMatrix();
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void triangleRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            isoTriangle(baseLen, sideLen);
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void reuleauxRing(float count, float radius, float baseLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            reuleaux(baseLen);
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void flameRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            flame(baseLen, sideLen);
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    void emptyRing(float radius) {
        pushMatrix();
        noFill();
        translate(width * 0.5f, height * 0.5f);
        arc(0, 0, radius * 2, radius * 2, 0, TWO_PI);
        popMatrix();
    }

    void lineRing(float count, float radius, float lineLen) {
        pushMatrix();
        translate(width * 0.5f, height * 0.5f);

        for (int i = 0; i < count; i++) {
            translate(0, radius);
            line(0, 0, 0, lineLen);
            translate(0, -radius);
            rotate(TWO_PI / count);
        }

        popMatrix();
    }

    // Draw a Reuleaux triangle
    void reuleaux(float radius) {
        //change this to suit PI/6 to point shape downwards, 0 to point left and so on
        float someRotation = PI / 6;

        float innerRadius = radius * sqrt(3) / 6;
        translate(0, innerRadius);
        for (int n = 0; n < 3; n++) {
            pushMatrix();
            //using trig, we know that the x-displacement is
            //length of displacement * cos of the angle of the displacement
            float xdisplacement = innerRadius * cos(n * TWO_PI / 3 + someRotation);
            //similarly y-displacement is
            //length of displacement * sin of the angle of the displacement
            float ydisplacement = innerRadius * sin(n * TWO_PI / 3 + someRotation);

            //so, to centre the shape, the translation values are
            float xpos = -xdisplacement;
            float ypos = -ydisplacement;
            translate(xpos, ypos);
            rotate(n * TWO_PI / 3 + someRotation);
            //for sanity's sake, draw arc from 30deg above to 30deg below horizontal
            arc(0, 0, radius, radius, -PI / 6, PI / 6);
            popMatrix();
        }
        translate(0, -innerRadius);
    }

    // Triangle with both sides curved
    void flame(float baseLen, float sideLen) {
        beginShape();
        float sx = baseLen / 2;
        float sy = 0;
        vertex(sx, sy);

        sx = -baseLen / 2;
        sy = 0;
        vertex(sx, sy);
        curveVertex(sx, sy);
        curveVertex(sx, sy);

        sx = 0;
        sy = sqrt(pow(sideLen, 2) - pow((float) 0.5 * baseLen, 2));

        curveVertex(sx + sx / 3, sideLen / 3);

        sx = 0;
        sy = sqrt(pow(sideLen, 2) - pow((float) 0.5 * baseLen, 2));
        // sidelen^2 = x^2 + (.5*baseLen)^2
        curveVertex(sx, sy);
        curveVertex(sx, sy);
        vertex(sx, sy);

        curveVertex(sx, sy);
        curveVertex(sx, sy);

        sx = baseLen / 2;
        sy = 0;
        curveVertex(sx + sx / 3, sideLen / 3);

        curveVertex(sx, sy);
        curveVertex(sx, sy);

        endShape(CLOSE);
    }

    void isoTriangle(float baseLen, float sideLen) {
        beginShape();
        float sx = baseLen / 2;
        float sy = 0;
        vertex(sx, sy);

        sx = -baseLen / 2;
        sy = 0;
        vertex(sx, sy);

        sx = 0;
        sy = sqrt(pow(sideLen, 2) - pow((float) 0.5 * baseLen, 2));
        // sidelen^2 = x^2 + (.5*baseLen)^2
        vertex(sx, sy);

        endShape(CLOSE);
    }

    void polygon(float x, float y, float radius, int npoints) {
        float angle = TWO_PI / npoints;
        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
            float sx = x + cos(a) * radius;
            float sy = y + sin(a) * radius;
            vertex(sx, sy);
        }
        endShape(CLOSE);
    }

    int randomPaletteColor() {
        return palette[(int) random(palette.length)];
    }

    void randomFillAndStroke() {
        randomFill();
        randomStroke();
    }

    void randomFill() {
        int color = randomPaletteColor();
        Config.setFill(color);
        AppletHelpers.setFill(color, this);
    }

    void randomStroke() {
        int color = randomPaletteColor();
        Config.setStroke(color);
        AppletHelpers.setStroke(color, this);
    }


}

/*
color[] palette;
color[][] paletteLibrary;

boolean shouldRedraw = true;

void setup() {
  // Set up the color palettes
  paletteLibrary = new color[5][5];
  // http://colorpalettes.net/color-palette-3609/
  paletteLibrary[0] = new color[]{ #b2b3b2, #eed1e2, #ae84a0, #7f2852, #0a0a18 };
  // http://colorpalettes.net/color-palette-2754/
  paletteLibrary[1] = new color[]{ #6b635b, #a9c695, #b1bb39, #e3e79f, #ededef };
  // http://colorpalettes.net/color-palette-3674/
  paletteLibrary[2] = new color[]{ #222628, #555860, #a2adc2, #e9e8eb, #fdfdff };
  // http://colorpalettes.net/color-palette-3670/
  paletteLibrary[3] = new color[]{ #0c090b, #312b2f, #767f88, #e4e9eb, #5178ba };
  // http://colorpalettes.net/color-palette-3606/
  paletteLibrary[4] = new color[]{ #b4d7d4, #314701, #6d8008, #fbcc38, #f5880d };

  // palette = [];
  size(640, 640);
}

void draw() {
  if (shouldRedraw) {
    drawMandala();
    shouldRedraw = false;
  }
}

void mouseClicked() {
  shouldRedraw = true;
}

void drawMandala() {
  int ringCount = 14;
  palette = paletteLibrary[ int(random(paletteLibrary.length)) ];

  background(56);

  int count = (int) random(10, 20);
  float radius = width/2 - 30;
  float shapeSize = 20;

  for ( int i =0; i < ringCount; i++ ) {
    randomFillAndStroke();
    radius = randomRing(count*(int)random(1,2.9), radius, shapeSize*random(.5, 2));
  }

//  int triangleCount = int(random(10, 20));
//   randomFillAndStroke();
//   //triangleRing(triangleCount, 180, 50, 100);
//   //flameRing(triangleCount, 230, 50, 100);
//   //randomFillAndStroke();
//   //reuleauxRing(triangleCount, 180, 100);
//   randomFillAndStroke();
//   triangleRing(triangleCount, 100, 20, 25);
//   randomFillAndStroke();
//   circleRing(triangleCount*2, 130, 10);
//   randomFillAndStroke();
//   lineRing(triangleCount, 100, 25);
//   randomFillAndStroke();
//   lineRing(triangleCount*2, 100, 25);
//   randomFillAndStroke();
//   emptyRing(100);
//   randomFillAndStroke();
//   emptyRing(140);
//   randomFillAndStroke();
//   rectRing(triangleCount*2, 140, 10, 20);
//   randomFillAndStroke();
//   emptyRing(160);
//   randomFillAndStroke();
//   inwardTriangleRing(triangleCount*2, 160, 20, 25);
//
//
//   emptyRing(185);
//   randomFillAndStroke();

}

// Pick one of the available shapes and draw it
        float randomRing(float count, float radius, float shapeSize) {

        int shape = (int) random(8);

        if (shape == 0) {
        // circle
        circleRing(count, radius, shapeSize);
        return radius - shapeSize*2;
        } else if (shape == 1) {
        // triangle
        triangleRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 2) {
        // inner triangle
        inwardTriangleRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 3) {
        // rect
        rectRing(count, radius, shapeSize, shapeSize);
        } else if (shape == 4 || shape == 7 || shape == 8 || shape == 6) {
        // empty ring
        emptyRing(radius);
        } else if (shape == 5) {
        lineRing(count, radius, shapeSize);
        }

        return radius - shapeSize;
        }

        void circleRing(float count, float ringRadius, float circleRadius) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, ringRadius);
        ellipse(0, 0, circleRadius, circleRadius);
        translate(0, -ringRadius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void rectRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        rect(-baseLen/2, 0, baseLen, sideLen);
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void inwardTriangleRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        pushMatrix();
        rotate(PI);
        translate(0, -sideLen);
        isoTriangle(baseLen, sideLen);
        popMatrix();
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void triangleRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        isoTriangle(baseLen, sideLen);
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void reuleauxRing(float count, float radius, float baseLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        reuleaux(baseLen);
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void flameRing(float count, float radius, float baseLen, float sideLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        flame(baseLen, sideLen);
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

        void emptyRing(float radius) {
        pushMatrix();
        noFill();
        translate(width*0.5, height*0.5);
        arc(0, 0, radius*2, radius*2, 0, TWO_PI);
        popMatrix();
        }

        void lineRing(float count, float radius, float lineLen) {
        pushMatrix();
        translate(width*0.5, height*0.5);

        for (int i=0; i < count; i++ ) {
        translate(0, radius);
        line(0, 0, 0, lineLen);
        translate(0, -radius);
        rotate(TWO_PI / count);
        }

        popMatrix();
        }

// Draw a Reuleaux triangle
        void reuleaux(float radius) {
        //change this to suit PI/6 to point shape downwards, 0 to point left and so on
        float someRotation = PI / 6;

        float innerRadius = radius * sqrt(3) / 6;
        translate(0, innerRadius);
        for (int n = 0; n < 3; n++)
        {
        pushMatrix();
        //using trig, we know that the x-displacement is
        //length of displacement * cos of the angle of the displacement
        float xdisplacement = innerRadius * cos(n * TWO_PI / 3 + someRotation);
        //similarly y-displacement is
        //length of displacement * sin of the angle of the displacement
        float ydisplacement = innerRadius * sin(n * TWO_PI / 3 + someRotation);

        //so, to centre the shape, the translation values are
        float xpos = - xdisplacement;
        float ypos = - ydisplacement;
        translate(xpos, ypos);
        rotate(n * TWO_PI / 3 + someRotation);
        //for sanity's sake, draw arc from 30deg above to 30deg below horizontal
        arc(0, 0, radius, radius, -PI/6, PI/6);
        popMatrix();
        }
        translate(0, -innerRadius);
        }

// Triangle with both sides curved
        void flame(float baseLen, float sideLen) {
        beginShape();
        float sx = baseLen / 2;
        float sy = 0;
        vertex(sx, sy);

        sx = -baseLen / 2;
        sy = 0;
        vertex(sx, sy);
        curveVertex(sx, sy);
        curveVertex(sx, sy);

        sx = 0;
        sy = sqrt( pow(sideLen, 2) - pow(0.5*baseLen, 2));

        curveVertex(sx+sx/3, sideLen/3);

        sx = 0;
        sy = sqrt( pow(sideLen, 2) - pow(0.5*baseLen, 2));
        // sidelen^2 = x^2 + (.5*baseLen)^2
        curveVertex(sx, sy);
        curveVertex(sx, sy);
        vertex(sx, sy);

        curveVertex(sx, sy);
        curveVertex(sx, sy);

        sx = baseLen / 2;
        sy = 0;
        curveVertex(sx+sx/3, sideLen/3);

        curveVertex(sx, sy);
        curveVertex(sx, sy);

        endShape(CLOSE);
        }

        void isoTriangle(float baseLen, float sideLen) {
        beginShape();
        float sx = baseLen / 2;
        float sy = 0;
        vertex(sx, sy);

        sx = -baseLen / 2;
        sy = 0;
        vertex(sx, sy);

        sx = 0;
        sy = sqrt( pow(sideLen, 2) - pow(0.5*baseLen, 2));
        // sidelen^2 = x^2 + (.5*baseLen)^2
        vertex(sx, sy);

        endShape(CLOSE);
        }

        void polygon(float x, float y, float radius, int npoints) {
        float angle = TWO_PI / npoints;
        beginShape();
        for (float a = 0; a < TWO_PI; a += angle) {
        float sx = x + cos(a) * radius;
        float sy = y + sin(a) * radius;
        vertex(sx, sy);
        }
        endShape(CLOSE);
        }


        void randomFillAndStroke() {
        randomFill();
        randomStroke();
        }

        void randomFill() {
        fill( randomPaletteColor() );
        }

        void randomStroke() {
        stroke(randomPaletteColor());
        }

        color randomPaletteColor() {
        return palette[int(random(palette.length))];
        }
 */