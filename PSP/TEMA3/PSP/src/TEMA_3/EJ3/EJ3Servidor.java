package TEMA_3.EJ3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EJ3Servidor {
	public static void main(String[] args) throws IOException {

		byte[] buffer = new byte[1024];
		System.out.println("Creando socket servidor");
		DatagramSocket socket = new DatagramSocket(6000);
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		socket.receive(packet);
		System.out.println("Aceptando conexiones");
		String txt = new String(packet.getData());
		System.out.println(txt);
		int puertoCliente = packet.getPort();
		InetAddress direccion = packet.getAddress();
		int largo = txt.length(); 
		int total = 0;
		for (int i = 0; i < largo; i++) {
			char c = txt.charAt(i);
			total+= (int)c;
		}
		buffer = Integer.toString(total).getBytes();
		DatagramPacket paquete = new DatagramPacket(buffer, buffer.length, direccion, puertoCliente);
		socket.send(paquete);
		socket.close();

	}
}