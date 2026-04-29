package com.controleremoto.handler;

import com.controleremoto.model.Requisicao;

public interface HandlerControle {

    HandlerControle setProximo(HandlerControle proximo);

    void processar(Requisicao requisicao);
}
