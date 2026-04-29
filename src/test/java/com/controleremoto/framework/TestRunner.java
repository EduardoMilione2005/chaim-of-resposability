package com.controleremoto.framework;

import java.lang.reflect.Method;
import java.util.*;


public class TestRunner {

    private record Resultado(String classe, String metodo, String descricao,
                             boolean passou, String erro, long duracaoMs) {}

    private final List<Class<?>> classes = new ArrayList<>();

    public TestRunner add(Class<?>... testClasses) {
        classes.addAll(Arrays.asList(testClasses));
        return this;
    }

    public void run() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║              SUITE DE TESTES — Controle Remoto               ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");

        List<Resultado> todos = new ArrayList<>();

        for (Class<?> clazz : classes) {
            List<Resultado> resultados = executarClasse(clazz);
            todos.addAll(resultados);
        }

        imprimirResumo(todos);
    }

    private List<Resultado> executarClasse(Class<?> clazz) {
        System.out.println();
        System.out.println("  📋 " + clazz.getSimpleName());
        System.out.println("  " + "─".repeat(60));

        List<Method> befores = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(BeforeEach.class))
                .toList();

        List<Method> testes = Arrays.stream(clazz.getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(Test.class))
                .sorted(Comparator.comparing(Method::getName))
                .toList();

        List<Resultado> resultados = new ArrayList<>();

        for (Method teste : testes) {
            Test ann = teste.getAnnotation(Test.class);
            String descricao = ann.descricao().isBlank() ? teste.getName() : ann.descricao();

            try {
                Object instancia = clazz.getDeclaredConstructor().newInstance();
                for (Method before : befores) {
                    before.setAccessible(true);
                    before.invoke(instancia);
                }
                teste.setAccessible(true);
                long inicio = System.currentTimeMillis();
                teste.invoke(instancia);
                long duracao = System.currentTimeMillis() - inicio;

                System.out.printf("  ✅ %-52s (%dms)%n", descricao, duracao);
                resultados.add(new Resultado(clazz.getSimpleName(), teste.getName(),
                        descricao, true, null, duracao));

            } catch (Throwable t) {
                Throwable causa = t.getCause() != null ? t.getCause() : t;
                System.out.printf("  ❌ %-52s%n", descricao);
                System.out.printf("     └─ %s%n", causa.getMessage());
                resultados.add(new Resultado(clazz.getSimpleName(), teste.getName(),
                        descricao, false, causa.getMessage(), 0));
            }
        }

        return resultados;
    }

    private void imprimirResumo(List<Resultado> todos) {
        long passou = todos.stream().filter(Resultado::passou).count();
        long falhou = todos.size() - passou;

        System.out.println();
        System.out.println("  " + "═".repeat(62));
        System.out.printf("  📊 RESULTADO FINAL: %d testes | ✅ %d passaram | ❌ %d falharam%n",
                todos.size(), passou, falhou);

        if (falhou > 0) {
            System.out.println();
            System.out.println("  FALHAS:");
            todos.stream()
                    .filter(r -> !r.passou())
                    .forEach(r -> System.out.printf("    ❌ [%s] %s%n       → %s%n",
                            r.classe(), r.descricao(), r.erro()));
        }

        System.out.println("  " + "═".repeat(62));
        System.out.println();

        if (falhou > 0) System.exit(1);
    }
}
