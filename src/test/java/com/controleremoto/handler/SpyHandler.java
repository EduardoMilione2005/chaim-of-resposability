package com.controleremoto.handler;
import com.controleremoto.model.Requisicao;

public class SpyHandler extends HandlerBase {
    public boolean foiChamado = false;
    public Requisicao ultimaRequisicao = null;

    public SpyHandler() { super("SpyHandler"); }

    @Override protected boolean podeProcessar(Requisicao r) { return true; }
    @Override protected void executar(Requisicao r) {
        foiChamado = true;
        ultimaRequisicao = r;
    }
}
