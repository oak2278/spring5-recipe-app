package guru.springframework.bootstrap;

import guru.springframework.models.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

    private RecipeRepository recipeRepository;
    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes(){

        List<Recipe> recipes = new ArrayList<>(2);

        //get UOMs
        Optional<UnitOfMeasure> eachUomOptional = unitOfMeasureRepository.findByDescription("Each");

        if(!eachUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> dashUomOptional = unitOfMeasureRepository.findByDescription("Dash");

        if(!dashUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> pintUomOptional = unitOfMeasureRepository.findByDescription("Pint");

        if(!pintUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()){
            throw new RuntimeException("Expected UOM not found");
        }

        //get optionals
        UnitOfMeasure eachUom = eachUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure dashUom = dashUomOptional.get();
        UnitOfMeasure pintUom = pintUomOptional.get();
        UnitOfMeasure tblUom = tableSpoonUomOptional.get();
        UnitOfMeasure teaUom = teaSpoonUomOptional.get();

        //get Categories

        Optional<Category> americanCatOptional = categoryRepository.findByDescription("American");
        if(!americanCatOptional.isPresent()) { throw new RuntimeException("Expected Category not found");}

        Optional<Category> mexicanCatOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCatOptional.isPresent()) { throw new RuntimeException("Expected Category not found");}

        Category americanCategory = americanCatOptional.get();
        Category mexicanCategory = mexicanCatOptional.get();

        //Guac

        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime("10 minutes");
        guacRecipe.setCookTime("0 minutes");
        guacRecipe.setServings(3);
        Note guacNotes = new Note();
        guacNotes.setRecipeNotes("For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                "\n" +
                "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries (see our Strawberry Guacamole).\n" +
                "\n" +
                "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                "\n" +
                "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                "\n" +
                "For a deviled egg version with guacamole, try our Guacamole Deviled Eggs!\n" +
                "\n");
        guacRecipe.setNotes(guacNotes);
        guacRecipe.setDirections("1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.\n" +
                "\n" +
                "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)\n" +
                "\n" +
                "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                "\n" +
                "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                "\n");
        guacRecipe.getIngredients().add(new Ingredient("Ripe avocados", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("Kosher salt", new BigDecimal(.5), teaUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("fresh lime of lemon juice", new BigDecimal(1), tblUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("minced onion", new BigDecimal(3), tblUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("serrano chiles", new BigDecimal(2), eachUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("cilantro grated", new BigDecimal(2), tblUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("grated black pepper", new BigDecimal(1), dashUom, guacRecipe));
        guacRecipe.getIngredients().add(new Ingredient("ripe tomato, seeds and pulp removed", new BigDecimal(.5), eachUom, guacRecipe));
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.getCategories().add(mexicanCategory);
        guacRecipe.getCategories().add(americanCategory);

        //ChickenTacos

        Recipe tacoRecipe = new Recipe();

        tacoRecipe.setDescription("Spicy Grilled Chicken Tacos");
        tacoRecipe.setPrepTime("20 minutes");
        tacoRecipe.setCookTime("15 minutes");
        tacoRecipe.setServings(5);
        Note tacoNotes = new Note();
        tacoNotes.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, " +
                "on buy it online. (If you can't find ancho chili powder, you replace the ancho chili," +
                " the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, " +
                "though the flavor won't be quite the same.)");
        tacoRecipe.setNotes(tacoNotes);
        tacoRecipe.setDirections("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                "\n" +
                "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                "\n" +
                "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.");
        tacoRecipe.setSource("https://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/");
        tacoRecipe.setDifficulty(Difficulty.MODERATE);
        tacoRecipe.getCategories().add(mexicanCategory);
        tacoRecipe.getIngredients().add(new Ingredient("ancho chile powder", new BigDecimal(2),tblUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), teaUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("dried cumin", new BigDecimal(1), teaUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), teaUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("salt", new BigDecimal(.5), teaUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("finely chopped garlic clove", new BigDecimal(1), eachUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), tblUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("fresh squeezed Orange Juice", new BigDecimal(3), tblUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("Olive Oil", new BigDecimal(2), tblUom, tacoRecipe));
        tacoRecipe.getIngredients().add(new Ingredient("chicken thighs", new BigDecimal(5), eachUom, tacoRecipe));

        tacoNotes.setRecipe(tacoRecipe);
        guacNotes.setRecipe(guacRecipe);

        recipes.add(tacoRecipe);
        recipes.add(guacRecipe);
        return recipes;
    }
}
