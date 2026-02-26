package dev.animedia.grpc.core;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class ContentPrivateServiceGrpc {

  private ContentPrivateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.core.ContentPrivateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest,
      dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest.class,
      responseType = dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest,
      dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest, dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> getSearchMethod;
    if ((getSearchMethod = ContentPrivateServiceGrpc.getSearchMethod) == null) {
      synchronized (ContentPrivateServiceGrpc.class) {
        if ((getSearchMethod = ContentPrivateServiceGrpc.getSearchMethod) == null) {
          ContentPrivateServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest, dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentPrivateServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest.class,
      responseType = dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest, dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getCreateMethod;
    if ((getCreateMethod = ContentPrivateServiceGrpc.getCreateMethod) == null) {
      synchronized (ContentPrivateServiceGrpc.class) {
        if ((getCreateMethod = ContentPrivateServiceGrpc.getCreateMethod) == null) {
          ContentPrivateServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest, dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentPrivateServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest.class,
      responseType = dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> getUpdateMethod;
    if ((getUpdateMethod = ContentPrivateServiceGrpc.getUpdateMethod) == null) {
      synchronized (ContentPrivateServiceGrpc.class) {
        if ((getUpdateMethod = ContentPrivateServiceGrpc.getUpdateMethod) == null) {
          ContentPrivateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentPrivateServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;
    if ((getDeleteMethod = ContentPrivateServiceGrpc.getDeleteMethod) == null) {
      synchronized (ContentPrivateServiceGrpc.class) {
        if ((getDeleteMethod = ContentPrivateServiceGrpc.getDeleteMethod) == null) {
          ContentPrivateServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ContentPrivateServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ContentPrivateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceStub>() {
        @java.lang.Override
        public ContentPrivateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPrivateServiceStub(channel, callOptions);
        }
      };
    return ContentPrivateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static ContentPrivateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceBlockingV2Stub>() {
        @java.lang.Override
        public ContentPrivateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPrivateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return ContentPrivateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ContentPrivateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceBlockingStub>() {
        @java.lang.Override
        public ContentPrivateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPrivateServiceBlockingStub(channel, callOptions);
        }
      };
    return ContentPrivateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ContentPrivateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ContentPrivateServiceFutureStub>() {
        @java.lang.Override
        public ContentPrivateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ContentPrivateServiceFutureStub(channel, callOptions);
        }
      };
    return ContentPrivateServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    default void create(dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void update(dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ContentPrivateService.
   */
  public static abstract class ContentPrivateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ContentPrivateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ContentPrivateService.
   */
  public static final class ContentPrivateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ContentPrivateServiceStub> {
    private ContentPrivateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPrivateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPrivateServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ContentPrivateService.
   */
  public static final class ContentPrivateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<ContentPrivateServiceBlockingV2Stub> {
    private ContentPrivateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPrivateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPrivateServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse search(dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse create(dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse update(dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service ContentPrivateService.
   */
  public static final class ContentPrivateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ContentPrivateServiceBlockingStub> {
    private ContentPrivateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPrivateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPrivateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse search(dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse create(dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse update(dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ContentPrivateService.
   */
  public static final class ContentPrivateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ContentPrivateServiceFutureStub> {
    private ContentPrivateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ContentPrivateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ContentPrivateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse> search(
        dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> create(
        dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse> update(
        dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> delete(
        dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest request) {
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
          serviceImpl.search((dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest) request,
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
              dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchRequest,
              dev.animedia.grpc.core.ContentTranslationPrivateProto.SearchResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.core.ContentTranslationPrivateProto.CreateRequest,
              dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.core.ContentTranslationPrivateProto.UpdateRequest,
              dev.animedia.grpc.core.ContentCommonProto.ContentTranslationResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.core.ContentTranslationPrivateProto.DeleteRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE)))
        .build();
  }

  private static abstract class ContentPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ContentPrivateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.core.ContentTranslationPrivateProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ContentPrivateService");
    }
  }

  private static final class ContentPrivateServiceFileDescriptorSupplier
      extends ContentPrivateServiceBaseDescriptorSupplier {
    ContentPrivateServiceFileDescriptorSupplier() {}
  }

  private static final class ContentPrivateServiceMethodDescriptorSupplier
      extends ContentPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ContentPrivateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (ContentPrivateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ContentPrivateServiceFileDescriptorSupplier())
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
