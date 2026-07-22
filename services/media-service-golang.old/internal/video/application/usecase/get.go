package usecase

import (
	"context"

	"media-service/internal/video/domain"
	"media-service/internal/video/application/dto"
)

type GetVideoUseCase interface {
	Execute(ctx context.Context, id string) (*dto.VideoResponseDto, error)
}

type GetVideoService struct {
	repo domain.VideoRepository
}

func NewGetVideoService(repo domain.VideoRepository) *GetVideoService {
	return &GetVideoService {
		repo: repo,
	}
}

func (v *GetVideoService) Execute(ctx context.Context, id string) (*dto.VideoResponseDto, error) {
	video, err := v.repo.FindById(ctx, id)

	if err != nil {
		return nil, err
	}

	response := 

	return response, nil
}