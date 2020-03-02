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
						
		long inicioIterativo = System.currentTimeMillis();
		System.out.println("Iterativo: "+iterativo(n,k));
		long finalIterativo = System.currentTimeMillis();
		
		long inicioRecursivo = System.currentTimeMillis();
		System.out.println("Recursivo: "+recursivo(n,k));
		long finalRecursivo = System.currentTimeMillis();
		
		long inicioPilas = System.currentTimeMillis();
		System.out.println("Pilas: "+pilas(n,k));
		long finalPilas = System.currentTimeMillis();
		
		System.out.println("Los tiempos empíricos son:");
		long tiempoIterativo = finalIterativo - inicioIterativo;
		long tiempoRecursivo = finalRecursivo - inicioRecursivo;
		long tiempoPilas = finalPilas - inicioPilas;
		
		System.out.println("Iterativo: "+tiempoIterativo+"\nRecursivo: "+tiempoRecursivo+"\nPilas: "+tiempoPilas);	
	}
	
	public static long iterativo(int n,int k){
		
		long factorialn=1,factorialk=1,factorialnk=1;
		long num_nk=n-k;
		
		while(n>0) {
			factorialn=factorialn*n;
			n--;
			if(k>0) {
				factorialk=factorialk*k;
				k--;
			}			
			if(num_nk>0) {
				factorialnk=factorialnk*num_nk;
				num_nk--;
			}			
		}
		
		return factorialn/(factorialk*factorialnk);
	}
	
	public static int recursivo(int n,int k) {
		int resultado=0;

		if(k!=0&&n!=0) {
			resultado=recursivo(n-1,k-1)+recursivo(n-1,k);
		}else {
			if(n>=k) {
				resultado++;
			}
		}

		return resultado;
	}
	
	public static int pilas(int n,int k) {
		Stack<Integer>pilaN=new Stack<Integer>(); //pila del número n
		Stack<Integer>pilaK=new Stack<Integer>(); //pila del número k
		Stack<Integer>pilaL=new Stack<Integer>(); //pila de llamadas
		Stack<Integer>pilaS=new Stack<Integer>(); //pila de resultados
		int resultado=0;
		
		pilaN.push(n);pilaK.push(k);pilaL.push(1);pilaS.push(0);
		// A LA GUARRA 
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
			resultado=pilaS.pop();
			if(!pilaN.empty()) {
				pilaL.push(pilaL.pop()+1);
				pilaS.push(pilaS.pop()+resultado);
			}
		}
		return resultado;
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