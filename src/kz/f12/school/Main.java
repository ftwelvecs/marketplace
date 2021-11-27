package kz.f12.school;

import kz.f12.school.dto.ProductDTO;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private final static int GET_ALL = 1;
    private final static int SEARCH = 2;
    private final static int ADD = 3;
    private final static int EDIT = 4;
    private final static int DELETE = 5;

    private ArrayList<ProductDTO> productList = new ArrayList<>();

    public static void main(String[] args) {
        printGreetings();

        Scanner sc = new Scanner(System.in);
        int command = Integer.parseInt(sc.nextLine());

        switch (command) {
            case GET_ALL:
                getAll();
                break;
            case SEARCH:
                search();
                break;
            case ADD:
                add();
                break;
            case EDIT:
                edit();
                break;
            case DELETE:
                delete();
                break;
            default:
                System.out.println("Некорректная команда. Попробуйте снова");
        }

    }

    public static void printGreetings() {
        System.out.println("Добро пожаловать в MarketPlace");
        System.out.println();
        System.out.println("Выберите команду действия:");
        System.out.println("\tНажмите 1 чтобы вывести весь список продуктов;");
        System.out.println("\tНажмите 2 чтобы найти продукт;");
        System.out.println("\tНажмите 3 чтобы добавить продукт;");
        System.out.println("\tНажмите 4 чтобы редактировать продукт;");
        System.out.println("\tНажмите 5 чтобы удалить продукт.");
        System.out.print("Ввод: ");
    }

    public static void getAll() {
        System.out.println("getAll() method was called");
    }

    public static void search() {
        System.out.println("search() method was called");
    }

    public static void add() {
        System.out.println("add() method was called");
    }

    public static void edit() {
        System.out.println("edit() method was called");
    }

    public static void delete() {
        System.out.println("delete() method was called");
    }
}
