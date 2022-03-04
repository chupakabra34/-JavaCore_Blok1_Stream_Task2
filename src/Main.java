import java.util.*;
import java.util.stream.Collectors;

/**
 * \* Created with IntelliJ IDEA.
 * \* Author: Prekrasnov Sergei
 * \* Date: 27.02.2022
 * \*  ----- group JAVA-27 -----
 * \* Description: Задача 2: Перепись населения
 * \
 */
public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Количество несовершеннолетних: " +
                persons.stream().filter(element -> element.getAge() < 18).count());
        System.out.println("Фамилий призывников:" +
                persons.stream().filter(element -> element.getAge() >= 18 && element.getAge() <= 27)
                        .filter(element -> element.getSex() == Sex.MAN)
                        .map(element -> element.getFamily())
                        .collect(Collectors.toList()));
        System.out.println("Список потенциально работоспособных людей с высшим образованием:" + persons.stream()
                .filter(element ->
                        (element.getAge() >= 18 && element.getAge() <= 60 && element.getSex() == Sex.WOMAN) ||
                                element.getAge() >= 18 && element.getAge() <= 65 && element.getSex() == Sex.MAN)
                .filter(element -> element.getEducation() == Education.HIGHER)
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()));
        ;
    }
}
