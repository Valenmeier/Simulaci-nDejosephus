public class AuxArbol {

    public static class NodoArbol {
        int valor;
        NodoArbol izquierda;
        NodoArbol derecha;
        NodoArbol(int valor) {
            this.valor = valor;
        }
    }

    public static NodoArbol insertar(NodoArbol raiz, int valor) {
        if (raiz == null) return new NodoArbol(valor);
        if (valor < raiz.valor)
            raiz.izquierda = insertar(raiz.izquierda, valor);
        else
            raiz.derecha = insertar(raiz.derecha, valor);
        return raiz;
    }

    public static NodoArbol eliminar(NodoArbol raiz, int valor) {
        if (raiz == null) return null;

        if (valor < raiz.valor) {
            raiz.izquierda = eliminar(raiz.izquierda, valor);
        } else if (valor > raiz.valor) {
            raiz.derecha = eliminar(raiz.derecha, valor);
        } else {

            if (raiz.izquierda == null && raiz.derecha == null)
                return null;

            else if (raiz.izquierda == null)
                return raiz.derecha;
            else if (raiz.derecha == null)
                return raiz.izquierda;

            else {
                NodoArbol sucesor = minimo(raiz.derecha);
                raiz.valor = sucesor.valor;
                raiz.derecha = eliminar(raiz.derecha, sucesor.valor);
            }
        }
        return raiz;
    }


    public static NodoArbol minimo(NodoArbol nodo) {
        while (nodo.izquierda != null) {
            nodo = nodo.izquierda;
        }
        return nodo;
    }


    public static void inOrder(NodoArbol raiz, java.util.List<Integer> lista) {
        if (raiz != null) {
            inOrder(raiz.izquierda, lista);
            lista.add(raiz.valor);
            inOrder(raiz.derecha, lista);
        }
    }

}
