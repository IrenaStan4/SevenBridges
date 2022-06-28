import org.junit.Test;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OptionalTasks extends Task2{

    /*
    a) If the number of items is even, please find the second cheapest item. If the number of
    items is an odd number, find the item whose price is closest to the average price
    (absolute value). When the item is chosen, print the item details (store name, item name
    and price).
    */

    @Test
    public void optionalTaskA() {

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("guitar" + Keys.ENTER);

        allElements = driver.findElements(By.xpath("//*[@id='search']//*[@class='a-price-whole']"));

        for (WebElement each : allElements) {

            allElementsGetText.add(each.getText());

        }

        //eliminate all items that have no price
        while (allElementsGetText.contains("")) {
            allElementsGetText.remove("");
        }


        //If the number of items is even, please find the second cheapest item.

        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();

        try {

        for (String each : allElementsGetText) {

            int eachItemPrice = Integer.parseInt(each);

            if (eachItemPrice % 2 == 0){
                evenNumbers.add(eachItemPrice);
            }else {
                oddNumbers.add(eachItemPrice);
            }
        }

        } catch (NumberFormatException e) {

        }

        Collections.sort(evenNumbers);

        int secondCheapestItem = evenNumbers.get(1);

        System.out.println("evenNumbers = " + evenNumbers);
        System.out.println("secondCheapestItem = " + secondCheapestItem);


        //If the number of items is an odd number, find the item whose price is closest to the average price
        //(absolute value).

        int sum = 0;

        for (int each : oddNumbers) {

            sum += each;

        }

        int averagePrice = sum / oddNumbers.size();

        System.out.println("sum = " + sum);
        System.out.println("averagePrice = " + averagePrice);

        oddNumbers.add(averagePrice);

        Collections.sort(oddNumbers);

        System.out.println("oddNumbers = " + oddNumbers);


        int numberBeforeAveragePrice = oddNumbers.get(oddNumbers.indexOf(averagePrice) -1);

        System.out.println("numberBeforeAveragePrice = " + numberBeforeAveragePrice);

        int numberAfterAveragePrice = oddNumbers.get(oddNumbers.indexOf(averagePrice) +1);

        System.out.println("numberAfterAveragePrice = " + numberAfterAveragePrice);


        int itemWithPriceClosestToAverage;

        if (averagePrice - numberBeforeAveragePrice > numberAfterAveragePrice - averagePrice){

            itemWithPriceClosestToAverage = numberAfterAveragePrice;

        }else {
            itemWithPriceClosestToAverage = numberBeforeAveragePrice;
        }

        System.out.println("itemWithPriceClosestToAverage = " + itemWithPriceClosestToAverage);

        //When the item is chosen, print the item details (store name, item name
        //and price).

        WebElement item = driver.findElement(By.xpath("//img[@alt='YAMAHA FG800 Solid Top Acoustic Guitar,Natural,Guitar Only']"));
        item.click();
        WebElement storeName = driver.findElement(By.xpath("//a[text()='Visit the Yamaha Store']"));
        WebElement itemName = driver.findElement(By.xpath("//span[text()='        YAMAHA FG800 Solid Top Acoustic Guitar,Natural,Guitar Only       ']"));
        WebElement itemPrice = driver.findElement(By.xpath("//*[@class='a-price-whole']"));

        System.out.println("storeName.getText() = " + storeName.getText());
        System.out.println("itemName.getText() = " + itemName.getText());
        System.out.println("itemPrice.getText() = " + itemPrice.getText());
    }
}

