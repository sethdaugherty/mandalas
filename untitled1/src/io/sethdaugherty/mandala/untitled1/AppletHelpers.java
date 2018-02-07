package io.sethdaugherty.mandala.untitled1;

import processing.core.PApplet;

public class AppletHelpers {
    public static void setFill(int color, PApplet applet) {
        // these two are equivalent, but the second is faster
        // fill( red(color), green(color), blue(color) );
        applet.fill( color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF);
    }

    public static void setStroke(int color, PApplet applet) {
        // these two are equivalent, but the second is faster
        // stroke( red(color), green(color), blue(color) );
        applet.stroke( color >> 16 & 0xFF, color >> 8 & 0xFF, color & 0xFF);
    }

    public static int color(String hex) {
        return Integer.decode(hex);
    }
}
