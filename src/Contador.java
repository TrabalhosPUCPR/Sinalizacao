import java.util.Random;
import java.util.concurrent.Semaphore;

public class Contador extends Thread{
    Padronizador padronizador;
    Semaphore semaphore, pad_con_semaphore;

    public Contador(Semaphore semaphore, Semaphore pad_con_semaphore, Padronizador padronizador) {
        this.padronizador = padronizador;
        this.semaphore = semaphore;
        this.pad_con_semaphore = pad_con_semaphore;
    }

    @Override
    public void run() {
        try {
            char[] vogais = {'A','E','I','O','U'};
            while (true){
                this.semaphore.acquire();
                int cont = 0;
                padronizador.list_semaphore.acquire();
                String text = padronizador.bufferTexto.poll();
                padronizador.list_semaphore.release();
                for(char i : text.toCharArray()){
                    for(char c : vogais){
                        if(i == c){
                            cont++;
                            break;
                        }
                    }
                }
                System.out.println("CONTADO: " + cont);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
