package dev.animedia.grpc.genre;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class GenreTranslationPublicServiceGrpc {

  private GenreTranslationPublicServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.genre.GenreTranslationPublicService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest,
      dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest.class,
      responseType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest,
      dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest, dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> getSearchMethod;
    if ((getSearchMethod = GenreTranslationPublicServiceGrpc.getSearchMethod) == null) {
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        if ((getSearchMethod = GenreTranslationPublicServiceGrpc.getSearchMethod) == null) {
          GenreTranslationPublicServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest, dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPublicServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest.class,
      responseType = dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getCreateMethod;
    if ((getCreateMethod = GenreTranslationPublicServiceGrpc.getCreateMethod) == null) {
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        if ((getCreateMethod = GenreTranslationPublicServiceGrpc.getCreateMethod) == null) {
          GenreTranslationPublicServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPublicServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest.class,
      responseType = dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> getUpdateMethod;
    if ((getUpdateMethod = GenreTranslationPublicServiceGrpc.getUpdateMethod) == null) {
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        if ((getUpdateMethod = GenreTranslationPublicServiceGrpc.getUpdateMethod) == null) {
          GenreTranslationPublicServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPublicServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;
    if ((getDeleteMethod = GenreTranslationPublicServiceGrpc.getDeleteMethod) == null) {
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        if ((getDeleteMethod = GenreTranslationPublicServiceGrpc.getDeleteMethod) == null) {
          GenreTranslationPublicServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPublicServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteBatch",
      requestType = dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod;
    if ((getDeleteBatchMethod = GenreTranslationPublicServiceGrpc.getDeleteBatchMethod) == null) {
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        if ((getDeleteBatchMethod = GenreTranslationPublicServiceGrpc.getDeleteBatchMethod) == null) {
          GenreTranslationPublicServiceGrpc.getDeleteBatchMethod = getDeleteBatchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPublicServiceMethodDescriptorSupplier("deleteBatch"))
              .build();
        }
      }
    }
    return getDeleteBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenreTranslationPublicServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceStub>() {
        @java.lang.Override
        public GenreTranslationPublicServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPublicServiceStub(channel, callOptions);
        }
      };
    return GenreTranslationPublicServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static GenreTranslationPublicServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceBlockingV2Stub>() {
        @java.lang.Override
        public GenreTranslationPublicServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPublicServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return GenreTranslationPublicServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenreTranslationPublicServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceBlockingStub>() {
        @java.lang.Override
        public GenreTranslationPublicServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPublicServiceBlockingStub(channel, callOptions);
        }
      };
    return GenreTranslationPublicServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenreTranslationPublicServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPublicServiceFutureStub>() {
        @java.lang.Override
        public GenreTranslationPublicServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPublicServiceFutureStub(channel, callOptions);
        }
      };
    return GenreTranslationPublicServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    default void create(dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void update(dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    default void deleteBatch(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GenreTranslationPublicService.
   */
  public static abstract class GenreTranslationPublicServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenreTranslationPublicServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenreTranslationPublicService.
   */
  public static final class GenreTranslationPublicServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GenreTranslationPublicServiceStub> {
    private GenreTranslationPublicServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPublicServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPublicServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteBatch(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteBatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GenreTranslationPublicService.
   */
  public static final class GenreTranslationPublicServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<GenreTranslationPublicServiceBlockingV2Stub> {
    private GenreTranslationPublicServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPublicServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPublicServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse search(dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse create(dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse update(dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse deleteBatch(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service GenreTranslationPublicService.
   */
  public static final class GenreTranslationPublicServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenreTranslationPublicServiceBlockingStub> {
    private GenreTranslationPublicServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPublicServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPublicServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse search(dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse create(dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse update(dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse deleteBatch(dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenreTranslationPublicService.
   */
  public static final class GenreTranslationPublicServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenreTranslationPublicServiceFutureStub> {
    private GenreTranslationPublicServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPublicServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPublicServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse> search(
        dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> create(
        dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse> update(
        dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> delete(
        dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> deleteBatch(
        dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteBatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_CREATE = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_DELETE = 3;
  private static final int METHODID_DELETE_BATCH = 4;

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
          serviceImpl.search((dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse>) responseObserver);
          break;
        case METHODID_DELETE_BATCH:
          serviceImpl.deleteBatch((dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest) request,
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
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest,
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest,
              dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest,
              dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE)))
        .addMethod(
          getDeleteBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE_BATCH)))
        .build();
  }

  private static abstract class GenreTranslationPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenreTranslationPublicServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.genre.GenreTranslationPrivateProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenreTranslationPublicService");
    }
  }

  private static final class GenreTranslationPublicServiceFileDescriptorSupplier
      extends GenreTranslationPublicServiceBaseDescriptorSupplier {
    GenreTranslationPublicServiceFileDescriptorSupplier() {}
  }

  private static final class GenreTranslationPublicServiceMethodDescriptorSupplier
      extends GenreTranslationPublicServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GenreTranslationPublicServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GenreTranslationPublicServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenreTranslationPublicServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .addMethod(getCreateMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getDeleteBatchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
