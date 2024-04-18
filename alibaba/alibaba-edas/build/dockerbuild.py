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
import dockeredas
import dockercommon

def main():
    apps = dockercommon.getAppConfigs()
    reload(sys)
    sys.setdefaultencoding('utf-8')
    if apps is None:
        print 'Can not read any application configs. Pelease check config file'
        sys.exit(1)
    for app in apps:
        if app.deployType == "image":
        	print "Handle app %s by image" % app.appId
        	dockeredas.deployAppByImageChangeOrder(app)
        elif app.deployType == "upload":
        	print "Handle app %s by upload" % app.appId
        	dockeredas.deployAppChangeOrder(app)
       

if __name__ == '__main__':
    main()