package com.modelncode.crudpattern.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.Entity

@Entity
@JsonIgnoreProperties(['dirtyPropertyNames', 'errors', 'dirty', 'attached', 'version'])
class User {
    Long id
    String name
    String alias
    String email
}
