package com.coohua;


import com.report.entry.ReportEntry;
import com.report.html.HtmlFile;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import static org.junit.Assert.assertTrue;

public class AndroidDriverLib {
    public static Logger logger = Logger.getLogger(AndroidDriverLib.class.getName());
    private AndroidDriver driver = null;
    ReportEntry re = new ReportEntry();
    HtmlFile ht =new HtmlFile();
    public void setUp(String p_deviceName,String p_appiumUrl,String p_appPackage,String p_appActivity) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        //capabilities.setCapability("deviceName", "192.168.249.101:5555");
        capabilities.setCapability("deviceName", p_deviceName);
        //capabilities.setCapability("deviceName", "f97f0457d73");
        //capabilities.setCapability("platformVersion", "4.3");
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability("noReset", "true"); //不需要再次安装apk
        capabilities.setCapability("unicodeKeyboard","true"); //输入中文
        capabilities.setCapability("resetKeyboard","true");   //输入中文
        capabilities.setCapability("appPackage", p_appPackage); //worktile的包
        capabilities.setCapability("appActivity", p_appActivity); //启动的activity  .ui.external.WelcomeActivity
        try {
            //driver = new AndroidDriver<MobileElement>(new URL("http://192.168.31.225:4723/wd/hub"), capabilities);
            driver = new AndroidDriver<MobileElement>(new URL(p_appiumUrl), capabilities);
            //driver = new AndroidDriver<MobileElement>(new URL("http://192.168.1.104:4723/wd/hub"), capabilities);
            driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            driver.quit();
            System.err.println("launch Android driver fail！"+e.toString());
        }
    }
    public static By parseObject(String p_object) {
        String newObject = null;

        if (p_object.startsWith(".//") || p_object.startsWith("//")) {
            return By.xpath(p_object);
        } else if (p_object.startsWith("link=") || p_object.startsWith("Link=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.linkText(newObject);
        } else if (p_object.startsWith("xpath=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.xpath(newObject);
        } else if (p_object.startsWith("id=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.id(newObject);
        } else if (p_object.startsWith("css=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.cssSelector(newObject);
        } else if (p_object.startsWith("class=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.className(newObject);
        } else if (p_object.startsWith("tagName=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.tagName(newObject);
        } else if (p_object.startsWith("name=")) {
            newObject = p_object.substring(p_object.indexOf("=") + 1);
            return By.name(newObject);
        } else
            return null;

    }
    public void newClik(String p_id){
    try{
        this.driver.findElement(parseObject(p_id)).click();
        logger.info(TextStore.T_ClickObject + p_id + TextStore.T_Pass);
    }catch (Exception e){
        logger.severe(TextStore.T_Exception + "com.coohua.AndroidDriverLib.newClik" +TextStore.T_DetailInfo +e.toString());

    }
    }
    public void newType(String p_id,String p_text) {
        CommonLib.waitTime(6);
        try {
            this.driver.findElement(parseObject(p_id)).clear();// 输入文字前，清除文本框中的文字
            this.driver.findElement(parseObject(p_id)).sendKeys(p_text);
            logger.info(TextStore.T_Input + p_text + TextStore.T_To +p_id+ TextStore.T_Pass);

        } catch (Exception e) {
            logger.severe(TextStore.T_Exception + "newType" + TextStore.T_DetailInfo + e.toString());
        }
    }

    public Boolean newIsElementPresent(String p_id){
        try{
            driver.findElement(parseObject(p_id)).isDisplayed();
            logger.info(TextStore.T_FindUi+p_id+TextStore.T_Pass);
            return true;
        }catch(Exception e){
            logger.severe(TextStore.T_Exception + "com.coohua.AndroidDriverLib.newFindUI"+TextStore.T_DetailInfo + e.toString());
            return false;
        }

    }
    public void NewVeryfiyEquals(String p_message,Object p_expected,Object p_actual){
        if (p_expected.equals(p_actual)){
            ht.write(p_message,p_expected.toString(),p_actual.toString());
            logger.info(p_message+"......pass");
        }else {
            ht.write(p_message,p_expected.toString(),p_actual.toString());
            logger.severe(p_message+".......FAID!");
        }
    }
    public void newAssertEquals(String p_message,Object p_expected,Object p_actual)throws Exception{
        if (p_expected.equals(p_actual)){
            ht.write(p_message,p_expected.toString(),p_actual.toString());
            logger.info(p_message+"......pass");
        }else {
            ht.write(p_message,p_expected.toString(),p_actual.toString());
            re.closeLog();
            driver.quit();
            logger.severe(p_message+ "......Exception Running Stop!!!!!! Progame Exit!");
        }
    }
    /**
     * scrollToByText 执行UiAutomonter的划屏操作
     * @param text
     * @return
     */
    public String scrollToByText(String text) {
        String uiScrollables = myUiScrollable("new UiSelector().descriptionContains(\"" + text + "\")")
                + myUiScrollable("new UiSelector().textContains(\"" + text + "\")");
        return uiScrollables;

    }
    private String myUiScrollable(String uiSelector) {
        return "new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(" + uiSelector
                + ".instance(0));";
    }


}
