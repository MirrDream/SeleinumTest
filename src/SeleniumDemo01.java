/**
 * Created by yujun on 2015/8/27.
 */
import com.thoughtworks.selenium.DefaultSelenium;
import com.thoughtworks.selenium.webdriven.ElementFinder;
import org.openqa.selenium.WebElement;

public class SeleniumDemo01
{
    public static void main(String[] args)
    {
        String host = "localhost";
        int port = 4444;
        String url = "http://www.baidu.com/";
        String browserType = "*firefox e:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe";

        String keyWordsLocator = "document.getElementById('kw')";
        String search = "document.getElementById('su')";
        DefaultSelenium selenium = new DefaultSelenium(host,port,browserType,url);
        selenium.start();
        selenium.open(url);
        selenium.type(keyWordsLocator, "java selenium");
        selenium.click(search);
        selenium.waitForPageToLoad("500000");
        selenium.stop();

    }
}

/*运行结果：
        1.firefox浏览器期待
        2.打开百度主页
        3.在搜索框中键入了"java selenium"
        4.点击"百度一下"按钮
        5.等等页面加载50s
        6.关闭浏览器
*/