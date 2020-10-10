package cn.kd.service;

import cn.kd.repository.AccountRepository;
import cn.kd.util.DateUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static cn.kd.common.PageConst.*;

public class WholeProcessTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(WholeProcessTask.class);

    static {
        logger.debug("加载浏览器driver");
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Administrator\\Downloads\\geckodriver.exe");

    }

    private String account;
    private String password;
    private WebDriver webDriver;
    private AccountRepository accountRepository;

    public WholeProcessTask(String account, String password, AccountRepository accountRepository) {
        this(account, password, accountRepository, new FirefoxDriver());
    }

    public WholeProcessTask(String account, String password, AccountRepository accountRepository, WebDriver webDriver) {
        this.account = account;
        this.password = password;
        this.accountRepository = accountRepository;
        this.webDriver = webDriver;
        logger.debug("account:{}，构造实例化完成，准备抓取", account);
    }

    @Override
    public void run() {
        this.login();
        this.sleep(SMALL_TIMEOUT);
        this.enterBackend();
        this.sleep(SMALL_TIMEOUT);
        this.enterRecordAndClickSearch(this.switchNextWindow(this.webDriver));
    }

    private void clieckDownload(WebDriver backendDriver) {
        // 点击下载
        WebDriverWait downLoadElementWait = new WebDriverWait(backendDriver, BIG_TIMEOUT);
        downLoadElementWait.until(ExpectedConditions.elementToBeClickable(By.className("head_select")));
        backendDriver.findElement(By.className("head_select")).click();

        WebDriverWait excelElementWait = new WebDriverWait(backendDriver, BIG_TIMEOUT);
        excelElementWait.until(ExpectedConditions.elementToBeClickable(By.name("excel")));
        backendDriver.findElement(By.name("excel")).click();
        logger.debug("生成zip文件");
    }

    /**
     * 进入聊天记录菜单，点击下载
     *
     * @param backendDriver
     */
    private void enterRecordAndClickSearch(WebDriver backendDriver) {
        backendDriver.findElement(By.partialLinkText(BACKEND_TAG_CHAT_RECORD)).click();
        backendDriver.findElement(By.className(BACKEND_TAG_LIST_SELECTED)).click(); // 点击年下拉列表
        this.sleep(SMALL_TIMEOUT);

        // 点击下拉列表中的<ul>
        if (DateUtils.isSameYear()) {
            backendDriver.findElement(By.className(BACKEND_TAG_SELECT_LIST)).findElement(By.className(BACKEND_TAG_THIS_YEAR)).click();
        } else {
            backendDriver.findElement(By.className(BACKEND_TAG_SELECT_LIST)).findElement(By.className(BACKEND_TAG_LAST_YEAR)).click();
        }
        this.sleep(SMALL_TIMEOUT);
        this.removeReadonlyAttribute(backendDriver);

        // 设置时间
        try {
            WebElement startTime = backendDriver.findElement(By.id("start_time"));
            startTime.clear();
            startTime.sendKeys(DateUtils.yesterdayBeginTime());
        } catch (Exception e) {
            WebElement startTime = backendDriver.findElement(By.id("start_time2"));
            startTime.clear();
            startTime.sendKeys(DateUtils.yesterdayBeginTime());
        }
        logger.debug("设置start_time时间");
        this.sleep(SMALL_TIMEOUT);


        try {
            WebElement startTime = backendDriver.findElement(By.id("end_time"));
            startTime.clear();
            startTime.sendKeys(DateUtils.yesterdayEndTime());
        } catch (Exception e) {
            WebElement startTime = backendDriver.findElement(By.id("end_time2"));
            startTime.clear();
            startTime.sendKeys(DateUtils.yesterdayEndTime());
        }
        logger.debug("设置end_time时间");
        this.sleep(2);

        backendDriver.findElement(By.id(BACKEND_TAG_SEARCH)).click();
        this.sleep(10);

        this.clieckDownload(backendDriver);
    }

    private void removeReadonlyAttribute(WebDriver backendDriver) {
        JavascriptExecutor jsDriver = ((JavascriptExecutor) backendDriver);
        jsDriver.executeScript("document.getElementsByClassName('start_time')[0].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('start_time')[1].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('end_time')[0].removeAttribute('readonly')");
        jsDriver.executeScript("document.getElementsByClassName('end_time')[1].removeAttribute('readonly')");
        logger.debug("商务日期中的readonly属性");
    }

    /**
     * 登录
     */
    private void login() {
        this.webDriver.get(LOGIN_URL);
        this.webDriver.findElement(By.name(LOGIN_TAG_ACCOUNT)).sendKeys(this.account);
        this.webDriver.findElement(By.className(LOGIN_TAG_TITLE)).click();
        this.webDriver.findElement(By.name(LOGIN_TAG_PASSWORD)).sendKeys(this.password);
        logger.debug("登录页设置用户名:{} 密码:{}", this.account, this.password);
        this.sleep(SMALL_TIMEOUT);
        this.webDriver.findElement(By.className(LOGIN_TAG_SUBMIT)).click();
//        this.webDriver.get(HOME_PAGE_URL);
        logger.debug("登录完成，跳转首页");
    }

    /**
     * 进入后台系统
     */
    public void enterBackend() {
        Actions builder = new Actions(this.webDriver);
        builder.moveToElement(this.webDriver.findElement(By.className(HOME_TAG_USER))).perform(); // 将鼠标移动到user上
        this.sleep(SMALL_TIMEOUT);

        WebDriverWait cusSystemWait = new WebDriverWait(this.webDriver, BIG_TIMEOUT);
        cusSystemWait.until(ExpectedConditions.elementToBeClickable(By.className(HOME_TAG_SYSTEM)));
        this.webDriver.findElement(By.className(HOME_TAG_SYSTEM)).click();
        logger.debug("点击[客服系统后台]");
    }

    /**
     * 切换下一个tap页
     *
     * @param webDriver
     * @return
     */
    private WebDriver switchNextWindow(WebDriver webDriver) {
        Set<String> windowHandles = webDriver.getWindowHandles();
        windowHandles.remove(webDriver.getWindowHandle());
        WebDriver window = webDriver.switchTo().window(windowHandles.iterator().next());
        logger.debug("从home page切换到[客服系统后台]tap");
        return window;
    }

    public void sleep(long timeout) {
        try {
            TimeUnit.SECONDS.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
