package com.controleremoto.model;

public class Requisicao {

    private final Comando comando;
    private final String dispositivo;
    private int parametro;

    public Requisicao(Comando comando, String dispositivo) {
        this.comando = comando;
        this.dispositivo = dispositivo;
        this.parametro = 0;
    }

    public Requisicao(Comando comando, String dispositivo, int parametro) {
        this.comando = comando;
        this.dispositivo = dispositivo;
        this.parametro = parametro;
    }

    public Comando getComando() {
        return comando;
    }

    public String getDispositivo() {
        return dispositivo;
    }

    public int getParametro() {
        return parametro;
    }

    @Override
    public String toString() {
        return String.format("[Requisição] Dispositivo: %-15s | Comando: %-20s | Parâmetro: %d",
                dispositivo, comando, parametro);
    }
}
