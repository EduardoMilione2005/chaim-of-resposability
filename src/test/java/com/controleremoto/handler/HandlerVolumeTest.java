package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerVolumeTest {

    private HandlerVolume handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerVolume();
    }

    @Test(descricao = "Volume inicial deve ser 50")
    void volumeInicialCinquenta() {
        assertEquals(50, handler.getVolume(), "Volume inicial deve ser 50");
    }

    @Test(descricao = "Som não deve iniciar mutado")
    void naoIniciarMutado() {
        assertFalse(handler.isMutado(), "Não deve iniciar mutado");
    }

    @Test(descricao = "AUMENTAR_VOLUME sem parâmetro incrementa 5")
    void aumentarVolumePadrao() {
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV"));
        assertEquals(55, handler.getVolume(), "Volume deve ser 55 após aumento padrão");
    }

    @Test(descricao = "AUMENTAR_VOLUME com parâmetro usa o valor fornecido")
    void aumentarVolumeComParametro() {
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV", 20));
        assertEquals(70, handler.getVolume(), "Volume deve ser 70 após +20");
    }

    @Test(descricao = "DIMINUIR_VOLUME sem parâmetro decrementa 5")
    void diminuirVolumePadrao() {
        handler.processar(new Requisicao(Comando.DIMINUIR_VOLUME, "TV"));
        assertEquals(45, handler.getVolume(), "Volume deve ser 45 após redução padrão");
    }

    @Test(descricao = "Volume não ultrapassa 100")
    void volumeNaoUltrapassaMaximo() {
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV", 100));
        assertEquals(100, handler.getVolume(), "Volume deve ser limitado a 100");
    }

    @Test(descricao = "Volume não fica abaixo de 0")
    void volumeNaoFicaAbaixoDeZero() {
        handler.processar(new Requisicao(Comando.DIMINUIR_VOLUME, "TV", 100));
        assertEquals(0, handler.getVolume(), "Volume não pode ser negativo");
    }

    @Test(descricao = "MUTAR deve ativar mute")
    void mutarAtivaMute() {
        handler.processar(new Requisicao(Comando.MUTAR, "TV"));
        assertTrue(handler.isMutado(), "Som deve estar mutado");
    }

    @Test(descricao = "MUTAR duas vezes remove mute")
    void mutarDuasVezesRemoveMute() {
        handler.processar(new Requisicao(Comando.MUTAR, "TV"));
        handler.processar(new Requisicao(Comando.MUTAR, "TV"));
        assertFalse(handler.isMutado(), "Segundo MUTAR deve remover o mute");
    }

    @Test(descricao = "DIMINUIR_VOLUME desativa mute automaticamente")
    void diminuirVolumeRemoveMute() {
        handler.processar(new Requisicao(Comando.MUTAR, "TV"));
        assertTrue(handler.isMutado());
        handler.processar(new Requisicao(Comando.DIMINUIR_VOLUME, "TV"));
        assertFalse(handler.isMutado(), "DIMINUIR_VOLUME deve remover mute");
    }

    @Test(descricao = "AUMENTAR_VOLUME desativa mute automaticamente")
    void aumentarVolumeRemoveMute() {
        handler.processar(new Requisicao(Comando.MUTAR, "TV"));
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV"));
        assertFalse(handler.isMutado(), "AUMENTAR_VOLUME deve remover mute");
    }

    @Test(descricao = "Comando de canal é encaminhado ao próximo handler")
    void comandoCanalEncaminhado() {
        SpyHandler spy = new SpyHandler(); handler.setProximo(spy);
        handler.processar(new Requisicao(Comando.PROXIMO_CANAL, "TV"));
        assertTrue(spy.foiChamado, "Próximo handler deve ser chamado");
    }
}
