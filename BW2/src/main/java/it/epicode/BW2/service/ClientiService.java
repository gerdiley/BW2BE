package it.epicode.BW2.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import it.epicode.BW2.model.BeServiceClienti;
import it.epicode.BW2.repository.ClientiRepo;

@Service
public class ClientiService {
	
	@Autowired
	ClientiRepo cr;
	
	public List<BeServiceClienti> sortByName(){
		return cr.sortByNomeContatto();
	}
	
	public List<BeServiceClienti> findClients(){
		return cr.findAll();
	}
	
	public List<BeServiceClienti> sortByFatturato(){
		return cr.findAll(Sort.by(Sort.Direction.ASC, "fatturatoAnnuale"));
	}
	public List<BeServiceClienti> sortByDate(){
		return cr.findAll(Sort.by(Sort.Direction.ASC, "dataInserimento"));
	}
	public List<BeServiceClienti> sortByDateUltimoContatto(){
		return cr.findAll(Sort.by(Sort.Direction.ASC, "dataUltimoContatto"));
	}

	public List<BeServiceClienti> sortByProvincia() {
		return cr.sortByNomeProvincia();
	}
	
	public List<BeServiceClienti> sortByDataInserimento() {
		return cr.findByOrderByDataInserimentoAsc();
	}
	public List<BeServiceClienti> sortByDataUltimoContatto() {
		return cr.findByOrderByDataUltimoContattoAsc();
	}
	
	//FILTER
	
	public Optional<List<BeServiceClienti>> filterByFatturato(BigDecimal min, BigDecimal max){
		return cr.findAllByFatturatoAnnuale(min, max);
	}
	public Optional<List<BeServiceClienti>> filterByNome(String nome){
		return cr.findAllByNomeContattoContaining(nome);
	}
	
	public List<BeServiceClienti> filterByDataInserimento(Timestamp start, Timestamp end){
	     return cr.findByDataInserimentoBetween(start, end);
	}
	
	public List<BeServiceClienti> filterByDataUltimoContatto(Timestamp start, Timestamp end){
		return cr.findByDataUltimoContattoBetween(start, end);
	}
	
}
