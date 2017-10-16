package com.example.seunghyunlee.newapp.objects;

/**
 * Created by seunghyunlee on 8/1/17.
 */

public class MenulistObject {
    private String menuName;
    private String menuRecipe;
    private String menuCover;

    public MenulistObject(String menuName, String menuRecipe, String menuCover){
        this.menuName = menuName;
        this.menuRecipe = menuRecipe;
        this.menuCover = menuCover;
    }

    public String getMenuName(){
        return menuName;
    }

    public String getMenuRecipe(){
        return menuRecipe;
    }

    public String getMenuCover(){
        return menuCover;
    }

}
