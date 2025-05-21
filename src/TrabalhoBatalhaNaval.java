import java.util.Random;
import java.util.Scanner;

public class TrabalhoBatalhaNaval {
    public static void main(String[] args) {
        TrabalhoBatalhaNaval jogo = new TrabalhoBatalhaNaval();
        jogo.jogar();
    }
    
    private static final int TAMANHO_TABULEIRO = 8;
    private static final int NUM_NAVIOS = 10;
    private static final int MAX_TIROS = 30;

    private char[][] tabuleiro;
    private boolean[][] navios; // true se há um navio na posição
    private int tirosRestantes;
    private int naviosEncontrados;

    public TrabalhoBatalhaNaval() {
        tabuleiro = new char[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        navios = new boolean[TAMANHO_TABULEIRO][TAMANHO_TABULEIRO];
        tirosRestantes = MAX_TIROS;
        naviosEncontrados = 0;
        inicializarTabuleiro();
        distribuirNavios();
    }

    private void inicializarTabuleiro() {
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                tabuleiro[i][j] = '~';
                navios[i][j] = false;
            }
        }
    }

    private void distribuirNavios() {
        Random random = new Random();
        int naviosColocados = 0;
        while (naviosColocados < NUM_NAVIOS) {
            int linha = random.nextInt(TAMANHO_TABULEIRO);
            int coluna = random.nextInt(TAMANHO_TABULEIRO);

            if (!navios[linha][coluna]) { // Se não há um navio já nesta posição
                navios[linha][coluna] = true;
                naviosColocados++;
            }
        }
    }

    public void exibirTabuleiro() {
        System.out.println("\n  0 1 2 3 4 5 6 7"); // Cabeçalho das colunas
        for (int i = 0; i < TAMANHO_TABULEIRO; i++) {
            System.out.print(i + " "); // Número da linha
            for (int j = 0; j < TAMANHO_TABULEIRO; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Tiros restantes: " + tirosRestantes);
        System.out.println("Navios encontrados: " + naviosEncontrados + "/" + NUM_NAVIOS);
    }

    public void jogar() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bem-vindo à Batalha Naval!");
        System.out.println("O computador distribuiu 10 navios em um tabuleiro 8x8.");
        System.out.println("Você tem " + MAX_TIROS + " tiros para encontrar todos eles.");
        System.out.println("~ = Água, A = Acerto, X = Erro");

        while (tirosRestantes > 0 && naviosEncontrados < NUM_NAVIOS) {
            exibirTabuleiro();
            int linha, coluna;

            System.out.print("Informe a linha (0-7) e a coluna (0-7) separadas por espaço: ");
            String input = scanner.nextLine();
            String[] coordenadas = input.split(" ");

            if (coordenadas.length != 2) {
                System.out.println("Entrada inválida. Por favor, digite a linha e a coluna separadas por um espaço.");
                tirosRestantes--; // Contar como um tiro
                continue;
            }

            try {
                linha = Integer.parseInt(coordenadas[0]);
                coluna = Integer.parseInt(coordenadas[1]);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite números para linha e coluna.");
                tirosRestantes--; // Contar como um tiro
                continue;
            }

            // Validação de coordenadas
            if (linha < 0 || linha >= TAMANHO_TABULEIRO || coluna < 0 || coluna >= TAMANHO_TABULEIRO) {
                System.out.println("Coordenada fora do tabuleiro! Tente novamente.");
                tirosRestantes--; // Contar como um tiro
                continue;
            }

            // Validação de alvo repetido
            if (tabuleiro[linha][coluna] == 'A' || tabuleiro[linha][coluna] == 'X') {
                System.out.println("Você já atirou nesta posição! Escolha outra.");
                tirosRestantes--; // Contar como um tiro
                continue;
            }

            tirosRestantes--; // Decrementa o tiro a cada tentativa válida (ou inválida tratada)

            // Lógica do acerto/erro
            if (navios[linha][coluna]) {
                System.out.println("Parabéns! Você acertou um navio!");
                tabuleiro[linha][coluna] = 'A';
                naviosEncontrados++;
            } else {
                System.out.println("Que pena! Você errou.");
                tabuleiro[linha][coluna] = 'X';
            }
        }

        exibirTabuleiro(); // Exibe o tabuleiro final

        if (naviosEncontrados == NUM_NAVIOS) {
            System.out.println("\n*********************************");
            System.out.println("* PARABÉNS! VOCÊ AFUNDOU TODOS OS NAVIOS! *");
            System.out.println("*********************************");
        } else {
            System.out.println("\n*********************************");
            System.out.println("* FIM DE JOGO! VOCÊ FICOU SEM TIROS. *");
            System.out.println("* VOCÊ ENCONTROU APENAS " + naviosEncontrados + " NAVIOS. *");
            System.out.println("*********************************");
        }

        scanner.close();
    }
}