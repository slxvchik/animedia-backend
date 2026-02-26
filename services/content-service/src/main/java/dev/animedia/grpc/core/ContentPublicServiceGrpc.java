package dev.animedia.grpc.core;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentPublicServiceGrpc {

  private ContentPublicServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.core.ContentPublicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentPublicProto.SearchRequest,
      dev.animedia.grpc.core.ContentPublicProto.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.core.ContentPublicProto.SearchRequest.class,
      responseType = dev.animedia.grpc.core.ContentPublicProto.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentPublicProto.SearchRequest,
      dev.animedia.grpc.core.ContentPublicProto.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentPublicProto.SearchRequest, dev.animedia.grpc.core.ContentPublicProto.SearchResponse> getSearchMethod;
    if ((getSearchMethod = ContentPublicServiceGrpc.getSearchMethod) == null) {
      synchronized (ContentPublicServiceGrpc.class) {
        if ((getSearchMethod = ContentPublicServiceGrpc.getSearchMethod) == null) {
          ContentPublicServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.core.ContentPublicProto.SearchRequest, dev.animedia.grpc.core.ContentPublicProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentPublicProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentPublicProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentPublicServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentPublicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceStub>() {
        @java.lang.Override
        public ContentPublicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPublicServiceStub(channel, callOptions);
        }
      };
    return ContentPublicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ContentPublicServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceBlockingV2Stub>() {
        @java.lang.Override
        public ContentPublicServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPublicServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ContentPublicServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentPublicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceBlockingStub>() {
        @java.lang.Override
        public ContentPublicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPublicServiceBlockingStub(channel, callOptions);
        }
      };
    return ContentPublicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentPublicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPublicServiceFutureStub>() {
        @java.lang.Override
        public ContentPublicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPublicServiceFutureStub(channel, callOptions);
        }
      };
    return ContentPublicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(dev.animedia.grpc.core.ContentPublicProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentPublicProto.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentPublicService.
   */
  public static abstract class ContentPublicServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentPublicServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentPublicService.
   */
  public static final class ContentPublicServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ContentPublicServiceStub> {
    private ContentPublicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPublicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPublicServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(dev.animedia.grpc.core.ContentPublicProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentPublicProto.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentPublicService.
   */
  public static final class ContentPublicServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ContentPublicServiceBlockingV2Stub> {
    private ContentPublicServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPublicServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPublicServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentPublicProto.SearchResponse search(dev.animedia.grpc.core.ContentPublicProto.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ContentPublicService.
   */
  public static final class ContentPublicServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentPublicServiceBlockingStub> {
    private ContentPublicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPublicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPublicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentPublicProto.SearchResponse search(dev.animedia.grpc.core.ContentPublicProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentPublicService.
   */
  public static final class ContentPublicServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentPublicServiceFutureStub> {
    private ContentPublicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPublicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPublicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.core.ContentPublicProto.SearchResponse> search(
        dev.animedia.grpc.core.ContentPublicProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH:
          serviceImpl.search((dev.animedia.grpc.core.ContentPublicProto.SearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentPublicProto.SearchResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getSearchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.core.ContentPublicProto.SearchRequest,
              dev.animedia.grpc.core.ContentPublicProto.SearchResponse>(
                service, METHODID_SEARCH)))
        .build();
  }

  private static abstract class ContentPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentPublicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.core.ContentPublicProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentPublicService");
    }
  }

  private static final class ContentPublicServiceFileDescriptorSupplier
      extends ContentPublicServiceBaseDescriptorSupplier {
    ContentPublicServiceFileDescriptorSupplier() {}
  }

  private static final class ContentPublicServiceMethodDescriptorSupplier
      extends ContentPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentPublicServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ContentPublicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentPublicServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
