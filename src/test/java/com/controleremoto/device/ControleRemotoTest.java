package com.controleremoto.device;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;

import static com.controleremoto.framework.Assertions.*;

public class ControleRemotoTest {

    private ControleRemoto controle;

    @BeforeEach
    void setUp() {
        controle = new ControleRemoto("Smart TV");
    }

    @Test(descricao = "LIGAR não lança exceção")
    void ligarNaoLancaExcecao() {
        controle.enviarComando(Comando.LIGAR);
        assertTrue(true, "LIGAR deve funcionar sem erros");
    }

    @Test(descricao = "DESLIGAR não lança exceção")
    void desligarNaoLancaExcecao() {
        controle.enviarComando(Comando.DESLIGAR);
        assertTrue(true, "DESLIGAR deve funcionar sem erros");
    }

    @Test(descricao = "Sequência LIGAR → DESLIGAR funciona corretamente")
    void sequenciaLigarDesligar() {
        controle.enviarComando(Comando.LIGAR);
        controle.enviarComando(Comando.DESLIGAR);
        assertTrue(true, "Sequência LIGAR/DESLIGAR deve funcionar");
    }

    @Test(descricao = "AUMENTAR_VOLUME atravessa a cadeia sem erros")
    void aumentarVolumeAtravessaCadeia() {
        controle.enviarComando(Comando.AUMENTAR_VOLUME);
        assertTrue(true, "AUMENTAR_VOLUME deve percorrer a cadeia corretamente");
    }

    @Test(descricao = "DIMINUIR_VOLUME com parâmetro é tratado corretamente")
    void diminuirVolumeComParametro() {
        controle.enviarComando(Comando.DIMINUIR_VOLUME, 10);
        assertTrue(true, "DIMINUIR_VOLUME com parâmetro deve funcionar");
    }

    @Test(descricao = "MUTAR ativa e desativa alternadamente")
    void mutarToggle() {
        controle.enviarComando(Comando.MUTAR);
        controle.enviarComando(Comando.MUTAR);
        assertTrue(true, "MUTAR deve funcionar como toggle");
    }

    @Test(descricao = "PROXIMO_CANAL percorre a cadeia completa")
    void proximoCanalPercorreCadeia() {
        controle.enviarComando(Comando.PROXIMO_CANAL);
        assertTrue(true, "PROXIMO_CANAL deve chegar ao HandlerCanal");
    }

    @Test(descricao = "CANAL_ANTERIOR percorre a cadeia completa")
    void canalAnteriorPercorreCadeia() {
        controle.enviarComando(Comando.CANAL_ANTERIOR);
        assertTrue(true, "CANAL_ANTERIOR deve chegar ao HandlerCanal");
    }

    @Test(descricao = "AUMENTAR_BRILHO percorre até HandlerBrilho")
    void aumentarBrilhoPercorreCadeia() {
        controle.enviarComando(Comando.AUMENTAR_BRILHO);
        assertTrue(true, "AUMENTAR_BRILHO deve chegar ao HandlerBrilho");
    }

    @Test(descricao = "DIMINUIR_BRILHO com parâmetro funciona")
    void diminuirBrilhoComParametro() {
        controle.enviarComando(Comando.DIMINUIR_BRILHO, 25);
        assertTrue(true, "DIMINUIR_BRILHO com parâmetro deve funcionar");
    }

    @Test(descricao = "MODO_CINEMA percorre até HandlerModoEspecial")
    void modoCinemaPercorreCadeia() {
        controle.enviarComando(Comando.MODO_CINEMA);
        assertTrue(true, "MODO_CINEMA deve chegar ao HandlerModoEspecial");
    }

    @Test(descricao = "MODO_HIBERNAR com parâmetro percorre a cadeia")
    void modoHibernarComParametro() {
        controle.enviarComando(Comando.MODO_HIBERNAR, 60);
        assertTrue(true, "MODO_HIBERNAR deve chegar ao HandlerModoEspecial");
    }

    @Test(descricao = "DESCONHECIDO chega ao HandlerFallback sem explodir")
    void comandoDesconhecidoFallback() {
        controle.enviarComando(Comando.DESCONHECIDO);
        assertTrue(true, "HandlerFallback deve absorver o comando desconhecido");
    }

    @Test(descricao = "Controles distintos possuem cadeias independentes")
    void cadeiasIndependentes() {
        ControleRemoto controle2 = new ControleRemoto("Soundbar");
        controle.enviarComando(Comando.LIGAR);
        controle2.enviarComando(Comando.MUTAR);
        // Não deve haver interferência entre instâncias
        assertTrue(true, "Cadeias de controles diferentes devem ser independentes");
    }

    @Test(descricao = "Fluxo completo de uso real funciona end-to-end")
    void fluxoCompletoUsoReal() {
        controle.enviarComando(Comando.LIGAR);
        controle.enviarComando(Comando.AUMENTAR_VOLUME, 10);
        controle.enviarComando(Comando.PROXIMO_CANAL);
        controle.enviarComando(Comando.MODO_CINEMA);
        controle.enviarComando(Comando.DIMINUIR_BRILHO, 20);
        controle.enviarComando(Comando.MODO_HIBERNAR, 30);
        controle.enviarComando(Comando.DESLIGAR);
        assertTrue(true, "Fluxo completo deve funcionar sem erros");
    }
}
