import java.util.Scanner;

abstract class Geo{
    public abstract  double computeArea();
}
class Rectangle extends Geo{
    int length,width;
    public Rectangle(int length,int width){
        this.width=width;
        this.length=length;
    }
    public double computeArea(){
        return this.length*this.width;
    }
}
class Circle extends  Geo{
    int radius;
    public Circle(int radius){
        this.radius=radius;
    }
    public  double computeArea(){
        return Math.PI*this.radius*this.radius;
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Geo[] objects = new Geo[4];
        objects[0] = new Rectangle(scanner.nextInt(), scanner.nextInt());
        objects[1] = new Rectangle(scanner.nextInt(), scanner.nextInt());
        objects[2] = new Circle(scanner.nextInt());
        objects[3] = new Circle(scanner.nextInt());

        double totalArea = sumArea(objects);
        System.out.printf("%.2f" ,totalArea);
        }

        public static double sumArea(Geo[] objects) {
            double sum = 0;
            for (Geo obj : objects) {
                sum += obj.computeArea();
            }
            return sum;
        }
}
