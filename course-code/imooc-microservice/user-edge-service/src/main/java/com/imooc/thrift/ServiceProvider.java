package com.imooc.thrift;

import com.imooc.thrift.message.MessageService;
import com.imooc.thrift.user.UserService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * description: ServiceProvider <br>
 * date: 2020/2/29 19:41 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Component
public class ServiceProvider {

    @Value(("${thrift.user.ip}"))
    private String userIp;

    @Value("${thrift.user.port}")
    private Integer UserPort;

    @Value("${thrift.message.ip}")
    private String messageIp;

    @Value("${thrift.message.port}")
    private Integer messagePort;

    public UserService.Client getUserService() {
        TProtocol protocol = getProtocol(userIp, UserPort);
        if (protocol == null) return null;
        return new UserService.Client(protocol);
    }

    public MessageService.Client getMessageService() {
        TProtocol protocol = getProtocol(messageIp, messagePort);
        if (protocol == null) return null;
        return new MessageService.Client(protocol);
    }

    private TProtocol getProtocol(String ip, Integer port) {
        TSocket socket = new TSocket(ip, port, 3000);
        TTransport transport = new TFramedTransport(socket);
        try {
            transport.open();
        } catch (TTransportException e) {
            e.printStackTrace();
            return null;
        }
        return new TBinaryProtocol(transport);
    }
}
