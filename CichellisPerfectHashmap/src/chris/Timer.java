package chris;

public class Timer {
	
	public static long Start;
	public static long End;
	public static long time;
	public static void startTimer() {
		Start = System.currentTimeMillis();
	}


	public static void endTimer() {
		End = System.currentTimeMillis();
		time = End - Start;
	}
	
	public static long getTime() {
		return time;
	}

}
