package com.controleremoto.handler;

import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class HandlerEnergia extends HandlerBase {

    private boolean ligado = false;

    public HandlerEnergia() {
        super("HandlerEnergia");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return requisicao.getComando() == Comando.LIGAR
                || requisicao.getComando() == Comando.DESLIGAR;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        if (requisicao.getComando() == Comando.LIGAR) {
            if (ligado) {
                System.out.printf("  ⚡ [%s] %s já está ligado.%n",
                        nomeHandler, requisicao.getDispositivo());
            } else {
                ligado = true;
                System.out.printf("  ✅ [%s] %s LIGADO com sucesso!%n",
                        nomeHandler, requisicao.getDispositivo());
            }
        } else {
            if (!ligado) {
                System.out.printf("  ⚡ [%s] %s já está desligado.%n",
                        nomeHandler, requisicao.getDispositivo());
            } else {
                ligado = false;
                System.out.printf("  🔴 [%s] %s DESLIGADO com sucesso!%n",
                        nomeHandler, requisicao.getDispositivo());
            }
        }
    }

    public boolean isLigado() {
        return ligado;
    }
}
