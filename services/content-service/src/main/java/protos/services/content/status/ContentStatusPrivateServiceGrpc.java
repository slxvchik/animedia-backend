package protos.services.content.status;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentStatusPrivateServiceGrpc {

  private ContentStatusPrivateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.status.ContentStatusPrivateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.SearchRequest,
      protos.services.content.status.ContentStatusPrivate.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = protos.services.content.status.ContentStatusPrivate.SearchRequest.class,
      responseType = protos.services.content.status.ContentStatusPrivate.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.SearchRequest,
      protos.services.content.status.ContentStatusPrivate.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.SearchRequest, protos.services.content.status.ContentStatusPrivate.SearchResponse> getSearchMethod;
    if ((getSearchMethod = ContentStatusPrivateServiceGrpc.getSearchMethod) == null) {
      synchronized (ContentStatusPrivateServiceGrpc.class) {
        if ((getSearchMethod = ContentStatusPrivateServiceGrpc.getSearchMethod) == null) {
          ContentStatusPrivateServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<protos.services.content.status.ContentStatusPrivate.SearchRequest, protos.services.content.status.ContentStatusPrivate.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPrivate.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPrivate.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusPrivateServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.CreateRequest,
      protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = protos.services.content.status.ContentStatusPrivate.CreateRequest.class,
      responseType = protos.services.content.status.ContentStatusCommon.ContentStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.CreateRequest,
      protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.CreateRequest, protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getCreateMethod;
    if ((getCreateMethod = ContentStatusPrivateServiceGrpc.getCreateMethod) == null) {
      synchronized (ContentStatusPrivateServiceGrpc.class) {
        if ((getCreateMethod = ContentStatusPrivateServiceGrpc.getCreateMethod) == null) {
          ContentStatusPrivateServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<protos.services.content.status.ContentStatusPrivate.CreateRequest, protos.services.content.status.ContentStatusCommon.ContentStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPrivate.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusCommon.ContentStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusPrivateServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.UpdateRequest,
      protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = protos.services.content.status.ContentStatusPrivate.UpdateRequest.class,
      responseType = protos.services.content.status.ContentStatusCommon.ContentStatusResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.UpdateRequest,
      protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.UpdateRequest, protos.services.content.status.ContentStatusCommon.ContentStatusResponse> getUpdateMethod;
    if ((getUpdateMethod = ContentStatusPrivateServiceGrpc.getUpdateMethod) == null) {
      synchronized (ContentStatusPrivateServiceGrpc.class) {
        if ((getUpdateMethod = ContentStatusPrivateServiceGrpc.getUpdateMethod) == null) {
          ContentStatusPrivateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<protos.services.content.status.ContentStatusPrivate.UpdateRequest, protos.services.content.status.ContentStatusCommon.ContentStatusResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPrivate.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusCommon.ContentStatusResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusPrivateServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = protos.services.content.status.ContentStatusPrivate.DeleteRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<protos.services.content.status.ContentStatusPrivate.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;
    if ((getDeleteMethod = ContentStatusPrivateServiceGrpc.getDeleteMethod) == null) {
      synchronized (ContentStatusPrivateServiceGrpc.class) {
        if ((getDeleteMethod = ContentStatusPrivateServiceGrpc.getDeleteMethod) == null) {
          ContentStatusPrivateServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<protos.services.content.status.ContentStatusPrivate.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  protos.services.content.status.ContentStatusPrivate.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusPrivateServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentStatusPrivateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceStub>() {
        @java.lang.Override
        public ContentStatusPrivateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPrivateServiceStub(channel, callOptions);
        }
      };
    return ContentStatusPrivateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ContentStatusPrivateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceBlockingV2Stub>() {
        @java.lang.Override
        public ContentStatusPrivateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPrivateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ContentStatusPrivateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentStatusPrivateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceBlockingStub>() {
        @java.lang.Override
        public ContentStatusPrivateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPrivateServiceBlockingStub(channel, callOptions);
        }
      };
    return ContentStatusPrivateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentStatusPrivateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusPrivateServiceFutureStub>() {
        @java.lang.Override
        public ContentStatusPrivateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusPrivateServiceFutureStub(channel, callOptions);
        }
      };
    return ContentStatusPrivateServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(protos.services.content.status.ContentStatusPrivate.SearchRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPrivate.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    default void create(protos.services.content.status.ContentStatusPrivate.CreateRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void update(protos.services.content.status.ContentStatusPrivate.UpdateRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(protos.services.content.status.ContentStatusPrivate.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentStatusPrivateService.
   */
  public static abstract class ContentStatusPrivateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentStatusPrivateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentStatusPrivateService.
   */
  public static final class ContentStatusPrivateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ContentStatusPrivateServiceStub> {
    private ContentStatusPrivateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPrivateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPrivateServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(protos.services.content.status.ContentStatusPrivate.SearchRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPrivate.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(protos.services.content.status.ContentStatusPrivate.CreateRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(protos.services.content.status.ContentStatusPrivate.UpdateRequest request,
        io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(protos.services.content.status.ContentStatusPrivate.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentStatusPrivateService.
   */
  public static final class ContentStatusPrivateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusPrivateServiceBlockingV2Stub> {
    private ContentStatusPrivateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPrivateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPrivateServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public protos.services.content.status.ContentStatusPrivate.SearchResponse search(protos.services.content.status.ContentStatusPrivate.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.services.content.status.ContentStatusCommon.ContentStatusResponse create(protos.services.content.status.ContentStatusPrivate.CreateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.services.content.status.ContentStatusCommon.ContentStatusResponse update(protos.services.content.status.ContentStatusPrivate.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(protos.services.content.status.ContentStatusPrivate.DeleteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ContentStatusPrivateService.
   */
  public static final class ContentStatusPrivateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusPrivateServiceBlockingStub> {
    private ContentStatusPrivateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPrivateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPrivateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public protos.services.content.status.ContentStatusPrivate.SearchResponse search(protos.services.content.status.ContentStatusPrivate.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.services.content.status.ContentStatusCommon.ContentStatusResponse create(protos.services.content.status.ContentStatusPrivate.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public protos.services.content.status.ContentStatusCommon.ContentStatusResponse update(protos.services.content.status.ContentStatusPrivate.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(protos.services.content.status.ContentStatusPrivate.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentStatusPrivateService.
   */
  public static final class ContentStatusPrivateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentStatusPrivateServiceFutureStub> {
    private ContentStatusPrivateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusPrivateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusPrivateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.services.content.status.ContentStatusPrivate.SearchResponse> search(
        protos.services.content.status.ContentStatusPrivate.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> create(
        protos.services.content.status.ContentStatusPrivate.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<protos.services.content.status.ContentStatusCommon.ContentStatusResponse> update(
        protos.services.content.status.ContentStatusPrivate.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> delete(
        protos.services.content.status.ContentStatusPrivate.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_CREATE = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_DELETE = 3;

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
          serviceImpl.search((protos.services.content.status.ContentStatusPrivate.SearchRequest) request,
              (io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusPrivate.SearchResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((protos.services.content.status.ContentStatusPrivate.CreateRequest) request,
              (io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((protos.services.content.status.ContentStatusPrivate.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<protos.services.content.status.ContentStatusCommon.ContentStatusResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((protos.services.content.status.ContentStatusPrivate.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse>) responseObserver);
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
              protos.services.content.status.ContentStatusPrivate.SearchRequest,
              protos.services.content.status.ContentStatusPrivate.SearchResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              protos.services.content.status.ContentStatusPrivate.CreateRequest,
              protos.services.content.status.ContentStatusCommon.ContentStatusResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              protos.services.content.status.ContentStatusPrivate.UpdateRequest,
              protos.services.content.status.ContentStatusCommon.ContentStatusResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              protos.services.content.status.ContentStatusPrivate.DeleteRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE)))
        .build();
  }

  private static abstract class ContentStatusPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentStatusPrivateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return protos.services.content.status.ContentStatusPrivate.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentStatusPrivateService");
    }
  }

  private static final class ContentStatusPrivateServiceFileDescriptorSupplier
      extends ContentStatusPrivateServiceBaseDescriptorSupplier {
    ContentStatusPrivateServiceFileDescriptorSupplier() {}
  }

  private static final class ContentStatusPrivateServiceMethodDescriptorSupplier
      extends ContentStatusPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentStatusPrivateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentStatusPrivateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentStatusPrivateServiceFileDescriptorSupplier())
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
