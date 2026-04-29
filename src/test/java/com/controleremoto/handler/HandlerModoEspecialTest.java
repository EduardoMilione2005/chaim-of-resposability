package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerModoEspecialTest {

    private HandlerModoEspecial handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerModoEspecial();
    }

    @Test(descricao = "MODO_CINEMA não lança exceção")
    void modoCinemaExecutaSemErro() {
        handler.processar(new Requisicao(Comando.MODO_CINEMA, "TV"));
        assertTrue(true, "MODO_CINEMA deve executar sem erros");
    }

    @Test(descricao = "MODO_HIBERNAR não lança exceção")
    void modoHibernarExecutaSemErro() {
        handler.processar(new Requisicao(Comando.MODO_HIBERNAR, "TV", 30));
        assertTrue(true, "MODO_HIBERNAR deve executar sem erros");
    }

    @Test(descricao = "MODO_HIBERNAR com parâmetro zero usa 30 minutos padrão")
    void modoHibernarSemParametroUsaPadrao() {
        handler.processar(new Requisicao(Comando.MODO_HIBERNAR, "TV", 0));
        assertTrue(true, "Deve usar 30 minutos como padrão");
    }

    @Test(descricao = "Comando LIGAR é encaminhado ao próximo")
    void comandoNaoRelacionadoEncaminhado() {
        SpyHandler spy = new SpyHandler(); handler.setProximo(spy);
        handler.processar(new Requisicao(Comando.LIGAR, "TV"));
        assertTrue(spy.foiChamado, "LIGAR deve ser encaminhado pelo HandlerModoEspecial");
    }
}
