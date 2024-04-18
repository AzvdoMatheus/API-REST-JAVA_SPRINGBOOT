package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.DadosCadastroMedicoDTO;
import med.voll.api.domain.medico.DadosDetalhesMedicoDTO;
import med.voll.api.domain.medico.DadosEdicaoMedicoDTO;
import med.voll.api.domain.medico.DadosListagemMedicoDTO;
import med.voll.api.domain.medico.MedicoJPA;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("/medicos")
public class medicosController {

  @Autowired //injecao de dependencia
  private MedicoRepository repository;
  
  @PostMapping
  @Transactional //transacao para o banco de dados
  //@Valid --> pede para executar as validacoes
  public ResponseEntity<DadosDetalhesMedicoDTO> cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO infosMedico, UriComponentsBuilder uriBuilder) {
    var medico = new MedicoJPA(infosMedico);
    repository.save(medico);

    var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
    
    return ResponseEntity.created(uri).body(new DadosDetalhesMedicoDTO(medico));
  }

  @GetMapping
  //objeto Pageable permite que defina um limite de requisicoes por pagina
  public ResponseEntity <Page<DadosListagemMedicoDTO>> listarMedicos(@PageableDefault(size=10, sort={"nome"})Pageable paginacao) {
    var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDTO::new);
    return ResponseEntity.ok(page);
  }

  @Transactional
  @PutMapping
  public ResponseEntity<DadosDetalhesMedicoDTO> atualizarDados(@RequestBody @Valid DadosEdicaoMedicoDTO infosMedico) {
    var medico = repository.getReferenceById(infosMedico.id());
    medico.atualizar(infosMedico);

    return ResponseEntity.ok(new DadosDetalhesMedicoDTO(medico));
  }

  @Transactional
  @DeleteMapping("/{id}") //parametro dinamico
  //@PathVariable é usado para obter os parametros da url, no caso id
  public ResponseEntity<Void> excluirMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);
    medico.excluir();

    return ResponseEntity.noContent().build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<DadosDetalhesMedicoDTO> detalharMedico(@PathVariable Long id) {
    var medico = repository.getReferenceById(id);

    return ResponseEntity.ok(new DadosDetalhesMedicoDTO(medico));
  }

}
