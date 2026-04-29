package com.controleremoto.handler;

import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class HandlerModoEspecial extends HandlerBase {

    public HandlerModoEspecial() {
        super("HandlerModoEspecial");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return requisicao.getComando() == Comando.MODO_HIBERNAR
                || requisicao.getComando() == Comando.MODO_CINEMA;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        if (requisicao.getComando() == Comando.MODO_HIBERNAR) {
            int minutos = requisicao.getParametro() > 0 ? requisicao.getParametro() : 30;
            System.out.printf("  😴 [%s] Modo Hibernar ativado! Desligando em %d minutos...%n",
                    nomeHandler, minutos);
            System.out.printf("  😴 [%s] → Brilho reduzido, volume diminuído, timer configurado.%n",
                    nomeHandler);
        } else {
            System.out.printf("  🎬 [%s] Modo Cinema ativado!%n", nomeHandler);
            System.out.printf("  🎬 [%s] → Brilho ajustado para 40%%, volume para 60%%, luz ambiente desligada.%n",
                    nomeHandler);
        }
    }
}
