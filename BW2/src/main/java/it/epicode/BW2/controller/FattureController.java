package it.epicode.BW2.controller;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.epicode.BW2.model.BeServiceFatture;
import it.epicode.BW2.service.FattureService;

@RestController
@CrossOrigin
public class FattureController {

	@Autowired
	FattureService fs;
	
	@GetMapping("/fatture/cliente")
	public List<BeServiceFatture> filterByUtente(@RequestParam String nome) {
		return fs.findFattureByCliente(nome);
	}
	
	@GetMapping("/fatture/stato")
	public List<BeServiceFatture> filterByStato(@RequestParam String stato) {
		return fs.findFattureByStato(stato);
	}
	
	@GetMapping("/fatture/importo")
	public Optional<List<BeServiceFatture>> filterByImporto(@RequestParam BigDecimal min, @RequestParam BigDecimal max) {
		return fs.findFattureImporto(min, max);
	}
	
	@GetMapping("/fatture/anno")
	public Optional<List<BeServiceFatture>> filterByAnno(@RequestParam int anno) {
		return fs.findFattureAnno(anno);
	}
	
	// DATA
    @GetMapping("fatture/filter/data-range")
    public List<BeServiceFatture> filterByDataRangeIns(@RequestParam("min") String min, @RequestParam("max") String max ) {
        Timestamp start = Timestamp.valueOf(min + " 00:00:00");
        Timestamp end = Timestamp.valueOf(max + " 23:59:59");
        return fs.findFattureData(start, end);
    }

    @GetMapping("fatture/filter/data")
        public List<BeServiceFatture> filterByDataIns(@RequestParam("date") String d) {
        Timestamp start = Timestamp.valueOf(d + " 00:00:00");
        Timestamp end = Timestamp.valueOf(d + " 23:59:59");
        return fs.findFattureData(start, end);
    }

    @GetMapping("fatture/filter/data-year")
        public List<BeServiceFatture> filterByDataYearIns(@RequestParam("year") String y) {
        Timestamp start = Timestamp.valueOf(y + "-01-01 00:00:00");
        Timestamp end = Timestamp.valueOf(y + "-12-31 23:59:59");
        return fs.findFattureData(start, end);
    }
}
