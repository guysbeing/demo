import java.text.DecimalFormat;
import java.util.Scanner;

interface Comparable {
    int compareTo(Object o);
}

abstract class GeometricObject implements Comparable {
    public static Comparable max(Comparable o1, Comparable o2) {
        if (o1.compareTo(o2) > 0) {
            return o1;
        } else {
            return o2;
        }
    }

    abstract double getArea();
}

class Rectangle extends GeometricObject {
    private String name;
    private double length;
    private double width;

    public Rectangle(String name, double length, double width) {
        this.name = name;
        this.length = length;
        this.width = width;
    }

    public double getArea() {
        return length * width;
    }

    public int compareTo(Object o) {
        Rectangle other = (Rectangle) o;
        if (this.getArea() > other.getArea()) {
            return 1;
        } else if (this.getArea() < other.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return name + " " + df.format(length) + " " + df.format(width) + " " + df.format(getArea());
    }
}

class Circle extends GeometricObject {
    private String name;
    private double radius;

    public Circle(String name, double radius) {
        this.name = name;
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public int compareTo(Object o) {
        Circle other = (Circle) o;
        if (this.getArea() > other.getArea()) {
            return 1;
        } else if (this.getArea() < other.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");
        return name + " " + df.format(radius) + " " + df.format(getArea());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String name1 = scanner.next();
        double length1 = scanner.nextDouble();
        double width1 = scanner.nextDouble();
        String name2 = scanner.next();
        double length2 = scanner.nextDouble();
        double width2 = scanner.nextDouble();
        String name3 = scanner.next();
        double radius1 = scanner.nextDouble();
        String name4 = scanner.next();
        double radius2 = scanner.nextDouble();

        Rectangle rectangle1 = new Rectangle(name1, length1, width1);
        Rectangle rectangle2 = new Rectangle(name2, length2, width2);
        Circle circle1 = new Circle(name3, radius1);
        Circle circle2 = new Circle(name4, radius2);

        Rectangle maxRectangle = (Rectangle) GeometricObject.max(rectangle1, rectangle2);
        Circle maxCircle = (Circle) GeometricObject.max(circle1, circle2);
        System.out.println(maxRectangle);
        System.out.println(maxCircle);
    }
}
