import java.util.Scanner;

public class Calc {
    public static double soma(double a, double b) {
        return a + b;
    }

    public static double subtrai(double a, double b) {
        return a - b;
    }

    public static double multiplica(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) {
        return a / b;
    }

    public static String despedida() {
        return "Sistema encerrado.";
    }

    public static void main(String[] args) {
        int op = 1;
        double v1 = 0, v2 = 0;
        while (op != 0) {

            Scanner scan = new Scanner(System.in);
            System.out.println("Digite o valor referente à opção desejada: ");
            System.out.println("1 - SOMA");
            System.out.println("2 - SUBTRAÇÃO");
            System.out.println("3 - MULTIPLICAÇÃO");
            System.out.println("4 - DIVISÃO");
            System.out.println("0 - SAIR");
            op = scan.nextInt();

            if (op != 0) {
                System.out.println("Informe o primeiro valor: ");
                v1 = scan.nextDouble();

                System.out.println("Informe o segundo valor: ");
                v2 = scan.nextDouble();
            }

            double resultado;

            switch (op) {
                case 1:
                    resultado = soma(v1, v2);
                    System.out.println("Resultado da soma: " + resultado);
                    break;
                case 2:
                    resultado = subtrai(v1, v2);
                    System.out.println("Resultado da subtração: " + resultado);
                    break;
                case 3:
                    resultado = multiplica(v1, v2);
                    System.out.println("Resultado da multiplicação: " + resultado);
                    break;
                case 4:
                    resultado = divide(v1, v2);
                    System.out.println("Resultado da divisão: " + resultado);
                    break;
                case 0:
                    despedida();
                    break;
                default:
                    System.out.println("Dígito inválido.");
                    break;
            }

            scan.close();
        }
    }
}
