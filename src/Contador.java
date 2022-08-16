import java.util.Random;
import java.util.concurrent.Semaphore;

public class Contador extends Thread{
    int cont = 0;
    Padronizador padronizador;
    Semaphore semaphore;

    public Contador(Semaphore semaphore, Padronizador padronizador) {
        this.padronizador = padronizador;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            this.semaphore.acquire();
            char[] vogais = {'A','E','I','O','U'};
            int cont = 0;
            for(char i : padronizador.texto.toCharArray()){
                for(char c : vogais){
                    if(i == c){
                        cont++;
                        break;
                    }
                }
            }
            System.out.println("Numero de vogais: " + cont);
            this.semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
