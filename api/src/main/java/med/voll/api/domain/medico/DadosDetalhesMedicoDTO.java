package med.voll.api.domain.medico;

import med.voll.api.domain.endereco.EnderecoJPA;

public record DadosDetalhesMedicoDTO(Long id, String nome, String email, String crm, String telefone, Especialidade especialidade, EnderecoJPA endereco) {

  public DadosDetalhesMedicoDTO(MedicoJPA medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
  }
} 
