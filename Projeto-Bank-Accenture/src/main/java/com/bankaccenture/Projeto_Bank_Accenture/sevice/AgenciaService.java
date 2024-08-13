package com.bankaccenture.Projeto_Bank_Accenture.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bankaccenture.Projeto_Bank_Accenture.commons.validacaoDeDados;
import com.bankaccenture.Projeto_Bank_Accenture.exception.CampoObrigatorioException;
import com.bankaccenture.Projeto_Bank_Accenture.model.Agencia;
import com.bankaccenture.Projeto_Bank_Accenture.repository.AgenciaRepository;

@Service
public class AgenciaService {

	@Autowired
	private AgenciaRepository agenciaRepository;
	private validacaoDeDados validacaoDeDados = new validacaoDeDados();

	@Transactional(readOnly = true)
	public List<Agencia> listarAgencias() {
		return agenciaRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Agencia listarAgenciaPorId(int id) {
		Agencia agencia = agenciaRepository.findById(id).orElse(null);
		return agencia;
	}

	@Transactional(readOnly = false)
	public Agencia cadastrarAgencia(Agencia agencia) throws CampoObrigatorioException {

		validacaoDeDados.validaCampos(agencia);
		return agenciaRepository.save(agencia);
	}

	@Transactional(readOnly = false)
	public Agencia atualizarAgencia(Agencia agencia, int id) {

		Agencia ag = this.listarAgenciaPorId(id);
		
		ag.setEndereco(agencia.getEndereco());
		ag.setNomeAgencia(agencia.getNomeAgencia());
		ag.setTelefone(agencia.getTelefone());
		ag.setIdCliente(agencia.getIdCliente());

		return agenciaRepository.save(ag);
	}

	@Transactional(readOnly = false)
	public String deletarAgenciaPorId(Agencia agencia) {
		int id = agencia.getIdAgencia();
		agenciaRepository.deleteById(agencia.getIdAgencia());
		return "Agencia de id " + id + " deletada com sucesso";
	}

}
