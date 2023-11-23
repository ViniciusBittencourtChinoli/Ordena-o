/*
 * Clique em nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt para alterar esta licença
 * Clique em nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java para editar este modelo
 */
package ordenacao;

/**
 *
 * @author itzvi
 */
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TesteOrdenacao {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //int[] tamanhos = {50, 500, 1000, 5000, 10000};
        int[] tamanhos = {8};

        System.out.println("Escolha o algoritmo de ordenação:");
        System.out.println("1. Insertion Sort");
        System.out.println("2. Quick Sort");
        System.out.println("3. Merge Sort");

        int escolha = scanner.nextInt();

        switch (escolha) {
            case 1:
                testeAlgoritmo("Insertion Sort", tamanhos);
                break;
            case 2:
                testeAlgoritmo("Quick Sort", tamanhos);
                break;
            case 3:
                testeAlgoritmo("Merge Sort", tamanhos);
                break;
        }
        scanner.close();
    }
    
    private static int trocasInsercao = 0;
    private static int iteracoesInsercao = 0;  
    private static int trocasMergeSort = 0;
    private static int iteracoesMergeSort = 0;
    private static int trocasQuickSort = 0;
    private static int iteracoesQuickSort = 0;
    
    private static void testeAlgoritmo(String algoritmo, int[] tamanhos) {
        System.out.println("Teste para " + algoritmo + ":");

        for (int tamanho : tamanhos) {
            long tempoTotal = 0;
            int totalTrocas = 0;
            int totalIteracoes = 0;

            for (int i = 0; i < 5; i++) {
                trocasInsercao = 0;
                iteracoesInsercao = 0;
                trocasQuickSort = 0;
                iteracoesQuickSort = 0;
                trocasMergeSort = 0;
                iteracoesMergeSort = 0;
                //int[] array = {4, 8, 6, 3, 7, 1};
                int[] array = gerarArrayAleatorio(tamanho);
                long inicio = System.nanoTime();
                

                switch (algoritmo) {
                    case "Insertion Sort":
                        insertSort(array);
                        totalTrocas += trocasInsercao;
                        totalIteracoes += iteracoesInsercao;
                        break;
                    case "Quick Sort":
                        quickSort(array, 0, array.length - 1);
                        totalTrocas += trocasQuickSort;
                        totalIteracoes += iteracoesQuickSort;
                        break;
                    case "Merge Sort":
                        mergeSort(array);
                        totalTrocas += trocasMergeSort;
                        totalIteracoes += iteracoesMergeSort;
                        break;
                }

                long fim = System.nanoTime();
                tempoTotal += fim - inicio;
            }

            // Calcular médias e imprimir resultados
            double mediaTempo = tempoTotal / 5.0;
            int mediaTrocas = totalTrocas / 5;
            int mediaIteracoes = totalIteracoes / 5;

            System.out.println("Tamanho do vetor: " + tamanho);
            System.out.println("Média Tempo: " + mediaTempo + " nanos");
            System.out.println("Média Trocas: " + mediaTrocas);
            System.out.println("Média Iterações: " + mediaIteracoes);
            System.out.println();
        }
    }

    
    private static int[] gerarArrayAleatorio(int tamanho) {
        int[] array = new int[tamanho];
        Random random = new Random();
        for (int i = 0; i < tamanho; i++) {
            array[i] = random.nextInt(1000);
        }
        return array;
    }

    private static void insertSort(int[] array) {
        int n = array.length;

        for (int i = 1; i < n; ++i) {
            int chave = array[i];
            int j = i - 1;

            // Encontrar a posição correta para o elemento atual
            while (j >= 0 && array[j] > chave) {
                array[j + 1] = array[j];
                j = j - 1;
                //System.out.println(Arrays.toString(array));
                //System.out.println(trocasInsercao);
                trocasInsercao++;
            }           

            // Inserir o elemento atual na posição correta
            array[j + 1] = chave;
            //System.out.println(Arrays.toString(array));
            //System.out.println(iteracoesInsercao);
            iteracoesInsercao++;
        }
    }
   


        private static void quickSort(int[] array, int inicio, int fim) {
            if (inicio < fim) {
                int indiceParticao = particionar(array, inicio, fim);

                quickSort(array, inicio, indiceParticao - 1);
                quickSort(array, indiceParticao + 1, fim);
                //System.out.println(Arrays.toString(array));
            }
        }

        private static int particionar(int[] array, int inicio, int fim) {
            int pivo = array[fim];
            int i = inicio - 1;
            //System.out.println(Arrays.toString(array));

            for (int j = inicio; j < fim; j++) {
                

                if (array[j] <= pivo) {
                    i++;
                    //System.out.println(trocasQuickSort);
                    trocasQuickSort++;
                    // Trocar array[i] e array[j]
                    
                    int temp = array[i];
                    array[i] = array[j];                    
                    array[j] = temp;
                    //System.out.println(Arrays.toString(array));
                }
                //System.out.println(Arrays.toString(array));
                //System.out.println(iteracoesQuickSort);
                iteracoesQuickSort++;
            }            

            // Trocar array[i+1] e array[fim]
            int temp = array[i + 1];
            array[i + 1] = array[fim];
            array[fim] = temp;
            //System.out.println(Arrays.toString(array));
            return i + 1;
        }      


    private static int[] mergeSort(int[] array) {
        if (array.length <= 1) {
            return array;
        }
        int meio = array.length / 2;
        int[] esquerda = Arrays.copyOfRange(array, 0, meio);
        int[] direita = Arrays.copyOfRange(array, meio, array.length);
        esquerda = mergeSort(esquerda);
        direita = mergeSort(direita);
        //System.out.println(Arrays.toString(array));        
        return merge(esquerda, direita);           
    }

    private static int[] merge(int[] esquerda, int[] direita) {
        int[] resultado = new int[esquerda.length + direita.length];
        int i = 0, j = 0, k = 0;

        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                resultado[k++] = esquerda[i++];
                //System.out.println(Arrays.toString(resultado));
            } else {
                resultado[k++] = direita[j++];
                //System.out.println(trocasMergeSort);
                trocasMergeSort += esquerda.length - i; // Contar trocas durante a fusão    
                //System.out.println(iteracoesMergeSort);
                iteracoesMergeSort++;
                //System.out.println(Arrays.toString(resultado));
            }
        }

        while (i < esquerda.length) {
            resultado[k++] = esquerda[i++];
            //System.out.println(Arrays.toString(resultado));
        }

        while (j < direita.length) {
            resultado[k++] = direita[j++];
            //System.out.println(Arrays.toString(resultado));            
        }
        //System.out.println(Arrays.toString(resultado));
        return resultado;
    }
}