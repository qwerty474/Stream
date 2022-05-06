package task02;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Book implements Comparator<Book> {

    public static void main(String[] args) {

        System.out.println(getBooks(10)); //1.1
        printNames(getBooks(10)); //1.2
        printTotalPrice(getBooks(10)); //1.3
        printBooksWithA(getBooks(10)); //1.4
        printBookWithHighestPrice(getBooks(10)); //1.5
        getBooksWithNameSize5(getBooks(10)); //1.6
        getBooksWithPriceLessThan30(getBooks(10)); //1.7
        sortBooks(getBooks(10)); //1.8

        Book book1 = new Book("myBook", 5);
        Book book2 = new Book("myBook", 5);
        Map<Book, String> mapWithBook = new HashMap<>();
        mapWithBook.put(book1, "util");
        mapWithBook.get(book2);



    }

    private String name;
    private int price;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static List<Book> getBooks(int bookQuantity) {

        List<Book> books = new ArrayList<>();
        Random random = new Random();

        int priceUpperBound = 99;
        int priceLowBound = 1;
        int nameUpperBound = 10;
        int nameLowBound = 3;
        int leftLimit = 97;
        int rightLimit = 122;

        for (int i = 0; i < bookQuantity; i++) {
            int randomPrice = ThreadLocalRandom.current().nextInt(priceLowBound, priceUpperBound + 1);
            int nameRandomLength = ThreadLocalRandom.current().nextInt(nameLowBound, nameUpperBound + 1);
            String randomName = random.ints(leftLimit, rightLimit + 1)
                    .limit(nameRandomLength)
                    .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                    .toString();

            Book book = new Book(randomName, randomPrice);
            books.add(book);
        }
        return books;
    }

    public static void printNames(List<Book> books) {

        books.forEach(book -> System.out.println(book.getName()));
    }

    public static void printTotalPrice(List<Book> books) {

        int total = books.stream().mapToInt(Book::getPrice).sum();
        System.out.println("Total price of all books: " + total);
    }

    public static void printBooksWithA(List<Book> books) {

        Predicate<Book> containsA = (book) -> book.getName().toLowerCase().contains("a");
        List<Book> bookNameContainsA = books
                .stream()
                .filter(containsA)
                .collect(Collectors.toList());
        bookNameContainsA.forEach(book -> System.out.println(book.getName()));
    }

    public static void printBookWithHighestPrice(List<Book> books) {

        String bookWithHighestPrice = "";
        for (int i = 0; i < books.size() - 1; i++) {
            if (books.get(i).getPrice() < books.get(i + 1).getPrice()) {
                bookWithHighestPrice = books.get(i + 1).getName();
            }
        }
        System.out.println(bookWithHighestPrice);
    }

    public static int getBooksWithNameSize5(List<Book> books) {

        Predicate<Book> isSize5 = (book) -> book.getName().length() == 5;
        List<Book> booksWithNameSize5 = books
                .stream()
                .filter(isSize5)
                .collect(Collectors.toList());
        return booksWithNameSize5.size();
    }

    public static List<Book> getBooksWithPriceLessThan30(List<Book> books) {

        Predicate<Book> priceLessThan30 = (book) -> book.getPrice() < 30;
        List<Book> booksWithPriceLessThan30 = books
                .stream()
                .filter(priceLessThan30)
                .collect(Collectors.toList());
        return booksWithPriceLessThan30;
    }

    public static List<Book> sortBooks(List<Book> books) {
        return books.stream().sorted().collect(Collectors.toList());
    }


    @Override
    public String toString() {
        return "name: " + this.getName() + ", price: " + this.getPrice() + "\n";
    }

    @Override
    public int compare(Book o1, Book o2) {
        if (o1.getName().compareTo(o2.getName()) == 0) {
            return Integer.valueOf(String.valueOf(o1.getPrice() > (o2.getPrice())));
        }
        return o1.getName().compareTo(o2.getName());
    }
}
