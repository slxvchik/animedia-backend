package mapper

import (
	"context"
	"media-service/internal/shared/apperror"
	"media-service/internal/video/application/dto"
	"media-service/internal/video/application/exception"
	"media-service/internal/video/domain"
)

type VideoApplicationMapper interface {
	ToVideoFormatDto(ctx context.Context, video domain.Format) (dto.FormatDto, error)
	ToVideoResponseDto(ctx context.Context, video domain.Video) (dto.VideoResponseDto, error)
}

type VideoApplicationMapperImpl struct {
}

func NewVideoApplicationMapper() VideoApplicationMapper {
	return &VideoApplicationMapperImpl{}
}

func (m *VideoApplicationMapperImpl) ToVideoFormatDto(ctx context.Context, f domain.Format) (dto.FormatDto, error) {
	op := "video.application.mapper:ToVideoFormatDto"

	if err := ctx.Err(); err != nil {
		var zero dto.FormatDto
		return zero, err
	}

	switch f {
	case domain.FormatHLS:
		return dto.FormatHLS, nil
	case domain.FormatMPEG_DASH:
		return dto.FormatMPEG_DASH, nil
	default:
		var zero dto.FormatDto
		return zero, apperror.New(exception.ErrFormatDtoNotFound, op)
	}
}

func (m *VideoApplicationMapperImpl) ToVideoResponseDto(ctx context.Context, v domain.Video) (dto.VideoResponseDto, error) {
	op := "video.application.mapper:ToVideoResponseDto"

	if err := ctx.Err(); err != nil {
		return dto.VideoResponseDto{}, apperror.New(err.Error(), op)
	}

	formatDto, err := m.ToVideoFormatDto(ctx, v.Format)
	if err != nil {
		var zero dto.VideoResponseDto
		return zero, err
	}

	return dto.VideoResponseDto{
		Id:            v.Id,
		FilePath:      v.FilePath,
		LanguageCodes: v.LanguageCodes,
		Extension:     v.Extension,
		Format:        formatDto,
	}, nil
}
