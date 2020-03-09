import java.util.Scanner;
import java.util.Stack;

public class Practica1 {

	public static void main (String []args) {

		int n = 0, k = 0;
		do {
			n = leerDouble("Escriba el valor de n: ");
			k = leerDouble("Escriba el valor de k: ");
			
			if (n < k) {
				System.out.println("No se cumple la condición n >= k. Vuelva a intentarlo.");
			}
			
		} while(n < k);
		
		long it;				
		double inicioIterativo = System.nanoTime();
		it = iterativo(n,k);
		double finalIterativo = System.nanoTime();
		System.out.println("Iterativo: "+ it);

		long re;
		double inicioRecursivo = System.nanoTime();
		re = recursivo(n,k);
		double finalRecursivo = System.nanoTime();
		System.out.println("Recursivo: "+ re);

		long pi;
		double inicioPilas = System.nanoTime();
		pi = pilas(n,k);
		double finalPilas = System.nanoTime();
		System.out.println("Pilas: "+ pi);
		
		System.out.println("Los tiempos empíricos en nanosegundos son:");
		double tiempoIterativo = finalIterativo - inicioIterativo;
		double tiempoRecursivo = finalRecursivo - inicioRecursivo;
		double tiempoPilas = finalPilas - inicioPilas;
		
		System.out.println("Iterativo: "+tiempoIterativo+"\nRecursivo: "+tiempoRecursivo+"\nPilas: "+tiempoPilas);	
	}
	
	public static long factorial(int n) {
		long fact = 1;		
		for(int i = 2; i <= n; i++) {
			fact = fact * i;
		}

		return fact;
	}
	
	public static long iterativo(int n,int k){
		
		long factorialn=1,factorialk=1,factorialnk=1;
		
		factorialn = factorial(n);
		factorialk = factorial(k);
		factorialnk = factorial(n-k);
		
		return factorialn/(factorialk*factorialnk);
	}
	
	public static int recursivo(int n,int k) {
		int resultado=0;

		if(k == 0 && n>0 || n == k) {
			resultado = 1;
		}else{
			resultado=recursivo(n-1,k-1)+recursivo(n-1,k);
		}
		
		return resultado;
	}
	
	public static int pilas(int n,int k) {
		Stack<Integer>pilaN=new Stack<Integer>(); //pila del número n
		Stack<Integer>pilaK=new Stack<Integer>(); //pila del número k
		Stack<Integer>pilaL=new Stack<Integer>(); //pila de llamadas
		Stack<Integer>pilaS=new Stack<Integer>(); //pila de resultados
		
		pilaN.push(n);pilaK.push(k);pilaL.push(1);pilaS.push(0);
		if (pilaK.peek() == 0 && pilaN.peek() == 0) {
			pilaS.push(1);
		}
		
		while(!pilaN.empty()) {
			while(pilaN.peek()>=0 && pilaK.peek()>=0 && pilaL.peek()<=2) {
				switch(pilaL.peek()) {
					case 1:
						pilaN.push(pilaN.peek()-1);
						pilaK.push(pilaK.peek()-1);
						break;
					case 2:
						pilaN.push(pilaN.peek()-1);
						pilaK.push(pilaK.peek());
						break;
				}
				pilaL.push(1);

				if (pilaK.peek() == 0 && pilaN.peek() == 0) {
					pilaS.push(1);
				}else {
					pilaS.push(0);
				}
				//System.out.println("Calculando N:"+pilaN.peek()+", K:"+pilaK.peek()+", resultado: "+pilaS.peek());
			}
			pilaN.pop();pilaK.pop();pilaL.pop();
			if(!pilaN.empty()) {
				pilaL.push(pilaL.pop()+1);
				pilaS.push(pilaS.pop()+pilaS.pop());
			}
		}
		return pilaS.peek();
	}
	
	public static int leerDouble(String mensaje) {
		Scanner TECLADO = new Scanner(System.in);
		int numero = 0;
		boolean valido = true;
		do {
			System.out.print(mensaje);
			try {
				valido = true;
				numero = TECLADO.nextInt();
			}catch(Exception InputMismatchException) {
				System.out.println("El dato introducido no es correcto, introduzca uno valido: ");
				valido=false;
				TECLADO.nextLine();
			}
		}while(!valido);

		return numero;
	}
}