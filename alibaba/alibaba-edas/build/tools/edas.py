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
dirname = os.path.join(BASEDIR, "build", "tools")
sys.path.append(dirname)
import common
import upload

DEFAULT_CONN_TIMEOUT = 500
DEFAULT_SOCKET_TIMEOUT = 6000
ENCODING = "UTF8"


socket.setdefaulttimeout(DEFAULT_SOCKET_TIMEOUT)

default = None

def getDefaultClient():
    global default
    if default is not None:
        return default
    ak, sk = common.getAkSk()
    host, port = common.getEdasConf()
    default = ApiClient(ak, sk, host, port)
    return default


class ApiClient(object):
    host = "edas.console.aliyun.com"
    port = 8080
    ak = None
    sk = None

    def __init__(self, ak, sk, host, port):
        self.ak = ak
        self.sk = sk
        self.host = host
        self.port = port
        pass

    def invokePost(self, path, body = None, headers={}):
        conn = None
        try:
            conn = httplib.HTTPConnection(self.host, self.port, timeout=DEFAULT_CONN_TIMEOUT)
            conn.request('POST', path, body = body, headers = headers)
            response = conn.getresponse()
            return response.status, response.read()
        except Exception, e:
            raise e
        finally:
            if conn:
                conn.close()


    def callApi(self, api, parameters={}, fileObj = None, headers = {}):
        parameters.update({"Action": api})
        parameters.update({"Version": "20151201"})
        parameters.update({"AccessKeyId": self.ak})
        lt = time.gmtime() # UTC格式当前时区时})
        st = time.strftime("%Y%m%dT%H%M%SZ",lt)# 格式化时间
        parameters.update({"Timestamp": st})
        parameters.update({"SignatureMethod": "HMAC-SHA1"})
        parameters.update({"SignatureVersion": "1.0"})
        parameters.update({"SignatureNonce": str(uuid.uuid1())})
        parameters.update({"Format": "JSON"})
        parameters.update({"Signature": self._calcSign(self.sk, parameters)})
        parameters = self._urlEncode(parameters)
        content = self._toQueryString(parameters)
        print content
        return self.invokePost("/api" + api +"?" + content, body = fileObj, headers = headers)

    def _calcSign(self, sk, parameters={}, method="POST"):
        patterns = []
        for key in sorted(parameters.keys()):
            value = parameters[key]
            pattern = "=".join([quote(str(key), safe=''), quote(str(value), safe='')])
            patterns.append(pattern)
        patternstr = "&".join(patterns)
        resultstr = method + "&%2F&" + quote(str(patternstr), safe='')
        # print resultstr
        h = hmac.new(sk + '&', resultstr, hashlib.sha1)
        s = h.digest()
        signature = s.encode('base64').rstrip()
        return signature

    def _urlEncode(self, parameters):
        encodedParams = {}
        for key in parameters:
            encodedParams[quote(str(key), safe='')] = quote(str(parameters[key]), safe='')
        return encodedParams

    def _toQueryString(self, parameters):
        patterns = []
        for key in parameters:
            value = parameters[key]
            pattern = "=".join([key, value])
            patterns.append(pattern)
        return "&".join(patterns)



def deployApp(app):
    appWarFile = os.path.join(BASEDIR, app.target)
    form = upload.MultiPartForm()
    form.add_file('appPackage', str(os.path.basename(appWarFile)), file_obj = open(appWarFile, 'rb'))
    body = str(form)
    headers = {}
    headers['Content-type'] = 'multipart/form-data; boundary=%s' % form.boundary
    headers['Content-length'] = len(body)
    parameters = {}
    parameters["appId"] = app.appId
    parameters["batch"] = app.batch
    parameters["deployGroupId"] = app.deployGroupId

    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/app/deploy", parameters, body, headers)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp
    # startParams = {}
    # startParams['UserId'] = app.userId
    # startParams["AppId"] = app.appId
    # time.sleep(10)
    # print "Starting application %s" % app.appName
    # code, resp = getDefaultClient().callApi("/app/start_app", startParams)
    # if code != 200:
    #     print "Start app faild. Ret: %d, Resp %s" % (code, resp)
    #     sys.exit(1)
    # msg = json.loads(resp)
    # if msg['code'] != 200:
    #     print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
    #     sys.exit(1)
    print code, resp
    
def deployAppWithUrl(app, warUrl):
    parameters = {}
    parameters["appId"] = app.appId
    parameters["batch"] = app.batch
    parameters["deployGroupId"] = app.deployGroupId
    parameters["warUrl"] = warUrl

    code, resp = getDefaultClient().callApi("/app/deploy", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

def deployAppEx(app):
    parameters = {}
    parameters["appId"] = 'b400ccdc-ed10-4476-bd4d-1c587bd60738'
    parameters["batch"] = 1
    parameters["warUrl"] = 'http://rdc-edas-oss.oss-cn-shanghai.aliyuncs.com/2017/testing/edas-test/2017-07-14-192213/edas-test.war'

    code, resp = getDefaultClient().callApi("/app/deploy", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp
