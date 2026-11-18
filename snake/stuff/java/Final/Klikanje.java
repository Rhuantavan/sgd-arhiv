import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Klikanje
{
	public static int a, b, c, stranica, premer;
	
	public static void main(String[] args)
	{
		boolean invalid = true;
		
		while (invalid)
		{
			System.out.println("Vnesi stranico kvadrata: ");
			stranica = BranjePodatkov.preberiInt();
			System.out.println("Vnesi preber kroga: ");
			premer = BranjePodatkov.preberiInt();
			
			if ( (premer < stranica || stranica * Math.sqrt(2) < premer) && premer > 0 && stranica > 0)
				invalid = false;
		}
		
		System.out.println("Vnesi a: ");
		a = BranjePodatkov.preberiInt();
		System.out.println("Vnesi b: ");
		b = BranjePodatkov.preberiInt();
		System.out.println("Vnesi c: ");
		c = BranjePodatkov.preberiInt();
		
		Okno okno = new Okno();
		okno.setVisible(true);
	}
}

class Okno extends JFrame
{
	public Okno()
	{
		Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)a.getWidth() / 2, (int)a.getHeight() / 2);
		this.setLocation((int)a.getWidth() / 4, (int)a.getHeight() / 4);
		this.setTitle("Povecaj in pomanjsaj");
		
		this.addWindowListener(new OknoListener());
		this.getContentPane().add(new MainPanel(), BorderLayout.CENTER);
	}	
}

class OknoListener extends WindowAdapter
{	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
}

class MainPanel extends JPanel implements MouseListener
{	
	private int a, b, c, stranica, premer;
	public MainPanel()
	{
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)d.getWidth() / 2, (int)d.getHeight() / 2);
		
		this.addMouseListener(this);
		
		this.stranica = Klikanje.stranica;
		this.premer = Klikanje.premer;
		this.a = Klikanje.a;
		this.b = Klikanje.b;
		this.c = Klikanje.c;
		
		if (stranica > premer)
			notranjiKrog = true;
		else if (stranica * Math.sqrt(2) < premer)
			notranjiKrog = false;

	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		int width = this.getWidth();
		int height = this.getHeight();
		
		g.drawRect((int)width / 2 - stranica / 2,(int)height / 2 - stranica / 2, stranica, stranica);
		g.drawArc((int)width / 2 - premer / 2,(int)height / 2 - premer / 2, premer, premer, 0, 360);
		
                // izhod ce je manjsi od 0 ali vecji od okna
		if (premer < 1 || premer > width || premer > height)
			System.exit(0);
		if (stranica < 1 || stranica > width || stranica > height)
			System.exit(0);
		
	}
	
	public void mousePressed(MouseEvent e)
	{
	}
	
	public void mouseReleased(MouseEvent e)
	{
	}
	
	public void mouseEntered(MouseEvent e)
	{
	}
	
	public void mouseExited(MouseEvent e)
	{
	}
	
	private boolean notranjiKrog;
	public void mouseClicked(MouseEvent e)
	{
		int x, y, cona;		
		int width = this.getWidth();
		int height = this.getHeight();
		x = Math.abs(e.getX() - width / 2);
		y = Math.abs(e.getY() - height / 2);
		
		// Ce je kvadrat vecji od kroga je krog notranji.
		if (stranica > premer)
			notranjiKrog = true;
		else if (stranica*Math.sqrt(2) < premer)
			notranjiKrog = false;
			
		cona = 0;
                // 1: znotraj notranjega
                // 2: med notranjim in zunanjim
                // 3: zunaj obeh
			
		double r = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
                // ce je znotraj krog...
		if (notranjiKrog)
		{
			if (r < premer / 2)
				cona = 1;
			else if (r >= premer / 2 && (x <= stranica / 2 && y <= stranica / 2))
				cona = 2;
			else
				cona = 3;
		}
		else
		{			
			if (x < stranica / 2 && y < stranica / 2)
				cona = 1;
			else if (r <= premer / 2 && (x >= stranica / 2 || y >= stranica / 2))
				cona = 2;
			else
				cona = 3;
		}
		
                // glede na to kje smo kliknili in kateri gumb spremeni ustrezen lik
		switch (cona)
		{
			case 1:
				if (e.getButton() == MouseEvent.BUTTON1)
					if (notranjiKrog)
						premer += a;
					else
						stranica += a;
				else if (e.getButton() == MouseEvent.BUTTON3)
					if (notranjiKrog)
						stranica += a;
					else
						premer += a;
						
				break;	
			case 2:
				if (e.getButton() == MouseEvent.BUTTON1)
					if (notranjiKrog)
						premer -= b;
					else
						stranica -= b;
				else if (e.getButton() == MouseEvent.BUTTON3)
					if (notranjiKrog)
						stranica -= b;
					else
						premer -= b;
			
				break;
			case 3:
				int k = ((int)(Math.random() * 2) == 0) ? 1 : -1;
				if (e.getButton() == MouseEvent.BUTTON1)
					if (notranjiKrog)
						premer += c * k;
					else
						stranica += c * k;
				else if (e.getButton() == MouseEvent.BUTTON3)
					if (notranjiKrog)
						stranica += c * k;
					else
						premer += c * k;
			
				break;
		}
				
		this.repaint();		
	}
}