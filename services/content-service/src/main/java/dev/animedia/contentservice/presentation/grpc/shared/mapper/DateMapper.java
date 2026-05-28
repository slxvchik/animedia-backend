package dev.animedia.contentservice.presentation.grpc.shared.mapper;

import dev.animedia.grpc.common.CommonProto;
import org.springframework.stereotype.Component;

import java.time.*;

@Component
public class DateMapper {
    public LocalDate toLocalDate(CommonProto.Date date) {
        if (date == null) {
            return null;
        }

        try {
            return LocalDate.of(
                date.getYear(),
                date.getMonth(),
                date.getDay()
            );
        } catch (DateTimeException e) {
            return null;
        }
    }
    public CommonProto.Date toGrpcDate(LocalDate localDate) {
        return CommonProto.Date.newBuilder()
            .setDay(localDate.getDayOfMonth())
            .setMonth(localDate.getMonthValue())
            .setYear(localDate.getYear())
            .build();
    }
    public LocalDateTime toLocalDateTime(com.google.protobuf.Timestamp timestamp) {
        Instant inst = Instant.ofEpochSecond(timestamp.getSeconds(), timestamp.getNanos());
        return LocalDateTime.ofInstant(inst, ZoneId.systemDefault());
    }

    public com.google.protobuf.Timestamp toGrpcTimestamp(LocalDateTime localDateTime) {
        return com.google.protobuf.Timestamp.newBuilder()
            .setSeconds(localDateTime.getSecond())
            .setNanos(localDateTime.getNano())
            .build();
    }
}
