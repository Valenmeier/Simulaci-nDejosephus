
public class NumerosAleatorios {

    public void resolverConArregloSimple(int n, int k) {
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

    public void resolverConListaCircular(int n, int k) {
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


    public void resolverConArbolBinario(int n, int k) {
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



}
