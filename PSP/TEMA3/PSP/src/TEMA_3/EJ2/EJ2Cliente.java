package TEMA_3.EJ2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EJ2Cliente {
	public static void main (String[ ] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Calcular la Hipotenusa de un Triángulo Rectangulo");
		System.out.print("Inserte el primer cateto: ");
		Double cat1 = sc.nextDouble();
		System.out.print("Inserte el Segundo cateto: ");
		Double cat2 = sc.nextDouble();
		try {
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 6000);
			clientSocket.connect(addr);
			
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			DataInputStream dis = new DataInputStream(is);
			DataOutputStream dos = new DataOutputStream(os);
			dos.writeDouble(cat1);
			dos.writeDouble(cat2);
			Double hipotenusa = dis.readDouble();
			if (cat1 < cat2) {
				System.out.println("Cateto 1 -> " + cat1);
				System.out.println("Cateto 2 -> " + cat2);
			} else {
				System.out.println("Cateto 1 -> " + cat2);
				System.out.println("Cateto 2 -> " + cat1);
			}
			System.out.println("Hipotenusa -> " + hipotenusa);
			
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
		sc.close();
	}
}