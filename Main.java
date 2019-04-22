import javax.swing.JFrame;

public class Main{

    public Main(){

        JFrame frame = new JFrame();
        Pelikentta pelikentta = new Pelikentta();

        frame.add(pelikentta);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("MATOPELI");
        frame.setLocationRelativeTo(null);

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }
}
