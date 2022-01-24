package kz.f12.school;

import kz.f12.school.model.dto.ProductDTO;
import kz.f12.school.service.ProductService;
import org.json.JSONArray;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    // команды выполнения
    private final static int GET_ALL = 1;
    private final static int SEARCH = 2;
    private final static int ADD = 3;
    private final static int EDIT = 4;
    private final static int DELETE = 5;
    private final static int EXIT = 6;

    // список продуктов
    private static List<ProductDTO> productList = new ArrayList<>();

    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        init();
        run();
    }

    public static void init() {
        productList = productService.getAll();
    }

    public static void run() {
        printGreetings();

        // именуем меткой бесконечный цикл
        // чтобы в случае необходимости мы могли
        // к нему обратиться
        MAIN_LOOP:
        while (true) {
            printCommands();
            Scanner sc = new Scanner(System.in);
            int command = Integer.parseInt(sc.nextLine());

            switch (command) {
                case GET_ALL:   getAll();   break;
                case SEARCH:    search(sc); break;
                case ADD:       add(sc);    break;
                case EDIT:      edit(sc);   break;
                case DELETE:    delete(sc); break;
                // останавливаем главный цикл по его метке
                case EXIT:      /* nope */  break MAIN_LOOP;
                default:
                    System.out.println("Некорректная команда. Попробуйте снова");
            }
            // чтобы разделить абзацы
            System.out.println();
        }
    }

    public static JSONArray getJson(String resourceName) {
        JSONTokener jsonTokener = new JSONTokener(Main.class.getResourceAsStream(resourceName));
        return (JSONArray) jsonTokener.nextValue();
    }

    public static void printGreetings() {
        System.out.println("Добро пожаловать в MarketPlace");
        System.out.println();
    }

    public static void printCommands() {
        System.out.println("Выберите команду действия:");
        System.out.println("\tНажмите 1 чтобы вывести весь список продуктов;");
        System.out.println("\tНажмите 2 чтобы найти продукт;");
        System.out.println("\tНажмите 3 чтобы добавить продукт;");
        System.out.println("\tНажмите 4 чтобы редактировать продукт;");
        System.out.println("\tНажмите 5 чтобы удалить продукт;");
        System.out.println("\tНажмите 6 чтобы выйти из программы.");
        System.out.print("Ввод: ");
    }

    public static void getAll() {
        // если список продуктов пуст, то пишем...
        if (productList.isEmpty()) {
            System.out.println("Список продуктов пуст");
        } else { // иначе...
            // заводим переменную чтобы вывести нумерацию
            int num = 0;
            System.out.println("Список продуктов:");
            for (ProductDTO product : productList) {
                System.out.println("\t" + (++num) + ") " + product.getName());
            }
        }
        // чтобы разделить абзацы
        System.out.println();
    }

    // Поиск будет происходить по имени продукта,
    // т.е. искать по первому вхождению имени.
    // Если в списке хранятся продукты: книга, телефон, телевизор,
    // то при введении слово "теле" должны находиться продукты: телефон и телевизор.
    // А если нету совпадения, выводить сообщение: "Продукт не найден"
    public static void search(Scanner sc) {
        System.out.print("\tВведите название продукта: ");
        String name = sc.nextLine().toLowerCase();
        boolean notFound = true;
        for (ProductDTO product : productList) {
            if (product.getName().toLowerCase().startsWith(name)) {
                notFound = false;
                System.out.println("\t Продукт: " + product.getName());
                System.out.println("\t Описание: " + product.getDescription());
                System.out.println("\t Цена: " + product.getPrice());
                System.out.println("\t Кол-во: " + product.getQuantity());
                System.out.println("\t Вес: " + product.getWeight());
                System.out.println();
            }
        }
        if (notFound) {
            System.out.println("Продукт не найден");
        }
    }

    // чтобы не создавать каждый раз объект Scanner
    // передаем его в метод как параметр
    public static void add(Scanner sc) {
        // сначала запрашиваем данные у пользователя
        // а после их сохраняем
        System.out.print("\tВведите название продукта: ");
        String name = sc.nextLine();

        System.out.print("\tВведите описание: ");
        String desc = sc.nextLine();

        System.out.print("\tВведите кол-во: ");
        int qty = Integer.parseInt(sc.nextLine());

        System.out.print("\tВведите цену: ");
        double price = Double.parseDouble(sc.nextLine());

        System.out.print("\tВведите вес продукта: ");
        double weight = Double.parseDouble(sc.nextLine());

        // за счет сохраненных данных
        // создаем объект продукта
        // и передаем их туда
        ProductDTO product = new ProductDTO();
        product.setName(name);
        product.setDescription(desc);
        product.setQuantity(qty);
        product.setPrice(price);
        product.setWeight(weight);

        productService.save(product);

        // включаем в общий список продуктов
        productList.add(product);

        System.out.println("Продукт сохранен");
        System.out.println();
    }

    // 1. Запросить у пользователя имя продукта, который мы хотим переименовать
    // 2. Ищем, если находим то где-то сохраняем, а если нет, выводить сообщение: Продукт не найден
    // 3. Если найден продукт, то запрашиваем новые параметры (имя, описания и т.д.) и сохраняем
    public static void edit(Scanner sc) {
        System.out.print("\tВведите название продукта: ");
        String searchedName = sc.nextLine();
        boolean notFound = true;
        for (ProductDTO product : productList) {
            if (product.getName().equalsIgnoreCase(searchedName)) {
                notFound = false;
                System.out.print("\tВведите новое название продукта: ");
                String name = sc.nextLine();

                System.out.print("\tВведите новое описание: ");
                String desc = sc.nextLine();

                System.out.print("\tВведите новое кол-во: ");
                int qty = Integer.parseInt(sc.nextLine());

                System.out.print("\tВведите новую цену: ");
                double price = Double.parseDouble(sc.nextLine());

                System.out.print("\tВведите новый вес продукта: ");
                double weight = Double.parseDouble(sc.nextLine());

                product.setName(name);
                product.setDescription(desc);
                product.setQuantity(qty);
                product.setPrice(price);
                product.setWeight(weight);
                productService.update(product);
                break;
            }
        }
        if (notFound) {
            System.out.println("Продукт не найден");
        }
    }

    public static void delete(Scanner sc) {
        System.out.print("\tВведите название продукта: ");
        String name = sc.nextLine().toLowerCase();
        boolean notFound = true;

        for (ProductDTO product : productList) {
            if (product.getName().equalsIgnoreCase(name)) {
                notFound = false;
                productService.delete(product.getId());
                productList.remove(product);
                break;
            }
        }
        if (notFound) {
            System.out.println("Продукт не найден");
        }
    }
}
