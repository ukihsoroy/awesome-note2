#!/opt/anaconda2/bin
# -*- coding:utf-8 -*-

from __future__ import unicode_literals
# 连接HDFS系统客户端
from hdfs3 import HDFileSystem


def file_exists(client):
    path = '/tmp/test'
    if client.exists(path):
        client.rm(path)

        client.makedirs(path)


def write_read(client):
    data = b"Hello Hadoop!" * 20
    file = '/tmp/test/file'
    with client.open(file, 'wb', replication=1) as f:
        f.write(data)

    with client.open(file, 'rb') as f:
        out = f.read(len(data))
        assert out == data


def read_lines(client):
    line = '/tmp/test/line'
    with client.open(line, 'wb', replication=1) as f:
        f.write(b"Hello\nHadoop!")

    with client.open(line, 'rb') as f:
        lines = f.readlines()
        assert len(lines) == 2


if __name__ == '__main__':
    host = 'http://192.168.58.128'
    port = 8020
    HDFS_client = HDFileSystem(host=host, port=port)

    file_exists(HDFS_client)

    write_read(HDFS_client)

    read_lines(HDFS_client)

    HDFS_client.disconnect()

    print('-' * 20)
    print('Hello Hadoop!')
