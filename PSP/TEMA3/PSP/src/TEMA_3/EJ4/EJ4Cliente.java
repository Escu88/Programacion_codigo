package TEMA_3.EJ4;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class EJ4Cliente {

	public static void main(String[] args) throws IOException {
		byte[] buffer = new byte[1024];
		FileReader fr = null;
		BufferedReader br = null;
		FileWriter fw = null;
		File fichero = new File("password.txt");
		fr = new FileReader(fichero);
		br = new BufferedReader(fr);
		String txt = br.readLine();
		InetAddress server = InetAddress.getByName("localhost");
		DatagramSocket socket = new DatagramSocket();
		buffer = txt.getBytes();
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, server, 6000);
		System.out.println("Envio el datagrama");
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
		fw = new FileWriter(fichero,true);
		if (total == Integer.parseInt(txt)) {
			System.out.println("La clave es correcta");
			fw.write("\n"+txt);
		} else {
			System.out.println("Clave incorrecta");
			fw.write("\nSe ha intentado acceder con una clave errónea");
		}
		socket.close();
		br.close();
		fr.close();
		fw.close();

	}

}