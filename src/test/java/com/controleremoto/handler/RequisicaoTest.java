package com.controleremoto.handler;

import com.controleremoto.framework.*;
import com.controleremoto.model.Comando;
import com.controleremoto.model.Requisicao;

import static com.controleremoto.framework.Assertions.*;

public class RequisicaoTest {

    @Test(descricao = "Requisição sem parâmetro tem parâmetro padrão 0")
    void parametroPadraoZero() {
        Requisicao req = new Requisicao(Comando.LIGAR, "TV");
        assertEquals(0, req.getParametro(), "Parâmetro padrão deve ser 0");
    }

    @Test(descricao = "Requisição com parâmetro preserva o valor")
    void parametroPreservado() {
        Requisicao req = new Requisicao(Comando.AUMENTAR_VOLUME, "TV", 42);
        assertEquals(42, req.getParametro(), "Parâmetro deve ser 42");
    }

    @Test(descricao = "Requisição preserva o comando")
    void comandoPreservado() {
        Requisicao req = new Requisicao(Comando.MODO_CINEMA, "Projetor");
        assertEquals(Comando.MODO_CINEMA, req.getComando(), "Comando deve ser MODO_CINEMA");
    }

    @Test(descricao = "Requisição preserva o dispositivo")
    void dispositivoPreservado() {
        Requisicao req = new Requisicao(Comando.LIGAR, "Soundbar XZ");
        assertEquals("Soundbar XZ", req.getDispositivo(), "Dispositivo deve ser 'Soundbar XZ'");
    }

    @Test(descricao = "toString não deve lançar exceção")
    void toStringFunciona() {
        Requisicao req = new Requisicao(Comando.PROXIMO_CANAL, "TV", 5);
        assertNotNull(req.toString(), "toString não deve retornar null");
    }

    @Test(descricao = "Enum Comando contém DESCONHECIDO")
    void enumContemDesconhecido() {
        boolean encontrado = false;
        for (Comando c : Comando.values()) {
            if (c == Comando.DESCONHECIDO) encontrado = true;
        }
        assertTrue(encontrado, "Enum deve conter DESCONHECIDO");
    }
}
