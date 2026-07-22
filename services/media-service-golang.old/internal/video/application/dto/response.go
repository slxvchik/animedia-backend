package dto

type VideoResponseDto struct {
	Id            string
	FilePath      string
	LanguageCodes []string
	Extension     string
	Format        FormatDto
}
