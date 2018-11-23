package com.coohua;

import com.report.entry.ReportEntry;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.URL;
import java.util.AbstractList;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.*;

public class AppiumTest {
     private AndroidDriver driver;
      String  filename ="E:\\Coohua\\Mavencoohua\\report\\coohua.html";
    static ReportEntry re =new ReportEntry();
    static BussinessLib bl=new BussinessLib();

    @Before
public void setUp(){
        try {
            bl.setUp("b95a520a","http://127.0.0.1:4723/wd/hub","com.coohuaclient",".business.home.activity.SplashActivity");
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    //@Test
    /* public void NewUserRegistration(){

        driver.findElement(By.id("com.coohuaclient:id/register")).click();
    }
    @test
    public void SkipUsers(){

        driver.findElement(By.id("com.coohuaclient:id/jump")).click();
        driver.findElement(By.name("领取奖励")).click();
        try {

        }catch (Exception e){


        }

    }*/
    @Test
        public void Test(){
        bl.Login("13611200683","qqqqqq","605311");
        re.crateLog(filename);
        try {
            bl.newAssertEquals("验证登录",true,bl.newIsElementPresent(ObjectStore.SignIn_MinePage));
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertTrue(bl.newIsElementPresent(ObjectStore.SignIn_MinePage));
    }
    @After
    public void tearDown() throws Exception {
        re.closeLog();
    }
}