/*
        Test Steps:
        1. Truy cập https://neymarsport.com/
        2. Nhấp vào user link (icon)
        3. Đăng nhập vào ứng dụng bằng thông tin xác thực đã tạo trước đó
        4. Click on 'User' (icon)
        5. *** note: After steps 4 and 5 "RECENT ORDERS" was not displayed
        Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending
        6. Lấy tất cả mã đơn hàng

 */
package test;
import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Test
public class testCase07 {
    public static void testCase07() {
        WebDriver driver = driverFactory.getChromeDriver();
        try {
            //1. Goto https://neymarsport.com/
            driver.get("https://neymarsport.com/");

            Thread.sleep(1000);
            // 2. Click on user link
            final By myUserLink = By.xpath("//a[@id='profile_icon']//*[name()='svg']");
            driver.findElement(myUserLink).click();

//            3. Đăng nhập vào ứng dụng bằng thông tin xác thực đã tạo trước đó
            WebElement emailField = driver.findElement(By.xpath("//input[@id='customer_email']"));
            emailField.sendKeys("emailll1@gmail.com");

            WebElement passwordField = driver.findElement(By.xpath("//input[@id='customer_password']"));
            passwordField.sendKeys("password");

            WebElement loginButton = driver.findElement(By.xpath("//input[@value='Đăng nhập']"));
            loginButton.click();
            Thread.sleep(500);

//            4. Click on 'User' (icon)
            WebElement myOrder = driver.findElement(By.xpath("//a[@id='profile_icon']//*[name()='svg']"));
            myOrder.click();
//                5. *** note: After steps 4 and 5 "RECENT ORDERS" was not displayed
//            Verify the previously created order is displayed in 'RECENT ORDERS' table and status is Pending

//            6. Lấy tất cả mã đơn hàng
            Thread.sleep(1000);
            List<WebElement> orderNumberElements = driver.findElements(By.cssSelector("#customer_orders tbody  .cancelled_order .text-center a"));
            System.out.println("Tất cả mã đơn bạn đã đặt là: ");
            for (WebElement orderNumberElement : orderNumberElements) {
                String orderNumber = orderNumberElement.getText();
                System.out.println(orderNumber);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.close();
        driver.quit();
    }
}
