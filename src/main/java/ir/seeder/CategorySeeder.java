package ir.seeder;

import ir.entity.Category;
import ir.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
@Order(1)
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        // Check if categories already exist
        if (categoryRepository.count() == 0) {
            List<Category> categories = List.of(
                    createCategory("Persian", "Traditional Iranian dishes"),
                    createCategory("Italian", "Authentic Italian recipes"),
                    createCategory("Vegetarian", "Plant-based meal options")
            );
            categoryRepository.saveAll(categories);
        }
    }

    private Category createCategory(String name, String description) {
        Category category = new Category();
        category.setName(name);
        category.setDescription(description);
        return category;
    }
}