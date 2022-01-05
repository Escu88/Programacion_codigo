package TEMA_3.EJ2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.ServerSocket;

public class EJ2Servidor {
	public static void main (String[ ] args) {
		try {
			System.out.println("Creando socket servidor");
			ServerSocket serverSocket = new ServerSocket();
			System.out.println("Realizando el bind");
			InetSocketAddress addr = new InetSocketAddress("localhost" , 6000) ;
			serverSocket.bind(addr);
			System.out.println("Aceptando conexiones");
			Socket newSocket = serverSocket.accept();
			System.out.println("Conexión recibida");
			InputStream is = newSocket.getInputStream();
			OutputStream os = newSocket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			Double cat1 = dis.readDouble();
			Double cat2 = dis.readDouble();
			Double hipotenusa = Math.sqrt((Math.pow(cat1, 2) + Math.pow(cat2, 2)));
			dos.writeDouble(hipotenusa);
			if (cat1 < cat2) {
				System.out.println("Cateto 1 ->" + cat1);
				System.out.println("Cateto 2 ->" + cat2);
			} else {
				System.out.println("Cateto 1 ->" + cat2);
				System.out.println("Cateto 2 ->" + cat1);
			}
			System.out.println("Hipotenusa -> " + hipotenusa);
			System.out.println("Cerrando el nuevo socket");
			newSocket.close();
			System.out.println("Cerrando el socket servidor");
			serverSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}