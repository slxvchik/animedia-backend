package dev.animedia.contentservice.domain.shared.model;

import java.util.Objects;

public abstract class BaseEntity<T> {
	protected T id;

	public T getId() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;

		if (o == null || getClass() != o.getClass()) return false;

		BaseEntity<?> that = (BaseEntity<?>) o;

		if (id == null || that.id == null) return false;

		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {
		return id != null ? Objects.hashCode(id) : System.identityHashCode(this);
	}
}
