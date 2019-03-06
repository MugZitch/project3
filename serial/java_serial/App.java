import com.fazecast.jSerialComm.*;

public class App {
	public static void main(String[] args) {
		Serial arduino = new Serial(); //Call Serial constructor
		arduino.listenSerial(); //Start listening
	}
}
