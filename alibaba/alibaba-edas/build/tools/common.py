#!/usr/bin/python2 -u
# -*-coding:utf-8-*-

import re
import os
import sys
import commands
import datetime
import subprocess
import glob
import httplib
import socket
import json
import hmac
import hashlib
import time
import uuid
from urllib import unquote
from urllib import quote
from urlparse import urlparse

BASEDIR = os.getenv("WORKSPACE", "/Users/wkh/ci/jenkinsTest")

def getAkSk():
    osscredentialsfile = os.path.join(BASEDIR, "build", ".osscredentials")
    ak = None
    sk = None
    with open(osscredentialsfile, 'r') as fp:
        content = fp.read()
        for line in content.split('\n'):
            if "accessid" in line:
                ak = line.split("=")[1].strip()
            if "accesskey" in line:
                sk = line.split("=")[1].strip()
    return ak, sk

def getOssConfigration():
    conffile = os.path.join(BASEDIR, "build", ".ossConfigration")
    with open(conffile, 'r') as fp:
        content = fp.read()
        for line in content.split('\n'):
            if "accesskeyid" in line:
                ak = line.split("=")[1].strip()
            if "accesskeysecret" in line:
                sk = line.split("=")[1].strip()
            if "endpoint" in line:
                endpoint = line.split("=")[1].strip()
            if "bucket" in line:
                bucket = line.split("=")[1].strip()
    return OssConfigration(ak, sk, endpoint, bucket)

def getEdasConf():
    conffile = os.path.join(BASEDIR, "build", "config.json")
    with open(conffile, 'r') as fp:
        content = fp.read()
        confs = json.loads(content)
        if "edas" in confs:
            edas = confs['edas']
            if 'host' in edas and 'port' in edas:
                return edas['host'], edas['port']
    return "edas.console.aliyun.com", 80
    

def getAppConfigs():
    conffile = os.path.join(BASEDIR, "build", "config.json")
    with open(conffile, 'r') as fp:
        content = fp.read()
        confs = json.loads(content)
        if "apps" in confs:
            apps = []
            for conf in confs['apps']:
                appName = conf['appName']
                appId = conf['appId']
                userId = conf['userId']
                target = conf['target']
                deployGroupId = conf['deployGroupId']
                batch = conf['batch']
                app = AppConfig(appName, appId, userId, target,deployGroupId,batch)
                apps.append(app)
        if len(apps) > 0:
            return apps
    return None

class AppConfig():
    def __init__(self, appName, appId, userId, target,deployGroupId,batch):
        self.appName = appName
        self.appId = appId
        self.userId = userId
        self.target = target
        self.batch = batch
        self.deployGroupId = deployGroupId

class OssConfigration():
    def __init__(self, accesskeyid, accesskeysecret, endpoint, bucket):
        self.accesskeyid = accesskeyid
        self.accesskeysecret= accesskeysecret
        self.endpoint = endpoint
        self.bucket = bucket
