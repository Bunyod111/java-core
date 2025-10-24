import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int number;
        String way;

        do {
            System.out.println("Введите номер задачи (1–10) или 0 для выхода:");
            number = scan.nextInt();

            if (number == 0) {
                System.out.println("Выход из программы. пока - пока");
                break;
            }

            if (number < 1 || number > 10) {
                System.out.println("Неправильный номер задачи. Повторите ввод.");
                continue;
            }

            System.out.println("Выберите способ решения: 'цикл' или 'поток'");
            way = scan.next().toLowerCase();

            switch (number) {
                case 1 -> {
                    if (way.equals("цикл")) zadacha1();
                    else if (way.equals("поток")) zadacha1pot();
                    else System.out.println("Неверно пишете");
                }
                case 2 -> {
                    if (way.equals("цикл")) zadacha2();
                    else if (way.equals("поток")) zadacha2pot();
                    else System.out.println("Неверно пишете");
                }
                case 3 -> {
                    if (way.equals("цикл")) zadacha3();
                    else if (way.equals("поток")) zadacha3pot();
                    else System.out.println("Неверно пишете");
                }
                case 4 -> {
                    if (way.equals("цикл")) zadacha4();
                    else if (way.equals("поток")) zadacha4pot();
                    else System.out.println("Неверно пишете");
                }
                case 5 -> {
                    if (way.equals("цикл")) zadacha5();
                    else if (way.equals("поток")) zadacha5pot();
                    else System.out.println("Неверно пишете");
                }
                case 6 -> {
                    if (way.equals("цикл")) zadacha6();
                    else if (way.equals("поток")) zadacha6pot();
                    else System.out.println("Неверно пишете");
                }
                case 7 -> {
                    if (way.equals("цикл")) zadacha7();
                    else if (way.equals("поток")) zadacha7pot();
                    else System.out.println("Неверно пишете");
                }
                case 8 -> {
                    if (way.equals("цикл")) zadacha8();
                    else if (way.equals("поток")) zadacha8pot();
                    else System.out.println("Неверно пишете");
                }
                case 9 -> {
                    if (way.equals("цикл")) zadacha9();
                    else if (way.equals("поток")) zadacha9pot();
                    else System.out.println("Неверно пишете");
                }
                case 10 -> {
                    if (way.equals("цикл")) zadacha10();
                    else if (way.equals("поток")) zadacha10pot();
                    else System.out.println("Неверно пишете");
                }
            }

        } while (true);

        scan.close();
    }

    // ЗАДАЧА 1
    static void zadacha1() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> result = new ArrayList<>();
        for (Integer number : numbers) {
            if (number % 2 == 0) {
                result.add(number * number);
            }
        }
        System.out.println("1 Цикл: " + result);
    }

    static void zadacha1pot() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> result = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(number -> number * number)
                .collect(Collectors.toList());
        System.out.println("1 Поток: " + result);
    }

    // ЗАДАЧА 2
    static void zadacha2() {
        List<String> words = List.of("apple", "banana", "pear", "pineapple");
        int count = 0;
        for (String word : words) {
            if (word.length() > 5) {
                count++;
            }
        }
        System.out.println("2 Цикл: " + count);
    }

    static void zadacha2pot() {
        List<String> words = List.of("apple", "banana", "pear", "pineapple");
        long count = words.stream()
                .filter(word -> word.length() > 5)
                .count();
        System.out.println("2 Поток: " + count);
    }

    // ЗАДАЧА 3
    static void zadacha3() {
        List<Integer> numbers = List.of(10, 2, 33, 4, 25);
        int min = numbers.get(0);
        int max = numbers.get(0);
        for (int number : numbers) {
            if (number < min) min = number;
            if (number > max) max = number;
        }
        System.out.println("3 Цикл: min=" + min + ", max=" + max);
    }

    static void zadacha3pot() {
        List<Integer> numbers = List.of(10, 2, 33, 4, 25);
        int min = numbers.stream().min(Integer::compareTo).get();
        int max = numbers.stream().max(Integer::compareTo).get();
        System.out.println("3 Поток: min=" + min + ", max=" + max);
    }

    // ЗАДАЧА 4
    static void zadacha4() {
        List<String> names = List.of("Alisa", "Bob", "Charlie", "David");
        int totalLength = 0;
        for (String name : names) {
            totalLength += name.length();
        }
        double average = (double) totalLength / names.size();
        System.out.println("4 Цикл: average length = " + average);
    }

    static void zadacha4pot() {
        List<String> names = List.of("Alisa", "Bob", "Charlie", "David");
        double average = names.stream()
                .mapToInt(name -> name.length())
                .average()
                .orElse(0);
        System.out.println("4 Поток: average length = " + average);
    }

    // ЗАДАЧА 5
    static void zadacha5() {
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");
        Set<String> unique = new HashSet<>(input);
        List<String> result = new ArrayList<>(unique);
        result.sort(Comparator.comparingInt(String::length));
        System.out.println("5 Цикл: " + result);
    }

    static void zadacha5pot() {
        List<String> input = List.of("apple", "pear", "apple", "banana", "pear");
        List<String> result = input.stream()
                .distinct()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());
        System.out.println("5 Поток: " + result);
    }

    // ЗАДАЧА 6
    static void zadacha6() {
        List<String> fruits = List.of("apple", "banana", "kiwi");
        Map<String, Integer> map = new HashMap<>();
        for (String fruit : fruits) {
            map.put(fruit, fruit.length());
        }
        System.out.println("6 Цикл: " + map);
    }

    static void zadacha6pot() {
        List<String> fruits = List.of("apple", "banana", "kiwi");
        Map<String, Integer> map = fruits.stream()
                .collect(Collectors.toMap(
                        fruit -> fruit,
                        fruit -> fruit.length()
                ));
        System.out.println("6 Поток: " + map);
    }

    // ЗАДАЧА 7
    static void zadacha7() {
        List<String> names = List.of("Alisa", "Andrey", "Boris", "Charlie", "Katya");
        Map<Character, List<String>> groups = new HashMap<>();
        for (String name : names) {
            char first = name.charAt(0);
            if (!groups.containsKey(first)) {
                groups.put(first, new ArrayList<>());
            }
            groups.get(first).add(name);
        }
        System.out.println("7 Цикл: " + groups);
    }

    static void zadacha7pot() {
        List<String> names = List.of("Alisa", "Andrey", "Boris", "Charlie", "Katya");
        Map<Character, List<String>> groups = names.stream()
                .collect(Collectors.groupingBy(name -> name.charAt(0)));
        System.out.println("7 Поток: " + groups);
    }

    // ЗАДАЧА 8
    static void zadacha8() {
        List<String> names = List.of("Tom", "Jerry", "Spike");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < names.size(); i++) {
            sb.append(names.get(i));
            if (i < names.size() - 1) {
                sb.append(", ");
            }
        }
        System.out.println("8 Цикл: " + sb);
    }

    static void zadacha8pot() {
        List<String> names = List.of("Tom", "Jerry", "Spike");
        String result = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("8 Поток: " + result);
    }

    // ЗАДАЧА 9
    static void zadacha9() {
        List<String> sentences = List.of("Java is cool", "Streams are powerful");
        List<String> words = new ArrayList<>();
        for (String sentence : sentences) {
            String[] parts = sentence.split(" ");
            for (String word : parts) {
                words.add(word);
            }
        }
        System.out.println("9 Цикл: " + words);
    }

    static void zadacha9pot() {
        List<String> sentences = List.of("Java is cool", "Streams are powerful");
        List<String> words = sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .collect(Collectors.toList());
        System.out.println("9 Поток: " + words);
    }

    // ЗАДАЧА 10
    static void zadacha10() {
        List<Tovar> items = List.of(
                new Tovar("Phone", "Electronics", 1200),
                new Tovar("TV", "Electronics", 1800),
                new Tovar("Apple", "Fruits", 2.5),
                new Tovar("Mango", "Fruits", 4.0)
        );
        Map<String, Tovar> expensive = new HashMap<>();
        for (Tovar item : items) {
            String category = item.kategoriya();
            if (!expensive.containsKey(category) || item.tsena() > expensive.get(category).tsena()) {
                expensive.put(category, item);
            }
        }
        System.out.println("10 Цикл: " + expensive);
    }

    static void zadacha10pot() {
        List<Tovar> items = List.of(
                new Tovar("Phone", "Electronics", 1200),
                new Tovar("TV", "Electronics", 1800),
                new Tovar("Apple", "Fruits", 2.5),
                new Tovar("Mango", "Fruits", 4.0)
        );
        Map<String, Optional<Tovar>> expensive = items.stream()
                .collect(Collectors.groupingBy(
                        Tovar::kategoriya,
                        Collectors.maxBy(Comparator.comparingDouble(Tovar::tsena))
                ));
        System.out.println("10 Поток: " + expensive);
    }

    // Структура товара
    record Tovar(String nazvanie, String kategoriya, double tsena) {}
}
