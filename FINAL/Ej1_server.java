package FINAL;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException ;
import java.io.InputStream;
import  java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner; 

public class Ej1_server extends Thread{
	private Socket clientSocket;

	public  Ej1_server (Socket socket) { 
		clientSocket = socket; 
	} 

	public static void main (String[] args) { 
		
		System.out.println("Creando socket servidor"); 
		ServerSocket serverSocket = null; 
		try { 
			serverSocket = new ServerSocket(); 
			System.out.println ("Realizando el bind"); 
			InetSocketAddress addr = new InetSocketAddress("172.26.80.22", 5555); 
			serverSocket.bind(addr); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		System.out.println("Aceptando conexiones"); 
		while (serverSocket != null) { 
			try {
				Socket newSocket = serverSocket.accept(); 
				System.out.println("Conexión recibida"); 
				Ej1_server hilo = new Ej1_server(newSocket); 
				hilo.start(); 
			} catch (IOException e) { 
				e.printStackTrace(); 
			} 
		} 
		System.out.println("Terminado"); 
	}

	public void run () { 
		Scanner sc = new Scanner(System.in);
		try { 
			System.out.println("Arrancando  hilo"); 
			InputStream is = clientSocket.getInputStream();
			DataInputStream dis = new DataInputStream(is);
			OutputStream os = clientSocket.getOutputStream(); 
			DataOutputStream dos = new DataOutputStream(os);
			String respuesta = dis.readUTF();
			System.out.println("Mensaje recibido: "+ respuesta); 
			
			if(respuesta.equals("Hola Servidor")) {
				dos.writeUTF("¿Cuál es tu nombre de usuario?");
				String nombre = dis.readUTF();
				System.out.println("Nombre del cliente "+nombre);
				//if (nombre != null) {
					
				//-------
				dos.writeUTF("Empezando conversacion...");
				String contestacion = dis.readUTF();
				System.out.println(contestacion);
				while(!contestacion.equals("Adios Servidor")) {
					
					dos.writeUTF(sc.next());
                    String recibido = dis.readUTF();
                    if (recibido.equals("Adios Servidor")) {
                        break;
                    }else {
                        System.out.println("Cliente: "+recibido);
                        String mensaje = sc.nextLine();
                        dos.writeUTF(mensaje);
                    }
                } 

				//}
				System.out.println("Enviando resultado"); 
			} else { 
				System.out.println("Operación no reconocida"); 
			} 
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		System.out.println("Conexión terminada"); 
	} 
}
