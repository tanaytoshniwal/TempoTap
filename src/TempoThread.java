import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
public class TempoThread extends Thread {
	static double time = 0;
	static int counter = 0;
	static String s1 = "beat.wav";
	public TempoThread(double time) {
		this.time = time;
	}
	public void run() {
		try {
			long start = System.currentTimeMillis();
			
			while(true) {
				long cur = System.currentTimeMillis();
				double tim = (cur - start)/1000.0;
				//System.out.println((cur-start)/1000.0);
				
				if(tim>=time) {
					counter++;
					start = cur;
					AudioInputStream audioIn = AudioSystem.getAudioInputStream(TempoThread.class.getResource(this.s1));
					AudioFormat format = audioIn.getFormat();
					DataLine.Info info = new DataLine.Info(Clip.class, format);
					Clip clip = (Clip)AudioSystem.getLine(info);
					clip.open(audioIn);
					clip.start();
					if(counter%4==0) {
						counter = 0;
						System.out.println(":1");
					}
					else {
						System.out.print(":0");
					}
					clip.close();
				}
			}
		}
		catch(Exception ee) {System.out.println(ee.getMessage());}
	}
}
