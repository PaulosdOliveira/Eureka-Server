package io.github.paulosdoliveira.msavaliadorcartao.ex;

public class DadosClienteNotFoundException extends  Exception{
    public DadosClienteNotFoundException(){
        super("Dados não encontrados para esse CPF");
    }
}
