package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerBrilhoTest {

    private HandlerBrilho handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerBrilho();
    }

    @Test(descricao = "Brilho inicial deve ser 70")
    void brilhoInicialSetenta() {
        assertEquals(70, handler.getBrilho(), "Brilho inicial deve ser 70");
    }

    @Test(descricao = "AUMENTAR_BRILHO sem parâmetro incrementa 10")
    void aumentarBrilhoPadrao() {
        handler.processar(new Requisicao(Comando.AUMENTAR_BRILHO, "TV"));
        assertEquals(80, handler.getBrilho(), "Brilho deve ser 80 após aumento padrão");
    }

    @Test(descricao = "DIMINUIR_BRILHO sem parâmetro decrementa 10")
    void diminuirBrilhoPadrao() {
        handler.processar(new Requisicao(Comando.DIMINUIR_BRILHO, "TV"));
        assertEquals(60, handler.getBrilho(), "Brilho deve ser 60 após redução padrão");
    }

    @Test(descricao = "AUMENTAR_BRILHO com parâmetro usa o valor fornecido")
    void aumentarBrilhoComParametro() {
        handler.processar(new Requisicao(Comando.AUMENTAR_BRILHO, "TV", 15));
        assertEquals(85, handler.getBrilho(), "Brilho deve ser 85 após +15");
    }

    @Test(descricao = "Brilho não ultrapassa 100")
    void brilhoNaoUltrapassaMaximo() {
        handler.processar(new Requisicao(Comando.AUMENTAR_BRILHO, "TV", 100));
        assertEquals(100, handler.getBrilho(), "Brilho deve ser limitado a 100");
    }

    @Test(descricao = "Brilho não fica abaixo de 0")
    void brilhoNaoFicaNegativo() {
        handler.processar(new Requisicao(Comando.DIMINUIR_BRILHO, "TV", 100));
        assertEquals(0, handler.getBrilho(), "Brilho não pode ser negativo");
    }

    @Test(descricao = "Comando de energia é encaminhado ao próximo handler")
    void comandoEnergiaEncaminhado() {
        SpyHandler spy = new SpyHandler(); handler.setProximo(spy);
        handler.processar(new Requisicao(Comando.LIGAR, "TV"));
        assertTrue(spy.foiChamado, "LIGAR deve ser encaminhado adiante");
    }
}
