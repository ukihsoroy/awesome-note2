# coding=UTF-8/

import random
import time

url_path = [
    "class/112.html",
    "class/128.html",
    "class/145.html",
    "class/146.html",
    "class/131.html",
    "class/130.html",
    "learn/821",
    "course/list"
]

ip_slices = [132, 156, 124, 10, 29, 167, 143, 178, 123, 111, 56, 44, 32, 11, 0, 45, 66]

http_referer = [
    "http://www.baidu.com/s?wd={query}",
    "http://www.sogou.com/web?query={query}",
    "http://cn.bing.com/search?q={query}",
    "http://search.yahoo.com/search?p={query}",
]

search_keyword = [
    "Spark SQL实战",
    "Hadoop基础",
    "Storm实战",
    "Spark Streaming实战",
    "大数据面试"
]


status_codes = ["200", "404", "500"]


def sample_url():
    return random.sample(url_path, 1)[0]


def sample_ip():
    slices = random.sample(ip_slices, 4)
    return ".".join([str(node) for node in slices])


def sample_referer():
    if random.uniform(0, 1) > 0.5:
        return "-"
    refer_str = random.sample(http_referer, 1)
    query_str = random.sample(search_keyword, 1)
    return refer_str[0].format(query=query_str[0])


def sample_status_code():
    return random.sample(status_codes, 1)[0]


def generator_log(count=10):
    time_str = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    f = open("/home/k.o/data/project/logs/access.log", "w+")
    while count >= 1:
        query_log = "{ip}\t{localTime}\t\"GET /{url} HTTP/1.1\"\t{status}\t{referer}".format(
            url=sample_url(),
            ip=sample_ip(),
            referer=sample_referer(),
            status=sample_status_code(),
            localTime=time_str)
        f.write(query_log + "\n")
        print(query_log)
        count = count - 1


if __name__ == '__main__':
    generator_log(100)
