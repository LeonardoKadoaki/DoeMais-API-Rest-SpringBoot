package com.doemais.api.search;

import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static com.doemais.api.utils.Utilidades.*;

@Repository
@Transactional
public class AnuncioSearch {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Anuncio> search(String text, final int pagina, final int limite)
            throws EntidadeNaoEncontradaException {

        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);

        QueryBuilder queryBuilder =
                fullTextEntityManager.getSearchFactory()
                        .buildQueryBuilder().forEntity(Anuncio.class).get();

        List<Anuncio> results = this.fullTextWithLike(text, queryBuilder, fullTextEntityManager);

        if (results.size() == 0) {
            results = this.fullText(text, queryBuilder, fullTextEntityManager);
        }

        List<Anuncio> resultsStreamed = results.stream()
                .filter(a -> a.getStatus().getIdStatus() == StatusAnuncioEnum.EM_ANDAMENTO.getValor())
                .sorted(Comparator.comparing(Anuncio::getDataCriacao).reversed())
                .collect(Collectors.toList());

        HashMap<Integer, Integer> indexes = checkPagination(resultsStreamed.size(), pagina, limite);
        return resultsStreamed.subList(indexes.get(FIRST_INDEX), indexes.get(LAST_INDEX)); // Cria e retorna a página
    }

    private List<Anuncio> fullTextWithLike(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {

        Query query =
                queryBuilder
                        .keyword()
                        .wildcard()
                        .onFields("titulo", "descricao")
                        .matching("*" + text + "*")
                        .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Anuncio.class);
        return jpaQuery.getResultList();
    }

    private List<Anuncio> fullText(String text, QueryBuilder queryBuilder, FullTextEntityManager fullTextEntityManager) {

        Query query =
                queryBuilder
                        .keyword()
                        .onFields("titulo", "descricao")
                        .matching(text)
                        .createQuery();

        FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, Anuncio.class);
        return jpaQuery.getResultList();
    }
}
