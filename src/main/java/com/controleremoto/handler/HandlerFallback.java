package com.controleremoto.handler;

import com.controleremoto.model.Requisicao;

public class HandlerFallback extends HandlerBase {

    public HandlerFallback() {
        super("HandlerFallback");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return true;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        System.out.printf("  ⚠️  [%s] Comando '%s' não reconhecido para o dispositivo '%s'. Ignorado.%n",
                nomeHandler, requisicao.getComando(), requisicao.getDispositivo());
    }
}
