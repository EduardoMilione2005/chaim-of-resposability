package com.controleremoto.handler;

import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class HandlerVolume extends HandlerBase {

    private int volume = 50;
    private boolean mutado = false;
    private static final int VOLUME_MIN = 0;
    private static final int VOLUME_MAX = 100;

    public HandlerVolume() {
        super("HandlerVolume");
    }

    @Override
    protected boolean podeProcessar(Requisicao requisicao) {
        return requisicao.getComando() == Comando.AUMENTAR_VOLUME
                || requisicao.getComando() == Comando.DIMINUIR_VOLUME
                || requisicao.getComando() == Comando.MUTAR;
    }

    @Override
    protected void executar(Requisicao requisicao) {
        switch (requisicao.getComando()) {
            case AUMENTAR_VOLUME -> {
                if (mutado) desfazerMute();
                int incremento = requisicao.getParametro() > 0 ? requisicao.getParametro() : 5;
                volume = Math.min(volume + incremento, VOLUME_MAX);
                System.out.printf("  🔊 [%s] Volume aumentado → %d%%%n", nomeHandler, volume);
            }
            case DIMINUIR_VOLUME -> {
                if (mutado) desfazerMute();
                int decremento = requisicao.getParametro() > 0 ? requisicao.getParametro() : 5;
                volume = Math.max(volume - decremento, VOLUME_MIN);
                System.out.printf("  🔉 [%s] Volume diminuído → %d%%%n", nomeHandler, volume);
            }
            case MUTAR -> {
                mutado = !mutado;
                if (mutado) {
                    System.out.printf("  🔇 [%s] Som MUTADO!%n", nomeHandler);
                } else {
                    System.out.printf("  🔊 [%s] Mute removido. Volume: %d%%%n", nomeHandler, volume);
                }
            }
        }
    }

    private void desfazerMute() {
        mutado = false;
        System.out.printf("  🔊 [%s] Mute desativado automaticamente.%n", nomeHandler);
    }

    public int getVolume() { return volume; }
    public boolean isMutado() { return mutado; }
}
