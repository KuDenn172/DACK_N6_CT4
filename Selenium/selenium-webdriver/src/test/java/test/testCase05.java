package test;
/* Verify can create an account in e-Commerce site and can share wishlist to other poeple using email.
Test Steps:
1. Go to https://neymarsport.com/
2. Click on User link
3. Click đăng ký link and fill New User information
4. Click Đăng ký
5. Verify Registration is done. Expected account registration done.
6. Go to Giầy bóng đá menu
7. Click select nike
8.Thêm vào giỏ hàng  1 sản phẩm và quay trở lại  mua hàng

*/


import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Test
public class testCase05 {
    public static void testCase05(){
        WebDriver driver = driverFactory.getChromeDriver();


        //Debug only
        try {
            //1. Goto https://neymarsport.com/
            driver.get("https://neymarsport.com/");

            Thread.sleep(1000);

            // 2. Click on user link
            final By myUserLink = By.xpath("//a[@id='profile_icon']//*[name()='svg']");
            driver.findElement(myUserLink).click();

//            3. Click đăng ký link and fill New User information
            final By myAccountLink = By.xpath("//div[@class='req_pass']//a[@title='Đăng ký'][contains(text(),'Đăng ký')]");
            driver.findElement(myAccountLink).click();

            WebElement firstNameField = driver.findElement(By.xpath("//input[@id='first_name']"));
            firstNameField.sendKeys("Tu1an112");

            WebElement lastNameField = driver.findElement(By.xpath("//input[@id='last_name']"));
            lastNameField.sendKeys("Ki1et112");

            WebElement emailField = driver.findElement(By.xpath("//input[@id='email']"));
            String emailValue = "emailll1@gmail.com";
            emailField.sendKeys(emailValue);

            WebElement passwordField = driver.findElement(By.xpath("//input[@id='password']"));
            passwordField.sendKeys("password");

//            4. Click ob button Đăng ký
            final By registerButton = By.xpath("//input[@value='Đăng ký']");
            driver.findElement(registerButton).click();

//            5. Verify Registration is done. Expected account registration done.
            WebElement successMessage = driver.findElement(By.xpath("//p[contains(@class,'email')]"));
            String actualSuccessMessage = successMessage.getText().trim();
            if (actualSuccessMessage.equals(emailValue)) {
                System.out.println("Đăng ký thành công");
            } else {
                System.out.println("Đăng ký tài khoản không thành công");
            }


            Thread.sleep(1000);

//            6. Go to Giầy bóng đá menu
            final By clickGiay = By.xpath("/html[1]/body[1]/div[2]/header[1]/div[2]/div[1]/div[2]/nav[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/a[1]/span[1]");
            driver.findElement(clickGiay).click();
            Thread.sleep(1000);

//            7. Click select nike
            final By nike = By.xpath("//div[@id='repontop-bor']//li[1]//label[1]//input[1]");
            driver.findElement(nike).click();
            Thread.sleep(1000);

//            8. Thêm vào giỏ hàng  1 sản phẩm và quay trở lại  mua hàng
            final By prodcut1 = By.xpath("//img[@alt='Nike Football Academy Premier League - White/Bright Crimson/Black']");
            driver.findElement(prodcut1).click();
            Thread.sleep(1000);
            final By addToCart1 = By.xpath("//button[@id='add-to-cart']");
            driver.findElement(addToCart1).click();

            Thread.sleep(1000);
            final By comeBack = By.xpath("//div[@class='comeback']//a[@href='/collections/all']");
            driver.findElement(comeBack).click();

            Thread.sleep(1000);
            final By prodcut2 = By.xpath("//img[@alt='adidas Predator Accuracy .4 TF Own Your Football - Core Black/Footwear White/Shock Pink']");
            driver.findElement(prodcut2).click();

            Thread.sleep(1000);
            final By addToCart2 = By.xpath("//button[@id='add-to-cart']");
            driver.findElement(addToCart2).click();

            } catch (Exception e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.close();
        driver.quit();
    }
}
