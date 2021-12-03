package barbero_durmiente;

import barbero_durmiente.Cliente;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BarberoDurmiente {

	public static void main(String[] args) throws InterruptedException {
		// Creamos un nuevo barbero pasandole un boolean que indica que NO esta ocupado
		Barbero barbero = new Barbero(false);
		// Bucle que crea 4 clientes
		for (int i = 0; i < 8; i++) {
			Cliente c = new Cliente(i + 1, barbero);
			c.start();
			// Importante minipause despues de crear un hilo
			Thread.sleep(100);

		}

	}
}

//Clase del barberp
class Barbero {
	public boolean ocupado; // Si esta ocupado o no
	public int sillas = 5;

	// Constructor por defecto que crea el barberp
	public Barbero(boolean ocupado) {
		this.ocupado = ocupado;
	}

	// Metodo ocupar silla,es sincronizado para que dos clientes no cojan una silla
	// a la vez y pete
	public synchronized void ocuparSilla(int numClie) {
		sillas--;
		System.out.println("Silla ocupada por el cliente número: " + numClie + "Quedan " + sillas + "sillas");

	}

	// Metodo que hara que un cliente deje la silla,es como que al que le toca que
	// le corten el pelo se levanta
	// Para ir a que se lo corten y libera ña silla
	public synchronized void dejarSilla(int numClie) {

		sillas++;
		System.out.println("Silla liberada por el cliente número: " + numClie);
	}

	// Metodo de coetar el pelo
	public synchronized void inicioCorte(int numClie) {
		// Si el barbero esta ocupado Espera
		ocuparSilla(numClie);

		while (isOcupado()) {
			System.out.println("Barbero ocupado, " + numClie + " espera");
			try {
				wait();
			} catch (InterruptedException ex) {
				Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
		// Una vez ya ha acabado con un cliente lo volvemos a ocupar por que va a
		// empezar a cortarselo a este
		this.setOcupado(true);
		dejarSilla(numClie);
		System.out.println("El barbero empieza a cortar el pelo al cliente " + numClie);
	}

	public synchronized void finCorte(int numClie) {
		// Simplemente le decimos que ya no esta ocuapdo el barbero
		this.setOcupado(false);
		System.out.println("El barbero termina de cortar el pelo al cliente " + numClie);
		notify();// Y se lo incicamos a todos los waiting
	}

	// Metodo isOcupado
	public boolean isOcupado() {
		return ocupado;
	}

	// Metodo setOcupado
	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;

	}
}

//Hilo del cliente
class Cliente extends Thread {
	// Le pasamos un barbero al cliente
	private Barbero bar;
	// Le asignamos un numero de cliente
	private int numCliente;

	// Constructor del cliente que asigna valores al crearlo
	public Cliente(int nCli, Barbero b) {
		this.numCliente = nCli;
		this.bar = b;
	}

	// Todo lo que hace esta en este run
	@Override
	public void run() {
		try {
			// El barbero empieza a cortar el pelo a este cliente
			if (bar.sillas > 0) {

				bar.inicioCorte(numCliente);

				sleep(800);// El barbero tarda 0.8 s en cortar el pelo
				// El barbero acaba de cortar el pelo
				bar.finCorte(numCliente);

			} else {
				System.out.println("No hay sillas,el cliente" + numCliente + "se va");
			}

		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
	}
}