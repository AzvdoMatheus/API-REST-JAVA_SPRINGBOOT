package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//precisa implementar a classe userDetailsService para o spring localizar
public class AutenticationService implements UserDetailsService{

  @Autowired
  private UsuarioRepository repository;

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      return repository.findByLogin(username);
  }
  

}
