package application;

public interface EditingInterface {

    long changeId (Product product);  // изменяет id, возвращает новый id - насколько это рационально?

    long changeProductNames (String oldProductName, String newProductName); //что-то вроде: заменить все наименования "хлеб белый" на "хлеб белый и пушистый"
    // возвращает -1 - если продуктов с oldProductName не найдено или количество продуктов, у которых были сделаны изменения
    // можно будет выводить: "5 items were changed"

    long changeProductSpecification (String oldSpecification, String newSpecification);

    long changePrices (String productName, double newPrice);

    void changePriceById (long id, double newPrice);

    long reducePrice (String productName, double percentage);

}
