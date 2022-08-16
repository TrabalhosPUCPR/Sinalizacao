# Sinalizacao
Sinalizacao de threads no Java

## Questao proposta:

Implemente uma aplicação composta por três threads, denominadas Gerador,
Padronizador e Contador, que executam em pipeline, com as seguintes definições:
### Gerador:
#### LOOP:
1. Gera um texto aleatório contendo 10 letras, incluindo minúsculas e maiúsculas
2. Sinaliza o Padronizador (por meio de semáforo) que um novo texto está
disponível

### Padronizador:
#### LOOP:
1. Espera um novo texto ser disponibilizado pelo Gerador
2. Gera uma nova versão do texto com todas as letras em maiúsculo (converte as
minúsculas em maiúsculas)
3. Sinaliza o Contador (por meio de semáforo) que um novo texto está disponível
### Contador:
#### LOOP:
1. Espera um novo texto ser disponibilizado pelo Padronizador
2. Conta e imprime o número de vogais no texto
Importante: deve-se garantir que o Gerador e o Contador executem de forma
independente, isto é, quando o Contador está verificando um texto, o Gerador pode
estar criando um novo texto.
