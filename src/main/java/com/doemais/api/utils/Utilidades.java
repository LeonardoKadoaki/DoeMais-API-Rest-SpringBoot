package com.doemais.api.utils;

import com.doemais.api.enums.StatusAnuncioEnum;
import com.doemais.api.exception.EntidadeNaoEncontradaException;
import com.doemais.api.models.Anuncio;
import com.doemais.api.models.StatusAnuncio;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe com métodos de utilidades diversas, usados em qualquer parte do projeto.
 */
public class Utilidades {

    //* Constantes utilizadas em checkPagination
    public static final int FIRST_INDEX = 0;
    public static final int LAST_INDEX = 1;
    //*

    /**
     * Verifica se é possível paginar o resultSet retornado pelo Lucene, com os parâmetros informados,
     * retornando os índices inicial e final da página em caso positivo. Esses índices são usados para
     * criar a página.
     * @author Rodrigo Gasperin
     * @param resultsSize tamanho do resultSet.
     * @param pagina página a ser consultada, dentro do resultSet.
     * @param limite número de objetos por página.
     * @return em caso de sucesso, os índices do primeiro e do último objeto da página são retornados.
     * @throws EntidadeNaoEncontradaException em caso de erro, essa exception é lançada.
     */
    public static HashMap<Integer, Integer> checkPagination(int resultsSize, int pagina, int limite)
            throws EntidadeNaoEncontradaException {

        if (resultsSize == 0) {
            throw new EntidadeNaoEncontradaException("A busca não retornou nenhum resultado");
        }

        if (limite < 1) {
            throw new ArrayIndexOutOfBoundsException("O limite deve ser um valor estritamente positivo");
        }

        if (limite > resultsSize) {
            limite = resultsSize;
        }

        int lastPage = (int)Math.ceil((double)resultsSize / (double)limite);

        if (pagina < 1 || pagina > lastPage) {
            throw new ArrayIndexOutOfBoundsException(
                    String.format("A página informada (%d) não está no intervalo [%d,%d]", pagina, 1, lastPage));
        }

        HashMap<Integer, Integer> indexes = new HashMap<>();
        indexes.put(FIRST_INDEX, (pagina-1)*limite);
        indexes.put(LAST_INDEX, Math.min(((pagina-1)*limite)+limite, resultsSize));

        return indexes;
    }

    /**
     * Recebe um anuncio como parâmetro e retorna um fork desse anúncio
     * @author Rodrigo Gasperin
     * @param original anúncio para forkar
     * @return um fork do anuncio recebido
     */
    public static Anuncio forkAnuncio(Anuncio original) {

        StatusAnuncio status = new StatusAnuncio();
        status.setIdStatus(StatusAnuncioEnum.EM_ANDAMENTO.getValor());

        Anuncio fork = new Anuncio();

        //fork.setIdAnuncio(); // GeneratedValue
        fork.setNextIdAnuncio(original.getNextIdAnuncio()); // Sempre zero
        fork.setTitulo(original.getTitulo());
        fork.setDescricao(original.getDescricao());
        fork.setCategoria(original.getCategoria());
        fork.setDataCriacao(original.getDataCriacao());
        fork.setDataExpiracao(original.getDataExpiracao());
        //fork.setDataFim(); // Somente anuncios concluídos
        //fork.setNotaAvaliacao(); // Somente anuncios concluídos
        //fork.setIdAvaliador(); // Somente anuncios concluídos
        fork.setQtdeItens(original.getQtdeItens() - 1); // Acabamos de doar um item!
        fork.setStatus(status);
        fork.setUsuarioAnunciante(original.getUsuarioAnunciante());
        //fork.setIdDonatario(); // Somente anuncios concluídos
        fork.setFotos(new ArrayList<>(original.getFotos())); // Está correto??
        fork.setCategoriaUsuarioAnunciante(original.getCategoriaUsuarioAnunciante()); // Sempre null

        return fork;
    }
}
