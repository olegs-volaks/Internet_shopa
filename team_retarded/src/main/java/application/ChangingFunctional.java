package main.java.application;

public interface ChangingFunctional {

    long changeId (Product product);  // изменяет id, возвращает новый id - насколько это рационально?

    long changeProductNames (String oldProductName, String newProductName); //что-то вроде: заменить все наименования "хлеб белый" на "хлеб белый и пушистый"
    // возвращает -1 - если продуктов с oldProductName не найдено или количество продуктов, у которых были сделаны изменения
    // можно будет выводить: "5 items were changed"

    long changeProductSpecification (String oldSpecification, String newSpecification);

    long changePrices (String productName, double newPrice);

    void changePriceById (long id, double newPrice);

    long reducePrice (String productName, double percentage);


    void reIdentifying ();  // при удалении продукта id других продуктов не изменяются. для непрерывной идентификации (нумерации)
    // время от времени нужно запускать этот метод. он переписывает id всех продуктов непрерывно
    // возможно этому методу стоит чего-то возвращать - например максимальный id


}
