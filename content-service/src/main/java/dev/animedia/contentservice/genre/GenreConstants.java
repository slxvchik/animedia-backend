package dev.animedia.contentservice.genre;

public final class GenreConstants {

    private GenreConstants() {}

    /**
     * Genres
     */
    public static final String GENRE_ALIAS_PATTERN = "^[a-z]{2,20}(?:-[a-z]{1,20}){0,5}$";

    public static final String GENRE_ALIAS_INVALID_CHARS_MESSAGE = "GENRE_ALIAS_INVALID_CHARS";
    public static final String GENRE_ALIAS_REQUIRED_MESSAGE = "GENRE_ALIAS_REQUIRED";
    public static final String GENRE_ALIAS_EXISTS_MESSAGE = "GENRE_ALIAS_EXISTS";

    public static final String GENRE_ID_REQUIRED_MESSAGE = "GENRE_ID_REQUIRED";
    public static final String GENRE_NAME_REQUIRED_MESSAGE = "GENRE_NAME_REQUIRED";

    public static final String GENRE_NOT_FOUND_MESSAGE = "GENRE_NOT_FOUND";
    public static final String GENRES_NOT_FOUND_MESSAGE = "GENRES_NOT_FOUND";

    /**
     * Translations
     */
    public static final String GENRE_TRANSLATION_ID_REQUIRED_MESSAGE = "GENRE_TRANSLATION_ID_REQUIRED";
    public static final String GENRE_TRANSLATION_NAME_REQUIRED_MESSAGE = "GENRE_TRANSLATION_NAME_REQUIRED";
    public static final String GENRE_TRANSLATION_NOT_FOUND_MESSAGE = "GENRE_TRANSLATION_NOT_FOUND";
    public static final String GENRE_TRANSLATIONS_NOT_FOUND_MESSAGE = "GENRE_TRANSLATIONS_NOT_FOUND";
    public static final String GENRE_TRANSLATION_EXISTS_MESSAGE = "GENRE_TRANSLATION_EXISTS";
    public static final String GENRE_TRANSLATIONS_EXISTS_MESSAGE = "GENRE_TRANSLATIONS_EXISTS";
    public static final String GENRE_TRANSLATIONS_LANGUAGE_CODES_SIZE_LIMIT_MESSAGE = "GENRE_TRANSLATIONS_LANGUAGE_CODES_SIZE_LIMIT_MESSAGE";
}
