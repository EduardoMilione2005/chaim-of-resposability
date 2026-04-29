package com.controleremoto.framework;

import java.util.Objects;

public final class Assertions {

    private Assertions() {}


    public static void assertEquals(Object esperado, Object obtido) {
        assertEquals(esperado, obtido, null);
    }

    public static void assertEquals(Object esperado, Object obtido, String mensagem) {
        if (!Objects.equals(esperado, obtido)) {
            String msg = mensagem != null ? mensagem + " → " : "";
            throw new AssertionError(msg +
                    "Esperado: <" + esperado + "> mas obteve: <" + obtido + ">");
        }
    }

    public static void assertEquals(int esperado, int obtido) {
        assertEquals((Integer) esperado, (Integer) obtido, null);
    }

    public static void assertEquals(int esperado, int obtido, String mensagem) {
        assertEquals((Integer) esperado, (Integer) obtido, mensagem);
    }

    public static void assertEquals(boolean esperado, boolean obtido, String mensagem) {
        assertEquals(Boolean.valueOf(esperado), Boolean.valueOf(obtido), mensagem);
    }


    public static void assertTrue(boolean condicao) {
        assertTrue(condicao, "Esperava true, mas obteve false");
    }

    public static void assertTrue(boolean condicao, String mensagem) {
        if (!condicao) throw new AssertionError(mensagem);
    }

    public static void assertFalse(boolean condicao) {
        assertFalse(condicao, "Esperava false, mas obteve true");
    }

    public static void assertFalse(boolean condicao, String mensagem) {
        if (condicao) throw new AssertionError(mensagem);
    }


    public static void assertNotNull(Object obj) {
        assertNotNull(obj, "Esperava valor não-nulo, mas obteve null");
    }

    public static void assertNotNull(Object obj, String mensagem) {
        if (obj == null) throw new AssertionError(mensagem);
    }

    public static void assertNull(Object obj, String mensagem) {
        if (obj != null) throw new AssertionError(mensagem + " → obteve: <" + obj + ">");
    }


    public static <T extends Throwable> T assertThrows(Class<T> tipo, Executable bloco) {
        try {
            bloco.execute();
        } catch (Throwable t) {
            if (tipo.isInstance(t)) {
                return tipo.cast(t);
            }
            throw new AssertionError("Esperava " + tipo.getSimpleName()
                    + " mas recebeu " + t.getClass().getSimpleName(), t);
        }
        throw new AssertionError("Esperava " + tipo.getSimpleName() + " mas nenhuma exceção foi lançada");
    }


    public static void fail(String mensagem) {
        throw new AssertionError(mensagem);
    }


    @FunctionalInterface
    public interface Executable {
        void execute() throws Throwable;
    }
}
