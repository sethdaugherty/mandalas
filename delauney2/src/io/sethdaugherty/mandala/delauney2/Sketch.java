package io.sethdaugherty.mandala.delauney2;

import io.github.jdiemke.triangulation.DelaunayTriangulator;
import io.github.jdiemke.triangulation.NotEnoughPointsException;
import io.github.jdiemke.triangulation.Triangle2D;
import io.github.jdiemke.triangulation.Vector2D;
import processing.core.PApplet;

import java.util.*;

public class Sketch extends PApplet {

    private static final int NEW_POINT_FRAME_COUNT = 3;
    private static final Integer MAX_TRIANGLE_AGE = 50;
    private static final float STROKE_WIDTH = 80;
    private static final float MAX_EDGE_LENGTH = 150;

    DelaunayTriangulator triangulator;
    List<Vector2D> pointSet = new ArrayList<>();
    Map<Vector2D, Integer> pointAges = new HashMap<>();
    List<Integer> triangleAges = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main("io.sethdaugherty.mandala.delauney2.Sketch", args);
    }

    @Override
    public void settings() {
        size(1000,  1000);
//        pointSet.add(new Vector2D(0, 0));
//        pointSet.add(new Vector2D(100, 0));
//        pointSet.add(new Vector2D(100, 100));
//        pointSet.add(new Vector2D(30, 60));
//        pointSet.add(new Vector2D(300, 100));
        triangulator = new DelaunayTriangulator(pointSet);
    }

    @Override
    public void setup() {
        colorMode(HSB, 360, 100, 100);

        background(0);
        noFill();
        stroke(255, 100, 100);
        strokeWeight(1);
    }

    @Override
    public void draw() {
        background(0);

        for (int i = 0; i < triangulator.getTriangles().size(); i++) {

            Triangle2D triangle = triangulator.getTriangles().get(i);
            Vector2D a = triangle.a;
            Vector2D b = triangle.b;
            Vector2D c = triangle.c;

            if (pointAges.containsKey(a) && pointAges.containsKey(b) && pointAges.containsKey(c)) {
                if (pointAges.get(a) < MAX_TRIANGLE_AGE && pointAges.get(b) < MAX_TRIANGLE_AGE && pointAges.get(c) < MAX_TRIANGLE_AGE) {
                    if (dist(a.x, a.y, b.x, b.y) < MAX_EDGE_LENGTH && dist(a.x, a.y, c.x, c.y) < MAX_EDGE_LENGTH && dist(c.x, c.y, b.x, b.y) < MAX_EDGE_LENGTH) {

                        noStroke();
                        fill(194, 80 + random(-10f, 10f), map(500 - pointAges.get(a) * 10, 0, 500, 30, 100));

                        triangle((float) a.x, (float) a.y, (float) b.x, (float) b.y, (float) c.x, (float) c.y);
                    }
                }
            }
        }

        Iterator<Vector2D> iter = pointSet.iterator();

        while (iter.hasNext()) {
            Vector2D point = iter.next();

            Integer age = pointAges.get(point);
            if (age < MAX_TRIANGLE_AGE) {
                pointAges.put(point, age + 1);
            }
            else {
                iter.remove();
                pointAges.remove(point);
            }

        }
    }

    private float dist(double x1, double y1, double x2, double y2) {
        return dist( (float) x1, (float) y1, (float) x2, (float) y2);
    }

    @Override
    public void mouseClicked() {
        mouseDragged();
    }

    @Override
    public void mouseDragged() {
        if (frameCount % NEW_POINT_FRAME_COUNT == 0) {
            // add the mouse point
            Vector2D newPoint = new Vector2D(mouseX, mouseY);
            pointSet.add(newPoint);
            pointAges.put(newPoint, 0);

            // but also add more points to make the stroke wider
            newPoint = new Vector2D(mouseX + random(0, STROKE_WIDTH), mouseY + random(0, STROKE_WIDTH));
            pointSet.add(newPoint);
            pointAges.put(newPoint, 0);

            newPoint = new Vector2D(mouseX + random(0, STROKE_WIDTH), mouseY - random(0, STROKE_WIDTH));
            pointSet.add(newPoint);
            pointAges.put(newPoint, 0);

            newPoint = new Vector2D(mouseX - random(0, STROKE_WIDTH), mouseY + random(0, STROKE_WIDTH));
            pointSet.add(newPoint);
            pointAges.put(newPoint, 0);

            newPoint = new Vector2D(mouseX - random(0, STROKE_WIDTH), mouseY - random(0, STROKE_WIDTH));
            pointSet.add(newPoint);
            pointAges.put(newPoint, 0);

            try {
                triangulator.triangulate();
            } catch (NotEnoughPointsException e1) {
            }
        }
    }
}
