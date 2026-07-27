package usecase

import (
	"context"

	"media-service/internal/shared/apperror"
	"media-service/internal/video/application/dto"
	"media-service/internal/video/application/mapper"
	"media-service/internal/video/domain"
)

type GetVideoUseCase interface {
	Execute(ctx context.Context, id string) (dto.VideoResponseDto, error)
}

type GetVideoService struct {
	repo   domain.VideoRepository
	mapper mapper.VideoApplicationMapper
}

func NewGetVideoService(repo domain.VideoRepository, mapper mapper.VideoApplicationMapper) *GetVideoService {
	return &GetVideoService{
		repo:   repo,
		mapper: mapper,
	}
}

func (v *GetVideoService) Execute(ctx context.Context, id string) (dto.VideoResponseDto, error) {
	op := "video.application.usecase:get"
	var zero dto.VideoResponseDto

	if err := ctx.Err(); err != nil {
		return zero, apperror.New(err.Error(), op)
	}

	video, err := v.repo.FindById(ctx, id)
	if err != nil {
		return zero, err
	}

	response, err := v.mapper.ToVideoResponseDto(ctx, video)
	if err != nil {
		return zero, err
	}

	return response, nil
}
