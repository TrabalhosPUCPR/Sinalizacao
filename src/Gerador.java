import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Gerador extends Thread{
    Semaphore semaphore, pad_semaphore;
    Semaphore list_semaphore = new Semaphore(1);
    LinkedList<String> queue = new LinkedList<>();

    public Gerador(Semaphore semaphore, Semaphore pad_semaphore) {
        this.semaphore = semaphore;
        this.pad_semaphore = pad_semaphore;
    }

    @Override
    public void run() {
        try {
            while (true){
                this.semaphore.acquire();
                StringBuilder texto = new StringBuilder();
                Random generator = new Random();
                for(int i = 0; i < 10; i++){
                    int r = generator.nextInt(65, 117);
                    if (r > 90) r += 6;
                    texto.append((char) r);
                }
                list_semaphore.acquire();
                this.queue.add(texto.toString());
                list_semaphore.release();
                this.pad_semaphore.release();
                this.semaphore.release();
                System.out.println("GERADO: " + texto);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
