本module是学习netty的初始模块

1、主要构建一个无响应服务(DiscardServer),接受任何请求，都会丢弃，不响应

ChannelOption
EventLoopGroup
NioEventLoopGroup
NioServerSocketChannel
ChannelFuture
ByteBuf
ChannelInboundHandler
ChannelInboundHandlerAdapter