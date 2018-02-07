package io.sethdaugherty.mandala.untitled1;

// TODO: using this global static config is garbage, but it works for now.
public class Config {
    private static int stroke = 255;
    private static int fill = 0;

    public static int getStroke() {
        return stroke;
    }

    public static int getFill() {
        return fill;
    }

    public static void setStroke(int s) {
        stroke = s;
    }

    public static void setFill(int f) {
        fill = f;
    }
}
