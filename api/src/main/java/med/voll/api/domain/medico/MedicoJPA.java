package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.EnderecoJPA;

@Table(name = "medicos")
@Entity(name = "MedicoJPA")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MedicoJPA {
  
  public MedicoJPA(DadosCadastroMedicoDTO infosMedico) {
    this.nome = infosMedico.nome();
    this.ativo= true;
    this.email = infosMedico.email();
    this.crm = infosMedico.crm();
    this.especialidade = infosMedico.especialidade();
    this.telefone = infosMedico.telefone();
    this.endereco = new EnderecoJPA(infosMedico.endereco());
  }

  public void atualizar(DadosEdicaoMedicoDTO dados) {

    if(dados.nome() != null) { this.nome = dados.nome(); }
    if(dados.telefone() != null) { this.telefone = dados.telefone(); }
    if(dados.endereco() != null) { this.endereco.atualizar(dados.endereco()); }
  }

  public void excluir() { this.ativo = false; }

  @Id @GeneratedValue(strategy =  GenerationType.IDENTITY) 
  private Long id;
  private String nome;
  private String email;
  private String telefone;
  private String crm;

  @Enumerated(EnumType.STRING)
  private Especialidade especialidade;

  @Embedded //utilizada para fazer composicao de classes, atributo da composicao
  private EnderecoJPA endereco;

  private boolean ativo;

}
