import java.util.concurrent.Semaphore;

public class main{
    public static void main(String[] args) {

        Semaphore gen_semaphore = new Semaphore(1);
        Semaphore pad_semaphore = new Semaphore(0);
        Semaphore con_semaphore = new Semaphore(0);

        Gerador gerador = new Gerador(gen_semaphore, pad_semaphore);
        Padronizador padronizador = new Padronizador(pad_semaphore, gerador, con_semaphore);
        Contador contador = new Contador(con_semaphore, padronizador);

        gerador.run();
        padronizador.run();
        contador.run();

        try {
            gerador.join();
            padronizador.join();
            System.out.println("String criada pelo gerador: " + gerador.texto);
            System.out.println("String criada pelo padronizador: " + padronizador.texto);
            contador.join();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
