/**
Heavily adapted from Electric Sphere by David Crooks
https://www.openprocessing.org/sketch/389855
*/

PVector[] pathPoints;

int lineCount = 0;
PImage img;

int focalX;
int focalY;
float lengthDeviation;
float fineLengthDeviation;

PGraphics pg;

void setup() {
  // size: 1258 by 1500
  img = loadImage("rembrandt_large.jpg");
  pg = createGraphics(1258, 1500);

  size(629, 750, P2D);
  //size(1258, 1500, P2D);
  noSmooth();
  stroke(0, 15);
  strokeWeight(1);
  strokeJoin(ROUND);
  background(0);

  pathPoints = new PVector[pg.width];
  focalX = -1;
  focalY = -1;
  lengthDeviation = pg.width / 5;
  fineLengthDeviation = pg.width / 10;
}

void draw() {
  pg.beginDraw();
  
  pathPoints = imgPoints();
  for(int j=0;j<6;j++){
     pathPoints = complexifyPath(pathPoints);
  }

  int strokeAlpha = determineStrokeAlpha(pathPoints);
  int strokeColor = determineStrokeColor(pathPoints);
  
  pg.stroke(strokeColor, strokeAlpha);
  pg.noFill();
  pg.beginShape();
  for(int i=0;i<pathPoints.length;i++){
    PVector v = pathPoints[i];
    pg.vertex(v.x, v.y);
  }
  pg.endShape();
  
  if (lineCount % 10 == 0) {
    stroke(strokeColor, strokeAlpha);
    noFill();
    beginShape();
    for(int i=0;i<pathPoints.length;i++){
      PVector v = pathPoints[i];
      vertex(v.x/2, v.y/2);
    }
    endShape();
  }

  lineCount = lineCount + 1;
  
  if (lineCount % 1000 == 0) {
    println("Line count", lineCount);
    pg.save("renders/render_" + lineCount + ".jpg");
  }
  
  pg.endDraw();
}

void mouseClicked() {
  if (focalX == mouseX*2) {
    fineLengthDeviation = fineLengthDeviation / 2;
  }
  else {
    fineLengthDeviation = pg.width/10; 
  }
  focalX = mouseX * 2;
  focalY = mouseY * 2;
}

void keyPressed() {
  // Clear the focus on a key press
  focalX = -1;
  focalY = -1;
}

int determineStrokeColor(PVector[] pathPoints) {
  PVector start = pathPoints[0];
  PVector end = pathPoints[pathPoints.length-1];
  
  color startColor = img.get((int)start.x, (int)start.y);
  color endColor = img.get((int)end.x, (int)end.y);
  
  int red = (int)((red(startColor) + red(endColor)) / 2);
  int green = (int)((green(startColor) + green(endColor)) / 2);
  int blue = (int)((blue(startColor) + blue(endColor)) / 2);

  return color(red, green, blue);
}

int determineStrokeAlpha(PVector[] pathPoints) {
  return 255;
}

PVector[] complexifyPath(PVector[] pathPoints){
  //create a new path array from the old one by adding new points inbetween the old points
  PVector[] newPath = new PVector[0];
  
  for(int i=0;i<pathPoints.length -1;i++){
    PVector v1 = pathPoints[i];
    PVector v2 = pathPoints[i+1];
    PVector midPoint = v1.add(v2).mult(0.5);
    float distance =  v1.dist(v2);
    
    //the new point is halfway between the old points, with some gaussian variation
    float standardDeviation = 0.125*distance;
    PVector v = new PVector(midPoint.x + (randomGaussian() * standardDeviation), midPoint.y + (randomGaussian() * standardDeviation));
    newPath = (PVector[]) append(newPath,v1);
    newPath = (PVector[]) append(newPath,v);
  }
  
  //don't forget the last point!
  newPath = (PVector[])append(newPath, pathPoints[pathPoints.length-1]);
  return newPath;  
}

/**
 * Determine the starting points for the line by picking two random points
 */
PVector[] imgPoints() {
  PVector[] points = new PVector[2];
  
  float x1; 
  float y1;
  float x2;
  float y2;
  if (focalX > -1) {
    x1 = focalX + randomGaussian()*50;
    y1 = focalY + randomGaussian()*50;
    x2 = x1 + randomGaussian() * fineLengthDeviation;
    y2 = y1 + randomGaussian() * fineLengthDeviation;
  }
  else {
    x1 = random(pg.width);
    y1 = random(pg.height);
    x2 = x1 + randomGaussian() * lengthDeviation;
    y2 = y1 + randomGaussian() * lengthDeviation;
  }
  points[0] = new PVector(x1, y1);
  points[1] = new PVector(x2, y2);
  return points;
}