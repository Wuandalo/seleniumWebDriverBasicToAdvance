package examples;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class StreamsAndLambda {

    @Test
    public void listingNamesWithoutStream() {

        ArrayList<String> names = new ArrayList<>();
        names.add("Adriano");
        names.add("Hugo");
        names.add("Angela");
        names.add("Marcelo");
        names.add("Angelica");
        int count = 0;

        for (String name : names) {
            if (name.startsWith("A")) {
                count++;
            }
        }

        System.out.println(count);

    }

    @Test
    public void listingNamesWithStream() {

        ArrayList<String> names = new ArrayList<>();
        names.add("Adriano");
        names.add("Hugo");
        names.add("Angela");
        names.add("Marcelo");
        names.add("Angelica");

        Long count = names.stream().filter(n -> n.startsWith("A")).count();
        System.out.println(count);

        //imprimindo só os nomes maiores que 5 caracteres
        names.stream().filter(n -> n.length() > 5).forEach(System.out::println);

        //imprimindo só os nomes maiores que 5 caracteres e limitando o resultado
        names.stream().filter(n -> n.length() > 5).limit(1).forEach(System.out::println);
    }

    @Test
    public void listindNamesWithoutListWithStream() {
        Long c = Stream.of("Adriano", "Angela", "Gustavo", "Laura").filter(n -> n.startsWith("A")).count();
        System.out.println(c);
    }

    @Test
    public void streamMap() {

        //Print the filter in uppercase
        Stream.of("Adriano", "Gustavo", "Laura", "Adriana").filter(n -> n.endsWith("a")).map(String::toUpperCase).forEach(System.out::println);

        //Print sorted
        Stream.of("Adriano", "Gustavo", "Laura", "Adriana").sorted().map(String::toUpperCase).forEach(System.out::println);

    }

    @Test
    public void concactStreams() {
        List<String> names = Arrays.asList("João", "Maria", "José");
        List<String> names1 = Arrays.asList("Zaquel", "Raquel", "Joel");

        Stream<String> concactNames = Stream.concat(names.stream(), names1.stream());
        //concactNames.sorted().forEach(System.out::println);

        assertTrue(concactNames.anyMatch(n -> n.equalsIgnoreCase("Maria")));

    }

    //Coletando os elementos a partir de um filtro e inserindo em uma lista
    @Test
    public void streamCollect() {

        List<String> ls = Stream.of("Adriano", "Juliana", "Ana", "Gustavo", "Laura", "Adriana").filter(n -> n.endsWith("a")).toList();
        for (String n : ls) {
            System.out.println(n);
        }

    }

    @Test
    public void printDistinctElements() {

        List<Integer> values = Arrays.asList(1, 1, 2, 4, 4, 5, 5, 10);
        values.stream().distinct().forEach(System.out::println);
        List<Integer> number = values.stream().sorted().toList();
        System.out.println(number.get(2));

    }

}