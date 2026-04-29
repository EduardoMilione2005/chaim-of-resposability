package com.controleremoto;

import com.controleremoto.device.ControleRemoto;
import com.controleremoto.model.Comando;

public class Main {

    public static void main(String[] args) {

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║     CONTROLE REMOTO — Chain of Responsibility        ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
        System.out.println();

        ControleRemoto controle = new ControleRemoto("Smart TV Samsung");

        System.out.println("═══ Cenário 1: Liga e testa energia ═══");
        controle.enviarComando(Comando.LIGAR);
        controle.enviarComando(Comando.LIGAR);

        System.out.println("═══ Cenário 2: Controle de volume ═══");
        controle.enviarComando(Comando.AUMENTAR_VOLUME);
        controle.enviarComando(Comando.AUMENTAR_VOLUME, 20);
        controle.enviarComando(Comando.MUTAR);
        controle.enviarComando(Comando.DIMINUIR_VOLUME);
        controle.enviarComando(Comando.DIMINUIR_VOLUME, 30);

        System.out.println("═══ Cenário 3: Troca de canais ═══");
        controle.enviarComando(Comando.PROXIMO_CANAL);
        controle.enviarComando(Comando.PROXIMO_CANAL);
        controle.enviarComando(Comando.CANAL_ANTERIOR);

        System.out.println("═══ Cenário 4: Ajuste de brilho ═══");
        controle.enviarComando(Comando.AUMENTAR_BRILHO);
        controle.enviarComando(Comando.DIMINUIR_BRILHO, 25);

        System.out.println("═══ Cenário 5: Modos especiais ═══");
        controle.enviarComando(Comando.MODO_CINEMA);
        controle.enviarComando(Comando.MODO_HIBERNAR, 60);  

        System.out.println("═══ Cenário 6: Comando desconhecido ═══");
        controle.enviarComando(Comando.DESCONHECIDO);

        System.out.println("═══ Cenário 7: Desligar ═══");
        controle.enviarComando(Comando.DESLIGAR);
        controle.enviarComando(Comando.DESLIGAR);

        System.out.println("╔══════════════════════════════════════════════════════╗");
        System.out.println("║                  FIM DA DEMONSTRAÇÃO                 ║");
        System.out.println("╚══════════════════════════════════════════════════════╝");
    }
}
