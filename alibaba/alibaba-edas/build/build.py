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

def main():
    apps = common.getAppConfigs()
    if apps is None:
        print 'Can not read any application configs. Pelease check config file'
        sys.exit(1)
    for app in apps:
        print "Handle app %s " % app.appId
        edas.deployApp(app)

if __name__ == '__main__':
    main()