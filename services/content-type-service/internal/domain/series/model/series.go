package series

import "time"

type Episode struct {
	ID                string
	ContentID         string
	VideoID           string
	Active            bool
	DurationInSeconds int64
	Episode           int32
	ReleaseDate       time.Time
}

type Translation struct {
	ID          string
	SeriesID    string
	LanguageID  string
	Name        string
	Description string
}
