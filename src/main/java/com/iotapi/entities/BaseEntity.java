package com.iotapi.entities;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class BaseEntity {
    private String id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

    public BaseEntity() {
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    // Método para "soft delete"
    public void delete() {
        this.deletedAt = LocalDateTime.now();
    }

    // Verifica se está deletado
    public boolean isDeleted() {
        return deletedAt != null;
    }

    // Atualiza timestamp (manualmente, usado quando for em memória (quando não houver banco de dados))
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
}

/*
VERSÃO COM BANCO DE DADOS (jpa)

@MappedSuperclass
@Where(clause = "deleted_at IS NULL") // Filtro para soft delete
public abstract class BaseEntityJpa {

    @Id
    @Column(name = "id", columnDefinition = "CHAR(36)")
    private String id;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp / @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    @PrePersist
    protected void onCreate() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
*/

/*
Para a coluna 'updated_at', QUANDO HOUVER BANCO DE DADOS, usar:
@LastModifiedDate (Spring Data JPA) ou @UpdateTimestamp (Hibernate (JPA Provider))

Se não houver banco, mas se for em MEMÓRIA, chamar método manual em todos os setters
                              que não são de carga inicial (que podem ser editados):

   public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
   }

*/

