/*
 * This file is generated by jOOQ.
 */
package supplement.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * サプリメントテーブル
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Supplements implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer id;
    private final String name;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Supplements(Supplements value) {
        this.id = value.id;
        this.name = value.name;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public Supplements(
        Integer id,
        String name,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Getter for <code>public.supplements.id</code>. サプリメントID
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Getter for <code>public.supplements.name</code>. サプリメント名
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter for <code>public.supplements.created_at</code>.
     */
    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Getter for <code>public.supplements.updated_at</code>.
     */
    public LocalDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Supplements other = (Supplements) obj;
        if (this.id == null) {
            if (other.id != null)
                return false;
        }
        else if (!this.id.equals(other.id))
            return false;
        if (this.name == null) {
            if (other.name != null)
                return false;
        }
        else if (!this.name.equals(other.name))
            return false;
        if (this.createdAt == null) {
            if (other.createdAt != null)
                return false;
        }
        else if (!this.createdAt.equals(other.createdAt))
            return false;
        if (this.updatedAt == null) {
            if (other.updatedAt != null)
                return false;
        }
        else if (!this.updatedAt.equals(other.updatedAt))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Supplements (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}
