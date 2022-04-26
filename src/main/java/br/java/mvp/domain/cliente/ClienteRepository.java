package br.java.mvp.domain.cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ClienteRepository extends MongoRepository<Cliente, Long> {

    @Query(value = "{ 'nome': ?0, 'idade': ?1 }", exists = true)
    boolean existsByNomeAndIdade(String nome, Integer idade);

    @Query(value = "{ 'idade': ?0 }")
    Page<Cliente> findByIdade(Integer idade, Pageable pageable);
}
