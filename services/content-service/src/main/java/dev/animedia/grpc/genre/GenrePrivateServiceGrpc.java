package dev.animedia.grpc.genre;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@io.grpc.stub.annotations.GrpcGenerated
public final class GenrePrivateServiceGrpc {

  private GenrePrivateServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "protos.services.content.genre.GenrePrivateService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest,
      dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "search",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest.class,
      responseType = dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest,
      dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> getSearchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest, dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> getSearchMethod;
    if ((getSearchMethod = GenrePrivateServiceGrpc.getSearchMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getSearchMethod = GenrePrivateServiceGrpc.getSearchMethod) == null) {
          GenrePrivateServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest, dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest.class,
      responseType = dev.animedia.grpc.genre.GenreCommonProto.GenreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getCreateMethod;
    if ((getCreateMethod = GenrePrivateServiceGrpc.getCreateMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getCreateMethod = GenrePrivateServiceGrpc.getCreateMethod) == null) {
          GenrePrivateServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> getCreateBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createBatch",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest.class,
      responseType = dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> getCreateBatchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> getCreateBatchMethod;
    if ((getCreateBatchMethod = GenrePrivateServiceGrpc.getCreateBatchMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getCreateBatchMethod = GenrePrivateServiceGrpc.getCreateBatchMethod) == null) {
          GenrePrivateServiceGrpc.getCreateBatchMethod = getCreateBatchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("createBatch"))
              .build();
        }
      }
    }
    return getCreateBatchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest.class,
      responseType = dev.animedia.grpc.genre.GenreCommonProto.GenreResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest,
      dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> getUpdateMethod;
    if ((getUpdateMethod = GenrePrivateServiceGrpc.getUpdateMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getUpdateMethod = GenrePrivateServiceGrpc.getUpdateMethod) == null) {
          GenrePrivateServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest, dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenreCommonProto.GenreResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteMethod;
    if ((getDeleteMethod = GenrePrivateServiceGrpc.getDeleteMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getDeleteMethod = GenrePrivateServiceGrpc.getDeleteMethod) == null) {
          GenrePrivateServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteBatch",
      requestType = dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest.class,
      responseType = dev.animedia.grpc.common.CommonProto.EmptyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest,
      dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod() {
    io.grpc.MethodDescriptor<dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse> getDeleteBatchMethod;
    if ((getDeleteBatchMethod = GenrePrivateServiceGrpc.getDeleteBatchMethod) == null) {
      synchronized (GenrePrivateServiceGrpc.class) {
        if ((getDeleteBatchMethod = GenrePrivateServiceGrpc.getDeleteBatchMethod) == null) {
          GenrePrivateServiceGrpc.getDeleteBatchMethod = getDeleteBatchMethod =
              io.grpc.MethodDescriptor.<dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest, dev.animedia.grpc.common.CommonProto.EmptyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteBatch"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  dev.animedia.grpc.common.CommonProto.EmptyResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GenrePrivateServiceMethodDescriptorSupplier("deleteBatch"))
              .build();
        }
      }
    }
    return getDeleteBatchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GenrePrivateServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceStub>() {
        @java.lang.Override
        public GenrePrivateServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePrivateServiceStub(channel, callOptions);
        }
      };
    return GenrePrivateServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static GenrePrivateServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceBlockingV2Stub>() {
        @java.lang.Override
        public GenrePrivateServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePrivateServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return GenrePrivateServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GenrePrivateServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceBlockingStub>() {
        @java.lang.Override
        public GenrePrivateServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePrivateServiceBlockingStub(channel, callOptions);
        }
      };
    return GenrePrivateServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GenrePrivateServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GenrePrivateServiceFutureStub>() {
        @java.lang.Override
        public GenrePrivateServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GenrePrivateServiceFutureStub(channel, callOptions);
        }
      };
    return GenrePrivateServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void search(dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     */
    default void create(dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    default void createBatch(dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateBatchMethod(), responseObserver);
    }

    /**
     */
    default void update(dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    default void delete(dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    default void deleteBatch(dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteBatchMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service GenrePrivateService.
   */
  public static abstract class GenrePrivateServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return GenrePrivateServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service GenrePrivateService.
   */
  public static final class GenrePrivateServiceStub
      extends io.grpc.stub.AbstractAsyncStub<GenrePrivateServiceStub> {
    private GenrePrivateServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePrivateServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePrivateServiceStub(channel, callOptions);
    }

    /**
     */
    public void search(dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createBatch(dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateBatchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteBatch(dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest request,
        io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteBatchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service GenrePrivateService.
   */
  public static final class GenrePrivateServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<GenrePrivateServiceBlockingV2Stub> {
    private GenrePrivateServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePrivateServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePrivateServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse search(dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponse create(dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList createBatch(dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getCreateBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponse update(dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse deleteBatch(dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest request) throws io.grpc.StatusException {
      return io.grpc.stub.ClientCalls.blockingV2UnaryCall(
          getChannel(), getDeleteBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service GenrePrivateService.
   */
  public static final class GenrePrivateServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<GenrePrivateServiceBlockingStub> {
    private GenrePrivateServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePrivateServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePrivateServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse search(dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponse create(dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList createBatch(dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateBatchMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.genre.GenreCommonProto.GenreResponse update(dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse delete(dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public dev.animedia.grpc.common.CommonProto.EmptyResponse deleteBatch(dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteBatchMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service GenrePrivateService.
   */
  public static final class GenrePrivateServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<GenrePrivateServiceFutureStub> {
    private GenrePrivateServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GenrePrivateServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GenrePrivateServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse> search(
        dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> create(
        dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList> createBatch(
        dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateBatchMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse> update(
        dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> delete(
        dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<dev.animedia.grpc.common.CommonProto.EmptyResponse> deleteBatch(
        dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteBatchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH = 0;
  private static final int METHODID_CREATE = 1;
  private static final int METHODID_CREATE_BATCH = 2;
  private static final int METHODID_UPDATE = 3;
  private static final int METHODID_DELETE = 4;
  private static final int METHODID_DELETE_BATCH = 5;

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
          serviceImpl.search((dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>) responseObserver);
          break;
        case METHODID_CREATE_BATCH:
          serviceImpl.createBatch((dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<dev.animedia.grpc.common.CommonProto.EmptyResponse>) responseObserver);
          break;
        case METHODID_DELETE_BATCH:
          serviceImpl.deleteBatch((dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest) request,
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
              dev.animedia.grpc.genre.GenrePrivateProto.SearchRequest,
              dev.animedia.grpc.genre.GenrePrivateProto.SearchResponse>(
                service, METHODID_SEARCH)))
        .addMethod(
          getCreateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenrePrivateProto.CreateRequest,
              dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>(
                service, METHODID_CREATE)))
        .addMethod(
          getCreateBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenrePrivateProto.CreateBatchRequest,
              dev.animedia.grpc.genre.GenreCommonProto.GenreResponseList>(
                service, METHODID_CREATE_BATCH)))
        .addMethod(
          getUpdateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenrePrivateProto.UpdateRequest,
              dev.animedia.grpc.genre.GenreCommonProto.GenreResponse>(
                service, METHODID_UPDATE)))
        .addMethod(
          getDeleteMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenrePrivateProto.DeleteRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE)))
        .addMethod(
          getDeleteBatchMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              dev.animedia.grpc.genre.GenrePrivateProto.DeleteBatchRequest,
              dev.animedia.grpc.common.CommonProto.EmptyResponse>(
                service, METHODID_DELETE_BATCH)))
        .build();
  }

  private static abstract class GenrePrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GenrePrivateServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return dev.animedia.grpc.genre.GenrePrivateProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GenrePrivateService");
    }
  }

  private static final class GenrePrivateServiceFileDescriptorSupplier
      extends GenrePrivateServiceBaseDescriptorSupplier {
    GenrePrivateServiceFileDescriptorSupplier() {}
  }

  private static final class GenrePrivateServiceMethodDescriptorSupplier
      extends GenrePrivateServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    GenrePrivateServiceMethodDescriptorSupplier(java.lang.String methodName) {
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
      synchronized (GenrePrivateServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GenrePrivateServiceFileDescriptorSupplier())
              .addMethod(getSearchMethod())
              .addMethod(getCreateMethod())
              .addMethod(getCreateBatchMethod())
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
