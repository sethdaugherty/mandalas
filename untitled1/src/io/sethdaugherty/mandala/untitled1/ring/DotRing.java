package io.sethdaugherty.mandala.untitled1.ring;

        import io.sethdaugherty.mandala.untitled1.shape.NullShape;
        import processing.core.PApplet;

public class DotRing implements MandalaRing<NullShape, PApplet> {

    private final float ringRadius;
    private final float circleRadius;
    private final int count;

    public DotRing(float ringRadius, float circleRadius, int count) {
        this.ringRadius = ringRadius;
        this.circleRadius = circleRadius;
        this.count = count;
    }

    @Override
    public void draw(PApplet p) {
        p.pushMatrix();
        p.translate(p.width*0.5f, p.height*0.5f);

        p.strokeWeight(0);
        //p.ellipse(0, 0, 2*circleRadius, 2*circleRadius);
        p.strokeWeight(1);
        for (int i=0; i < count; i++ ) {
            p.translate(0, ringRadius*.95f);
            p.ellipse(0, 0, circleRadius, circleRadius);
            p.translate(0, -ringRadius*.95f);
            p.rotate(p.TWO_PI / count);
        }

        p.popMatrix();
    }
}
