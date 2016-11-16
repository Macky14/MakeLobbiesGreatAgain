import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Overlay extends JPanel {
	private static final long serialVersionUID = -470849574354121503L;
	
	private String locale = null;
	
	Overlay() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		this.setOpaque(false);

		JWindow frame = new JWindow();
		frame.setBackground(new Color(0, 0, 0, 0));
		frame.setFocusableWindowState(false);
		
		frame.add(this);
		frame.setAlwaysOnTop(true);
		frame.pack();
		frame.setLocation(5, 400);
		frame.setVisible(true);
		
		Thread t = new Thread("UIPainter"){
			public void run() {
				try{
					while(true){
						Thread.sleep(250);
						Overlay.this.repaint();
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		};
		t.setDaemon(true);
		t.start();
	}
	
	public void setKillerLocale(String locale){
		this.locale = locale;
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(117, 20);
	}
	
	@Override
	protected void paintComponent(Graphics gr) {
		super.paintComponent(gr);
		Graphics2D g = (Graphics2D) gr.create();
		g.setColor(getBackground());
		g.setFont(g.getFont().deriveFont(15f));
		//g.setComposite(AlphaComposite.SrcOver.derive(0f));
		g.setColor(new Color(0,0,0,0));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(new Color(0f,0f,0f,.5f));
		g.fillRect(6, 0, 111, 18);
		
		g.setColor(Color.GREEN);
		g.drawString("Killer Locale: " + locale, 7, 14);
		
		g.dispose();
	}
}
