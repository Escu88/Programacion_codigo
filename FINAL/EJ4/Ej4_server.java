package FINAL.EJ4;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException ;
import java.io.InputStream;
import  java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner; 

public class Ej4_server extends Thread{
	private Socket clientSocket;
	private static int tipo1 = 0;
	private static int tipo2 = 0;

	public  Ej4_server (Socket socket) { 
		clientSocket = socket; 
	} 

	public static void main (String[] args) { 
		
		System.out.println("Creando socket servidor"); 
		ServerSocket serverSocket = null; 
		try { 
			serverSocket = new ServerSocket(); 
			System.out.println ("Realizando el bind"); 
			InetSocketAddress addr = new InetSocketAddress("172.26.80.22", 5556); 
			serverSocket.bind(addr); 
		} catch (IOException e) { 
			e.printStackTrace(); 
		} 
		System.out.println("Aceptando conexiones"); 
		while (serverSocket != null) { 
			try {
				Socket newSocket = serverSocket.accept(); 
				System.out.println("Conexión recibida"); 
				Ej4_server hilo = new Ej4_server(newSocket); 
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
       	 Date inicio = new Date(System.currentTimeMillis());


			File f = new File("prueba.txt");
			FileWriter fw = new FileWriter(f,true);
			
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
				dos.writeUTF("¿Qué tipo de usuario eres?");
				String tipo_usu = dis.readUTF();
				if (tipo_usu.equals("1")) {
					tipo1++;
				dos.writeUTF("Empezando conversacion...");
				String contestacion = dis.readUTF();
				System.out.println(contestacion);
				while(!contestacion.equals("Adios Servidor")) {
					
					dos.writeUTF(sc.next());
                    String recibido = dis.readUTF();
                    if (recibido.equals("Adios Servidor")) {
            			
                    	 Date fin = new Date(System.currentTimeMillis());
                         
                         long duracion_base=fin.getTime()-inicio.getTime();
                         long dia=duracion_base/(24*60*60*1000);
                         long hora=(duracion_base/(60*60*1000)-dia*24);
                         long min=((duracion_base/(60*1000))-dia*24*60-hora*60);
                         long s=(duracion_base/1000-dia*24*60*60-hora*60*60-min*60);
                         
                         fw.write("Cliente: "+nombre+" | Fecha de inicio de la conexion: "+inicio
                         		+ "\nDuracion de la conexión: "+dia+ " dias "+hora+ " horas "+ min+ " minutos "+s+" segundos.\n\n");
                    	//System.out.println("\n  "+day+ " dias "+hour+ " horas "+ min+ " minutos "+s+" segundos.");   
                    	fw.close();
            			break;
            			
                    }else {
                        System.out.println("Cliente: "+recibido);
                        String mensaje = sc.nextLine();
                        System.out.println("Contestas...");
                        dos.writeUTF(mensaje);
                    }
                } 
				}else if (tipo_usu.equals("2")) {
					tipo2++;
               	 
					FileReader fr = new FileReader(f);
					//while (f)
					//leer fichero pa pasarlo
					BufferedReader br = new BufferedReader(fr);
					StringBuilder sb = new StringBuilder();
					try {
						
						String line;
						String mensaje = "";
						while ((line = br.readLine()) != null) {
							mensaje = mensaje+"\n"+line;
						}
						dos.writeUTF(mensaje);
					}finally {
						br.close();
					}
				}else if(tipo_usu.equals("3")) {
					dos.writeUTF("¿De qué usuario quieres saber más información");
					String respuesta_tipo = dis.readUTF();
					if (respuesta_tipo.equals("1")) dos.writeUTF("Se han conectado "+tipo1+" usuarios de tipo 1");
					else if (respuesta_tipo.equals("2")) dos.writeUTF("Se han conectado "+tipo2+" usuarios de tipo 2");
				}else {
					dos.writeUTF("El usuario es incorrecto");
				}
				//}
				System.out.println("Enviando resultado"); 
			} else { 
				System.out.println("Operación no reconocida"); 
			} 
				
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		
		System.out.println("Conexión  terminada"); 
	} 
}
