public class Funcao {
    public static void main(String[] args) {
        double valor = somarDecimal(7.3, 4.9);
        System.out.println("Soma é " + valor);

        // int n = jogadorDaVez(7);
        System.out.println();

    }

    private static double somarDecimal(double v1, double v2) {
        return v1 + v2;
    }

    public static int somar(int a, int b) {
        return a + b;
    }

    public String jogadorDaVez(int numeroJogada){
        if (numeroJogada % 2 == 0){
            return "Jogador 1";
        } else {
            return "Jogador 2";
        }
    }

}
