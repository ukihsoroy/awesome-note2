package com.imooc.user.thrift;

import com.imooc.thrift.user.UserService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * description: ThriftServer <br>
 * date: 2020/2/29 19:27 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
@Configuration
public class ThriftServer {

    @Autowired
    private UserService.Iface userService;

    @Value("${server.port}")
    public Integer port;

    @PostConstruct
    public void startThriftServer() throws TTransportException {
        TProcessor processor = new UserService.Processor<>(userService);

        TNonblockingServerSocket socket = new TNonblockingServerSocket(port);

        TNonblockingServer.Args args = new TNonblockingServer.Args(socket);
        args.processor(processor);
        args.transportFactory(new TFramedTransport.Factory());
        args.protocolFactory(new TBinaryProtocol.Factory());
        TServer server = new TNonblockingServer(args);
        server.serve();
    }
}
