//package FINAL.EJ2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import  java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner; 

public class Ej2_client { 
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		try { 
			System.out.println("Creando socket cliente"); 
			Socket clientSocket = new Socket(); 
			System.out.println("Estableciendo la conexión"); 
			InetSocketAddress addr = new InetSocketAddress("172.26.80.22", 5555); 
			clientSocket.connect(addr); 

			InputStream is = clientSocket.getInputStream(); 
			DataInputStream dis = new DataInputStream(is);
			OutputStream os = clientSocket.getOutputStream();
			DataOutputStream dos = new DataOutputStream(os);
			
			System.out.println("Enviando petición de suma"); 
			dos.writeUTF("Hola Servidor"); 
			
			String respuestanombre = dis.readUTF();
			System.out.println(respuestanombre);
			dos.writeUTF("Adrian");
			String responder  = null;
			
			do  {
			
				System.out.println("Servidor: "+dis.readUTF());
				System.out.println("Contestas...");
				responder = sc.nextLine();
				dos.writeUTF(responder);
			
			}while (!responder.equals("Adios Servidor"));

			clientSocket.close(); 
			System.out.println("Terminado");
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
}
