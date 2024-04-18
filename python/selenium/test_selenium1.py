# coding = utf-8
from selenium import webdriver
import time




browser = webdriver.Chrome()

browser.get("http://cn.aliyun.com")

print("login aliyun. redirect dataworks.")

browser.find_element_by_xpath('//*[@id="module-YrEpAzATZ"]/div/div[4]/div[2]').click()

# browser.find_element_by_id("kw").send_keys("selenium")
# browser.find_element_by_id("su").click()

time.sleep(10)

browser.close()
