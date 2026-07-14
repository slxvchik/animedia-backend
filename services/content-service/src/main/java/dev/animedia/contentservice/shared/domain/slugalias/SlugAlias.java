package dev.animedia.contentservice.shared.domain.slugalias;

import java.util.Objects;
import java.util.regex.Pattern;

public class SlugAlias {
	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");
	private String value;

	public SlugAlias(String value) {
		validate(value);
		this.value = value;
	}

	private void validate(String value) {
		if (value == null || !ALIAS_PATTERN.matcher(value).matches()) {
			throw new SlugAliasPatternException(value);
		}
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		validate(value);
		this.value = value;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass()) return false;
		SlugAlias slugAlias = (SlugAlias) o;
		return Objects.equals(value, slugAlias.value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(value);
	}
}
