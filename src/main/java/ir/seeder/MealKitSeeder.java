package ir.seeder;

import ir.entity.Category;
import ir.entity.MealKit;
import ir.repository.CategoryRepository;
import ir.repository.MealKitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Order(2)
@Slf4j
public class MealKitSeeder implements CommandLineRunner {

    private final MealKitRepository mealKitRepository;
    private final CategoryRepository categoryRepository;

    /**
     * This method runs automatically when the Spring Boot application starts.
     * It checks whether meal kits already exist in the database.
     * If none are found, it triggers the seeding process to populate initial meal kit data.
     */
    @Override
    public void run(String... args) {
        if (mealKitRepository.count() == 0) {
            seedMealKits();
        } else {
            log.info("Meal kits already exist, skipping seeding.");
        }
    }

    /**
     * Seeds the database with a predefined list of meal kits if
     * there are at least 3 categories available.
     * Each meal kit is associated with a distinct category.
     */
    protected void seedMealKits() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.size() >= 3) {
            List<MealKit> mealKits = List.of(
                    createMealKit("Ghormeh Sabzi", "A traditional Persian stew with herbs and meat", 15.99, 450, 30, categories.get(0)),
                    createMealKit("Margherita Pizza", "Classic Italian pizza with tomato and mozzarella", 12.99, 300, 20, categories.get(1)),
                    createMealKit("Veggie", "Healthy vegetarian with assorted vegetables", 10.99, 250, 15, categories.get(2))
            );
            mealKitRepository.saveAll(mealKits);
            log.info("Successfully seeded {} meal kits.", mealKits.size());
        } else {
            log.warn("Not enough categories to seed meal kits. Found: {}", categories.size());
        }
    }

    /**
     * Creates a new MealKit entity and assigns it to the specified category.
     * Note: This method sets the relationship only from MealKit to Category
     * and avoids touching category.getMealKits() to prevent LazyInitializationException.
     */
    private MealKit createMealKit(String name, String description, Double price, Integer calories, Integer prepTime, Category category) {
        MealKit mealKit = new MealKit();
        mealKit.setName(name);
        mealKit.setDescription(description);
        mealKit.setPrice(price);
        mealKit.setCalories(calories);
        mealKit.setPrepTime(prepTime);
        mealKit.setImageUrl(null);
        mealKit.setCategories(Collections.singletonList(category)); // Set one side of relationship only
        return mealKit;
    }
}
