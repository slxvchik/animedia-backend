PROTOC = protoc
PROTOC_DIR = C:/Program Files/protoc-33.5-win64/bin
GRPC_PLUGIN = $(PROTOC_DIR)/protoc-gen-grpc-java-1.79.0-windows-x86_64.exe
PROTO_PATH = protos

LANGUAGE_OUT = ../services/language-service/protobuf
CONTENT_OUT = ../services/content-service/protobuf

COMMON = common/common.proto
LANGUAGE = services/language/language.proto
CONTENT = services/content/content.proto

.PHONY: all clean dirs language content

all: dirs language content

dirs:
	@if not exist $(LANGUAGE_OUT) mkdir $(LANGUAGE_OUT)
	@if not exist $(CONTENT_OUT) mkdir $(CONTENT_OUT)

common: dirs
	$(PROTOC) --proto_path=$(PROTO_PATH) --java_out=$(LANGUAGE_OUT) $(PROTO_PATH)/$(COMMON)
	$(PROTOC) --proto_path=$(PROTO_PATH) --java_out=$(CONTENT_OUT) $(PROTO_PATH)/$(COMMON)

language: common
	$(PROTOC) --proto_path=$(PROTO_PATH) \
		--java_out=$(LANGUAGE_OUT) \
		--grpc-java_out=$(LANGUAGE_OUT) \
		--plugin=protoc-gen-grpc-java=$(GRPC_PLUGIN) \
		$(PROTO_PATH)/$(LANGUAGE)

content: common
	$(PROTOC) --proto_path=$(PROTO_PATH) --java_out=$(CONTENT_OUT) $(PROTO_PATH)/$(CONTENT)

clean:
	@if exist $(LANGUAGE_OUT) rmdir /s /q $(LANGUAGE_OUT)
	@if exist $(CONTENT_OUT) rmdir /s /q $(CONTENT_OUT)