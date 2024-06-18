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
import dockercommon
import upload

DEFAULT_CONN_TIMEOUT = 5000
DEFAULT_SOCKET_TIMEOUT = 6000
ENCODING = "UTF8"


socket.setdefaulttimeout(DEFAULT_SOCKET_TIMEOUT)

default = None

def getDefaultClient():
    global default
    if default is not None:
        return default
    ak, sk = dockercommon.getAkSk()
    host, port = dockercommon.getEdasConf()
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


    def callApi(self, api, parameters={}, form = None):
        parameters.update({"Action": api})
        parameters.update({"Version": "20151201"})
        parameters.update({"AccessKeyId": self.ak})
        lt = time.gmtime() # UTC格式当前时区时})
        st = time.strftime("%Y-%m-%dT%H:%M:%SZ",lt)# 格式化时间
        parameters.update({"Timestamp": st})
        parameters.update({"SignatureMethod": "HMAC-SHA1"})
        parameters.update({"SignatureVersion": "1.0"})
        parameters.update({"SignatureNonce": str(uuid.uuid1())})
        parameters.update({"Format": "JSON"})
        parameters.update({"Signature": self._calcSign(self.sk, parameters)})
        if "fileItem" in parameters.keys():
            del parameters["fileItem"]
            form.add_fields(parameters)
            body = str(form)
            headers = {}
            headers['Content-type'] = 'multipart/form-data; boundary=%s' % form.boundary
            headers['Content-length'] = len(body)
            return self.invokePost("/api" + api, body = body, headers = headers)
        else:
            parameters = self._urlEncode(parameters)
            content = self._toQueryString(parameters)
            return self.invokePost("/api" + api +"?" + content)

        print(parameters)
        
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
    # form.add_file('fileItem', str(os.path.basename(appWarFile)), file_obj = open(appWarFile, 'rb'))
    form.add_file('fileItem', str(os.path.basename(appWarFile)), file_obj = open(appWarFile, 'rb'))
    print appWarFile
    print str(os.path.basename(appWarFile))
    # headers = {}
    # body = None
    parameters = {}
    parameters["appId"] = app.appId
    parameters["type"] = app.deployType
    parameters["deployToStr"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["description"] = app.description
    parameters["act"] = "deploy"
    parameters["fileItem"] = str(os.path.basename(appWarFile)).encode('utf8')
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", parameters, form)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp
    startParams = {}
    startParams["appId"] = app.appId
    startParams["act"] = "start"

    time.sleep(10)
    print "Starting application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", startParams)
    if code != 200:
        print "Start app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp


def deployAppByImage(app):
    parameters = {}
    parameters["appId"] = app.appId
    parameters["type"] = app.deployType
    parameters["deployToStr"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["description"] = app.description
    parameters["act"] = "deploy"
    parameters["imageUrl"] = app.imageUrl
    parameters["regionId"] = app.regionId
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

    startParams = {}
    startParams["appId"] = app.appId
    startParams["act"] = "start"

    time.sleep(10)
    print "Starting application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", startParams)
    if code != 200:
        print "Start app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

def deployAppWithUrl(app, warUrl):
    parameters = {}
    parameters["appId"] = app.appId
    parameters["type"] = "url"
    parameters["deployToStr"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["description"] = app.description
    parameters["act"] = "deploy"
    parameters["warUrl"] = warUrl
    parameters["regionId"] = app.regionId
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

    startParams = {}
    startParams["appId"] = app.appId
    startParams["act"] = "start"

    time.sleep(10)
    print "Starting application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/application", startParams)
    if code != 200:
        print "Start app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp


def deployAppChangeOrder(app):
    appWarFile = os.path.join(BASEDIR, app.target)
    form = upload.MultiPartForm()
    # form.add_file('fileItem', str(os.path.basename(appWarFile)), file_obj = open(appWarFile, 'rb'))
    form.add_file('fileItem', str(os.path.basename(appWarFile)), file_obj = open(appWarFile, 'rb'))
    print appWarFile
    print str(os.path.basename(appWarFile))
    # headers = {}
    # body = None
    parameters = {}
    parameters["appId"] = app.appId
    parameters["deployType"] = app.deployType
    parameters["groupId"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["desc"] = app.description
    parameters["regionId"] = app.regionId
    parameters["releaseType"] = "0"
    parameters["batchWaitTime"] = "0"
    parameters["batch"] = str(app.batch)
    parameters["dockerize"] = "true"
    parameters["fileItem"] = str(os.path.basename(appWarFile)).encode('utf8')
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/changeorder/co_deploy", parameters, form)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp


def deployAppByImageChangeOrder(app):
    parameters = {}
    parameters["appId"] = app.appId
    parameters["deployType"] = app.deployType
    parameters["groupId"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["desc"] = app.description
    parameters["imageUrl"] = app.imageUrl
    parameters["regionId"] = app.regionId
    parameters["releaseType"] = "0"
    parameters["batchWaitTime"] = "0"
    parameters["batch"] = str(app.batch)
    parameters["dockerize"] = "true"
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/changeorder/co_deploy", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

def deployAppWithUrlChangeOrder(app, warUrl):
    parameters = {}
    parameters["appId"] = app.appId
    parameters["deployType"] = "url"
    parameters["groupId"] = app.deployToStr
    parameters["packageVersion"] = app.packageVersion
    parameters["desc"] = app.description
    parameters["regionId"] = app.regionId
    parameters["releaseType"] = "0"
    parameters["batchWaitTime"] = "0"
    parameters["batch"] = str(app.batch)
    parameters["dockerize"] = "true"
    parameters["warUrl"] = warUrl
    
    print "Deploying application %s" % app.appName
    code, resp = getDefaultClient().callApi("/v5/changeorder/co_deploy", parameters)
    if code != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    msg = json.loads(resp)
    if msg['code'] != 200:
        print "Deploy app faild. Ret: %d, Resp %s" % (code, resp)
        sys.exit(1)
    print code, resp

