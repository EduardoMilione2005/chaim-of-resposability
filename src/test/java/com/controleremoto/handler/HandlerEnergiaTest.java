package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerEnergiaTest {

    private HandlerEnergia handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerEnergia();
    }

    @Test(descricao = "Dispositivo deve iniciar desligado")
    void dispositivoIniciaDesligado() {
        assertFalse(handler.isLigado(), "Estado inicial deve ser desligado");
    }

    @Test(descricao = "LIGAR deve ligar o dispositivo")
    void ligarDispositivoDesligado() {
        handler.processar(new Requisicao(Comando.LIGAR, "TV"));
        assertTrue(handler.isLigado(), "Dispositivo deve estar ligado após LIGAR");
    }

    @Test(descricao = "DESLIGAR deve desligar o dispositivo")
    void desligarDispositivoLigado() {
        handler.processar(new Requisicao(Comando.LIGAR, "TV"));
        handler.processar(new Requisicao(Comando.DESLIGAR, "TV"));
        assertFalse(handler.isLigado(), "Dispositivo deve estar desligado após DESLIGAR");
    }

    @Test(descricao = "LIGAR dispositivo já ligado não altera estado")
    void ligarDispotitivoJaLigado() {
        handler.processar(new Requisicao(Comando.LIGAR, "TV"));
        handler.processar(new Requisicao(Comando.LIGAR, "TV")); // idempotente
        assertTrue(handler.isLigado(), "Deve permanecer ligado");
    }

    @Test(descricao = "DESLIGAR dispositivo já desligado não altera estado")
    void desligarDispositivoJaDesligado() {
        handler.processar(new Requisicao(Comando.DESLIGAR, "TV")); // já está desligado
        assertFalse(handler.isLigado(), "Deve permanecer desligado");
    }

    @Test(descricao = "Comando não relacionado é passado ao próximo handler")
    void comandoNaoRelacionadoEncaminhado() {
        SpyHandler spy = new SpyHandler(); handler.setProximo(spy);

        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV"));
        assertTrue(spy.foiChamado, "Próximo handler deve ser chamado para comando de volume");
    }

    @Test(descricao = "Sem próximo handler, comando desconhecido não lança exceção")
    void semProximoHandlerNaoExplode() {
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV"));
        assertFalse(handler.isLigado(), "Estado não deve mudar");
    }
}
