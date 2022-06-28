import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Task3 extends Task2{

    //3. Calculate the average price of all listed items from the first page (it can happen that
    //several pages are available - pagination)

    @DisplayName("All listed items form the first page")
    @Test
    public void allItemsFromFirstPage() {

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

    }

    @DisplayName("All listed items - pagination")
    @Test
    public void amazonPagination2nd() {

        int total = 0;
        int averagePrice = 0;

        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("guitar" + Keys.ENTER);

        //while loop will search through next page until next button is enabled
        String disabled = "";
        while (!disabled.equals("true")) {

            WebElement nextButton = driver.findElement(By.cssSelector(".s-pagination-item.s-pagination-next"));

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
            try {

                for (String eachPrice : allElementsGetText) {

                    int sum = Integer.parseInt(eachPrice);

                    total += sum;

                }
            } catch (NumberFormatException e) {
            }

            //get average price by dividing total sum with number of guitars listed
            averagePrice = total / allElementsGetText.size();

            //just to see next page while running the test (not necessary)
            js.executeScript("arguments[0].scrollIntoView(true)", nextButton);

            //CLICK the next button if it is active Otherwise we will get while condition "false"
            nextButton.click();

            disabled = driver.findElement(By.cssSelector(".s-pagination-item.s-pagination-next")).getAttribute("aria-disabled") + "";
        }

        System.out.println("total = " + total);
        System.out.println("averagePrice = " + averagePrice);
    }
}
