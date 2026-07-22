package mapper

import (
	"errors"
	"media-service/internal/video/application/dto"
	"media-service/internal/video/domain"
)

type VideoApplicationMapper interface {
	toVideoFormatDto(video *domain.Format) (dto.FormatDto, error)
	toVideoResponseDto(video *domain.Video) dto.VideoResponseDto
}

type VideoApplicationMapperImpl struct {
}

func (m *VideoApplicationMapperImpl) toVideoFormatDto(f *domain.Format) (dto.FormatDto, error) {
	if f == nil {
		return dto.FormatDto{}, errors.New("err")
	}

	switch *f {
	case domain.FormatHLS:
		return dto.FormatHLS, nil
	case domain.FormatMPEG_DASH:
		return dto.FormatMPEG_DASH, nil
	default:
		return dto.FormatDto{}, errors.New("err")
	}
}

func (m *VideoApplicationMapperImpl) toVideoResponseDto(v *domain.Video) dto.VideoResponseDto {
	formatDto, err := m.toVideoFormatDto(v.Format)
	if err != nil {
		formatDto = dto.FormatDto{}
	}

	return dto.VideoResponseDto{
		Id:            v.Id,
		FilePath:      v.FilePath,
		LanguageCodes: v.LanguageCodes,
		Extension:     v.Extension,
		Format:        formatDto,
	}
}
