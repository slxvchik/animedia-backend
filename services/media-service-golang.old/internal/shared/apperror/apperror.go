package apperror

import (
	"errors"
	"fmt"
	"time"
)

type AppError struct {
	Created  time.Time
	Err      error
	Operation string
}

func New(ErrCode string, Location string) *AppError {
	return &AppError{
		Created:  time.Now(),
		Err:      errors.New(ErrCode),
		Operation: Location,
	}
}

func (a *AppError) Error() string {
	if a.Err != nil {
		return a.Err.Error()
	}
	return "Error not found"
}

func (a *AppError) Log() string {
	if a.Err != nil {
		timeStr := a.Created.Local().Format(time.RFC3339)
		return fmt.Sprintf("[%s] %s: %s", timeStr, a.Operation, a.Err.Error())
	}
	return "Error not found"
}
