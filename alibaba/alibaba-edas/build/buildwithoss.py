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
import importlib
from urllib import unquote
from urllib import quote
from urlparse import urlparse
BASEDIR = os.getenv("WORKSPACE", "/Users/wkh/ci/jenkinsTest")
dirname = os.path.join(BASEDIR, "build", "tools")
sys.path.append(dirname)
import edas
import common
import upload2Oss

def main():
    ossConfig = common.getOssConfigration()
    if ossConfig is None:
        print 'Can not read any oss configs. Pelease check config file'
        sys.exit(1)
    apps = common.getAppConfigs()
    if apps is None:
        print 'Can not read any application configs. Pelease check config file'
        sys.exit(1)
    for app in apps:
        print "upload file to oss: %s" % ossConfig.endpoint
        fileUrl = upload2Oss.uploadFile2Oss(ossConfig, app)
        print "fileUrl : %s" % fileUrl
        print "Handle app %s " % app.appId
        edas.deployAppWithUrl(app, fileUrl)

if __name__ == '__main__':
    main()