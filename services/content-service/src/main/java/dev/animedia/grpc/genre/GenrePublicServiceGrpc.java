package dev.animedia.grpc.genre;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class GenrePublicServiceGrpc {

  private GenrePublicServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.genre.GenrePublicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePublicProto.SearchRequest,
      dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.genre.GenrePublicProto.SearchRequest.class,
      responseType = dev.animedia.grpc.genre.GenrePublicProto.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePublicProto.SearchRequest,
      dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePublicProto.SearchRequest, dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> getSearchMethod;
    if ((getSearchMethod = GenrePublicServiceGrpc.getSearchMethod) == null) {
      synchronized (GenrePublicServiceGrpc.class) {
        if ((getSearchMethod = GenrePublicServiceGrpc.getSearchMethod) == null) {
          GenrePublicServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePublicProto.SearchRequest, dev.animedia.grpc.genre.GenrePublicProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePublicProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePublicProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePublicServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenrePublicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceStub>() {
        @java.lang.Override
        public GenrePublicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePublicServiceStub(channel, callOptions);
        }
      };
    return GenrePublicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static GenrePublicServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceBlockingV2Stub>() {
        @java.lang.Override
        public GenrePublicServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePublicServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return GenrePublicServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenrePublicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceBlockingStub>() {
        @java.lang.Override
        public GenrePublicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePublicServiceBlockingStub(channel, callOptions);
        }
      };
    return GenrePublicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenrePublicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePublicServiceFutureStub>() {
        @java.lang.Override
        public GenrePublicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePublicServiceFutureStub(channel, callOptions);
        }
      };
    return GenrePublicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(dev.animedia.grpc.genre.GenrePublicProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GenrePublicService.
   */
  public static abstract class GenrePublicServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenrePublicServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenrePublicService.
   */
  public static final class GenrePublicServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GenrePublicServiceStub> {
    private GenrePublicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePublicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePublicServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(dev.animedia.grpc.genre.GenrePublicProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GenrePublicService.
   */
  public static final class GenrePublicServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<GenrePublicServiceBlockingV2Stub> {
    private GenrePublicServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePublicServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePublicServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenrePublicProto.SearchResponse search(dev.animedia.grpc.genre.GenrePublicProto.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service GenrePublicService.
   */
  public static final class GenrePublicServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenrePublicServiceBlockingStub> {
    private GenrePublicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePublicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePublicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenrePublicProto.SearchResponse search(dev.animedia.grpc.genre.GenrePublicProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenrePublicService.
   */
  public static final class GenrePublicServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenrePublicServiceFutureStub> {
    private GenrePublicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePublicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePublicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenrePublicProto.SearchResponse> search(
        dev.animedia.grpc.genre.GenrePublicProto.SearchRequest request) {
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
          serviceImpl.search((dev.animedia.grpc.genre.GenrePublicProto.SearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePublicProto.SearchResponse>) responseObserver);
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
              dev.animedia.grpc.genre.GenrePublicProto.SearchRequest,
              dev.animedia.grpc.genre.GenrePublicProto.SearchResponse>(
                service, METHODID_SEARCH)))
        .build();
  }

  private static abstract class GenrePublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenrePublicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.genre.GenrePublicProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenrePublicService");
    }
  }

  private static final class GenrePublicServiceFileDescriptorSupplier
      extends GenrePublicServiceBaseDescriptorSupplier {
    GenrePublicServiceFileDescriptorSupplier() {}
  }

  private static final class GenrePublicServiceMethodDescriptorSupplier
      extends GenrePublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GenrePublicServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GenrePublicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenrePublicServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
