import java.awt.Color;
import java.awt.Graphics;

public class Omena{
	private int xKoord, yKoord, leveys, korkeus;
	
	public Omena(int xKoord, int yKoord, int tileSize){
		this.xKoord = xKoord;
		this.yKoord = yKoord;
		leveys = tileSize;
		korkeus = tileSize;
	}
	
	public void tick(){
		
	}
	
	public void draw(Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(xKoord * leveys, yKoord * korkeus, leveys, korkeus);
	} 
	
	public int annaxKoord(){
		return xKoord;
	}
	
	public void asetaxKoord(int xKoord){
		this.xKoord = xKoord;
	} 
	
	public int annayKoord(){
		return yKoord;
	}
	
	public void asetayKoord(int yKoord){
		this.yKoord = yKoord;
	}
} 