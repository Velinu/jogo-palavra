import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Bem-vindo ao jogo de Adivinhação de Números!");
            System.out.print("Defina o valor mínimo do intervalo: ");
            int min = obterEntradaValida(scanner);
            
            System.out.print("Defina o valor máximo do intervalo: ");
            int max = obterEntradaValida(scanner);

            if (min >= max) {
                System.out.println("Intervalo inválido. O valor máximo deve ser maior que o mínimo.");
                continue;
            }

            int numeroSecreto = random.nextInt(max - min + 1) + min;
            int tentativas = 0;
            boolean acertou = false;

            System.out.println("Tente adivinhar o número entre " + min + " e " + max);

            while (!acertou) {
                System.out.print("Digite sua tentativa (ou digite 'sair' para encerrar): ");
                String entrada = scanner.next();
                if (entrada.equalsIgnoreCase("sair")) {
                    System.out.println("Você decidiu encerrar o jogo.");
                    break;
                }

                try {
                    int tentativa = Integer.parseInt(entrada);
                    tentativas++;

                    if (tentativa == numeroSecreto) {
                        System.out.println("Parabéns! Você acertou o número em " + tentativas + " tentativas.");
                        acertou = true;
                    } else if (tentativa < numeroSecreto) {
                        System.out.println("O número é maior que " + tentativa + ". Tente novamente.");
                    } else {
                        System.out.println("O número é menor que " + tentativa + ". Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida! Por favor, insira um número válido.");
                }
            }

            if (!acertou) {
                System.out.println("O número secreto era: " + numeroSecreto);
            }

            System.out.print("Deseja jogar novamente? (sim/não): ");
            String resposta = scanner.next();
            continuar = resposta.equalsIgnoreCase("sim");
        }

        System.out.println("Obrigado por jogar! Até a próxima.");
        scanner.close();
    }

    private static int obterEntradaValida(Scanner scanner) {
        int numero = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                numero = scanner.nextInt();
                entradaValida = true;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Por favor, insira um número válido.");
                scanner.next();  // Limpar entrada inválida
            }
        }
        return numero;
    }
}

