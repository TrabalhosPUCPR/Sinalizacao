import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread{
    Semaphore semaphore;
    String texto = "";
    Semaphore pad_padronizador;

    public Gerador(Semaphore semaphore, Semaphore pad_semaphore) {
        this.semaphore = semaphore;
        this.pad_padronizador = pad_semaphore;
    }

    @Override
    public void run() {
        try {
            this.semaphore.acquire();
            this.texto = "";
            Random generator = new Random();
            for(int i = 0; i < 10; i++){
                /*
                a-z na ascii table e 65-90
                A-Z na ascii table e 97-122
                */
                int r = generator.nextInt(65, 117);
                if (r > 90) r += 6;
                this.texto += (char) r;
            }
            this.semaphore.release();
            this.pad_padronizador.release();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
