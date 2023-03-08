package it.epicode.BW2;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import antlr.collections.List;
import it.epicode.BW2.model.BeServiceClienti;
import it.epicode.BW2.model.BeServiceIndirizzi;
import it.epicode.BW2.model.BeServiceStatoFattura;
import it.epicode.BW2.repository.ClientiRepo;
import it.epicode.BW2.service.ClientiService;
import it.epicode.BW2.service.FattureService;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfiguration
@WebAppConfiguration
public class EpicEnergyServiceTest {

	BeServiceIndirizzi indirizzo1 = new BeServiceIndirizzi();
	BeServiceIndirizzi indirizzo2 = new BeServiceIndirizzi();
	BeServiceClienti cliente1 = new BeServiceClienti();
	BeServiceClienti cliente2 = new BeServiceClienti();
	
	@Resource
	@Autowired
	ClientiService cs;
	
	@Resource
	@Autowired
	FattureService fs;
	
	@Before
	public void setUp() {
		
		indirizzo1.setCap("00123");
		indirizzo1.setCivico("1");
		indirizzo1.setLocalita("Roma");
		indirizzo1.setVia("Papuasia");
		
		indirizzo2.setCap("00124");
		indirizzo2.setCivico("2");
		indirizzo2.setLocalita("Roma");
		indirizzo2.setVia("Archimede");
		
		cliente1.setCognomeContatto("Lo Gatto");
		cliente1.setNomeContatto("Sergio");
		cliente1.setEmail("s@email.com");
		cliente1.setPec("s@pec.it");
		cliente1.setBeServiceIndirizzi1(indirizzo1);
		cliente1.setBeServiceIndirizzi2(indirizzo1);

		cliente2.setCognomeContatto("Berlangieri");
		cliente2.setNomeContatto("Mariagrazia");
		cliente2.setEmail("b@email.com");
		cliente2.setPec("b@pec.it");
		cliente2.setBeServiceIndirizzi1(indirizzo1);
		cliente2.setBeServiceIndirizzi2(indirizzo2);
	}
	
	@Test
	@DisplayName("Verifica dei dati nel Database dopo il run")
	public void testDataAvailability() {
		assertNotNull(cs.findClients());
		assertNotNull(fs.findFattureAnno(2020));
	}
	
	@Test
	@DisplayName("Test Dei Metodi del ClientService")
	public void testClientService() {
		assertTrue(cs.filterByNome("Quirino").get().size()==1);
		assertFalse(cs.filterByNome("Quirino").get().size()==0);
		assertTrue(cs.filterByDataInserimento(Timestamp.valueOf("2020-12-30 16:34:26.662"), Timestamp.valueOf("2021-03-05 14:06:43.709")).size()==9);
		assertFalse(cs.filterByDataInserimento(Timestamp.valueOf("2020-12-30 16:34:26.662"), Timestamp.valueOf("2021-03-05 14:06:43.709")).size()==10);
		assertTrue(cs.sortByDate().get(0).getDataInserimento().before(cs.sortByDate().get(1).getDataInserimento()));
		assertFalse(cs.sortByDate().get(1).getDataInserimento().before(cs.sortByDate().get(0).getDataInserimento()));
		assertTrue(cs.sortByFatturato().get(0).getFatturatoAnnuale().shortValue() < cs.sortByFatturato().get(1).getFatturatoAnnuale().shortValue());
		assertFalse(cs.sortByFatturato().get(0).getFatturatoAnnuale().shortValue() > cs.sortByFatturato().get(1).getFatturatoAnnuale().shortValue());
	}
	
	@Test
	@DisplayName("Test Dei Metodi del FattureService")
	public void testFattureService() {
		assertTrue(fs.findFattureAnno(2020).get().size()==4013);
		assertFalse(fs.findFattureAnno(2020).get().size()>4013);
		assertTrue(fs.findFattureByCliente("Quirino").size()==1);
		assertFalse(fs.findFattureByCliente("Quirino").size()>1);
		assertTrue(fs.findFattureImporto(new BigDecimal(3244.92),  new BigDecimal(3544.92)).get().size()==372);
		assertFalse(fs.findFattureImporto(new BigDecimal(3244.92),  new BigDecimal(3544.92)).get().size()==373);
		assertTrue(fs.findFattureData(Timestamp.valueOf("2020-12-30 16:34:26.662"), Timestamp.valueOf("2021-03-05 14:06:43.709")).size()==1);
	}
	
	
	
	@Test
	public void testSameAddress() {
		assertTrue(cliente1.getBeServiceIndirizzi1().equals(cliente1.getBeServiceIndirizzi2()));
	}
	@Test
	public void testDifferentAddress() {
		assertFalse(cliente2.getBeServiceIndirizzi1().equals(cliente2.getBeServiceIndirizzi2()));
	}

	@Test
	public void test() {
		assertTrue(true);
	}

}
