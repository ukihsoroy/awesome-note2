package org.ko.hadoop.analysis;

import com.kumkee.userAgent.UserAgent;
import com.kumkee.userAgent.UserAgentParser;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * UserAgent测试类
 */

public class UserAgentTest {

    private static final Logger _LOGGER = LoggerFactory.getLogger(UserAgentTest.class);


    @Test void whenReadFileSuccess () throws Exception {
        String path = "D:/imooc/10小时入门大数据/10000_access.log";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(
                                new File(path))));
        String line;
        int i = 1;

        Map<String, Integer> browserMap = new HashMap<>();


        while ((line = reader.readLine()) != null) {
            if (StringUtils.isNotBlank(line)) {
                UserAgentParser userAgentParser  = new UserAgentParser();
                UserAgent agent = userAgentParser.parse(line);
                _LOGGER.info("----------------{}---------------", i ++);
                _LOGGER.info("浏览器信息: {}", agent.getBrowser());
                _LOGGER.info("引擎信息: {}", agent.getEngine());
                _LOGGER.info("引擎版本信息: {}", agent.getEngineVersion());
                _LOGGER.info("操作系统信息: {}", agent.getOs());
                _LOGGER.info("平台信息: {}", agent.getPlatform());
                _LOGGER.info("版本号: {}", agent.getVersion());
                _LOGGER.info("是否为手机端: {}", agent.isMobile());

                browserMap.merge(agent.getBrowser(), 1, (a, b) -> a + b);

            }
        }

        browserMap.forEach((k, v) -> _LOGGER.info("count {}: {}个", k, v));
    }


    /**
     * UserAgentParser工具类的使用
     */
    @Test void whenUserAgentParserSuccess () {
        String source = "116.22.196.70 - - [10/Nov/2016:00:01:02 +0800] \"POST /course/ajaxmediauser HTTP/1.1\" 200 54 \"www.imooc.com\" \"http://www.imooc.com/code/3500\" mid=3500&time=60 \"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0\" \"-\" 10.100.134.244:80 200 0.026 0.026";
        UserAgentParser userAgentParser  = new UserAgentParser();
        UserAgent agent = userAgentParser.parse(source);
        _LOGGER.info("浏览器信息: {}", agent.getBrowser());
        _LOGGER.info("引擎信息: {}", agent.getEngine());
        _LOGGER.info("引擎版本信息: {}", agent.getEngineVersion());
        _LOGGER.info("操作系统信息: {}", agent.getOs());
        _LOGGER.info("平台信息: {}", agent.getPlatform());
        _LOGGER.info("版本号: {}", agent.getVersion());
        _LOGGER.info("是否为手机端: {}", agent.isMobile());
    }


    @Test void whenGetCharacterPositionSuccess () {
        String value = "116.22.196.70 - - [10/Nov/2016:00:01:02 +0800] \"POST /course/ajaxmediauser HTTP/1.1\" 200 54 \"www.imooc.com\" \"http://www.imooc.com/code/3500\" mid=3500&time=60 \"Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.22 Safari/537.36 SE 2.X MetaSr 1.0\" \"-\" 10.100.134.244:80 200 0.026 0.026";
        int index = this.getCharacterPosition(value, "\"", 7);
        _LOGGER.info("索引的位置: {}", index);
    }

    /**
     * 获取指定字符串中指定标识的字符串出现的索引位置
     * @param value
     * @param operator
     * @param index
     * @return
     */
    private int getCharacterPosition (String value, String operator, int index) {
        Matcher slashMatcher = Pattern.compile(operator).matcher(value);
        int mIdx = 0;

        while (slashMatcher.find()) {
            mIdx ++;

            if (mIdx == index) {
                break;
            }
        }
        return slashMatcher.start();
    }

    /**
     * null + 1: throw NullPrintException
     */
    @Test void whenNullPlusOne () {
        Map<String, Integer> contains = new HashMap<>();
        _LOGGER.info("null + 1 = {}", (contains.get("") + 1));
    }
}
