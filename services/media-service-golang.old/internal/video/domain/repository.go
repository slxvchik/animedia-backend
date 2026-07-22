package domain

import "context"

type VideoRepository interface {
	FindById(ctx context.Context, id string) (Video, error)
	Create(ctx context.Context, video Video) (string, error)
	Update(ctx context.Context, video Video) error
	Delete(ctx context.Context, id string) error
}
