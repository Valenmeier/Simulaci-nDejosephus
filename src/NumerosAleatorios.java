import java.util.Arrays;
import java.util.Random;

public class NumerosAleatorios {

    public void josephusConArregloSimple(int n, int k) {
        long inicio = System.nanoTime();

        boolean[] vivos = new boolean[n];
        for (int i = 0; i < n; i++) vivos[i] = true;

        int index = 0;
        int contador = 0;
        int vivosRestantes = n;

        while (vivosRestantes > 1) {
            if (vivos[index]) {
                contador++;
                if (contador == k) {
                    vivos[index] = false;
                    vivosRestantes--;
                    contador = 0;
                }
            }
            index = (index + 1) % n;
        }

        int sobreviviente = -1;
        for (int i = 0; i < n; i++) {
            if (vivos[i]) {
                sobreviviente = i + 1;
                break;
            }
        }

        long fin = System.nanoTime();

        System.out.println("=== Método con Arreglo Simple ===");
        System.out.println("n = " + n + ", k = " + k);
        System.out.println("Sobreviviente: " + sobreviviente);
        System.out.println("Tiempo de ejecución (ns): " + (fin - inicio));
        System.out.println();
    }

    public void josephusConListaCircular(int n, int k) {
        long inicio = System.nanoTime();

        Nodo cabeza = new Nodo(1);
        Nodo actual = cabeza;
        for (int i = 2; i <= n; i++) {
            actual.siguiente = new Nodo(i);
            actual = actual.siguiente;
        }
        actual.siguiente = cabeza; // cerrar círculo

        Nodo anterior = actual;
        actual = cabeza;

        while (actual.siguiente != actual) {
            for (int i = 1; i < k; i++) {
                anterior = actual;
                actual = actual.siguiente;
            }
            anterior.siguiente = actual.siguiente; // eliminar
            actual = actual.siguiente;
        }

        long fin = System.nanoTime();

        System.out.println("=== Método con Lista Enlazada Circular ===");
        System.out.println("n = " + n + ", k = " + k);
        System.out.println("Sobreviviente: " + actual.valor);
        System.out.println("Tiempo de ejecución (ns): " + (fin - inicio));
        System.out.println();
    }

    private static class Nodo {
        int valor;
        Nodo siguiente;
        Nodo(int valor) {
            this.valor = valor;
        }
    }


    public void josephusConArbolBinario(int n, int k) {
        long inicio = System.nanoTime();
        AuxArbol.NodoArbol raiz = null;
        for (int i = 1; i <= n; i++) {
            raiz = AuxArbol.insertar(raiz, i);
        }
        java.util.List<Integer> vivos = new java.util.ArrayList<>();
        AuxArbol.inOrder(raiz, vivos);
        int index = 0;
        while (vivos.size() > 1) {
            index = (index + k - 1) % vivos.size();
            int eliminado = vivos.remove(index);
            raiz = AuxArbol.eliminar(raiz, eliminado);
        }
        long fin = System.nanoTime();

        System.out.println("=== Método con Árbol Binario de Búsqueda ===");
        System.out.println("n = " + n + ", k = " + k);
        System.out.println("Sobreviviente: " + vivos.get(0));
        System.out.println("Tiempo de ejecución (ns): " + (fin - inicio));
        System.out.println();
    }
    public void probarRandom() {
        long semilla = 12345L;
        Random r1 = new Random(semilla);

        int[] seq1 = new int[100];
        for (int i = 0; i < seq1.length; i++) {
            seq1[i] = r1.nextInt(10);
        }

        System.out.println("Secuencia 1:");
        for (int n : seq1) System.out.print(n + " ");
        System.out.println("\n");

        Random r2 = new Random(semilla);

        int[] seq2 = new int[100];
        for (int i = 0; i < seq2.length; i++) {
            seq2[i] = r2.nextInt(10);
        }

        System.out.println("Secuencia 2:");
        for (int n : seq2) System.out.print(n + " ");
        System.out.println("\n");

        System.out.println("¿Secuencia 1 es igual a Secuencia 2? " + java.util.Arrays.equals(seq1, seq2));
        System.out.println();

        Random r3 = new Random(99999L);

        int[] seq3 = new int[100];
        for (int i = 0; i < seq3.length; i++) {
            seq3[i] = r3.nextInt(10);
        }

        System.out.println("Secuencia con nueva semilla:");
        for (int n : seq3) System.out.print(n + " ");
        System.out.println("\n");

        System.out.println("¿Secuencia 1 es igual a Secuencia con nueva semilla? " +
                java.util.Arrays.equals(seq1, seq3));
        System.out.println();

        System.out.println("Objetos Random con misma semilla ===");

        long nuevaSemilla = 2024L;

        Random a = new Random(nuevaSemilla);
        Random b = new Random(nuevaSemilla);

        int[] seqA = new int[100];
        int[] seqB = new int[100];

        for (int i = 0; i < 100; i++) {
            seqA[i] = a.nextInt(10);
            seqB[i] = b.nextInt(10);
        }

        System.out.println("Secuencia A:");
        for (int n : seqA) System.out.print(n + " ");

        System.out.println("\n\nSecuencia B:");
        for (int n : seqB) System.out.print(n + " ");

        boolean iguales = Arrays.equals(seqA, seqB);

        System.out.println("\n\n¿Las dos secuencias son idénticas? " + iguales);
        System.out.println();
    }



}
