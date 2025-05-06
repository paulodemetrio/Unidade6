public class ExemploVetor {
    public static void main(String[] args) {
        new ExemploVetor();
    }
    private int numeros[] = new int[5];
    private ExemploVetor(){
        getValores();
        imprimirValores();
        System.out.println("=======================================");
        randomizarValores();
        imprimirValores();
    }
    private void imprimirValores(){
        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Posição " + i + " = " + numeros[i]);
        }
    }
    private void randomizarValores(){
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = (int)(Math.random() * 10);
        }
    }
    private void getValores() {
        numeros[0] = 1;
        numeros[1] = 1;
        numeros[2] = 3;
        numeros[3] = 4;
        numeros[4] = 2;
    }
}
