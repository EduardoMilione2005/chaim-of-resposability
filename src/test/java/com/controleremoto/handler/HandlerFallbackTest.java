package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerFallbackTest {

    private HandlerFallback handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerFallback();
    }

    @Test(descricao = "Fallback processa qualquer comando sem lançar exceção")
    void processaQualquerComando() {
        for (Comando cmd : Comando.values()) {
            handler.processar(new Requisicao(cmd, "TV"));
        }
        assertTrue(true, "Nenhum comando deve lançar exceção no fallback");
    }

    @Test(descricao = "Fallback absorve DESCONHECIDO")
    void processaComandoDesconhecido() {
        handler.processar(new Requisicao(Comando.DESCONHECIDO, "SoundBar"));
        assertTrue(true, "DESCONHECIDO deve ser absorvido pelo fallback");
    }
}
