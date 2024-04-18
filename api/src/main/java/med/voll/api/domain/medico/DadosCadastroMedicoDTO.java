package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEnderecoDTO;

//CLASSES DTO PRECISAM TER APENAS OS ATRIBUTOS BASICOS, AO DEFINI-LOS, O PROPRIO
//JAVA JA DEFINE OS METODOS GETTER E SETTERS, CONSTRUTORES, HASHCODE(), TOSTRING(), EQUALS()
//NAO POSSUEM METODOS OU ACOES QUE IRA REALIZAR
public record DadosCadastroMedicoDTO(

    @NotBlank //usado apenas para campos string
    String nome, 

    @NotBlank
    @Email //verifica se tem formato de email
    String email, 

    @NotBlank
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}") // precisa ter 4-6 char
    String crm, 

    @NotNull
    Especialidade especialidade,

    @NotNull
    @Valid //executa as validacoes da classe enderecoDTO
    DadosEnderecoDTO endereco
) {}

