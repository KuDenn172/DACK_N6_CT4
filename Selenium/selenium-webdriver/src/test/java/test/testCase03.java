package test;
/*
Test Steps:
1.  Go to https://soccerstore.vn/
2.  Click on -> Nike
3.  In the list of all nike , click on �ADD TO CART� for Bóng Nike Flight Premier League màu vàng-DN3602-710
4. Change �QTY� value to 1000 and click �UPDATE� button. Expected that an error is displayed
"The requested quantity for "Bóng Nike Flight Premier League màu vàng-DN3602-710" is not available.
5. Verify the error message
6. Then click on "Xóa" link in the footer of list of all nike. A message "SHOPPING CART IS EMPTY" is shown.
7. Verify cart is empty
*/

import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;
import java.util.List;

@Test
public class testCase03 {

    public static void testCase03(){
        WebDriver driver = driverFactory.getChromeDriver();

        //Debug only
        try {
            //Step 1 Go to https://soccerstore.vn/
            driver.get("https://soccerstore.vn/");

            //Debug only
            Thread.sleep(2000);
            final By popupNo = By.cssSelector("#onesignal-slidedown-cancel-button");
            List<WebElement> popupNoElements = driver.findElements(popupNo);
            if (!popupNoElements.isEmpty()) {
                popupNoElements.get(0).click();

                // Step 2. Click on -> Nike
                final By sanPhamLink = By.xpath("//img[@alt='Nike']");
                driver.findElement(sanPhamLink).click();
                Thread.sleep(1000);

                // Step 3: In the list of all nike, Click on add to cart
                final By clickProduct = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]");
                driver.findElement(clickProduct).click();
                final By addToCart = By.xpath("//button[@class='btn btn-primary single-product-submit']");
                driver.findElement(addToCart).click();

                Thread.sleep(1000);

                // Step 4: Change "QTY" value to 1000 and click "UPDATE" button
                    WebElement Quantity = driver.findElement(By.xpath("//input[@name='quantity']"));
                    Quantity.clear();
                    Quantity.sendKeys("1000");
                    final By update = By.xpath("//div[@id='page']");
                    driver.findElement(update).click();

                //// Step 5: Verify error message
                try{
                    WebElement Message = driver.findElement(By.xpath("//div[@class='alert shop-voucher-alert']"));
                    String errorMessage = "\"The requested quantity for \"Bóng Nike Flight Premier League màu vàng-DN3602-710\" is not available.";
                    String actualSuccessMessage = Message.getText().trim();
                    if (actualSuccessMessage.equals(errorMessage)) {
                        System.out.println("Không cho cập nhật");
                    } else {
                        System.out.println("Cho phép cập nhật");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }


                Thread.sleep(1000);
                // Step 6: Click on "Xóa" link
                final By ClickToEmpty = By.xpath("//i[@class='ti-trash']");
                driver.findElement(ClickToEmpty).click();


                // Step 7: Verify cart is empty
                WebElement VerifyEmptyCart = driver.findElement(By.xpath("//div[@class='alert shop-cart-alert']"));
                String cartMessage = "Không có sản phẩm nào trong giỏ hàng";
                String actualSuccessMessage = VerifyEmptyCart.getText().trim();
                if (actualSuccessMessage.equals(cartMessage)) {
                    System.out.println("Giỏ hàng trống");
                } else {
                    System.out.println("Giỏ hàng còn sản phẩm");
                }
                }
            } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.close();
        driver.quit();
    }

}
