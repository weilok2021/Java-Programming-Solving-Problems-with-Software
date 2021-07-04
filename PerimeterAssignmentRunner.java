import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        //Put code here
        int count = 0;
        for (Point currPt : s.getPoints()) {    
            count = count + 1;
        }
        return count;
    }
    public double getAverageLength(Shape s) {
        // Put code here
        int numPoints = getNumPoints(s);
        double perimeter = getPerimeter(s);
        double average = perimeter / numPoints;
        return average;
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prevPt = s.getLastPoint();
        double largestSide = 0;
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            if (currDist > largestSide) {
                largestSide = currDist;
            }
            // update the previous point to current point
            prevPt = currPt;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = 0;
        for (Point currPt : s.getPoints()) {
            // for each point's x coordinate
            if (currPt.getX() > largestX) {
                largestX = currPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPeri = 0;
        // for each file selected by dr
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            // convert points in each file to a shape.
            Shape s = new Shape(fr);
            // for each shape in each file, find the largest perimeter over them
            double length = getPerimeter(s);
            if (length > largestPeri){
                largestPeri = length;
            }
        }   
        return largestPeri;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largest = getLargestPerimeterMultipleFiles();
        
        for (File f : dr.selectedFiles()) {
            // file resource is a class represents file, and allows access to its content a line at a time.
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            if (getPerimeter(s) == largest) {
                // replace File temp variable with the file storing the shape with largest perimeter
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int numPoints = getNumPoints(s);
        double average = getAverageLength(s);
        double longestSide = getLargestSide(s);
        double largestX = getLargestX(s);
        System.out.println("perimeter = " + length);
        System.out.println("number of points = " + numPoints);
        System.out.println("Averqage length = " + average );
        System.out.println("Longest side = " + longestSide );
        System.out.println("Largest X = " + largestX);
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPeri = getLargestPerimeterMultipleFiles();
        System.out.println("Largest peri = " + largestPeri);
        
        //DirectoryResource dr = new DirectoryResource();
        // user can select multiple files
        // for (File f : dr.selectedFiles()) {
        //     FileResource fr = new FileResource(f);
        //     System.out.println(f);
        //    }
}

    public void testFileWithLargestPerimeter() {
        // Put code here
        String fileName = getFileWithLargestPerimeter();
        System.out.println("largest perimeter file = " + fileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+ peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
