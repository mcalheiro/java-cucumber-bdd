package br.com.alura.leilao.acceptance.steps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class PropondoLanceSteps {
	
	private Lance lance;
	private Leilao leilao;
	private ArrayList<Lance> lances;

	@Before
	public void setup() {
		this.lances = new ArrayList<Lance>();
		leilao = new Leilao("Tablet XPTO");
	}
	
	@After
	public void teardown() {
	
	}
	
	@Dado("um lance valido")
	public void um_lance_valido() {
		Usuario usuario = new Usuario("fulano");
	    lance= new Lance(usuario, BigDecimal.TEN);
	}

	@Quando("propoe ao leilao")
	public void propoe_ao_leilao() {
	    leilao.propoe(lance);
	}
	
	@Entao("o lance eh aceito")
	public void o_lance_eh_aceito() {
	    Assert.assertEquals(1, leilao.getLances().size());
	    Assert.assertEquals(BigDecimal.TEN, leilao.getLances().get(0).getValor());
	}
	
	@Dado("um lance de {double} reais do usuario {string}")
	public void um_lance_de_reais_do_usuario_fulano(Double valor, String nomeUsuario) {
	    Lance lance = new Lance(new Usuario(nomeUsuario), new BigDecimal(valor));
	    lances.add(lance);
	}

	@Quando("propoe varios lances ao leilao")
	public void propoe_varios_lances_ao_leilao() {
	   this.lances.forEach(lance -> leilao.propoe(lance));
	}
	
	@Entao("os lances sao aceitos")
	public void os_lances_sao_aceitos() {
	    Assert.assertEquals(this.lances.size(), leilao.getLances().size());
	    Assert.assertEquals(this.lances.get(0).getValor(), leilao.getLances().get(0).getValor());
	    Assert.assertEquals(this.lances.get(1).getValor(), leilao.getLances().get(1).getValor());

	}
	
	@Dado("um lance de {double} reais e do usuario {string}")
	public void um_lance_de_reais(Double valor, String usuario) {
	    this.lance = new Lance(new BigDecimal(valor));
	}
	
	@Entao("o lance nao eh aceito")
	public void o_lance_nao_eh_aceito() {
		Assert.assertEquals(0, leilao.getLances().size());
	}
	
	@Dado("dois lances")
	public void dois_lances(DataTable dataTable) {
		List<Map<String, String>> valores = dataTable.asMaps();
		
		for (Map<String, String> mapa: valores) {

			String valor = mapa.get("valor");
			String nome = mapa.get("usuario");
			Lance lance = new Lance(new Usuario(nome), new BigDecimal(valor));
			lances.add(lance);
		}
	}

	
	@Entao("o segundo lance nao eh aceito")
	public void o_segundo_lance_nao_eh_aceito() {
	    Assert.assertEquals(1, leilao.getLances().size());
	    Assert.assertEquals(this.lances.get(0).getValor(), leilao.getLances().get(0).getValor());
	}


}
