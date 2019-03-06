import com.fazecast.jSerialComm.*;
import java.util.Scanner;

public class Serial {
	public static void listenSerial() {
		/*
		 * Change "COM4" to your USB port connected to the Arduino
		 * You can find the right port using the ArduinIDE
		 *
		 * PS: Unix based operating systems use "/dev/ttyUSB"
		 */
		SerialPort comPort = SerialPort.getCommPort("COM4");
		Scanner scanner = new Scanner(System.in);

		System.out.println("Welcome, Insert your card please");
		
		//set the baud rate to 9600 (same as the Arduino)
		comPort.setBaudRate(9600);
		
		//open the port
		comPort.openPort();
		
		//create a listener and start listening
		comPort.addDataListener(new SerialPortDataListener() {
			@Override
			public int getListeningEvents() { 
				return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; 
			}
			@Override
			public void serialEvent(SerialPortEvent event)
			{
				if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE)
				return; //wait until we receive data
			
				byte[] newData = new byte[comPort.bytesAvailable()]; //receive incoming bytes
				comPort.readBytes(newData, newData.length); //read incoming bytes
				String serialData = new String(newData); //convert bytes to string
				

				//print string received from the Arduino
				if (serialData.contains("B0 BD 1D A8"))
				{
					System.out.println("Enter your pincode: ");
					String pincode = scanner.nextLine();
					if (pincode.contains("2801"))
  					{
						System.out.println("Authorized access");
					}
					else
					{
						System.out.println("Authorized denied");
					}
					System.out.println("Thank you, come again");
				}
			}
		});
	}
}