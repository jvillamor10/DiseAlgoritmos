public class Practica1 {

	public static void main (String []args) {

		int[] numeros = {1,2,4,10,20,50,100,1000,2000,5000,10000,11000}; 
		boolean opcion_correcta=true;

		System.out.println("HOLA");
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
	public static int iterativo(int n,int k){
		double resultado=0;
		int factorialn=1,factorialk=1,factorialnk=1;
		int num_n=n;
		int num_k=k;
		int num_nk=n-k;
		
		while(num_n!=0) {
			factorialn=factorialn*num_n;
			num_n--;
		}
		while(num_k!=0) {
			factorialk=factorialk*num_k;
			num_k--;
		}
		while(num_nk!=0) {
			factorialnk=factorialnk*num_nk;
			num_nk--;
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