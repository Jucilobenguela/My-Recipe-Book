package academy.mindswap.my_recipe_book.enums;

public enum Category {
    BREAK_FAST(1),
    LUNCH(2),
    DESSERT(3),
    DINNER(4);
    private int numberDescription;

    private Category(int numberDescription) {
        this.numberDescription=numberDescription;
    }

    public int getNumberDescription() {
        return numberDescription;
    }
}
