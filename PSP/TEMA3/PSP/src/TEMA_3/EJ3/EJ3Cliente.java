package TEMA_3.EJ3;

import java.io.IOException;
import java.lang.System.Logger.Level;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class EJ3Cliente {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		byte[] buffer = new byte[1024];
		System.out.print("Inserte una cadena ");
		String txt = sc.nextLine();
		InetAddress server = InetAddress.getByName("localhost");
		DatagramSocket socket = new DatagramSocket();
		buffer = txt.getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, server, 6000);
		System.out.println("Enviar datos");
		socket.send(packet);
		DatagramPacket data = new DatagramPacket(buffer, buffer.length);
		socket.receive(data);
		System.out.println("Recibo la peticion");
		int largo = txt.length(); 
		int total = 0;
		for (int i = 0; i < largo; i++) {
			char c = txt.charAt(i);
			total+= (int)c;
		}
		txt = new String(data.getData());
		if (total == Integer.parseInt(txt)) {
			System.out.println("Contraseña correcta");
		}
		socket.close();
		sc.close();
	}
}