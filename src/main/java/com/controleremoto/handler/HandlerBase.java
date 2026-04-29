package com.controleremoto.handler;

import com.controleremoto.model.Requisicao;


public abstract class HandlerBase implements HandlerControle {

    private HandlerControle proximo;
    protected final String nomeHandler;

    public HandlerBase(String nomeHandler) {
        this.nomeHandler = nomeHandler;
    }

    @Override
    public HandlerControle setProximo(HandlerControle proximo) {
        this.proximo = proximo;
        return proximo;
    }

    @Override
    public void processar(Requisicao requisicao) {
        if (podeProcessar(requisicao)) {
            executar(requisicao);
        } else if (proximo != null) {
            System.out.printf("  [%s] Não é meu comando. Passando adiante...%n", nomeHandler);
            proximo.processar(requisicao);
        } else {
            System.out.printf("  ❌ Nenhum handler conseguiu processar: %s%n", requisicao.getComando());
        }
    }
    
    protected abstract boolean podeProcessar(Requisicao requisicao);
    
    protected abstract void executar(Requisicao requisicao);
}
