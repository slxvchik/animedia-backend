package dev.animedia.grpc.language;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class LanguageServiceGrpc {

  private LanguageServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.language.LanguageService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getGetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "get",
      requestType = dev.animedia.grpc.common.CommonProto.AppRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.AppResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getGetMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse> getGetMethod;
    if ((getGetMethod = LanguageServiceGrpc.getGetMethod) == null) {
      synchronized (LanguageServiceGrpc.class) {
        if ((getGetMethod = LanguageServiceGrpc.getGetMethod) == null) {
          LanguageServiceGrpc.getGetMethod = getGetMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "get"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LanguageServiceMethodDescriptorSupplier("get"))
              .build();
        }
      }
    }
    return getGetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.common.CommonProto.AppRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.AppResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse> getSearchMethod;
    if ((getSearchMethod = LanguageServiceGrpc.getSearchMethod) == null) {
      synchronized (LanguageServiceGrpc.class) {
        if ((getSearchMethod = LanguageServiceGrpc.getSearchMethod) == null) {
          LanguageServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LanguageServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = dev.animedia.grpc.common.CommonProto.AppRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.AppResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse> getCreateMethod;
    if ((getCreateMethod = LanguageServiceGrpc.getCreateMethod) == null) {
      synchronized (LanguageServiceGrpc.class) {
        if ((getCreateMethod = LanguageServiceGrpc.getCreateMethod) == null) {
          LanguageServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LanguageServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = dev.animedia.grpc.common.CommonProto.AppRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.AppResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse> getUpdateMethod;
    if ((getUpdateMethod = LanguageServiceGrpc.getUpdateMethod) == null) {
      synchronized (LanguageServiceGrpc.class) {
        if ((getUpdateMethod = LanguageServiceGrpc.getUpdateMethod) == null) {
          LanguageServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LanguageServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dev.animedia.grpc.common.CommonProto.AppRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.AppResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest,
      dev.animedia.grpc.common.CommonProto.AppResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse> getDeleteMethod;
    if ((getDeleteMethod = LanguageServiceGrpc.getDeleteMethod) == null) {
      synchronized (LanguageServiceGrpc.class) {
        if ((getDeleteMethod = LanguageServiceGrpc.getDeleteMethod) == null) {
          LanguageServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.common.CommonProto.AppRequest, dev.animedia.grpc.common.CommonProto.AppResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.AppResponse.getDefaultInstance()))
              .setSchemaDescriptor(new LanguageServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static LanguageServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LanguageServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LanguageServiceStub>() {
        @java.lang.Override
        public LanguageServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LanguageServiceStub(channel, callOptions);
        }
      };
    return LanguageServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static LanguageServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LanguageServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LanguageServiceBlockingV2Stub>() {
        @java.lang.Override
        public LanguageServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LanguageServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return LanguageServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static LanguageServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LanguageServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LanguageServiceBlockingStub>() {
        @java.lang.Override
        public LanguageServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LanguageServiceBlockingStub(channel, callOptions);
        }
      };
    return LanguageServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static LanguageServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<LanguageServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<LanguageServiceFutureStub>() {
        @java.lang.Override
        public LanguageServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new LanguageServiceFutureStub(channel, callOptions);
        }
      };
    return LanguageServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void get(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMethod(), responseObserver);
    }

    /**
     */
    default void search(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    default void create(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void update(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service LanguageService.
   */
  public static abstract class LanguageServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return LanguageServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service LanguageService.
   */
  public static final class LanguageServiceStub
      extends io.grpc.stub.AbstractAsyncStub<LanguageServiceStub> {
    private LanguageServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LanguageServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LanguageServiceStub(channel, callOptions);
    }

    /**
     */
    public void get(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void search(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dev.animedia.grpc.common.CommonProto.AppRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service LanguageService.
   */
  public static final class LanguageServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<LanguageServiceBlockingV2Stub> {
    private LanguageServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LanguageServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LanguageServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse get(dev.animedia.grpc.common.CommonProto.AppRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse search(dev.animedia.grpc.common.CommonProto.AppRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse create(dev.animedia.grpc.common.CommonProto.AppRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse update(dev.animedia.grpc.common.CommonProto.AppRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse delete(dev.animedia.grpc.common.CommonProto.AppRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service LanguageService.
   */
  public static final class LanguageServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<LanguageServiceBlockingStub> {
    private LanguageServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LanguageServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LanguageServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse get(dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse search(dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse create(dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse update(dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.AppResponse delete(dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service LanguageService.
   */
  public static final class LanguageServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<LanguageServiceFutureStub> {
    private LanguageServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected LanguageServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new LanguageServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.AppResponse> get(
        dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.AppResponse> search(
        dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.AppResponse> create(
        dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.AppResponse> update(
        dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.AppResponse> delete(
        dev.animedia.grpc.common.CommonProto.AppRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET = 0;
  private static final int METHODID_SEARCH = 1;
  private static final int METHODID_CREATE = 2;
  private static final int METHODID_UPDATE = 3;
  private static final int METHODID_DELETE = 4;

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
        case METHODID_GET:
          serviceImpl.get((dev.animedia.grpc.common.CommonProto.AppRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((dev.animedia.grpc.common.CommonProto.AppRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((dev.animedia.grpc.common.CommonProto.AppRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((dev.animedia.grpc.common.CommonProto.AppRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dev.animedia.grpc.common.CommonProto.AppRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.AppResponse>) responseObserver);
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
          getGetMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.common.CommonProto.AppRequest,
              dev.animedia.grpc.common.CommonProto.AppResponse>(
                service, METHODID_GET)))
        .addMethod(
          getSearchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.common.CommonProto.AppRequest,
              dev.animedia.grpc.common.CommonProto.AppResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.common.CommonProto.AppRequest,
              dev.animedia.grpc.common.CommonProto.AppResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.common.CommonProto.AppRequest,
              dev.animedia.grpc.common.CommonProto.AppResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.common.CommonProto.AppRequest,
              dev.animedia.grpc.common.CommonProto.AppResponse>(
                service, METHODID_DELETE)))
        .build();
  }

  private static abstract class LanguageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    LanguageServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.language.LanguageProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("LanguageService");
    }
  }

  private static final class LanguageServiceFileDescriptorSupplier
      extends LanguageServiceBaseDescriptorSupplier {
    LanguageServiceFileDescriptorSupplier() {}
  }

  private static final class LanguageServiceMethodDescriptorSupplier
      extends LanguageServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    LanguageServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (LanguageServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new LanguageServiceFileDescriptorSupplier())
              .addMethod(getGetMethod())
              .addMethod(getSearchMethod())
              .addMethod(getCreateMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getDeleteMethod())
              .build();
        }
      }
    }
    return result;
  }
}
