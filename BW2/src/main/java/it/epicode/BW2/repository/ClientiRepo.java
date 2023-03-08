package it.epicode.BW2.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.epicode.BW2.model.BeServiceClienti;

@Repository
public interface ClientiRepo extends JpaRepository<BeServiceClienti, Long>, JpaSpecificationExecutor<BeServiceClienti> {
	
	@Query( nativeQuery = true,
			value = "SELECT * FROM be_service_clienti c ORDER BY c.nome_contatto ASC"
			)
	List<BeServiceClienti> sortByNomeContatto();
	
	@Query( nativeQuery = true,
			value = "SELECT * "
					+ "FROM be_service_clienti AS c "
					+ "INNER JOIN be_service_indirizzi AS i ON c.indirizzo_sede_legale_id = i.id "
					+ "INNER JOIN be_service_comuni AS co ON i.comune_id = co.id "
					+ "INNER JOIN be_service_province AS p ON co.provincia_id = p.id "
					+ "ORDER BY p.nome ASC"
			)
	List<BeServiceClienti> sortByNomeProvincia();
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM be_service_clienti AS c "
					+ "WHERE c.fatturato_annuale > :m AND c.fatturato_annuale < :c "
			)
	Optional<List<BeServiceClienti>> findAllByFatturatoAnnuale(@Param("m") BigDecimal min, @Param("c") BigDecimal max);
	
	@Query(
			nativeQuery = true,
			value = "SELECT * FROM be_service_clienti AS c WHERE c.nome_contatto LIKE :m"+"%"
			)
	Optional<List<BeServiceClienti>> findAllByNomeContattoContaining(@Param("m") String nome);
	
	Optional<List<BeServiceClienti>> findAllByDataInserimentoBetween(Date from, Date to);
	
	 // DATA INSERIMENTO
    List<BeServiceClienti> findByDataInserimentoBetween(Timestamp start, Timestamp end);

    // DATA ULTIMO CONTATTO
    List<BeServiceClienti> findByDataUltimoContattoBetween(Timestamp start, Timestamp end);

    // SORTING DATA INSERIMENTO
    List<BeServiceClienti> findByOrderByDataInserimentoAsc();

    // SORTING DATA ULTIMO
    List<BeServiceClienti> findByOrderByDataUltimoContattoAsc();
}
