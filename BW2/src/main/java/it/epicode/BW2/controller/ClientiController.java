package it.epicode.BW2.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.BW2.model.BeServiceClienti;
import it.epicode.BW2.repository.ClientiRepo;
import it.epicode.BW2.service.ClientiService;

@RestController
@RequestMapping("/clienti")
@CrossOrigin
public class ClientiController {

	@Autowired
	ClientiService cs;

	@GetMapping("/sort/name")
	public List<BeServiceClienti> sortedByName() {
		return cs.sortByName();
	}

	@GetMapping("/sort/fatturato")
	public List<BeServiceClienti> sortedByFatturato() {
		return cs.sortByFatturato();
	}

	@GetMapping("/sort/date")
	public List<BeServiceClienti> sortedByDate() {
		return cs.sortByDate();
	}

	@GetMapping("/sort/lastdate")
	public List<BeServiceClienti> sortedByUltimoContatto() {
		return cs.sortByDateUltimoContatto();
	}

	@GetMapping("/sort/provincia")
	public List<BeServiceClienti> sortedByProvincia() {
		return cs.sortByProvincia();
	}

	@GetMapping("/filter/fatturato")
	public Optional<List<BeServiceClienti>> filterByFatturato(@RequestParam BigDecimal min,
			@RequestParam BigDecimal max) {
		return cs.filterByFatturato(min, max);
	}

	@GetMapping("/filter/name")
	public Optional<List<BeServiceClienti>> filterByData(@RequestParam String name) {
		return cs.filterByNome(name);
	}

	// DATA INSERIMENTO
	@GetMapping("/filter/data-inserimento-range")
	public List<BeServiceClienti> filterByDataRangeIns(@RequestParam("min") String min,
			@RequestParam("max") String max) {
		Timestamp start = Timestamp.valueOf(min + " 00:00:00");
		Timestamp end = Timestamp.valueOf(max + " 23:59:59");
		return cs.filterByDataInserimento(start, end);
	}

	@GetMapping("/filter/data-inserimento")
	public List<BeServiceClienti> filterByDataIns(@RequestParam("date") String d) {
		Timestamp start = Timestamp.valueOf(d + " 00:00:00");
		Timestamp end = Timestamp.valueOf(d + " 23:59:59");
		return cs.filterByDataInserimento(start, end);
	}

	@GetMapping("/filter/data-inserimento-year")
	public List<BeServiceClienti> filterByDataYearIns(@RequestParam("year") String y) {
		Timestamp start = Timestamp.valueOf(y + "-01-01 00:00:00");
		Timestamp end = Timestamp.valueOf(y + "-12-31 23:59:59");
		return cs.filterByDataInserimento(start, end);
	}

	// DATA ULTIMO CONTATTO
	@GetMapping("/filter/data-ultimo-range")
	public List<BeServiceClienti> filterByDataRangeUlt(@RequestParam("min") String min,
			@RequestParam("max") String max) {
		Timestamp start = Timestamp.valueOf(min + " 00:00:00");
		Timestamp end = Timestamp.valueOf(max + " 23:59:59");
		return cs.filterByDataUltimoContatto(start, end);
	}

	@GetMapping("/filter/data-ultimo")
	public List<BeServiceClienti> filterByDataUlt(@RequestParam("date") String d) {
		Timestamp start = Timestamp.valueOf(d + " 00:00:00");
		Timestamp end = Timestamp.valueOf(d + " 23:59:59");
		return cs.filterByDataUltimoContatto(start, end);
	}

	@GetMapping("/filter/data-ultimo-year")
	public List<BeServiceClienti> filterByDataYearUlt(@RequestParam("year") String y) {
		Timestamp start = Timestamp.valueOf(y + "-01-01 00:00:00");
		Timestamp end = Timestamp.valueOf(y + "-12-31 23:59:59");
		return cs.filterByDataUltimoContatto(start, end);
	}

	// SORTING DATA INSERIMENTO
	@GetMapping("/sort/data-inserimento")
	public List<BeServiceClienti> sortedByDataInserimento() {
		return cs.sortByDataInserimento();
	}

	// SORTING DATA ULTIMO
	@GetMapping("/sort/data-ultimo")
	public List<BeServiceClienti> sortedByDataUltimo() {
		return cs.sortByDataUltimoContatto();
	}

}
