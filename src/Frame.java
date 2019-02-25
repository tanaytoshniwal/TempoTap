import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.border.BevelBorder;

public class Frame extends JFrame {
	
	static JButton tap, reset;
	static int count = 0;
	static double time = 0;
	static long tim=0;
	static TempoThread thread = null;
	
	public Frame() {
		/* Frame Settings */
		super("Tempo-Tap");
		setSize(500, 500);
		setLocationRelativeTo(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		
		tap = new JButton("Tap!");
		tap.setBorder(new BevelBorder(0));
		tap.setBounds(new Rectangle(100, 100, 300, 300));
		tap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Frame.count++;
				if(Frame.count == 4) {
					Frame.thread = new TempoThread(Frame.time/4);
					Frame.thread.start();
					//System.out.println(Frame.time/4);
					Frame.tap.setEnabled(false);
				}
				if(Frame.count == 1) {
					Frame.tim = System.currentTimeMillis();
				}
				Frame.time += (System.currentTimeMillis() - Frame.tim)/1000.0;
			}
		});
		
		reset = new JButton("Reset");
		reset.setBounds(new Rectangle(100, 409, 300, 30));
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Frame.thread.stop();
				}
				catch(Exception ee) {}
				Frame.tap.setEnabled(true);
				Frame.count = 0;
				Frame.time = Frame.tim = 0;
				Frame.thread = null;
			}
		});
		
		add(tap);
		add(reset);
	}
	
	public static void main(String[] args) throws Exception{
		new Frame().setVisible(true);
	}
}
