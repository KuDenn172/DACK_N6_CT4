
package test;
/*
Test Steps
Step 1. Go to https://soccerstore.vn/
Step 2. Xác minh tiêu đề của trang
Step 3. Click on -> Sản phẩm
Step 4. Chọn danh mục "Phụ kiện bóng đá"
Step 5. Xác minh tất cả các sản phẩm đều là phụ kiện bóng đá
*/
import org.openqa.selenium.*;
import driver.driverFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class testCase01 {

    public static void testCase01(){
        WebDriver driver = driverFactory.getChromeDriver();
        try {

            //Step 1 Go to https://soccerstore.vn/
            driver.get("https://soccerstore.vn/");

            //Step 2. Xác minh tiêu đề của trang
            String actualTitle = driver.getTitle();
            String expectedTitle = "Giày Đá Bóng - Giày Bóng Đá Chính Hãng | Soccerstore.vn";
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Tiêu đề đúng.");
            } else {
                System.out.println("Tiêu đề sai.");
            }
            Thread.sleep(2000);

            final By popupNo = By.cssSelector("#onesignal-slidedown-cancel-button");
            List<WebElement> popupNoElements = driver.findElements(popupNo);
            if (!popupNoElements.isEmpty()) {
                popupNoElements.get(0).click();


                // Step 3. Click on -> Sản phẩm
                final By sanPhamLink = By.xpath("/html[1]/body[1]/div[3]/header[1]/div[2]/div[1]/div[1]/nav[1]/ul[1]/li[1]/a[1]");
                driver.findElement(sanPhamLink).click();
                Thread.sleep(1000);

                //Step 4. Chọn danh mục "Phụ kiện bóng đá"
                final By selectProduct = By.xpath("//ul[@class='filter-terms']//a[contains(text(),'Phụ kiện bóng đá')]");
                driver.findElement(selectProduct).click();


                //Step 5. Xác minh tất cả các sản phẩm đều là phụ kiện bóng đá
                List<WebElement> sanPham = driver.findElements(By.cssSelector(".item-pro > .title-pro"));
                for (WebElement sp : sanPham) {
                    String sanPhamText = sp.getText();
                    if (!sanPhamText.contains("Phụ kiện bóng đá")) {
                        Assert.fail("Có một sản phẩm không phải là phụ kiện bóng đá.");
                    }
                }
                System.out.println("Tất cả các sản phẩm là phụ kiện bóng đá.");
                }
            } catch(Exception e){
//            để in ra thông tin về lỗi (exception) và ngăn chặn chương trình bị dừng đột ngột khi có lỗi xảy ra.
                e.printStackTrace();
            }

        driver.close();
        driver.quit();
    }
}