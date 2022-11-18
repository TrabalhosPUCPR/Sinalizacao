import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Padronizador extends Thread{
    Semaphore pad_gen_semaphore, con_semaphore, pad_con_semaphore;
    Queue<String> bufferTexto = new LinkedList<>();
    Semaphore list_semaphore = new Semaphore(1);
    Gerador gerador;

    public Padronizador(Semaphore pad_gen_semaphore, Semaphore pad_con_semaphore, Gerador gerador, Semaphore con_semaphore) {
        this.pad_gen_semaphore = pad_gen_semaphore;
        this.pad_con_semaphore = pad_con_semaphore;
        this.gerador = gerador;
        this.con_semaphore = con_semaphore;
    }

    @Override
    public void run() {
        try {
            while (true){
                this.pad_gen_semaphore.acquire();
                gerador.list_semaphore.acquire();
                String text = gerador.queue.poll().toUpperCase();
                gerador.list_semaphore.release();
                list_semaphore.acquire();
                this.bufferTexto.add(text);
                list_semaphore.release();
                this.con_semaphore.release();
                System.out.println("PADRONIZADO: " + text);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
