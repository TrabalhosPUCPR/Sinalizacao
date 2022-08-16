import java.util.concurrent.Semaphore;

public class Padronizador extends Thread{
    Semaphore semaphore;
    String texto;
    Semaphore con_semaphore;
    Gerador gerador;

    public Padronizador(Semaphore semaphore, Gerador gerador, Semaphore con_semaphore) {
        this.semaphore = semaphore;
        this.gerador = gerador;
        this.con_semaphore = con_semaphore;
    }

    @Override
    public void run() {
        try {
            this.semaphore.acquire();
            this.texto = gerador.texto.toUpperCase();
            this.semaphore.release();
            this.con_semaphore.release();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
