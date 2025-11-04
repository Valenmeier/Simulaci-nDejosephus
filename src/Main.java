import java.util.Random;

public class Main {
    public static void main(String[] args) {
        NumerosAleatorios josephus = new NumerosAleatorios();
        int n = 20;
        int k = 4;
        josephus.resolverConArregloSimple(n, k);
        josephus.resolverConListaCircular(n, k);
        josephus.resolverConArbolBinario(n, k);
    }
}
