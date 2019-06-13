package warshall;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Scanner;

public class Warshall {

	private int cantidadNodos;
	private int cantidadAristas;
	private int nivelActual;
	private boolean[][] matrizActual;
	private boolean[][] matrizAnterior;
	
	public Warshall(Scanner entrada) {
		int nodo1, nodo2;
		this.cantidadNodos = entrada.nextInt();
		this.cantidadAristas = entrada.nextInt();
		this.nivelActual = 1;
		this.matrizActual = new boolean[this.cantidadNodos][this.cantidadNodos];
		this.matrizAnterior = new boolean[this.cantidadNodos][this.cantidadNodos];
		for (boolean[] row : matrizAnterior) {
			Arrays.fill(row, false);
		}
		for(int i=0; i<this.cantidadAristas; i++) {
			nodo1 = entrada.nextInt()-1;
			nodo2 = entrada.nextInt()-1;
			this.matrizAnterior[nodo1][nodo2] = true;
		}
		this.matrizActual = this.matrizAnterior;
	}
	
	public static boolean existe(boolean valor1, boolean valor2) {
		if(valor1 == true || valor2== true)
			return true;
		return false;
	}
	
	public void calcularWarshall() {
		while(this.nivelActual <= this.cantidadNodos) {
			for(int i=0; i<this.cantidadNodos; i++) {
				for(int j=0; j<this.cantidadNodos; j++) {
					if(i != j && i != this.nivelActual-1 && j != this.nivelActual-1) {
						this.matrizActual[i][j] = existe(this.matrizAnterior[i][j] ,
							this.matrizAnterior[i][this.nivelActual-1] && this.matrizAnterior[this.nivelActual-1][j]);
					}
				}
			}
			this.matrizAnterior = this.matrizActual;
			this.nivelActual++;
		}
		
	}
	
	public void mostrarMatriz() {
		for(int i=0; i<this.cantidadNodos; i++) {
			for(int j=0; j<this.cantidadNodos; j++) {
				System.out.print(this.matrizActual[i][j] + " ");
			}
			System.out.print('\n');
		}
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner entrada = new Scanner(new FileReader("warshall.in"));
		Warshall warshall = new Warshall(entrada);
		entrada.close();
		warshall.calcularWarshall();
		warshall.mostrarMatriz();
	}

}
