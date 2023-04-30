package com.example.data.mapper

interface EntityMapper<Domain, Data> {
  fun mapToDomain(entity: Data): Domain
}
