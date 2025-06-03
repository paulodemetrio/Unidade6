import java.util.Scanner;
import java.util.Random;

public class BatalhaNaval {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random random = new Random();
        char[][] tabuleiroExibe = new char[8][8];
        char[][] tabuleiroRegistra = new char[8][8];
        int[] acertosEtentativas = new int[2];
        int acertos = 0, tentativas = 30;
        populaTabuleiro(tabuleiroExibe);
        populaTabuleiro(tabuleiroRegistra);
        registrarNavios(tabuleiroRegistra, random);
        jogar(tentativas, acertos, tabuleiroExibe, tabuleiroRegistra, acertosEtentativas, scan);
        scan.close();
    }

    private static char[][] populaTabuleiro(char tabuleiro[][]) {
        for (int i = 0; i < tabuleiro.length; i++) {
            for (int j = 0; j < tabuleiro.length; j++) {
                tabuleiro[i][j] = '~';
            }
        }
        return tabuleiro;
    }

    private static char[][] registrarNavios(char tabuleiro[][], Random random) {
        int navios = 1;
        while (navios < 11) {
            int linha = random.nextInt(8);
            int coluna = random.nextInt(8);
            if (tabuleiro[linha][coluna] != 'N') {
                tabuleiro[linha][coluna] = 'N';
                navios++;
            }
        }
        return tabuleiro;
    }

    private static void jogar(int tentativas, int acertos, char[][] tabuleiroExibe, char[][] tabuleiroRegistra,
            int acertosEtentativas[], Scanner scan) {
        menu(tentativas);
        do {
            if (tentativas == 0 && acertos < 10) {
                System.out.println("Suas tentativas acabaram e ainda há navios não afundados.");
                System.out.println("Navios encontrados: " + acertos + "/10");
                System.out.println("Posições dos navios:");
                mostrarTabuleiro(tabuleiroRegistra);
                break;

            } else if (acertos == 10) {
                System.out.println("****************");
                System.out.println("Parabéns! Você afundou todos os navios!");
                System.out.println("Navios encontrados: " + acertos + "/10");
                mostrarTabuleiro(tabuleiroRegistra);
                break;

            } else {
                System.out.println("-----------------------------------------");
                System.out.println("Você ainda possui " + tentativas + " tentativas.");
                System.out.println("Navios encontrados: " + acertos + "/10");
                mostrarTabuleiro(tabuleiroExibe);
                acertosEtentativas = atacar(tabuleiroRegistra, tabuleiroExibe, scan, tentativas, acertos);
                tentativas = acertosEtentativas[0];
                acertos = acertosEtentativas[1];
            }

        } while (tentativas >= 0 && acertos <= 10);
        System.out.println();
        System.out.println("Fim do jogo!");
    }

    private static void menu(int tentativas) {
        System.out.println("**********************************");
        System.out.println("Bem-vindo ao jogo Batalha Naval!");
        System.out.println("Foram distribuídos 10 navios em um tabuleiro 8x8.");
        System.out.println("Você tem " + tentativas + " tiros para encontrá-los.");
        System.out.println("Legenda: '~' = Água, A = Navio Afundado, X = Tiro na água");
    }

    private static void mostrarTabuleiro(char tabuleiro[][]) {
        System.out.println("\n  0 1 2 3 4 5 6 7");
        for (int i = 0; i < tabuleiro.length; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < tabuleiro[i].length; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static int[] atacar(char[][] tabuleiroRegistra, char[][] tabuleiroExibe, Scanner scan, int tentativas,
            int acertos) {
        System.out.print("Digite a linha (0-7): ");
        int linhaAtaque = scan.nextInt();
        System.out.print("Digite a coluna (0-7): ");
        int colunaAtaque = scan.nextInt();

        if (linhaAtaque < 0 || linhaAtaque >= 8 || colunaAtaque < 0 || colunaAtaque >= 8) {
            System.out.println("-----------------");
            System.out.println("Posição inválida!");
        } else if (tabuleiroExibe[linhaAtaque][colunaAtaque] == 'A' || tabuleiroExibe[linhaAtaque][colunaAtaque] == 'X') {
            System.out.println("-----------------------------");
            System.out.println("Você já atirou nesta posição!");
        } else if (tabuleiroRegistra[linhaAtaque][colunaAtaque] == 'N') {
            System.out.println("----------------------");
            System.out.println("Você afundou um navio!");
            tabuleiroExibe[linhaAtaque][colunaAtaque] = 'A';
            acertos++;
        } else {
            System.out.println("-------------");
            System.out.println("Tiro na água!");
            tabuleiroExibe[linhaAtaque][colunaAtaque] = 'X';
        }
        tentativas--;
        return new int[] {tentativas, acertos};
    }
}
