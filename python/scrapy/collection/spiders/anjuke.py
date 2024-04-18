# -*- coding: utf-8 -*-
import scrapy


class AnjukeSpider(scrapy.Spider):
    name = 'anjuke'
    allowed_domains = ['https://dalian.anjuke.com/']
    start_urls = ['https://dalian.anjuke.com/']

    def parse(self, response):
        print(response.body)
        pass
