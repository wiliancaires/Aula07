package br.sceweb.teste;

import static org.junit.Assert.*;

import org.joda.time.DateTime;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import br.sceweb.modelo.Convenio;
import br.sceweb.modelo.ConvenioDAO;
import br.sceweb.modelo.Empresa;
import br.sceweb.modelo.EmpresaDAO;

public class UC05CadastrarConvenio2 {

	static ConvenioDAO convenioDAO;
	static Convenio convenio;
	static Convenio novoConvenio;
	static EmpresaDAO empresaDAO;
	static Empresa empresa;
	static String cnpj;
	static String dataInicio;
	static String dataTermino;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		/*
		 * empresaDAO = new EmpresaDAO(); empresa = new Empresa();
		 * empresa.setNomeDaEmpresa("empresa x");
		 * empresa.setCnpj("81965361000174"); empresa.setNomeFantasia(
		 * "empresa x"); empresa.setEndereco("rua taquari");
		 * empresa.setTelefone("2222"); empresaDAO.adiciona(empresa);
		 */
		convenioDAO = new ConvenioDAO();
		convenio = new Convenio("81965361000174", "03/05/2016", "20/05/2016");
	}

	// Verificar o comportamento do sistema na inclusão de um convênio com
	// sucesso.
	@Test
	public void CT01UC05_Cadastrar_Convenio_com_sucesso() {
		convenio.setDataInicio(dataInicio);
		convenio.setDataTermino(dataTermino);
		// assertEquals(1, convenioDAO.adiciona(convenio));
		assertTrue(dataInicio.equals(convenio.getDataInicio()));
		assertTrue(dataTermino.equals(convenio.getDataTermino()));
	}

	// Verificar o comportamento do sistema na inclusão de um convênio com CNPJ
	// não cadastrado.
	@Test
	public void CT02UC05_Cadastrar_Convenio_CPNJ_nao_cadastrado() {
		cnpj = "81965361000175";
		convenio.setCNPJ(cnpj);
	}

	// Verificar o comportamento do sistema na inclusão de um convênio com CNPJ
	// inválido.
	@Test(expected = IllegalArgumentException.class)
	public void CT03UC05_Cadastrar_Convenio_CPNJ_invalido() {
		cnpj = "11111";
		convenio.setCNPJ(cnpj);
	}

	// Testar o comportamento do sistema na inclusão de um convênio que já esta
	// cadastrado na Data Inicio
	@Test
	public void CT04UC05_Cadastrar_Convenio_Ja_Cadastrado_DTI() {
		convenioDAO.adiciona(convenio);
		convenioDAO.adiciona(convenio);
		// assertEquals(0, convenioDAO.adiciona(convenio));
	}

	// Testar o comportamento do sistema na inclusão de um convênio que já esta
	// cadastrado na Data Fim
	@Test
	public void CT05UC05_Cadastrar_Convenio_Ja_Cadastrado_DTF() {
		convenioDAO.adiciona(convenio);
		convenioDAO.adiciona(convenio);
		// assertEquals(0, convenioDAO.adiciona(convenio));
	}

	// Testar o comportamento do sistema na inclusão de um convênio com Data
	// Inicio maior que Data Fim
	@Test
	public void CT06UC05_Cadastrar_Convenio_DTI_maior_DTF() {
		convenio.setDataInicio("31/12/2016");
		convenio.setDataTermino("21/10/2016");
	}

	// Testar o comportamento do sistema na inclusão de um convênio
	@Test
	public void CT07UC05_Cadastrar_Convenio_DTI_formato_invalido() {
		assertFalse(convenio.validaData("42/04/2015"));
	}

	// Testar o comportamento do sistema na inclusão de um convênio
	@Test
	public void CT08UC05_Cadastrar_Convenio_DTF_formato_invalido() {
		assertFalse(convenio.validaData("42/04/2015"));
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

}
