package controllers;

import constants.Message;
import constants.Path;
import constants.Regex;
import models.Ingredient;
import models.Menu;
import models.Searcher;
import utils.ConsoleColors;
import utils.StringTools;
import utils.Utils;

import java.util.*;

public class MenuManagement implements Searcher<Menu> {
    private List<Menu> drinkList = new ArrayList<>();
    private Map<Ingredient,Integer> recipe;
    private IngredientManagement idm = new IngredientManagement();
    private static Scanner sc = new Scanner(System.in);

    //func 2.1: Add the drink to the menu
    public void addDrink(){
        idm.loadData(Path.URL_INGREDIENT_TXT);
        boolean isExist = false;
        String code;
        do{
            isExist = false;
            // Add a new drink to the list
            code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
            for (Menu drink : drinkList) {
                if (drink.getCode().equalsIgnoreCase(code)) {
                    isExist = true;
                    System.out.println(Message.DRINK_CODE_IS_EXISTED + ", " + Message.ADD_DRINK_FAILED);
                    break;
                }
            }
        }while(isExist);
        String name = Utils.getString(Message.INPUT_DRINK_NAME, Regex.D_NAME, Message.DRINK_NAME_IS_REQUIRED, Message.DRINK_NAME_MUST_START_WITH_LETTER);
        recipe = new HashMap<>();
        do{
            idm.showIngredientList();
            recipe.putAll(inputIngredientCodeAndQuantity());
        }while(Utils.getUserConfirmation(Message.DO_YOU_WANT_TO_CONTINUE_TO_ADD_INGREDIENT));
        //add the drink to the list
        drinkList.add(new Menu(code, name, recipe));
    }

    private Map<Ingredient,Integer> inputIngredientCodeAndQuantity(){
        Map<Ingredient,Integer> ingredientMap = new HashMap<>();
        //input each ingredient code and quantity
        String iCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE,Message.INGREDIENT_CODE_IS_REQUIRED, Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);
        //find the ingredient by code
        //if the ingredient is not found, ask the user to input again
        if(idm.checkToExist(iCode)){
            int quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, 0);
            //add the ingredient and quantity to the list
            recipe.put(idm.searchObject(iCode), quantity);
        }else{
            System.out.println("The ingredient is not found");
        }
        return ingredientMap;
    }

    //func 2.2: Update the drink information
    public void updateDrink(){
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObject(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_DOES_NOT_EXIST);
            return;
        }
        //check if the ingredient code is existed
        System.out.println("Before updating: ");
        menuItem.showInfo();

        String name = Utils.getString(Message.INPUT_DRINK_NAME, Regex.D_NAME, Message.DRINK_NAME_IS_REQUIRED, Message.DRINK_NAME_MUST_START_WITH_LETTER);
        if(name.isEmpty()){
            name = menuItem.getName();
        }else{
            menuItem.setName(name);
        }

        String ingredientCode = Utils.getString(Message.INPUT_INGREDIENT_ID, Regex.I_CODE, Message.INGREDIENT_CODE_IS_REQUIRED, Message.INGREDIENT_CODE_MUST_BE_I_AND_2_DIGITS);


        for(Ingredient ingredient : recipe.keySet()){
            if(ingredient.getCode().equals(ingredientCode)){
                int quantity = Utils.getInt(Message.INPUT_INGREDIENT_QUANTITY, 0);

            }
        }
    }

    //func 2.3: Delete the drink
    public void deleteDrink(){
        String code = Utils.getString(Message.INPUT_DRINK_CODE, Regex.D_CODE, Message.DRINK_CODE_IS_REQUIRED, Message.DRINK_CODE_MUST_BE_D_AND_2_DIGITS);
        Menu menuItem = this.searchObject(code);
        if(menuItem == null) {
            System.out.println(Message.DRINK_DOES_NOT_EXIST);
            return;
        }
        drinkList.remove(menuItem);
        System.out.println(Message.DELETE_DRINK_SUCCESSFULLY);
    }

    //func 2.4: Show all drinks in the menu
    public void showAllDrinks(){
        if(drinkList.isEmpty()){
            System.out.println(Message.DRINK_LIST_IS_EMPTY);
            return;
        }
        this.sortDrinkListByName(drinkList);
        StringTools.printLine("d");
        for(Menu menu : drinkList){
            System.out.printf(ConsoleColors.PURPLE_BACKGROUND + "| %-5s | %-20s | " + ConsoleColors.RESET, menu.getCode(), menu.getName());
            Map<Ingredient, Integer> recipe = menu.getRecipe();
            String titleRecipe = String.format("| %-5s | %-20s | %-10s |", "Code", "Name", "Quantity");
            for (Map.Entry<Ingredient, Integer> entry : recipe.entrySet()) {
                Ingredient ingredient = entry.getKey();
                int quantity = entry.getValue();
                System.out.printf("\n| %-20s | %-10d |", ingredient.getName(), quantity);
                StringTools.printLine("d");
            }
        }
    }

    private void sortDrinkListByName(List<Menu> drinkList){
        drinkList.sort(new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
    }

    @Override
    public boolean checkToExist(String code) {
        Menu menu = this.searchObject(code);
        return menu != null;
    }

    @Override
    public int searchIndex(String code) {
        for(int i = 0; i < drinkList.size(); i++){
            if(drinkList.get(i).getCode().equals(code)){
                return i;
            }
        }
        return -1;
    }

    @Override
    public Menu searchObject(String code) {
        int pos = this.searchIndex(code);
        return pos == -1 ? null : drinkList.get(pos);
    }
}
