import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;


public class Pelikentta extends JPanel implements Runnable, KeyListener{
	private static final long serialVersionUID = 1L;
	public static final int LEVEYS = 500, KORKEUS = 500;
	private Thread thread;
	private boolean running;
	private boolean oikea = true, vasen = false, ylos = false, alas = false;
	private Mato b;
	private ArrayList<Mato> mato;
	private Omena omena;
	private ArrayList<Omena> omenat; 
	private Random r;
	private int xKoord = 10, yKoord = 10, koko = 15;
	private int ticks = 0;

	public Pelikentta(){
		setFocusable(true);
		
		setPreferredSize(new Dimension(LEVEYS, KORKEUS));
		addKeyListener(this);
		
		mato = new ArrayList<Mato>();
		omenat = new ArrayList<Omena>();
		
		r = new Random();
		
		start();
	}
	
	public void start(){
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void stop(){
		running = false;
		try {
			thread.join();
		} 
		catch (InterruptedException e) {
			e.printStackTrace();
			}
	}
	
	public void tick(){
		if(mato.size() ==0){
			b = new Mato (xKoord, yKoord, 10);
			mato.add(b);
			}
		ticks++;
		if(ticks > 150000){
			if(oikea) xKoord++;
			if(vasen) xKoord--;
			if(ylos) yKoord--;
			if(alas) yKoord++;
			
			ticks = 0;
			
			b = new Mato(xKoord, yKoord, 10);
			mato.add(b);
			
			if(mato.size() > koko){
				mato.remove(0);
				}
			}
		if(omenat.size() ==0){
			int xKoord = r.nextInt(49);
			int yKoord = r.nextInt(49);
			omena = new Omena(xKoord, yKoord, 10);
			omenat.add(omena);
			}
		
		//Syominen ja madon kasvatus
		for(int i = 0 ; i < omenat.size() ; i++){
			if(xKoord == omenat.get(i).annaxKoord() && yKoord == omenat.get(i).annayKoord()){
				koko++;
				omenat.remove(i);
				i++;
				}
			}
		
		//Tormays matoon
		for(int i = 0 ; i < mato.size(); i++){
			if(xKoord == mato.get(i).annaxKoord() && yKoord == mato.get(i).annayKoord()){
				if(i !=mato.size()- 1){
					System.out.println();
					stop();
					}
				}
			} 
	
		//Tormays seinaan
		if(xKoord < 0 || xKoord > 49 || yKoord < 0 || yKoord > 49){
		System.out.println("Requiescat in pace Kari Kastemato");
		stop();
		}
	
}
	
	public void paint(Graphics g){
		g.clearRect(0, 0, LEVEYS, KORKEUS);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, LEVEYS, KORKEUS);
		
		for(int i = 0; i < LEVEYS/10 ; i++){
			g.drawLine(i * 10, 0, i *10, KORKEUS);
		}
		
		for(int i = 0; i < KORKEUS/10 ; i++){
			g.drawLine(0, i * 10 , KORKEUS, i * 10);
		}
		
		for(int i = 0 ; i <mato.size(); i++){
			mato.get(i).draw(g);
		}
		
		for(int i = 0 ; i < omenat.size(); i++){
			omenat.get(i).draw(g);
		}
		
	}
		
		
		public void run(){
			while(running){
				tick();
				repaint();
			}
		}
		
		public void keyPressed(KeyEvent e){
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_RIGHT && !vasen){
				oikea = true;
				ylos = false;
				alas = false;
			}
			
			if(key == KeyEvent.VK_LEFT && !oikea){
				vasen = true;
				ylos = false;
				alas = false;
			}
			
			if(key == KeyEvent.VK_UP && !alas){
				ylos = true;
				vasen = false;
				oikea = false;
			}
			
			if(key == KeyEvent.VK_DOWN && !ylos){
				alas = true;
				vasen = false;
				oikea = false;
			}
			
		}
		
	
		public void keyReleased(KeyEvent arg0){
			
		}
		
		public void keyTyped(KeyEvent arg0) {
			
		}
}
