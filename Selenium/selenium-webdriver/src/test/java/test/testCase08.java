/*Verify you are able to change or reorder previously added product
        *  Test Data = QTY = 10
        Test Steps:
        1. Truy cập https://neymarsport.com/
        2. Nhấp vào user link (icon)
        3. Đăng nhập vào ứng dụng bằng thông tin xác thực đã tạo trước đó
        4. Go to Giầy bóng đá menu and thêm 1 sản phầm  vào giỏ hàng
        5. Click on 'Thanh toán' link ,
        6. Click on 'Giỏ hàng' and change QTY & click Update
        7. Verify Grand Total is changed
        8. Complete Billing & Shipping Information
        9. Verify order is generated and note the order number

 */
package test;

import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
@Test
public class testCase08 {
    public static void testCase08() {
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

//            4. Go to Giầy bóng đá menu and thêm 1 sản phầm vào giỏ hàng

            final By clickGiay = By.xpath("/html[1]/body[1]/div[2]/header[1]/div[2]/div[1]/div[2]/nav[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/a[1]/span[1]");
            driver.findElement(clickGiay).click();

            final By prodcut2 = By.xpath("//img[@alt='Giày đá bóng Kamito TA11 TF Touch of Magic - Neon/Gold']");
            driver.findElement(prodcut2).click();
            Thread.sleep(1000);

            final By addToCart2 = By.xpath("//button[@id='add-to-cart']");
            driver.findElement(addToCart2).click();

            Thread.sleep(1000);
//            5. Click on 'Thanh toán' link và thông tin số tiền ban đầu
            WebElement thanhToan = driver.findElement(By.xpath("//button[@id='checkout']"));
            thanhToan.click();
            WebElement firstPrice = driver.findElement(By.xpath("//span[@class='payment-due-price']"));
            String firstPriceText = firstPrice.getText().trim();
            System.out.println("Số tiền ban đầu: "+ firstPriceText);

//            6. Click on 'Giỏ hàng' and change QTY & click Update
            WebElement gioHang = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[2]/div[1]/ul[1]/li[1]/a[1]"));
            gioHang.click();
            Thread.sleep(1000);

            WebElement changQty = driver.findElement(By.xpath("//input[@id='updates_1100769690']"));
            changQty.clear();
            changQty.sendKeys("2");

            Thread.sleep(1000);

            final By update = By.xpath("//button[@id='update-cart']");
            driver.findElement(update).click();

//            7. Verify Grand Total is changed
            Thread.sleep(1000);
            WebElement thanhToan1 = driver.findElement(By.xpath("//button[@id='checkout']"));
            thanhToan1.click();

            WebElement lastPrice = driver.findElement(By.xpath("//span[@class='payment-due-price']"));
            String lastPriceText = lastPrice.getText().trim();
            if (firstPriceText.equals(lastPriceText)) {
                System.out.println("Số tiền không thay đổi");
            } else {
                System.out.println("Số tiền đã thay đổi");
            }

//            8. Complete Billing & Shipping Information
            WebElement sdtField = driver.findElement(By.xpath("//input[@id='billing_address_phone']"));
            sdtField.sendKeys("012345679");

            WebElement address = driver.findElement(By.xpath("//input[@id='billing_address_address1']"));
            address.sendKeys("HCM,Quận nào đó");
            Thread.sleep(1000);

            WebElement shippingTinh = driver.findElement(By.xpath("//select[@id='customer_shipping_province']"));
            Select selectOption = new Select(shippingTinh);
            selectOption.selectByVisibleText("Hồ Chí Minh");
            Thread.sleep(1000);

            WebElement shippingQuan = driver.findElement(By.xpath("//select[@id='customer_shipping_district']"));
            Select selectOption1 = new Select(shippingQuan);
            selectOption1.selectByVisibleText("Quận 1");
            Thread.sleep(1000);

            WebElement shippingPhuong = driver.findElement(By.xpath("//select[@id='customer_shipping_ward']"));
            Select selectOption2 = new Select(shippingPhuong);
            selectOption2.selectByVisibleText("Phường Bến Nghé");

            Thread.sleep(1000);
            WebElement ppThanhToan = driver.findElement(By.xpath("//input[@id='payment_method_id_128303']"));
            ppThanhToan.click();

            Thread.sleep(1000);

            WebElement datHangButton = driver.findElement(By.xpath("//button[@class='step-footer-continue-btn btn']"));
            datHangButton.click();

//            9. Verify order is generated and note the order number
            WebElement successMessage = driver.findElement(By.xpath("//h2[@class='os-header-title']"));
            String successMessageText = successMessage.getText().trim();
            if (successMessageText.equals("Đặt hàng thành công")) {
                WebElement orderNum = driver.findElement(By.xpath("//span[@class='os-order-number']"));
                System.out.println("Mã của đơn đặt hàng của bạn là: " + orderNum.getText());
            } else {
                System.out.println("Đặt hàng bị thất bại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // Close the browser
        driver.close();
        driver.quit();
    }
}
