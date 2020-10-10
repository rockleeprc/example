package cn.kd.service;

import okhttp3.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TestLogin3 {
    static {
        // https://github.com/mozilla/geckodriver/releases
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Downloads\\geckodriver.exe");
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        t1();
    }

    public static void t4() throws IOException {
        String url = "https://www4c1.53kf.com/callback_down.php?arg=p10129013_10177802&action=down_record&url=/20201009/619652de-36ec-421b-8446-ad950440ad48.zip&file_name=%E8%81%8A%E5%A4%A9%E8%AE%B0%E5%BD%95excel%E4%B8%8B%E8%BD%BD2020-06-08%2000:00:00~2020-09-08%2023:59:59.zip&alias=www4c1.53kf.com&time=1602238732&down_key=4a3b5d8c129926724ce7b5a25511ff6d";
        OkHttpClient client = new OkHttpClient.Builder().build();
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        Call call = client.newCall(request);
        Response response = call.execute();
        //获取下载的内容输入流
        ResponseBody body = response.body();
        InputStream inputStream = body.byteStream();
        final long lengh = body.contentLength();
        System.out.println("文件大小" + lengh);
        // 文件保存到本地
        File file1 = new File("D:\\");
        File file = new File(file1, "ok2.zip");
        FileOutputStream outputStream = new FileOutputStream(file);
        int lien = 0;
        byte[] bytes = new byte[1024];
        while ((lien = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, lien);
        }
        outputStream.flush();
        inputStream.close();
        outputStream.close();
        System.out.println("下载完成");
    }

    /**
     * 下载
     */
    public static void t3() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Downloads\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addPreference("browser.download.dir", "D:\\");
        //browser.download.folderList 设置Firefox的默认 下载 文件夹。0是桌面；1是“我的下载”；2是自定义
        options.addPreference("browser.download.folderList", 2);
        options.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream, application/vnd.ms-excel, text/csv, application/zip");
        WebDriver driver = new FirefoxDriver(options);
        driver.get("https://www4c1.53kf.com/callback_down.php?arg=p10129013_10177802&action=down_record&url=/20201009/b330bf1e-1031-4977-8d36-1142be6751d0.zip&file_name=%E8%81%8A%E5%A4%A9%E8%AE%B0%E5%BD%95excel%E4%B8%8B%E8%BD%BD2020-10-07%2000:00:00~2020-10-08%2023:59:59.zip&alias=www4c1.53kf.com&time=1602237356&down_key=2d5bd36f7282d1e967961125ccbf9f50");

        sleep(60);
    }

    /**
     * 点击删除
     */
    public static void t2() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Downloads\\geckodriver.exe");
        DesiredCapabilities caps = setDownloadsPath();//更改默认下载路径

        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.53kf.com/login/guide?url=http://www.53kf.com");

        WebElement email = driver.findElement(By.name("account"));
        email.sendKeys("53kf58@sina.com");
        sleep(2);
        driver.findElement(By.className("header-title")).click();

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("g48t1s50");
        sleep(2);
        WebElement submit = driver.findElement(By.className("submit-btn"));
        submit.click();
        sleep(2);
        driver.get("https://www.53kf.com/");
        sleep(2);
        System.out.println("title=" + driver.getTitle());

        WebElement parentMenu = driver.findElement(By.className("user"));
        Actions builder = new Actions(driver);
        builder.moveToElement(parentMenu).perform(); // 将鼠标移动到user上
        sleep(10);

        WebDriverWait cusSystemWait = new WebDriverWait(driver, 60);
        cusSystemWait.until(ExpectedConditions.elementToBeClickable(By.className("cus-system")));
        WebElement kfSystem = driver.findElement(By.className("cus-system"));
        kfSystem.click();
        sleep(2);

        // 记录中心
        System.out.println("switch windown");
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(driver.getWindowHandle());
        WebDriver backendDriver = driver.switchTo().window(windowHandles.iterator().next());
//        System.out.println("switch getWindowHandle = " + backendDriver.getWindowHandle());
//        System.out.println("switch gettile = " + backendDriver.getTitle());
//        System.out.println("switch getPageSource = " + backendDriver.getPageSource());
        sleep(3);


        // 下载
        backendDriver.findElement(By.partialLinkText("记录下载")).click();

        // 删除

        backendDriver.findElements(By.className("delete")).forEach(webElement -> {
            webElement.click();
            System.out.println("click delete");
            sleep(5);
            backendDriver.findElement(By.className("yes")).click();
            System.out.println("delete yes");
        });


        sleep(50);
    }

    /**
     * 点击生成excel
     */
    public static void t1() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://www.53kf.com/login/guide?url=http://www.53kf.com");

        WebElement email = driver.findElement(By.name("account"));
        email.sendKeys("53kf58@sina.com");
        sleep(2);
        driver.findElement(By.className("header-title")).click();

        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("g48t1s50");
        sleep(2);
        WebElement submit = driver.findElement(By.className("submit-btn"));
        submit.click();
        sleep(2);
//        driver.get("https://www.53kf.com/");
        sleep(2);
        System.out.println("title=" + driver.getTitle());
        sleep(50);
        WebElement parentMenu = driver.findElement(By.className("user"));
        Actions builder = new Actions(driver);
        builder.moveToElement(parentMenu).perform(); // 将鼠标移动到user上
        sleep(5);

        WebDriverWait cusSystemWait = new WebDriverWait(driver, 10);
        cusSystemWait.until(ExpectedConditions.elementToBeClickable(By.className("cus-system")));
        WebElement kfSystem = driver.findElement(By.className("cus-system"));
        kfSystem.click();
        sleep(2);

        // 记录中心
        System.out.println("聊天记录 record");
        Set<String> windowHandles = driver.getWindowHandles();
        windowHandles.remove(driver.getWindowHandle());
        WebDriver backendDriver = driver.switchTo().window(windowHandles.iterator().next());
//        System.out.println("switch getWindowHandle = " + backendDriver.getWindowHandle());
//        System.out.println("switch gettile = " + backendDriver.getTitle());
//        System.out.println("switch getPageSource = " + backendDriver.getPageSource());
        sleep(3);
        backendDriver.findElement(By.partialLinkText("聊天记录")).click();
        sleep(2);
        // 设置年份
        backendDriver.findElement(By.className("list_selected")).click(); // 点击年下拉列表
        sleep(2);
        // lately_three：近三个月 last_year：去年 this_year：今年
        backendDriver.findElement(By.className("select_list")).findElement(By.className("lately_three")).click();//选择下拉列表值
        sleep(2);

        // 删除日期中的readonly
        JavascriptExecutor jsDriver = ((JavascriptExecutor) backendDriver);
        jsDriver.executeScript("document.getElementsByClassName('start_time')[0].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('start_time')[1].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('end_time')[0].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('end_time')[1].removeAttribute('readonly')");
//        jsDriver.executeScript("document.getElementById('start_time').removeAttribute('readonly')");
//        jsDriver.executeScript("document.getElementsByClassName('start_time')[0].readOnly=false");
//        jsDriver.executeScript("document.getElementById('end_time').removeAttribute('readonly')");
        System.out.println("remove readonly");

        // 设置时间
        try {
            WebElement startTime = backendDriver.findElement(By.id("start_time"));
            startTime.clear();
            startTime.sendKeys("2020-06-01 00:00");
        } catch (Exception e) {
            System.out.println("into start_time2");
            WebElement startTime = backendDriver.findElement(By.id("start_time2"));
            startTime.clear();
            startTime.sendKeys("2020-06-01 00:00");
        }
        sleep(2);
        System.out.println("set start time");

        try {
            WebElement startTime = backendDriver.findElement(By.id("end_time"));
            startTime.clear();
            startTime.sendKeys("2020-10-08 23:59");
        } catch (Exception e) {
            System.out.println("into end_time2");
            WebElement startTime = backendDriver.findElement(By.id("end_time2"));
            startTime.clear();
            startTime.sendKeys("2020-10-08 23:59");
        }
        sleep(2);
        System.out.println("set ending time");

        backendDriver.findElement(By.id("search")).click();
        sleep(10);

        // 点击下载
        WebDriverWait downLoadElementWait = new WebDriverWait(backendDriver, 10);
        downLoadElementWait.until(ExpectedConditions.elementToBeClickable(By.className("head_select")));
        WebElement downLoadElement = backendDriver.findElement(By.className("head_select"));
//        Actions action = new Actions(backendDriver);
//        action.moveToElement(downLoadElement).perform();
        downLoadElement.click();
        System.out.println("download click");
//        sleep(20);

        WebDriverWait excelElementWait = new WebDriverWait(backendDriver, 20);
        excelElementWait.until(ExpectedConditions.elementToBeClickable(By.name("excel")));
        WebElement downLoadExcelElement = backendDriver.findElement(By.name("excel"));
        downLoadExcelElement.click();
        System.out.println("download click 下载");
    }

    public static void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //单独重构成一个方法，然后调用
    public static DesiredCapabilities setDownloadsPath() {
        String downloadsPath = "D:\\dataSource\\outputReport\\Downloads";
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", downloadsPath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(ChromeOptions.CAPABILITY, options);
        return caps;
    }
}
