package com.controleremoto.handler;

import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class HandlerBrilho extends HandlerBase {

    private int brilho = 70;
    private static final int BRILHO_MIN = 0;
    private static final int BRILHO_MAX = 100;

    public HandlerBrilho() {
        super("HandlerBrilho");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return requisicao.getComando() == Comando.AUMENTAR_BRILHO
                || requisicao.getComando() == Comando.DIMINUIR_BRILHO;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        int passo = requisicao.getParametro() > 0 ? requisicao.getParametro() : 10;

        if (requisicao.getComando() == Comando.AUMENTAR_BRILHO) {
            brilho = Math.min(brilho + passo, BRILHO_MAX);
            System.out.printf("  ☀️  [%s] Brilho aumentado → %d%%%n", nomeHandler, brilho);
        } else {
            brilho = Math.max(brilho - passo, BRILHO_MIN);
            System.out.printf("  🌑 [%s] Brilho diminuído → %d%%%n", nomeHandler, brilho);
        }
    }

    public int getBrilho() { return brilho; }
}
