import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Temperature
{
	public static void main(String[] args)
	{
		Okno okno = new Okno();
		okno.setVisible(true);
		
		/*Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
		okno.setSize((int)a.getWidth() / 2, (int)a.getHeight() / 2);
		okno.setLocation((int)a.getWidth() / 4, (int)a.getHeight() / 4);*/
		
		//Graf panel = new Graf();
		/*OrangePanel panel = new OrangePanel();
		panel.setVisible(true);
		okno.getContentPane().add(panel, BorderLayout.CENTER);
		panel.repaint();*/
	}
	
	public static double[][] t = {{37.5,37.9},{38.4,39.1},{40.5,42.0},{36.5,35.0},{37.5,37.9},{36.5,35.9},{36.6,36.9}};
}

class Okno extends JFrame implements WindowListener, KeyListener
{
	public Okno()
	{
                // Iz velikosti zaslona dobljeni podatki o oknu in polozaju.
		Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)a.getWidth() / 2, (int)a.getHeight() / 2);
		this.setLocation((int)a.getWidth() / 4, (int)a.getHeight() / 4);
		this.setTitle("Tedenske temperature");
		
		this.addWindowListener(this);
		this.addKeyListener(this);
		this.getContentPane().add(new Graf(), BorderLayout.CENTER);
	}
	
	public void windowActivated(WindowEvent e)
	{
	}
	
	public void windowClosing(WindowEvent e)
	{
		System.exit(0);
	}
	
	public void windowClosed(WindowEvent e)
	{
	}
	
	public void windowDeactivated(WindowEvent e)
	{
	}
	
	public void windowDeiconified(WindowEvent e)
	{
	}
	
	public void windowIconified(WindowEvent e)
	{
	}
	
	public void windowOpened(WindowEvent e)
	{
	}
	
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}
	
	public void keyTyped(KeyEvent e)
	{
	}
	
	public void keyReleased(KeyEvent e)
	{
	}
	
}

class Graf extends JPanel implements ActionListener
{
	private JButton button;
	private JButton button2;
	private int graph = 1;
	
	public Graf()
	{
		Dimension a = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize((int)a.getWidth() / 2, (int)a.getHeight() / 2);
		this.setLayout(null);
		
		button = new JButton();
		button.setText("Zamenjaj graf");
		button.setSize(200, 25);
		button.setLocation((int)this.getWidth() / 2 - 200, this.getHeight());
		button.addActionListener(this);
		this.add(button);
		
		button2 = new JButton();
		button2.setText("Generiraj podatke");
		button2.setSize(200, 25);
		button2.setLocation((int)this.getWidth() / 2, 10);
		button2.addActionListener(this);
		this.add(button2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("Zamenjaj graf"))
		{
			graph = (graph + 1) % 2;
			this.repaint();
		}
		else if (e.getActionCommand().equals("Generiraj podatke"))
		{
			for (int i = 0; i < Temperature.t.length; i++)
			{
				for (int j = 0; j < Temperature.t[i].length; j++)
				{
					Temperature.t[i][j] = Math.random() * 7 + 35;
				}				
			}
			this.repaint();
		}
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		button.setLocation((int)this.getWidth() / 2 - 200, 10);
		button2.setLocation((int)this.getWidth() / 2, 10);
		int width = this.getWidth() - 20;
		int height = this.getHeight() - 55;
		
		int x, y;
		Graphics2D gr = (Graphics2D)g;
		
		gr.setColor(Color.white);
		gr.fillRect(10, 45, width, height);
		gr.setColor(Color.black);
		gr.drawRect(10, 45, width, height);
		
		//narisi temperaturne crte
		for (int i = 0; i < 15; i++)
		{
			if (i == 10)
				gr.setColor(Color.red);
			else
				gr.setColor(Color.black);
				
			if (i % 2 == 0)
				gr.setStroke(new BasicStroke(2));
			else
				gr.setStroke(new BasicStroke(1));
			
			y = (int)i * ((height - 40) / 14) + 65;
			gr.drawLine(35, y, width, y);
			
			//napisi temperaturo pri polnih temperaturah
			if (i % 2 == 0)
				gr.drawString((42-(i/2)) + "°", 20, y-2);
		}
		
		gr.setStroke(new BasicStroke(1));
		
		//narisi stevilke 1-7
		for (int i = 0; i < 7; i++)
		{
			x = (int)i * (width - 79) / 7 + (width - 79) / 14 + 59 - 2;
			y = height - 10 + 45;
			gr.drawString((i+1) + "", x, y);
		}
		
		//narisi graf
		int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
		int cWidth = (int)(width - 69) / 14;
		int cHeight;
		double razmerje = ((double)height - (double)45) / (double)7;
		
		for (int i = 0; i < Temperature.t.length; i++)
		{
			for (int j = 0; j < Temperature.t[i].length; j++)
			{
				if (graph == 0)
				{
					gr.setStroke(new BasicStroke(2));
					gr.setColor(Color.blue);
					x1 = x2;
					y1 = y2;
					x2 = (int)i * (width - 79) / 14 + 59;
					x2 = (int)i * (width - 79) / 7 + (j*2+1) * (width - 79) / 28 + 59;
					y2 = (int)45 + height - 25 - (int)(razmerje * (Temperature.t[i][j] - (double)35.0));
										
					if ( i != 0 || j != 0)
						gr.drawLine(x1, y1, x2, y2);
				}
				else
				{
					x1 = (int)(i * (width - 79) / 7 + j * (width - 79) / 14 + 59);
					cHeight = (int)(razmerje * (Temperature.t[i][j] - (double)35.0));
					y1 = 45 + height - 25 - cHeight;
					
					if (j == 0)
						gr.setColor(Color.blue);
					else
						gr.setColor(Color.green);
						
					gr.fillRect(x1, y1, cWidth, cHeight);
					gr.setColor(Color.black);
					gr.drawRect(x1, y1, cWidth, cHeight);
				}
			}
		}
		
	}
}