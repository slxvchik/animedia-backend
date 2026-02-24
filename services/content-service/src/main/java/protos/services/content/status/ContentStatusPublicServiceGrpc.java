package protos.services.content.status;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentStatusPublicServiceGrpc {

  private ContentStatusPublicServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.status.ContentStatusPublicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPublic.SearchRequest,
      protos.services.content.status.ContentStatusPublic.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = protos.services.content.status.ContentStatusPublic.SearchRequest.class,
      responseType = protos.services.content.status.ContentStatusPublic.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPublic.SearchRequest,
      protos.services.content.status.ContentStatusPublic.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPublic.SearchRequest, protos.services.content.status.ContentStatusPublic.SearchResponse> getSearchMethod;
    if ((getSearchMethod = ContentStatusPublicServiceGrpc.getSearchMethod) == null) {
      synchronized (ContentStatusPublicServiceGrpc.class) {
        if ((getSearchMethod = ContentStatusPublicServiceGrpc.getSearchMethod) == null) {
          ContentStatusPublicServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<protos.services.content.status.ContentStatusPublic.SearchRequest, protos.services.content.status.ContentStatusPublic.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPublic.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPublic.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusPublicServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentStatusPublicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceStub>() {
        @java.lang.Override
        public ContentStatusPublicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPublicServiceStub(channel, callOptions);
        }
      };
    return ContentStatusPublicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ContentStatusPublicServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceBlockingV2Stub>() {
        @java.lang.Override
        public ContentStatusPublicServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPublicServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ContentStatusPublicServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentStatusPublicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceBlockingStub>() {
        @java.lang.Override
        public ContentStatusPublicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPublicServiceBlockingStub(channel, callOptions);
        }
      };
    return ContentStatusPublicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentStatusPublicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPublicServiceFutureStub>() {
        @java.lang.Override
        public ContentStatusPublicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPublicServiceFutureStub(channel, callOptions);
        }
      };
    return ContentStatusPublicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(protos.services.content.status.ContentStatusPublic.SearchRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPublic.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentStatusPublicService.
   */
  public static abstract class ContentStatusPublicServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentStatusPublicServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentStatusPublicService.
   */
  public static final class ContentStatusPublicServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ContentStatusPublicServiceStub> {
    private ContentStatusPublicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPublicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPublicServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(protos.services.content.status.ContentStatusPublic.SearchRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPublic.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentStatusPublicService.
   */
  public static final class ContentStatusPublicServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusPublicServiceBlockingV2Stub> {
    private ContentStatusPublicServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPublicServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPublicServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public protos.services.content.status.ContentStatusPublic.SearchResponse search(protos.services.content.status.ContentStatusPublic.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ContentStatusPublicService.
   */
  public static final class ContentStatusPublicServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusPublicServiceBlockingStub> {
    private ContentStatusPublicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPublicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPublicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protos.services.content.status.ContentStatusPublic.SearchResponse search(protos.services.content.status.ContentStatusPublic.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentStatusPublicService.
   */
  public static final class ContentStatusPublicServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentStatusPublicServiceFutureStub> {
    private ContentStatusPublicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPublicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPublicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.services.content.status.ContentStatusPublic.SearchResponse> search(
        protos.services.content.status.ContentStatusPublic.SearchRequest request) {
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
          serviceImpl.search((protos.services.content.status.ContentStatusPublic.SearchRequest) request,
              (io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPublic.SearchResponse>) responseObserver);
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
              protos.services.content.status.ContentStatusPublic.SearchRequest,
              protos.services.content.status.ContentStatusPublic.SearchResponse>(
                service, METHODID_SEARCH)))
        .build();
  }

  private static abstract class ContentStatusPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentStatusPublicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protos.services.content.status.ContentStatusPublic.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentStatusPublicService");
    }
  }

  private static final class ContentStatusPublicServiceFileDescriptorSupplier
      extends ContentStatusPublicServiceBaseDescriptorSupplier {
    ContentStatusPublicServiceFileDescriptorSupplier() {}
  }

  private static final class ContentStatusPublicServiceMethodDescriptorSupplier
      extends ContentStatusPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentStatusPublicServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentStatusPublicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentStatusPublicServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
