
import oss2
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
dirname = os.path.join(BASEDIR, "build", "tools")
sys.path.append(dirname)
import common
import upload

def uploadFile2Oss(ossconfig, app):
    auth = oss2.Auth(ossconfig.accesskeyid, ossconfig.accesskeysecret)
    bucket = oss2.Bucket(auth, ossconfig.endpoint, ossconfig.bucket)
    appWarFile = os.path.join(BASEDIR, app.target)
    uuidCode = str(uuid.uuid1());
    filePath = os.path.join(app.appId,uuidCode,str(os.path.basename(appWarFile)))
    bucket.put_object_from_file(filePath, appWarFile)
    warUrl = "http://" + ossconfig.bucket + "." + ossconfig.endpoint+"/"+filePath
    return warUrl