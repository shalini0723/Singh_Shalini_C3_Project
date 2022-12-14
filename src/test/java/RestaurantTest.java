import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE

    @BeforeEach
    public void setUp(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant res = new Restaurant("Mock Restaurant","",LocalTime.parse("10:00:00"),LocalTime.parse("22:00:00"));
        Restaurant spiedRes = Mockito.spy(res);
        when(spiedRes.getCurrentTime()).thenReturn( LocalTime.parse("11:00:00"));
        assertTrue(spiedRes.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant res = new Restaurant("Mock Restaurant","",LocalTime.parse("10:00:00"),LocalTime.parse("22:00:00"));
        Restaurant spiedRes = Mockito.spy(res);
        when(spiedRes.getCurrentTime()).thenReturn(LocalTime.parse("23:00:00"));
        assertFalse(spiedRes.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>ITEM TOTAL<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void select_item_from_menu_should_give_total_price_of_selected_item_if_items_are_present() {
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Sweet corn soup");
        selectedItems.add("Vegetable lasagne");
        assertEquals(restaurant.getPriceForSelectedItems(selectedItems),388);
    }

    @Test
    public void select_item_from_menu_should_give_0_price_of_selected_item_if_items_are_Not_present() {
        List<String> selectedItems = new ArrayList<>();
        selectedItems.add("Missing Item1");
        assertEquals(restaurant.getPriceForSelectedItems(selectedItems),0);
    }

    @Test
    public void select_item_from_menu_should_give_0_price_of_selected_item_if_no_items_are_selected() {
        List<String> selectedItems = new ArrayList<>();
        assertEquals(restaurant.getPriceForSelectedItems(selectedItems),0);
    }
    //<<<<<<<<<<<<<<<<<<<<<<<ITEM TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}