package comic

import "time"

type Chapter struct {
	ID          string
	ContentID   string
	Pages       int32
	Episode     int32
	Active      bool
	ReleaseDate time.Time
}

type ChapterImage struct {
	ID        string
	ChapterID string
	Page      int32
	ImageID   string
}

type ChapterTranslation struct {
	ID          string
	ChapterID   string
	LanguageID  string
	Name        string
	Description string
}
