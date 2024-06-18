package org.ko.netty.t3.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class HelloWorldClientHandler extends ChannelInboundHandlerAdapter {
    private byte[] req;
    private int counter;

    public HelloWorldClientHandler() {
        req = ("Unless required by applicable law or agreed to in writing, software" +
                "  distributed under the License is distributed on an \"AS IS\" BASIS," +
                "  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied." +
                "  See the License for the specific language governing permissions and" +
                "  limitations under the License.This connector uses the BIO implementation that requires the JSSE" +
                "  style configuration. When using the APR/native implementation, the" +
                "  penSSL style configuration is required as described in the APR/native" +
                "  documentation.An Engine represents the entry point (within Catalina) that processes" +
                "  every request.  The Engine implementation for Tomcat stand alone" +
                "  analyzes the HTTP headers included with the request, and passes them" +
                "  on to the appropriate Host (virtual host)# Unless required by applicable law or agreed to in writing, software" +
                "# distributed under the License is distributed on an \"AS IS\" BASIS," +
                "# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied." +
                "# See the License for the specific language governing permissions and" +
                "# limitations under the License.# For example, set the org.apache.catalina.util.LifecycleBase logger to log" +
                "# each component that extends LifecycleBase changing state:" +
                "#org.apache.catalina.util.LifecycleBase.level = FINE\n").getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message;

        //第一版 发送两次消息----------
        //将上面的所有字符串作为一个消息体发送出去-----
//        message = Unpooled.buffer(req.length);
//        message.writeBytes(req);
//        ctx.writeAndFlush(message);

        //第二版 发送四次消息----------
        //模拟多数据 拆包的情况 将上面的消息循环三次---修改了ServerChannelInitializer 添加了Last
//        for (int i = 0; i < 3; i++) {
//            message = Unpooled.buffer(req.length);
//            message.writeBytes(req);
//            ctx.writeAndFlush(message);
//        }

        //第三版 发送一次消息 不会发生拆包的情况 去掉了上面英文的\n 只保留了最后一个 添加了Last
        message = Unpooled.buffer(req.length);
        message.writeBytes(req);
        ctx.writeAndFlush(message);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String buf = (String)msg;
        System.out.println("Now is : " + buf + " ; the counter is : "+ (++counter));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}