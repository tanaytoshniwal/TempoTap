
public class TempoThread extends Thread {
	static double time = 0;
	static int counter = 0;
	public TempoThread(double time) {
		this.time = time;
	}
	public void run() {
		long start = System.currentTimeMillis();
		while(true) {
			long cur = System.currentTimeMillis();
			double tim = (cur - start)/1000.0;
			//System.out.println((cur-start)/1000.0);
			if(tim>=time) {
				counter++;
				start = cur;
				if(counter%4==0) {
					counter = 0;
					System.out.println(":1");
				}
				else {
					System.out.print(":0");
				}
			}
		}
	}
}
