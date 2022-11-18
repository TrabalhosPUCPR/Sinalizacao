import java.util.concurrent.Semaphore;

public class Main{
    public static void main(String[] args) {

        Semaphore gen_semaphore = new Semaphore(1);
        Semaphore pad_gen_semaphore = new Semaphore(0);
        Semaphore pad_con_semaphore = new Semaphore(0);
        Semaphore con_semaphore = new Semaphore(0);

        Gerador gerador = new Gerador(gen_semaphore, pad_gen_semaphore);
        Padronizador padronizador = new Padronizador(pad_gen_semaphore, pad_con_semaphore, gerador, con_semaphore);
        Contador contador = new Contador(con_semaphore, pad_con_semaphore, padronizador);

        gerador.start();
        padronizador.start();
        contador.start();
    }
}
