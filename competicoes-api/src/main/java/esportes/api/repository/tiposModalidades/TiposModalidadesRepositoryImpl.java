package esportes.api.repository.tiposModalidades;

import esportes.api.entity.TipoModalidade;
import esportes.api.entity.TipoModalidade_;
import esportes.api.filter.TipoModalidadeFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class TiposModalidadesRepositoryImpl implements TiposModalidadesRepositoryQuery{
    @PersistenceContext
    private EntityManager manager;
    @Override
    public Page<TipoModalidade> filtrar(TipoModalidadeFilter tipoModalidadeFilter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<TipoModalidade> criteria = builder.createQuery(TipoModalidade.class);
        Root<TipoModalidade> root = criteria.from(TipoModalidade.class);

        // Correctly passing Root<TipoModalidade> to toOrders
        List<Order> orders = QueryUtils.toOrders(pageable.getSort(), root, builder);

        Predicate[] predicates = criarRestricoes(tipoModalidadeFilter, builder, root);
        criteria.where(predicates).orderBy(orders);

        TypedQuery<TipoModalidade> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(tipoModalidadeFilter));
    }

    private Predicate[] criarRestricoes(
            TipoModalidadeFilter tipoModalidadeFilter, CriteriaBuilder builder, Root<TipoModalidade> root) {

        List<Predicate> predicates = new ArrayList<>();

        // MODALIDADE ID
		if(tipoModalidadeFilter.getId() != null) {
			predicates.add(builder.equal(root.get(TipoModalidade_.ID), tipoModalidadeFilter.getId()));
		}

        // NOME
	    if (StringUtils.hasLength(tipoModalidadeFilter.getNome())) {
	      predicates.add(
	          builder.like(
	              builder.lower(root.get(TipoModalidade_.NOME)),
	              "%" + tipoModalidadeFilter.getNome().toLowerCase() + "%"));
	    }
        return predicates.toArray(new Predicate[predicates.size()]);
    }
    private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(TipoModalidadeFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<TipoModalidade> root = criteria.from(TipoModalidade.class);

        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
