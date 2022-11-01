public class Zipper {

    public static void main(String args[]) {

    Zip circ = new Zip("OOOO");
    Zip line = new Zip("---------");

    circ.start();
    line.start();
    }

}


class Zip extends Thread{

    String tooth;

    public Zip (String tooth) {
	this.tooth = tooth;
    }

    public void run() {
	    while (true) {
		System.out.println(tooth);
	    }
    }
}
