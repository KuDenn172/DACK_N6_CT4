package test;
/*
Test Steps:
1.  Go to https://soccerstore.vn/
2.  Click on -> Nike
3. In the list of all Sản phẩm , read the cost of Bóng Nike Flight Premier League màu vàng-DN3602-710 (which is 3.800.000đ)
4. Click on Bóng Nike Flight Premier League màu vàng-DN3602-710
5. Read the Bóng Nike Flight Premier League màu vàng-DN3602-710 from detail page.
6. Compare Product value in list and details page should be equal (3.800.000đ).
*/


import driver.driverFactory;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.util.List;

@Test
public class testCase02 {
    public static void testCase02(){
        WebDriver driver = driverFactory.getChromeDriver();

    try{
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


//        3. In the list of all nike , read the cost of Bóng Nike Flight Premier League màu vàng-DN3602-710 (which is 3.800.000đ)
            WebElement giaSanPham = driver.findElement(By.xpath("//span[contains(text(),'3.800.000đ')]"));
            String Cost = giaSanPham.getText();
            System.out.println("The main cost: " + Cost);

            Thread.sleep(1000);
            //4. Click on -> Bóng Nike Flight Premier League màu vàng-DN3602-710
            final By clickProduct = By.xpath("/html[1]/body[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/a[1]/span[1]");
            driver.findElement(clickProduct).click();


            //5. Read the Bóng Nike Flight Premier League màu vàng-DN3602-710
            WebElement XperiaDetailPage = driver.findElement(By.xpath("//h1[@class='single-product-title']"));
            String XperiaDetailText = XperiaDetailPage.getText();
            System.out.println("Trang chi tiết về: " + XperiaDetailText);


            //6. Compare Product value in list and details page should be equal (3.800.000đ).
            WebElement priceDetailPage = driver.findElement(By.cssSelector(".single-product-price.cl--primary"));
            String priceDetailText = priceDetailPage.getText();

            if (Cost.equals(priceDetailText)) {
                System.out.println("Giá trị Sản phẩm bằng");
            } else {
                System.out.println("Giá trị Sản phẩm Không bằng");
            }

        }

    }catch (Exception e){
        e.printStackTrace();
    }
        // Close the browser
        driver.close();
        driver.quit();
    }
}
