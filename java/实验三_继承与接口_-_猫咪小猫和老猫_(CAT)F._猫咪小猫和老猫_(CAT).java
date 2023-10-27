abstract class Cat {
    public String name;

    public Cat(String name) {
        this.name = name;
    }

    public abstract void move();

    public abstract void sound();

    public abstract void show();
}

class AdultCat extends Cat {
    public AdultCat(String name) {
        super(name);
    }

    public void move() {
        System.out.println("I can move quickly.");
    }

    public void sound() {
        System.out.println("mie mie!");
    }
    public void show() {
        System.out.println("I am " + name + ",I am a cat!");
    }
}

class YoungCat extends Cat {
    public YoungCat(String name) {
        super(name);
    }

    public void move() {
        System.out.println("I like jumping.");
    }

    public void sound() {
        System.out.println("mie mie!");
    }
    public  void show() {
        System.out.println("I am " + name + ",I am a young cat!");
    }
}

class OldCat extends Cat {
    public OldCat(String name) {
        super(name);
    }

    public void move() {
        System.out.println("I move slowly.");
    }

    public void sound() {
        System.out.println("miem miem!");
    }
    public  void show() {
        System.out.println("I am " + name + ",I am a old cat!");
    }
}

public class Main {
    public static void main(String args[]) {
        Cat[] catfamily = new Cat[3];

        catfamily[0] = new OldCat("HuangLao");
        catfamily[1] = new AdultCat("LaoMei");
        catfamily[2] = new YoungCat("XiaoLi");

        for (int i = 0; i < 3; i++) {
            catfamily[i].show();
            catfamily[i].move();
            catfamily[i].sound();

            System.out.println();
        }
    }
}
