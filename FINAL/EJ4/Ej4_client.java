package FINAL.EJ4;
//package FINAL.EJ3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import  java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner; 

public class Ej4_client { 
	
	public static void main (String[] args) {
		Scanner sc = new Scanner(System.in);
		try { 
			System.out.println("Creando socket cliente"); 
			Socket clientSocket = new Socket(); 
			System.out.println("Estableciendo la conexión"); 
			InetSocketAddress addr = new InetSocketAddress("172.26.80.22", 5556); 
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
			System.out.println(dis.readUTF());
			String tipo_user = sc.nextLine();
			dos.writeUTF(tipo_user);
			if (tipo_user.equals("1")) {
			do  {
				System.out.println("Servidor: "+dis.readUTF());
				System.out.println("Contestas...");
				responder = sc.nextLine();
				dos.writeUTF(responder);
			
			}while (!responder.equals("Adios Servidor"));
			}else if (tipo_user.equals("2")) {
				File f = new File("cliente.txt");

				try {
				System.out.println("Escribiendo fichero");
				String contesta = dis.readUTF();
				System.out.println(contesta);
				FileWriter fw = new FileWriter(f,true);
				fw.write(contesta);
				fw.close();
				}catch(IOException e) {
					System.out.println("mal");
					System.out.println(dis.readUTF());
				}
			}else if(tipo_user.equals("3")) {
				System.out.println(dis.readUTF());
				String tipo_saber = sc.next();
				dos.writeUTF(tipo_saber);
				System.out.println(dis.readUTF());
			}else {
				System.out.println(dis.readUTF());
			}
			clientSocket.close(); 
			System.out.println("Terminado");
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
	} 
}
