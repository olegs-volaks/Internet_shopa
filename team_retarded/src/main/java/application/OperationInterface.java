package application;

import java.util.List;

public interface OperationInterface {

    long add (Product product);  // добавляет продукт в ArrayList, возвращает id (id генерится при добавлении продукта в список)
                                    // либо -1 в случае, если товар не был добавлен (не обязательно)

    long addList (List<Product> array); // добавляет список в основной список продуктов (далее в базу), с генерированием id-s

    long delete (Product product); // возвращает id (id генерируется при добавлении продукта в список)
                                    // либо -1 в случае, если товар не был найден в списке

    long deleteById (long id);      // 0 - удаление прошло успешно, -1 - продукт не был найден в списке

    void deleteAll();

    List<Product> deleteAllNamed (String productName); // удаляет из списка все продукты с именем productName,
                                                        // возвращает список удалённых продуктов (не обязательно)

    List<Product> deleteAllWithPrice (double price); // удаляет из списка все продукты с ценой price,
                                                         // возвращает список удалённых продуктов (не обязательно)



}
