package utility;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {

    public static void takeScreenshot(WebDriver driver, String testName) {
        // Generate timestamp
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        // Create destination path
        String screenshotName = testName + "_" + timestamp + ".png";
        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + screenshotName;

        // Take screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        try {
            // Create folder if it doesn't exist
            File destDir = new File(System.getProperty("user.dir") + "/Screenshots");
            if (!destDir.exists()) {
                destDir.mkdir();
            }
            // Copy the screenshot to destination
            FileUtils.copyFile(srcFile, new File(screenshotPath));
            System.out.println("Screenshot saved: " + screenshotPath);
        } catch (IOException e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
    }
}