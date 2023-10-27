import java.util.Scanner;

class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }
}

class ComparableCircle extends Circle implements Comparable<ComparableCircle> {
    public ComparableCircle(double radius) {
        super(radius);
    }

    public int compareTo(ComparableCircle other) {
        double thisArea = this.getArea();
        double otherArea = other.getArea();

        if (thisArea < otherArea) {
            return -1;
        }
        else {
            return 0;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double radius1 = scanner.nextDouble();
        double radius2 = scanner.nextDouble();

        ComparableCircle circle1 = new ComparableCircle(radius1);
        ComparableCircle circle2 = new ComparableCircle(radius2);

        int result = circle1.compareTo(circle2);
        if (result < 0) {
            System.out.printf("The max circle's area is %.2f", circle2.getArea());
        } else{
            System.out.printf("The max circle's area is %.2f", circle1.getArea());
        }
    }
}
