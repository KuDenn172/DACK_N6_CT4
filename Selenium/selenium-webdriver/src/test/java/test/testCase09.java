/*  Verify Discount Coupon works correctly

Test Case Description:
  1. Truy cập https://neymarsport.com/
  2. Go to Giầy bóng đá menu and thêm 1 sản phầm  vào giỏ hàng
  3.Nhấp vào thanh toán
  4. Nhập mã giảm giá
  5. Xác minh giảm giá được tạo

TestData:  Coupon Code: NEYMARSPORT
*/

package test;
import driver.driverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

@Test
public class testCase09 {
    public static void TestCase9(){
        WebDriver driver = driverFactory.getChromeDriver();
        try{
            //1. Goto https://neymarsport.com/
            driver.get("https://neymarsport.com/");


//            4. Go to Giầy bóng đá menu and thêm 1 sản phầm vào giỏ hàng
            final By clickGiay = By.xpath("/html[1]/body[1]/div[2]/header[1]/div[2]/div[1]/div[2]/nav[1]/div[1]/div[2]/div[1]/div[2]/ul[1]/li[3]/a[1]/span[1]");
            driver.findElement(clickGiay).click();
            Thread.sleep(1000);

            final By prodcut2 = By.xpath("//img[@alt='adidas Predator Accuracy .4 TF Own Your Football - Core Black/Footwear White/Shock Pink']");
            driver.findElement(prodcut2).click();
            Thread.sleep(1000);

            final By addToCart2 = By.xpath("//button[@id='add-to-cart']");
            driver.findElement(addToCart2).click();

            Thread.sleep(1000);
//            5. Click on 'Thanh toán' link
            WebElement thanhToan = driver.findElement(By.xpath("//button[@id='checkout']"));
            thanhToan.click();

            Thread.sleep(1000);
//            6. Nhập mã giảm giá
            WebElement maGiamGia = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/div[1]/input[1]"));
            maGiamGia.clear();
            maGiamGia.sendKeys("NEYMARSPORT");


//            7. Xác minh giảm giá được tạo
            WebElement suDung = driver.findElement(By.xpath("/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/div[1]/div[1]/div[2]/form[1]/div[1]/div[1]/div[1]/button[1]"));
            suDung.click();
            Thread.sleep(1000);
                WebElement Message = driver.findElement(By.cssSelector("div[class='order-summary order-summary-is-collapsed'] p[class='field-message field-message-error']"));
                String expectedMessage = "Sử dụng mã giảm giá thành công";
                String actualMessage = Message.getText().trim();
                if (actualMessage.equals(expectedMessage)) {
                    System.out.println(expectedMessage);
                } else {
                    System.out.println("Không tìm thấy mã giảm giá");
                }

        }catch (Exception e){
            e.printStackTrace();
        }
        driver.quit();
    }
}

