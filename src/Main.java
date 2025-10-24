import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        zadacha1();
        zadacha2();
        zadacha3();
        zadacha4();
        zadacha5();
        zadacha6();
        zadacha7();
        zadacha8();
        zadacha9();
        zadacha10();
        zadacha11();
        zadacha12();
        zadacha13();
        zadacha14();
    }

    // 1. Проверка, что строка не пуста и длиннее 3 символов
    static void zadacha1() {
        Predicate<String> proverka = stroka -> stroka != null && !stroka.isEmpty() && stroka.length() > 3;
        System.out.println("1: " + proverka.test("java")); // true
    }

    // 2. Получить длину строки
    static void zadacha2() {
        Function<String, Integer> dlinaStroki = stroka -> stroka.length();
        System.out.println("2: " + dlinaStroki.apply("programmirovanie")); // 17
    }

    // 3. Supplier: генерирует UUID
    static void zadacha3() {
        Supplier<UUID> generaciya = UUID::randomUUID;
        System.out.println("3: " + generaciya.get());
    }

    // 4. Печать строки в верхнем регистре
    static void zadacha4() {
        Consumer<String> pechat = stroka -> System.out.println("4: " + stroka.toUpperCase());
        pechat.accept("privet mir");
    }

    // 5. Сумма двух чисел
    static void zadacha5() {
        BiFunction<Integer, Integer, Integer> summa = (a, b) -> a + b;
        System.out.println("5: " + summa.apply(10, 20)); // 30
    }

    // 6. trim + toUpperCase
    static void zadacha6() {
        Function<String, String> obrabotka = stroka -> stroka.trim().toUpperCase();
        System.out.println("6: " + obrabotka.apply("  hello java  "));
    }

    // 7. Consumer: печать строки и её длины
    static void zadacha7() {
        Consumer<String> pechatStroki = stroka -> System.out.println("Stroka: " + stroka);
        Consumer<String> pechatDlina = stroka -> System.out.println("Dlina: " + stroka.length());
        Consumer<String> vseVmeste = pechatStroki.andThen(pechatDlina);
        System.out.println("7:");
        vseVmeste.accept("spring boot");
    }

    // 8. Проверка: число нечётное или отрицательное
    static void zadacha8() {
        Predicate<Integer> chetnoe = chislo -> chislo % 2 == 0;
        Predicate<Integer> polozhitelnoe = chislo -> chislo >= 0;
        Predicate<Integer> neChetnoeIliOtricatelnoe = chetnoe.negate().or(polozhitelnoe.negate());

        System.out.println("8: " + neChetnoeIliOtricatelnoe.test(3));   // true
        System.out.println("8: " + neChetnoeIliOtricatelnoe.test(-4));  // true
        System.out.println("8: " + neChetnoeIliOtricatelnoe.test(2));   // false
    }

    // 9. multiply + toStr → andThen
    static void zadacha9() {
        BiFunction<Integer, Integer, Integer> umnozhenie = (a, b) -> a * b;
        Function<Integer, String> vStroku = x -> "Result: " + x;

        // andThen создаёт BiFunction, которая возвращает строку
        BiFunction<Integer, Integer, String> result = umnozhenie.andThen(vStroku);

        System.out.println("9: " + result.apply(5, 6)); // Result: 30
    }


    // 10. Добавить "!!!" к строке
    static void zadacha10() {
        UnaryOperator<String> dobavit = s -> s + "!!!";
        System.out.println("10: " + dobavit.apply("vnimanie"));
    }

    // 11. Свой метод filter
    static void zadacha11() {
        List<String> spisok = List.of("java", "go", "rust", "c", "kotlin");
        List<String> otfiltrovannye = mojFilter(spisok, slovo -> slovo.length() > 2);
        System.out.println("11: " + otfiltrovannye);
    }

    static <T> List<T> mojFilter(List<T> spisok, Predicate<T> uslovie) {
        List<T> rezultat = new ArrayList<>();
        for (T element : spisok) {
            if (uslovie.test(element)) {
                rezultat.add(element);
            }
        }
        return rezultat;
    }

    // 12. Свой метод map
    static void zadacha12() {
        List<String> slova = List.of("odin", "dva", "tri");
        List<Integer> dlinaSlov = mojMap(slova, slovo -> slovo.length());
        System.out.println("12: " + dlinaSlov); // [4, 3, 3]
    }

    static <T, R> List<R> mojMap(List<T> spisok, Function<T, R> preobrazovatel) {
        List<R> rezultat = new ArrayList<>();
        for (T element : spisok) {
            rezultat.add(preobrazovatel.apply(element));
        }
        return rezultat;
    }

    // 13. Свой метод forEach
    static void zadacha13() {
        List<String> bukvy = List.of("A", "B", "C");
        System.out.println("13:");
        mojForEach(bukvy, bukva -> System.out.println("Element: " + bukva));
    }

    static <T> void mojForEach(List<T> spisok, Consumer<T> deistvie) {
        for (T element : spisok) {
            deistvie.accept(element);
        }
    }

    // 14. Свой метод generate
    static void zadacha14() {
        Supplier<Integer> randomChislo = () -> new Random().nextInt(100);
        List<Integer> chisla = mojGenerate(randomChislo, 5);
        System.out.println("14: " + chisla);
    }

    static <T> List<T> mojGenerate(Supplier<T> postavshik, int n) {
        List<T> rezultat = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            rezultat.add(postavshik.get());
        }
        return rezultat;
    }
}
