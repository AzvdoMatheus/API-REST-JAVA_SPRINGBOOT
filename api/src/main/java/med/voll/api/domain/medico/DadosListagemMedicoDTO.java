package med.voll.api.domain.medico;

//record para devolver a funcao do controller apenas os dados que sao pedidos
public record DadosListagemMedicoDTO(Long id, String nome, String email, String crm, Especialidade especialidade) {

  public DadosListagemMedicoDTO(MedicoJPA medico) {
    this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
  }
}
