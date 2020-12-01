package com.my.moms.pantry;


import java.util.Random;

public class grocery {
    private String name;
    private String lifecycle; //in days
    private String quantity;
    private String dateAdded;


    public grocery(){}



    public grocery(String name, String quantity, String lifecycle, String dateAdded){
        this.name = name;
        this.quantity = quantity;
        this.lifecycle = lifecycle;
        this.dateAdded = dateAdded;


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLifecycle() {
        return lifecycle;
    }

    public void setLifecycle(String mLifecycle) {
        this.lifecycle = mLifecycle;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String mQuantity) {
        this.quantity = mQuantity;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDatAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }


    private static final Random RANDOM = new Random();

    public static int getRandFoodImage() {
        switch (RANDOM.nextInt(13)) {
            default:
            case 0:
                return R.drawable.foods_1;
            case 1:
                return R.drawable.foods_2;
            case 2:
                return R.drawable.foods_3;
            case 3:
                return R.drawable.foods_4;
            case 4:
                return R.drawable.foods_5;
            case 5:
                return R.drawable.foods_6;
            case 6:
                return R.drawable.foods_7;
            case 7:
                return R.drawable.foods_8;
            case 8:
                return R.drawable.foods_9;
            case 9:
                return R.drawable.foods_10;
            case 10:
                return R.drawable.foods_11;
            case 11:
                return R.drawable.foods_12;
            case 12:
                return R.drawable.foods_13;
            case 13:
                return R.drawable.foods_14;

        }
    }



    public static final String[] foodStrings = {
            "lemons", "tomatoes", "carrots", "onions", "lettuce", "pickles", "peppers", "cilantro",
            "crackers", "pasta", "chicken", "steak", "salmon", "shrimp" , "lettuce",
            "corn", "green onions", "lemonade", "limes", "rice", "white rice", "milk", "limes",
            "mushrooms", "yellow onions", "oranges", "bell peppers", "pineapple", "curry", "soup", "eggs",
            "cheese"," pasta sauce", "muffins", "cookies", "bananas", "purple onion", "avocados", "broccoli",
            "celery", "ice cream", "pizza"," butter lettuce", "croissant", "steak", "jalepeno"
    };

}



