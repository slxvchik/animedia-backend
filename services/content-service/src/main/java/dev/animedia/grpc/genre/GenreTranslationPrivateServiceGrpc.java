package dev.animedia.grpc.genre;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class GenreTranslationPrivateServiceGrpc {

  private GenreTranslationPrivateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.genre.GenreTranslationPrivateService";

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
    if ((getSearchMethod = GenreTranslationPrivateServiceGrpc.getSearchMethod) == null) {
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        if ((getSearchMethod = GenreTranslationPrivateServiceGrpc.getSearchMethod) == null) {
          GenreTranslationPrivateServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest, dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPrivateServiceMethodDescriptorSupplier("search"))
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
    if ((getCreateMethod = GenreTranslationPrivateServiceGrpc.getCreateMethod) == null) {
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        if ((getCreateMethod = GenreTranslationPrivateServiceGrpc.getCreateMethod) == null) {
          GenreTranslationPrivateServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPrivateServiceMethodDescriptorSupplier("create"))
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
    if ((getUpdateMethod = GenreTranslationPrivateServiceGrpc.getUpdateMethod) == null) {
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        if ((getUpdateMethod = GenreTranslationPrivateServiceGrpc.getUpdateMethod) == null) {
          GenreTranslationPrivateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreTranslationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPrivateServiceMethodDescriptorSupplier("update"))
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
    if ((getDeleteMethod = GenreTranslationPrivateServiceGrpc.getDeleteMethod) == null) {
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        if ((getDeleteMethod = GenreTranslationPrivateServiceGrpc.getDeleteMethod) == null) {
          GenreTranslationPrivateServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPrivateServiceMethodDescriptorSupplier("delete"))
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
    if ((getDeleteBatchMethod = GenreTranslationPrivateServiceGrpc.getDeleteBatchMethod) == null) {
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        if ((getDeleteBatchMethod = GenreTranslationPrivateServiceGrpc.getDeleteBatchMethod) == null) {
          GenreTranslationPrivateServiceGrpc.getDeleteBatchMethod = getDeleteBatchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreTranslationPrivateProto.DeleteBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenreTranslationPrivateServiceMethodDescriptorSupplier("deleteBatch"))
              .build();
        }
      }
    }
    return getDeleteBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenreTranslationPrivateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceStub>() {
        @java.lang.Override
        public GenreTranslationPrivateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPrivateServiceStub(channel, callOptions);
        }
      };
    return GenreTranslationPrivateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static GenreTranslationPrivateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceBlockingV2Stub>() {
        @java.lang.Override
        public GenreTranslationPrivateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPrivateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return GenreTranslationPrivateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenreTranslationPrivateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceBlockingStub>() {
        @java.lang.Override
        public GenreTranslationPrivateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPrivateServiceBlockingStub(channel, callOptions);
        }
      };
    return GenreTranslationPrivateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenreTranslationPrivateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenreTranslationPrivateServiceFutureStub>() {
        @java.lang.Override
        public GenreTranslationPrivateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenreTranslationPrivateServiceFutureStub(channel, callOptions);
        }
      };
    return GenreTranslationPrivateServiceFutureStub.newStub(factory, channel);
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
   * Base class for the server implementation of the service GenreTranslationPrivateService.
   */
  public static abstract class GenreTranslationPrivateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenreTranslationPrivateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenreTranslationPrivateService.
   */
  public static final class GenreTranslationPrivateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GenreTranslationPrivateServiceStub> {
    private GenreTranslationPrivateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPrivateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPrivateServiceStub(channel, callOptions);
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
   * A stub to allow clients to do synchronous rpc calls to service GenreTranslationPrivateService.
   */
  public static final class GenreTranslationPrivateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<GenreTranslationPrivateServiceBlockingV2Stub> {
    private GenreTranslationPrivateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPrivateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPrivateServiceBlockingV2Stub(channel, callOptions);
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
   * A stub to allow clients to do limited synchronous rpc calls to service GenreTranslationPrivateService.
   */
  public static final class GenreTranslationPrivateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenreTranslationPrivateServiceBlockingStub> {
    private GenreTranslationPrivateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPrivateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPrivateServiceBlockingStub(channel, callOptions);
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
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenreTranslationPrivateService.
   */
  public static final class GenreTranslationPrivateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenreTranslationPrivateServiceFutureStub> {
    private GenreTranslationPrivateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenreTranslationPrivateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenreTranslationPrivateServiceFutureStub(channel, callOptions);
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

  private static abstract class GenreTranslationPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenreTranslationPrivateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.genre.GenreTranslationPrivateProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenreTranslationPrivateService");
    }
  }

  private static final class GenreTranslationPrivateServiceFileDescriptorSupplier
      extends GenreTranslationPrivateServiceBaseDescriptorSupplier {
    GenreTranslationPrivateServiceFileDescriptorSupplier() {}
  }

  private static final class GenreTranslationPrivateServiceMethodDescriptorSupplier
      extends GenreTranslationPrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GenreTranslationPrivateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GenreTranslationPrivateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenreTranslationPrivateServiceFileDescriptorSupplier())
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
