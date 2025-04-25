package io.github.paulosdoliveira.msavaliadorcartao.ex;

public class ErroComunicacaoMicroServiceException extends Exception {


    private final Integer status;

    public ErroComunicacaoMicroServiceException(String erro, Integer status) {
        super(erro);
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }
}
