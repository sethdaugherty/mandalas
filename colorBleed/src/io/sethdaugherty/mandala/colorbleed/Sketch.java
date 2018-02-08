package io.sethdaugherty.mandala.colorbleed;

import io.sethdaugherty.mandala.colorbleed.SketchHelpers;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

public class Sketch extends PApplet{

    private int[] palette;
    private int[][] paletteLibrary;
    private List<Brush> brushes;

    private PGraphics mask;
    private PGraphics surface;

    public static void main(String[] args) {
        PApplet.main("io.sethdaugherty.mandala.colorbleed.Sketch", args);
    }

    @Override
    public void settings() {
        size(1000, 1000);

    }

    @Override
    public void setup() {
        surface = createGraphics(width, height);
        surface.beginDraw();
        surface.background(255);
        surface.endDraw();
        background(255);

        paletteLibrary = new int[1][5];
        // http://colorpalettes.net/color-palette-3609/
        paletteLibrary[0] = new int[]{SketchHelpers.color("#d38a58"), SketchHelpers.color("#eae6e2"), SketchHelpers.color("#a83252"), SketchHelpers.color("#e28b80"), SketchHelpers.color("#dd5e58")};
        // http://colorpalettes.net/color-palette-2754/
        //paletteLibrary[1] = new int[]{SketchHelpers.color("#6b635b"), SketchHelpers.color("#a9c695"), SketchHelpers.color("#b1bb39"), SketchHelpers.color("#e3e79f"), SketchHelpers.color("#ededef")};
        // http://colorpalettes.net/color-palette-3674/
        //paletteLibrary[2] = new int[]{SketchHelpers.color("#222628"), SketchHelpers.color("#555860"), SketchHelpers.color("#a2adc2"), SketchHelpers.color("#e9e8eb"), SketchHelpers.color("#fdfdff")};
        // http://colorpalettes.net/color-palette-3670/
        //paletteLibrary[3] = new int[]{SketchHelpers.color("#0c090b"), SketchHelpers.color("#312b2f"), SketchHelpers.color("#767f88"), SketchHelpers.color("#e4e9eb"), SketchHelpers.color("#5178ba")};
        // http://colorpalettes.net/color-palette-3606/
        //paletteLibrary[4] = new int[]{SketchHelpers.color("#b4d7d4"), SketchHelpers.color("#314701"), SketchHelpers.color("#6d8008"), SketchHelpers.color("#fbcc38"), SketchHelpers.color("#f5880d")};
        palette = paletteLibrary[(int) (random(paletteLibrary.length))];

        brushes = new ArrayList<>();
        for (int x=0; x < 5; x++) {
            brushes.add(new Brush(this, randomPaletteColor()));
        }
        brushes.add(new Brush(this, 16777215));
    }

    @Override
    public void draw() {
        surface.beginDraw();
        //fill(80);
        //noStroke();
        //randomFill();
        //ellipse(width / 2, width / 2, width-50, width-50);
        for (Brush brush : brushes) {
            brush.paint(this, surface);
        }
        surface.endDraw();

        mask = createGraphics(width, height);
        mask.beginDraw();
        mask.ellipse(width/2f, height/2f, width, height);
        mask.endDraw();

        surface.mask(mask.get());
        image(surface, 0, 0);
    }

    @Override
    public void mouseClicked() {
        setup();
    }

    int randomPaletteColor() {
        return palette[(int) random(palette.length)];
    }

    /**
     *
     */
//
//    // Watercolor
//// Levente Sandor, 2013
//
//    ArrayList<Brush> brushes;
//
//    void setup() {
//        size(500, 500);
//        background(255);
//        brushes = new ArrayList<Brush>();
//        for (int i = 0; i < 50; i++) {
//            brushes.add(new Brush());
//        }
//    }
//
//    void draw() {
//        for (Brush brush : brushes) {
//            brush.paint();
//        }
//    }
//
//    void mouseClicked() {
//        setup();
//    }
//
//    class Brush {
//        float angle;
//        int components[];
//        float x, y;
//        color clr;
//
//        Brush() {
//            angle = random(TWO_PI);
//            x = random(width);
//            y = random(height);
//            clr = color(random(255), random(255), random(255), 5);
//            components = new int[2];
//            for (int i = 0; i < 2; i++) {
//                components[i] = int(random(1, 5));
//            }
//        }
//
//        void paint() {
//            float a = 0;
//            float r = 0;
//            float x1 = x;
//            float y1 = y;
//            float u = random(0.5, 1);
//
//            fill(clr);
//            noStroke();
//
//            beginShape();
//            while (a < TWO_PI) {
//                vertex(x1, y1);
//                float v = random(0.85, 1);
//                x1 = x + r * cos(angle + a) * u * v;
//                y1 = y + r * sin(angle + a) * u * v;
//                a += PI / 180;
//                for (int i = 0; i < 2; i++) {
//                    r += sin(a * components[i]);
//                }
//            }
//            endShape(CLOSE);
//
//            if (x < 0 || x > width ||y < 0 || y > height) {
//                angle += HALF_PI;
//            }
//
//            x += 2 * cos(angle);
//            y += 2 * sin(angle);
//            angle += random(-0.15, 0.15);
//        }
//    }
}
