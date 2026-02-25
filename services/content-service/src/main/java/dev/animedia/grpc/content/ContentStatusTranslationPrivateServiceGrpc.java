package dev.animedia.grpc.content;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentStatusTranslationPrivateServiceGrpc {

  private ContentStatusTranslationPrivateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.status.ContentStatusTranslationPrivateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest.class,
      responseType = dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest, dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getCreateMethod;
    if ((getCreateMethod = ContentStatusTranslationPrivateServiceGrpc.getCreateMethod) == null) {
      synchronized (ContentStatusTranslationPrivateServiceGrpc.class) {
        if ((getCreateMethod = ContentStatusTranslationPrivateServiceGrpc.getCreateMethod) == null) {
          ContentStatusTranslationPrivateServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest, dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusTranslationPrivateServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest.class,
      responseType = dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> getUpdateMethod;
    if ((getUpdateMethod = ContentStatusTranslationPrivateServiceGrpc.getUpdateMethod) == null) {
      synchronized (ContentStatusTranslationPrivateServiceGrpc.class) {
        if ((getUpdateMethod = ContentStatusTranslationPrivateServiceGrpc.getUpdateMethod) == null) {
          ContentStatusTranslationPrivateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusTranslationPrivateServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;
    if ((getDeleteMethod = ContentStatusTranslationPrivateServiceGrpc.getDeleteMethod) == null) {
      synchronized (ContentStatusTranslationPrivateServiceGrpc.class) {
        if ((getDeleteMethod = ContentStatusTranslationPrivateServiceGrpc.getDeleteMethod) == null) {
          ContentStatusTranslationPrivateServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentStatusTranslationPrivateServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentStatusTranslationPrivateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceStub>() {
        @java.lang.Override
        public ContentStatusTranslationPrivateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusTranslationPrivateServiceStub(channel, callOptions);
        }
      };
    return ContentStatusTranslationPrivateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ContentStatusTranslationPrivateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceBlockingV2Stub>() {
        @java.lang.Override
        public ContentStatusTranslationPrivateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusTranslationPrivateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ContentStatusTranslationPrivateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentStatusTranslationPrivateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceBlockingStub>() {
        @java.lang.Override
        public ContentStatusTranslationPrivateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusTranslationPrivateServiceBlockingStub(channel, callOptions);
        }
      };
    return ContentStatusTranslationPrivateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentStatusTranslationPrivateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentStatusTranslationPrivateServiceFutureStub>() {
        @java.lang.Override
        public ContentStatusTranslationPrivateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentStatusTranslationPrivateServiceFutureStub(channel, callOptions);
        }
      };
    return ContentStatusTranslationPrivateServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void create(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void update(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentStatusTranslationPrivateService.
   */
  public static abstract class ContentStatusTranslationPrivateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentStatusTranslationPrivateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentStatusTranslationPrivateService.
   */
  public static final class ContentStatusTranslationPrivateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ContentStatusTranslationPrivateServiceStub> {
    private ContentStatusTranslationPrivateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusTranslationPrivateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusTranslationPrivateServiceStub(channel, callOptions);
    }

    /**
     */
    public void create(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentStatusTranslationPrivateService.
   */
  public static final class ContentStatusTranslationPrivateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusTranslationPrivateServiceBlockingV2Stub> {
    private ContentStatusTranslationPrivateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusTranslationPrivateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusTranslationPrivateServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse create(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse update(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ContentStatusTranslationPrivateService.
   */
  public static final class ContentStatusTranslationPrivateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentStatusTranslationPrivateServiceBlockingStub> {
    private ContentStatusTranslationPrivateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusTranslationPrivateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusTranslationPrivateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse create(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse update(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentStatusTranslationPrivateService.
   */
  public static final class ContentStatusTranslationPrivateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentStatusTranslationPrivateServiceFutureStub> {
    private ContentStatusTranslationPrivateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentStatusTranslationPrivateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentStatusTranslationPrivateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> create(
        dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse> update(
        dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> delete(
        dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_UPDATE = 1;
  private static final int METHODID_DELETE = 2;

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
        case METHODID_CREATE:
          serviceImpl.create((dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest) request,
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
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.CreateRequest,
              dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.UpdateRequest,
              dev.animedia.grpc.content.ContentStatusCommonProto.ContentStatusTranslationResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.DeleteRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE)))
        .build();
  }

  private static abstract class ContentStatusTranslationPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentStatusTranslationPrivateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.content.ContentStatusTranslationPrivateProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentStatusTranslationPrivateService");
    }
  }

  private static final class ContentStatusTranslationPrivateServiceFileDescriptorSupplier
      extends ContentStatusTranslationPrivateServiceBaseDescriptorSupplier {
    ContentStatusTranslationPrivateServiceFileDescriptorSupplier() {}
  }

  private static final class ContentStatusTranslationPrivateServiceMethodDescriptorSupplier
      extends ContentStatusTranslationPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentStatusTranslationPrivateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentStatusTranslationPrivateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentStatusTranslationPrivateServiceFileDescriptorSupplier())
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
