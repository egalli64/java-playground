/*
 * Java Playground
 * 
 * https://github.com/egalli64/java-playground
 * 
 * Examples from Effective Java (3rd edition) by Joshua Block
 * 
 * Chapter 2: Creating and Destroying Objects
 */
package effective.ch2.i2;

/**
 * NutritionFacts implementing the Telescoping Constructor pattern
 */
public class NutritionFactsTelescopic {
    private final int servingSize; // (mL) required
    private final int servings; // (per container) required
    private final int calories; // (per serving) optional
    private final int fat; // (g/serving) optional
    private final int sodium; // (mg/serving) optional
    private final int carbohydrate; // (g/serving) optional

    public NutritionFactsTelescopic(int servingSize, int servings) {
        this(servingSize, servings, 0);
    }

    public NutritionFactsTelescopic(int servingSize, int servings, int calories) {
        this(servingSize, servings, calories, 0);
    }

    public NutritionFactsTelescopic(int servingSize, int servings, int calories, int fat) {
        this(servingSize, servings, calories, fat, 0);
    }

    public NutritionFactsTelescopic(int servingSize, int servings, int calories, int fat, int sodium) {
        this(servingSize, servings, calories, fat, sodium, 0);
    }

    public NutritionFactsTelescopic(int servingSize, int servings, int calories, int fat, int sodium,
            int carbohydrate) {
        this.servingSize = servingSize;
        this.servings = servings;
        this.calories = calories;
        this.fat = fat;
        this.sodium = sodium;
        this.carbohydrate = carbohydrate;
    }

    @Override
    public String toString() {
        return "{servingSize=" + servingSize + ", servings=" + servings + ", calories=" + calories + ", fat=" + fat
                + ", sodium=" + sodium + ", carbohydrate=" + carbohydrate + "}";
    }
}
