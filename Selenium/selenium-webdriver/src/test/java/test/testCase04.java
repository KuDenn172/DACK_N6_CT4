package test;
/*
Test Steps:
1. Go to https://soccerstore.vn/
2.  Click on -> Nike
3. In nike products list , click -> Nike Mercurial Vapor 15 Pro TF Air Zoom màu trắng xanh – Nike Blast Pack – DJ5605-146
4. Click on ��Add To cart�� button. Không chọn size
5. Verify the pop-up window and check that the products are reflected in it
Heading "Kết quả" with selected products in it.
6. Close the Popup
*/

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import driver.driverFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
@Test
public class testCase04 {
    public static void testcase04(){
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


//            3. In nike products list , click -> Nike Mercurial Vapor 15 Pro TF Air Zoom màu trắng xanh – Nike Blast Pack – DJ5605-146
                WebElement selectPro = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/a[1]/span[1]"));
                selectPro.click();



//          4. Click on ��Add To cart�� button. Không chọn size and  A popup opens
            WebElement AddToCart = driver.findElement(By.xpath("//button[@class='btn btn-primary single-product-submit']"));
            AddToCart.click();

                Thread.sleep(500);

//        5. Verify the pop-up window and check that the products are reflected in it
//        Heading "Kết quả" with selected products in it.
            WebElement popupTitle = driver.findElement(By.cssSelector(".modal-title"));
            String titleText = popupTitle.getText();
            if (titleText.equals("Kết quả")) {
                WebElement productInfo = driver.findElement(By.cssSelector(".modal-body"));
                String productText = productInfo.getText();
                    System.out.println("Nội dung popup: "+productText);
            } else {
                System.out.println("Kiểm tra thất bại: popup không hiển thị");
            }

//        6. Close the Popup
                WebElement closeButton = driver.findElement(By.cssSelector(".btn-close-popup"));
                closeButton.click();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.close();
        driver.quit();
    }



}
