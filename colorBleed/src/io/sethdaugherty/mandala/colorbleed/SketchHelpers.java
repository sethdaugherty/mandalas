package io.sethdaugherty.mandala.colorbleed;

import processing.core.PApplet;
import processing.core.PGraphics;

public class SketchHelpers {
    public static void setFill(int color, PGraphics applet, float alpha) {
        // these two are equivalent, but the second is faster
        // fill( red(color), green(color), blue(color) );
        applet.fill( color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, alpha);
    }

    public static void setStroke(int color, PGraphics applet, float alpha) {
        // these two are equivalent, but the second is faster
        // stroke( red(color), green(color), blue(color) );
        applet.stroke( color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF, alpha);
    }

    public static int color(String hex) {
        return Integer.decode(hex);
    }

    public static int darkenColor(int color) {

        return color - 1118481;
    }
}

