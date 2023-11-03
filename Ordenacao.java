/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package a3;

/**
 *
 * @author itzvi
 */
import java.util.Arrays;
import java.util.Random;

public class Ordenacao {
    private static int trocas = 0;
    private static int iteracoes = 0;

    public static void main(String[] args) {
        int[] tamanhos = {50, 500, 1000, 5000, 10000};
        int rodadas = 5; 

        for (int tamanho : tamanhos) {
            // Arrays para armazenar as métricas de cada rodada
            long tempoBubbleSort = 0;
            long tempoMergeSort = 0;
            long tempoQuickSort = 0;
            long trocasBubbleSort = 0;
            long trocasMergeSort = 0;
            long trocasQuickSort = 0;
            long iteracoesBubbleSort = 0;
            long iteracoesMergeSort = 0;
            long iteracoesQuickSort = 0;

            for (int rodada = 0; rodada < rodadas; rodada++) {
                int[] vetor = gerarVetorAleatorio(tamanho);

                int[] copiaVetor = Arrays.copyOf(vetor, vetor.length);
                long inicio = System.nanoTime();
                bubbleSort(copiaVetor);
                long fim = System.nanoTime();
                tempoBubbleSort += (fim - inicio);
                trocasBubbleSort += trocas;
                iteracoesBubbleSort += iteracoes;
                resetarContadores();

                copiaVetor = Arrays.copyOf(vetor, vetor.length);
                inicio = System.nanoTime();
                mergeSort(copiaVetor);
                fim = System.nanoTime();
                tempoMergeSort += (fim - inicio);
                trocasMergeSort += trocas;
                iteracoesMergeSort += iteracoes;
                resetarContadores();

                copiaVetor = Arrays.copyOf(vetor, vetor.length);
                inicio = System.nanoTime();
                quickSort(copiaVetor, 0, copiaVetor.length - 1);
                fim = System.nanoTime();
                tempoQuickSort += (fim - inicio);
                trocasQuickSort += trocas;
                iteracoesQuickSort += iteracoes;
                resetarContadores();
            }

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println("Tamanho do Vetor: " + tamanho);
            System.out.println("Bubble Sort - Tempo Médio: " + tempoBubbleSort / rodadas + " ns");
            System.out.println("Bubble Sort - Trocas Médias: " + trocasBubbleSort / rodadas);
            System.out.println("Bubble Sort - Iterações Médias: " + iteracoesBubbleSort / rodadas);
            System.out.println("--------------------------------------------");
            System.out.println("Merge Sort - Tempo Médio: " + tempoMergeSort / rodadas + " ns");
            System.out.println("Merge Sort - Trocas Médias: " + trocasMergeSort / rodadas);
            System.out.println("Merge Sort - Iterações Médias: " + iteracoesMergeSort / rodadas);
            System.out.println("--------------------------------------------");
            System.out.println("Quick Sort - Tempo Médio: " + tempoQuickSort / rodadas + " ns");
            System.out.println("Quick Sort - Trocas Médias: " + trocasQuickSort / rodadas);
            System.out.println("Quick Sort - Iterações Médias: " + iteracoesQuickSort / rodadas);
        }
    }

    // Função para resetar as variáveis de trocas e iterações
    private static void resetarContadores() {
        trocas = 0;
        iteracoes = 0;
    }

    // Função para gerar um vetor de inteiros aleatórios
    private static int[] gerarVetorAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = random.nextInt(10000); //intervalo de valores aleatórios
        }
        return vetor;
    }

    //Bubble Sort
    private static void bubbleSort(int[] vetor) {
        int n = vetor.length;
        boolean trocou;
        for (int i = 0; i < n - 1; i++) {
            trocou = false;
            for (int j = 0; j < n - i - 1; j++) {
                iteracoes++;
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocas++;
                    trocou = true;
                }
            }
            if (!trocou) {
                break;
            }
        }
    }

    //Merge Sort
    private static void mergeSort(int[] vetor) {
        if (vetor.length > 1) {
            int meio = vetor.length / 2;
            int[] esquerda = Arrays.copyOfRange(vetor, 0, meio);
            int[] direita = Arrays.copyOfRange(vetor, meio, vetor.length);

            mergeSort(esquerda);
            mergeSort(direita);

            int i = 0, j = 0, k = 0;

            while (i < esquerda.length && j < direita.length) {
                iteracoes++;
                if (esquerda[i] < direita[j]) {
                    vetor[k++] = esquerda[i++];
                } else {
                    vetor[k++] = direita[j++];
                }
            }

            while (i < esquerda.length) {
                vetor[k++] = esquerda[i++];
            }

            while (j < direita.length) {
                vetor[k++] = direita[j++];
            }
        }
    }

    //Quick Sort
    private static void quickSort(int[] vetor, int baixo, int alto) {
        if (baixo < alto) {
            int pi = particionar(vetor, baixo, alto);

            quickSort(vetor, baixo, pi - 1);
            quickSort(vetor, pi + 1, alto);
        }
    }

    //particionar o vetor no Quick Sort
    private static int particionar(int[] vetor, int baixo, int alto) {
        int pivo = vetor[alto];
        int i = baixo - 1;

        for (int j = baixo; j < alto; j++) {
            iteracoes++;
            if (vetor[j] < pivo) {
                i++;
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
                trocas++;
            }
        }

        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[alto];
        vetor[alto] = temp;
        trocas++;

        return i + 1;
    }
}
