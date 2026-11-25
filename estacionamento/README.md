# Sistema de Gerenciamento de Estacionamento

Projeto final de Java (Programação Orientada a Objetos), versão 1, com interação pelo console.

## Funcionalidades
- Cadastrar veículo (placa, marca, modelo e tipo: carro, moto ou caminhonete)
- Registrar entrada (associa o veículo a uma vaga livre)
- Registrar saída (calcula o tempo de permanência e o valor, e libera a vaga)
- Consultar vagas, listar veículos estacionados e consultar veículo pela placa
- Histórico de entradas e saídas

## Conceitos de POO usados
Classes e objetos, encapsulamento, herança (`Veiculo` -> `Carro`, `Moto`, `Caminhonete`),
polimorfismo (cada tipo calcula o valor do seu jeito) e interface (`Cobravel`).

## Como rodar
Na pasta do projeto:

```
javac -d bin src/*.java
java -cp bin Main
```

Requer JDK 17 ou superior.
