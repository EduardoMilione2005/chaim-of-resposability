package com.controleremoto;

import com.controleremoto.device.ControleRemotoTest;
import com.controleremoto.framework.TestRunner;
import com.controleremoto.handler.*;

public class TestMain {

    public static void main(String[] args) {
        new TestRunner()
                .add(RequisicaoTest.class)
                .add(HandlerEnergiaTest.class)
                .add(HandlerVolumeTest.class)
                .add(HandlerCanalTest.class)
                .add(HandlerBrilhoTest.class)
                .add(HandlerModoEspecialTest.class)
                .add(HandlerFallbackTest.class)
                .add(ControleRemotoTest.class)
                .run();
    }
}
