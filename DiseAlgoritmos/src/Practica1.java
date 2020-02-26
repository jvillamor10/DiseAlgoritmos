import java.util.Stack;

public class Practica1 {

	public static void main (String []args) {

		int[] numeros = {1,2,4,10,20,50,100,1000,2000,5000,10000,11000}; 
		boolean opcion_correcta=true;

		System.out.println("Iterativo: "+iterativo(20,5));
		System.out.println("Recursivo: "+recursivo(20,5));


		/*for(int i=0;i<numeros.length;i++) { // Recorre el array numeros calculando el tiempo por cada posicion.
			//long tiempoit1_inicial=calcularTiempo(tiempo); // Calcula el tiempo en el que empieza.
			double it1=iterativo1(numeros[i]); // Lleva a cabo la ejecución del método.
			//long tiempoit1_final=calcularTiempo(tiempo)-tiempoit1_inicial; // El tiempo final será el tiempo hasta el momento menos el de inicio.
			//long tiempoit2_inicial=calcularTiempo(tiempo);
			double it2=iterativo2(numeros[i]);
			//long tiempoit2_final=calcularTiempo(tiempo)-tiempoit2_inicial;
			//long tiemporec_inicial=calcularTiempo(tiempo);
			double rec=recursivo(numeros[i]);
			//long tiemporec_final=calcularTiempo(tiempo)-tiemporec_inicial;
			
			if(it1<10) {
				System.out.println(numeros[i]+"\t|   "+it1+"\t\t|\t"+tiempoit1_final+"\t|\t"+tiempoit2_final+"\t\t|\t"+tiemporec_final); // Mostramos por pantalla los tiempos
			}else {
				System.out.println(numeros[i]+"\t|   "+it1+"\t|\t"+tiempoit1_final+"\t|\t"+tiempoit2_final+"\t\t|\t"+tiemporec_final);
			}
		}		*/
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
				
		//OTRA FORMA DE CALCULAR EL FACTORIAL, DA IGUAL DE MAL.
		/*for(int i = 2; i <= n; i++) {
			factorialn = factorialn * i;
			System.out.println("N "+ factorialn);
		}
		
		for(int i = 2; i <= k; i++) {
			factorialk = factorialk * i;
		}
		
		for(int i = 2; i <= n-k; i++) {
			factorialnk = factorialnk * i;
		}
		System.out.println("Factorial N: "+ factorialn +"Factorial K: "+ factorialk +"Factorial nk: "+ factorialnk);*/
		
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
		while(!pilaN.empty()) {
			while(pilaN.peek()>0&&pilaK.peek()>0) {
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
				
			}
			pilaN.pop();pilaK.pop();pilaL.pop();resultado=pilaS.pop();
			if(!pilaN.empty()) {
				pilaL.push(pilaL.pop()+1);
				pilaS.push(pilaS.pop()+resultado);
			}
		}
		return resultado;
	}
	
	public static long calcularTiempo(char opcion) { // Calculará el tiempo el milisegundos o nanosegundos según la opción
		long resultado=0;
		switch(opcion) {
			case 'm':
				resultado=System.currentTimeMillis();
				break;
			case 'n':
				resultado=System.nanoTime();
				break;
		}
		return resultado;
	}
}