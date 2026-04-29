package com.controleremoto.handler;

import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class HandlerCanal extends HandlerBase {

    private int canalAtual = 1;
    private static final int CANAL_MIN = 1;
    private static final int CANAL_MAX = 999;

    public HandlerCanal() {
        super("HandlerCanal");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return requisicao.getComando() == Comando.PROXIMO_CANAL
                || requisicao.getComando() == Comando.CANAL_ANTERIOR;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        int canalAnterior = canalAtual;

        if (requisicao.getComando() == Comando.PROXIMO_CANAL) {
            canalAtual = (canalAtual >= CANAL_MAX) ? CANAL_MIN : canalAtual + 1;
            System.out.printf("  📺 [%s] Canal: %d → %d (próximo)%n",
                    nomeHandler, canalAnterior, canalAtual);
        } else {
            canalAtual = (canalAtual <= CANAL_MIN) ? CANAL_MAX : canalAtual - 1;
            System.out.printf("  📺 [%s] Canal: %d → %d (anterior)%n",
                    nomeHandler, canalAnterior, canalAtual);
        }
    }

    public int getCanalAtual() { return canalAtual; }
}
