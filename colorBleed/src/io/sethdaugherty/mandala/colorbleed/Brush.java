package io.sethdaugherty.mandala.colorbleed;

import processing.core.PApplet;
import processing.core.PGraphics;

public class Brush {

    private float angle;
    private float x;
    private float y;
    private int color;
    private int[] components;
    private float radius;

    public Brush(PApplet p, int color) {
        this.angle = p.random(p.TWO_PI);
        this.x = p.random(p.width);
        this.y = p.random(p.height);
        this.color = color;
        this.components = new int[2];
        for (int i =0; i< 2; i++ ) {
            this.components[i] = (int)p.random(1, 5);
        }

        this.radius = p.random(30);
    }


    public void paint(PApplet p, PGraphics g) {

        SketchHelpers.setFill(this.color, g, 150);
        SketchHelpers.setStroke(SketchHelpers.darkenColor(this.color), g, 160);
        g.strokeWeight(1);

        x = x + p.random(-30f, 30f);
        x = p.max(0, x);
        x = p.min(x, p.width);
        y = y + p.random( -30f, 30f);
        y = p.max(0, y);
        y = p.min(y, p.height);

        radius = radius + p.random(-2f, 2f);
        radius = p.max(radius, 0);
        radius = p.min(radius, 22);
        g.ellipse(x, y, radius, radius);

        /*
        float a = 0;
        float r = 0;
        float x1 = x;
        float y1 = y;
        float u = p.random(0.5f, 1f);

        p.beginShape();
        while (a < p.TWO_PI) {
            p.vertex(x1, y1);
            float v = p.random(0.85f, 1f);
            x1 = x + r * p.cos(angle + a) * u * v;
            y1 = y + r * p.sin(angle + a) * u * v;
            a += p.PI / 180;
            for (int i = 0; i < 2; i++) {
                r += p.sin(a * components[i]);
            }
        }
        p.endShape(p.CLOSE);

        if (x < 0 || x > p.width ||y < 0 || y > p.height) {
            angle += p.HALF_PI;
        }

        x += 2 * p.cos(angle);
        y += 2 * p.sin(angle);
        angle += p.random(-0.15f, 0.15f);
        */
    }
}

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

