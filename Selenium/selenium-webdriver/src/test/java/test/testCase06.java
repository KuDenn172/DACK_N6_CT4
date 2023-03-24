package test;
/* Verify user is able to purchase product using registered email id(USE CHROME BROWSER)
Test Steps:
1. Truy cập https://neymarsport.com/
2. Nhấp vào user link
3. Đăng nhập vào ứng dụng bằng thông tin xác thực đã tạo trước đó
4. Nhấp vào liên kết Giỏ hàng
5. Trong trang tiếp theo, nhấp vào thanh toán
6. Nhập số điện thoại để liên hệ
7. Chọn phương thức và nhập thông tin vận chuyển
8. Chọn phương thức thanh toán
9. Trong Thông tin thanh toán, kiểm tra Phí vận chuyển
10. Xác minh chi phí vận chuyển được tạo
11. Nhấp vào nút 'Hoàn tất đơn hàng'
12. Xác minh Đơn hàng đã được tạo. Lưu ý số thứ tự
*/


import driver.driverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
@Test
public class testCase06 {
    public static void testCase06(){
        WebDriver driver = driverFactory.getChromeDriver();
//Do trang lỗi trong qua trình đăng nhập ạ nên mong cô thử lại nhiều lần

        //Debug only
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


//            4. Nhấp vào liên kết Giỏ hàng
            WebElement gioHang = driver.findElement(By.xpath("//img[@alt='cart']"));
            gioHang.click();

            Thread.sleep(500);
//            5. Trong trang tiếp theo, nhấp vào thanh toán
            WebElement thanhToan = driver.findElement(By.xpath("//button[@id='checkout']"));
            thanhToan.click();

//            6. Nhập số điện thoại để liên hệ
            WebElement sdtField = driver.findElement(By.xpath("//input[@id='billing_address_phone']"));
            sdtField.sendKeys("012345679");

//            7. Chọn phương thức và nhập thông tin vận chuyển
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
//            8. Chọn phương thức thanh toán
            WebElement ppThanhToan = driver.findElement(By.xpath("//label[@for='payment_method_id_128303']"));
            ppThanhToan.click();

            Thread.sleep(1000);
//            9. Trong Thông tin thanh toán, kiểm tra Phí vận chuyển
            WebElement phiVanChuyen = driver.findElement(By.xpath("//tr[@class='total-line total-line-shipping']//td[@class='total-line-price']"));
            System.out.println("Phí vận chuyển đơn hàng của bạn là: " + phiVanChuyen.getText());

//            10. Xác minh chi phí vận chuyển được tạo
            String actualSuccessMessage = "Miễn phí";
            if (actualSuccessMessage.equals(phiVanChuyen.getText())) {
                System.out.println("Đúng chi phí vận chuyển");
            } else {
                System.out.println("Không đúng chi phí vận chuyển");
            }

            Thread.sleep(500);
//            11. Nhấp vào nút 'Hoàn tất đơn hàng'
            WebElement datHangButton = driver.findElement(By.xpath("//button[@class='step-footer-continue-btn btn']"));
            datHangButton.click();

            Thread.sleep(500);
//            12. Xác minh Đơn hàng đã được tạo. Lưu ý số thứ tự
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
