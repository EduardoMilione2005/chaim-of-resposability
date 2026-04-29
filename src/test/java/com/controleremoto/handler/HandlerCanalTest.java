package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class HandlerCanalTest {

    private HandlerCanal handler;

    @BeforeEach
    void setUp() {
        handler = new HandlerCanal();
    }

    @Test(descricao = "Canal inicial deve ser 1")
    void canalInicialUm() {
        assertEquals(1, handler.getCanalAtual(), "Canal inicial deve ser 1");
    }

    @Test(descricao = "PROXIMO_CANAL deve avançar o canal")
    void proximoCanalAvanca() {
        handler.processar(new Requisicao(Comando.PROXIMO_CANAL, "TV"));
        assertEquals(2, handler.getCanalAtual(), "Canal deve ser 2 após avançar");
    }

    @Test(descricao = "CANAL_ANTERIOR no canal 1 vai para 999 (wrap-around)")
    void canalAnteriorNoInicioVaiParaFinal() {
        handler.processar(new Requisicao(Comando.CANAL_ANTERIOR, "TV"));
        assertEquals(999, handler.getCanalAtual(), "Do canal 1, anterior deve ir para 999");
    }

    @Test(descricao = "PROXIMO_CANAL no canal 999 volta para 1 (wrap-around)")
    void proximoCanalNoFinalVoltaParaInicio() {
        handler.processar(new Requisicao(Comando.CANAL_ANTERIOR, "TV")); // vai para 999
        assertEquals(999, handler.getCanalAtual());
        handler.processar(new Requisicao(Comando.PROXIMO_CANAL, "TV")); // 999 → 1
        assertEquals(1, handler.getCanalAtual(), "Do canal 999, próximo deve ser 1");
    }

    @Test(descricao = "Múltiplos avanços de canal acumulam corretamente")
    void multiplosAvancosAcumulam() {
        for (int i = 0; i < 5; i++) {
            handler.processar(new Requisicao(Comando.PROXIMO_CANAL, "TV"));
        }
        assertEquals(6, handler.getCanalAtual(), "Após 5 avanços o canal deve ser 6");
    }

    @Test(descricao = "Avanço seguido de recuo retorna ao canal original")
    void avancaERecuaRetornaOriginal() {
        handler.processar(new Requisicao(Comando.PROXIMO_CANAL, "TV"));
        handler.processar(new Requisicao(Comando.CANAL_ANTERIOR, "TV"));
        assertEquals(1, handler.getCanalAtual(), "Deve retornar ao canal 1");
    }

    @Test(descricao = "Comando de volume é encaminhado ao próximo handler")
    void comandoVolumeEncaminhado() {
        SpyHandler spy = new SpyHandler(); handler.setProximo(spy);
        handler.processar(new Requisicao(Comando.AUMENTAR_VOLUME, "TV"));
        assertTrue(spy.foiChamado, "Próximo handler deve ser chamado para AUMENTAR_VOLUME");
    }
}
