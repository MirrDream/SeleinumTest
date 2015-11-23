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

/*���н����
        1.firefox������ڴ�
        2.�򿪰ٶ���ҳ
        3.���������м�����"java selenium"
        4.���"�ٶ�һ��"��ť
        5.�ȵ�ҳ�����50s
        6.�ر������
*/