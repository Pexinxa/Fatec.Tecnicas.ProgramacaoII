package view;

import model.Funcionario;

import java.util.*;
import java.util.stream.*;

public class Main {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Pedro","TI",3800.00,7),
                new Funcionario("Octávio","RH",2800.00,3),
                new Funcionario("Silvio","RH",4100.00,15),
                new Funcionario("João","Financeiro",6500.00,20),
                new Funcionario("Caio","Financeiro",2500.00,1),
                new Funcionario("Gabriela","TI",4700.00,11),
                new Funcionario("Gabriel","Financeiro",5900.00,14),
                new Funcionario("Vinicius","TI",2900.00,2)
        ));

        separador("LISTA COMPLETA DE FUNCIONÁRIOS");
        funcionarios.forEach(System.out::println);


        separador("FILTRAGEM — Salário Superior a R$ 3.000,00");
        List<Funcionario> comSalarioAlto = funcionarios.stream()
                .filter(f -> f.getSalario() > 3000.00)
                .collect(Collectors.toList());
        comSalarioAlto.forEach(System.out::println);

        separador("MAPEAMENTO — Aumento de 5% para Funcionários com mais de 10 Anos");
        List<Funcionario> comAumento = funcionarios.stream()
                .map(f -> {
                    Funcionario copia = new Funcionario(
                            f.getNome(), f.getDepartamento(), f.getSalario(), f.getAnosDeServico()
                    );
                    if (f.getAnosDeServico() > 10) {
                        copia.setSalario(f.getSalario() * 1.05);
                    }
                    return copia;
                })
                .collect(Collectors.toList());

        comAumento.stream()
                .filter(f -> f.getAnosDeServico() > 10)
                .forEach(f -> System.out.printf(
                        "%-20s → Novo salário: R$ %.2f%n", f.getNome(), f.getSalario()));


        separador("ORDENAÇÃO — Ordem Alfabética pelo Nome");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);


        separador("REDUÇÃO — Total da Folha Salarial");
        double totalSalarios = funcionarios.stream()
                .mapToDouble(Funcionario::getSalario)
                .reduce(0.0, Double::sum);
        System.out.printf("Total gasto com salários: R$ %.2f%n", totalSalarios);


        separador("AGRUPAMENTO — Média Salarial por Departamento");
        Map<String, Double> mediaPorDepartamento = funcionarios.stream()
                .collect(Collectors.groupingBy(
                        Funcionario::getDepartamento,
                        Collectors.averagingDouble(Funcionario::getSalario)
                ));
        mediaPorDepartamento.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(e -> System.out.printf(
                        "Departamento: %-15s | Média Salarial: R$ %.2f%n",
                        e.getKey(), e.getValue()));


        separador("DESAFIO — Funcionário com Maior Tempo de Serviço");
        funcionarios.stream()
                .max(Comparator.comparingInt(Funcionario::getAnosDeServico))
                .ifPresent(f -> System.out.printf(
                        "Funcionário: %s | Departamento: %s | Anos de Serviço: %d%n",
                        f.getNome(), f.getDepartamento(), f.getAnosDeServico()));


        separador("SAÍDA FORMATADA PERSONALIZADA (Lambda)");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(f -> System.out.printf(
                        "Funcionário: %s, Departamento: %s, Salário: R$ %.2f%n",
                        f.getNome(), f.getDepartamento(), f.getSalario()));
    }

    private static void separador(String titulo) {
        System.out.println("\n");
        System.out.println("  " + titulo);
    }
}