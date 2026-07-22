package domain

type Format int

const (
	FormatMPEG_DASH Format = iota
	FormatHLS
)

type Video struct {
	Id            string
	FilePath      string
	LanguageCodes []string
	Extension     string
	Format        Format
}
