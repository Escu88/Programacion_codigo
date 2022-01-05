package TEMA_3.EJ1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class EJ1Cliente {
	public static void main (String[ ] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.print("Introduce número a calcular: ");
		Double num = sc.nextDouble();
		try {
			System.out.println("Creando socket cliente");
			Socket clientSocket = new Socket();
			System.out.println("Estableciendo la conexión");
			InetSocketAddress addr = new InetSocketAddress("localhost", 6000);
			clientSocket.connect(addr);
			InputStream is = clientSocket.getInputStream();
			OutputStream os = clientSocket.getOutputStream();
			DataInputStream entrada = new DataInputStream(is);
			DataOutputStream salida = new DataOutputStream(os);
			salida.writeDouble(num);
			Double cuadrado = entrada.readDouble();
			System.out.println("Cliente: Cuadrado de  " + num + " = " + cuadrado);
			System.out.println("Cerrando el socket cliente");
			clientSocket.close();
			System.out.println("Terminado");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}