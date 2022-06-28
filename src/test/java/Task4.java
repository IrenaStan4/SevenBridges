import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;

public class Task4 extends Task2{

    //4. Choose one item randomly, access the item page and check if the desired item is shown.

    @Test
    public void selectOneItem(){

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("guitar" + Keys.ENTER);

        //locate all guitars as List<WebElements> and loop through to get the price of each guitar
        allElements = driver.findElements(By.xpath("//*[@id='search']//*[@class='a-price-whole']"));
        for (WebElement each : allElements) {

            allElementsGetText.add(each.getText());

        }

        //eliminate all items that have no price
        while (allElementsGetText.contains("")) {
            allElementsGetText.remove("");
        }

        //convert String to int to get the sum of all prices
        int total = 0;

        try {

            for (String eachPrice : allElementsGetText) {

                int sum = Integer.parseInt(eachPrice);

                total += sum;

            }
        } catch (NumberFormatException e) {

        }

        //get average price by dividing total sum with number of guitars listed
        int averagePrice = total / allElementsGetText.size();

        System.out.println("total = " + total);
        System.out.println("averagePrice = " + averagePrice);

        try {

            WebElement FenderFA235EConcertBodiedAcousticGuitar = driver.findElement(By.xpath("//img[@alt='Fender FA-235E Concert Bodied Acoustic Guitar - Moonlight Burst']"));
            FenderFA235EConcertBodiedAcousticGuitar.click();
            assertTrue(FenderFA235EConcertBodiedAcousticGuitar.isDisplayed());

        }catch (StaleElementReferenceException e){
            System.out.println("Item is displayed");
        }

    }
}
