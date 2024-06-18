#!/usr/bin/python2 -u
# -*-coding:utf-8-*-

import mimetools
import mimetypes
import itertools
import sys

class MultiPartForm():
    def __init__(self):
        self.form_fields = []
        self.files = []
        self.boundary = mimetools.choose_boundary()

    def add_field(self, name, value):
        """添加field数据到form表单"""
        self.form_fields.append((name, value))

    def add_fields(self,parameters={}):
        """添加field数据到form表单"""
        for key in parameters.keys():
            self.add_field(key,parameters[key])
        print self.form_fields


    def add_file(self, fieldname, filename, file_obj, mimetype=None):
        """添加文件到表单"""
        if not mimetype:
            mimetype = mimetypes.guess_type(filename)[0] or 'application/octet-stream'
        self.files.append((fieldname, filename, mimetype, file_obj.read()))

    def toStr(self):
        return self.__str__()

    def __str__(self):
        """拼接form报文"""
        parts = []
        part_boundary = "--%s" % self.boundary

        # 添加fields
        parts.extend(
            [part_boundary,
            'Content-Disposition: form-data; name="%s"' % name.encode('utf-8'),
            '',
            value.encode('utf-8'),] for name, value in self.form_fields
            )
        # print parts
        # flattened = list(itertools.chain(*parts))
        # print flattened
        # 添加要上传的files
        parts.extend(
            [
            '',
            part_boundary,
            'Content-Disposition: form-data; name="%s"; filename="%s"' % (field_name.encode('utf-8'), filename.encode('utf-8')),
            '',
            body,] for field_name, filename, content_type, body in self.files
            )
        # print parts

        # 压平parts添加boundary终止符
        flattened = list(itertools.chain(*parts))
        flattened.append('--%s--' % self.boundary)
        flattened.append('')
        # print flattened
        return "\r\n".join(flattened)


if __name__ == '__main__':
    filename = '/var/jenkins_home/workspace/myapp/edas-demo-portal/target/portal.war'
    form = MultiPartForm()

    form.add_file('appPackage', 'portal.war', file_obj = open(filename, 'rb'))
    # request = urllib2.Request('http://url.com/s/api?cmd=Image.add')

    body = str(form)
    print body[:100]

    # request.add_header('Content-type', 'multipart/form-data; boundary=%s' % form.boundary)
    # request.add_header('Content-length', len(body))
    # request.add_header('Cookie', 'SPSSID=xxxxxxxxxx')
    # request.add_data(body)

    # print(urllib2.urlopen(request).read())