package com.controleremoto.device;

import com.controleremoto.handler.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

public class ControleRemoto {

    private final HandlerControle cadeia;
    private final String dispositivoAlvo;

    public ControleRemoto(String dispositivoAlvo) {
        this.dispositivoAlvo = dispositivoAlvo;
        this.cadeia = montarCadeia();
    }

    private HandlerControle montarCadeia() {
        HandlerEnergia energia        = new HandlerEnergia();
        HandlerVolume volume          = new HandlerVolume();
        HandlerCanal canal            = new HandlerCanal();
        HandlerBrilho brilho          = new HandlerBrilho();
        HandlerModoEspecial especial  = new HandlerModoEspecial();
        HandlerFallback fallback      = new HandlerFallback();

        energia.setProximo(volume)
                .setProximo(canal)
                .setProximo(brilho)
                .setProximo(especial)
                .setProximo(fallback);

        return energia;
    }

    public void enviarComando(Comando comando) {
        Requisicao req = new Requisicao(comando, dispositivoAlvo);
        System.out.println("▶ " + req);
        cadeia.processar(req);
        System.out.println();
    }

    public void enviarComando(Comando comando, int parametro) {
        Requisicao req = new Requisicao(comando, dispositivoAlvo, parametro);
        System.out.println("▶ " + req);
        cadeia.processar(req);
        System.out.println();
    }
}
