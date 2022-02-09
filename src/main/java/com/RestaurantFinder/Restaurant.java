package com.RestaurantFinder;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<com.RestaurantFinder.Item> menu = new ArrayList<com.RestaurantFinder.Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        //default menu items
        this.menu.add(new com.RestaurantFinder.Item("Lemon Rice",73));
        this.menu.add(new com.RestaurantFinder.Item("Sambar Rice",45));
    }
    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public LocalTime getOpeningTime() { return openingTime; }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() { return closingTime; }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen(){
        LocalTime time = LocalTime.now();
        int isStillOpen = time.compareTo(closingTime);
        int isOpen = time.compareTo(openingTime);
        if(isStillOpen<0&&isOpen>=0){
            return true;
        }
        return false;
    }


    public List<com.RestaurantFinder.Item> getMenu() {
        return this.menu;
    }

    private com.RestaurantFinder.Item findItemByName(String itemName){
        for(com.RestaurantFinder.Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        com.RestaurantFinder.Item newItem = new com.RestaurantFinder.Item(name,price);
        menu.add(newItem);
    }

    public void removeFromMenu(String itemName) throws com.RestaurantFinder.itemNotFoundException {

        com.RestaurantFinder.Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new com.RestaurantFinder.itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public int getOrderValue(List<com.RestaurantFinder.Item> item){
        int totalValue = 0;
        for (com.RestaurantFinder.Item myItem : item) {
            totalValue += myItem.getPrice();
        }
        return totalValue;
    }
}
