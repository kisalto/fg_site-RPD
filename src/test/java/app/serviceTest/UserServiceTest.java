//package app.serviceTest;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import app.auth.User;
//import app.exception.MesmoApelido;
//import app.repository.UserRepository;
//import app.services.UserService;
//
//public class UserServiceTest {
//    
//    @InjectMocks
//    UserService userService = new UserService();
//    
//    @Mock
//    UserRepository userRepository;
//    
//    User usuarioNovo;
//
//    @BeforeEach
//    void setup() {
//        MockitoAnnotations.openMocks(this);
//        
//        usuarioNovo = new User();
//        usuarioNovo.setId(1L);
//        usuarioNovo.setApelido("Teste_da_Silva");
//        usuarioNovo.setEmail("testedasilva@gmail.com");
//        usuarioNovo.setIsMod(false);
//        usuarioNovo.setIsVet(false);
//        usuarioNovo.setSenha("123456");
//        
//        when(userRepository.findByApelido("Teste_da_Silva")).thenReturn(null);
//        when(userRepository.save(any(User.class))).thenReturn(usuarioNovo);
//    }
//    
//    @Test
//    @DisplayName("Teste criar usuario com apelido único")
//    void cenario01() {
//        String retorno = userService.save(usuarioNovo);
//        assertEquals("Usuário criado com sucesso!", retorno);
//    }
//    
//    @Test
//    @DisplayName("Teste criar usuario com apelido duplicado")
//    void cenario02() {
//        User usuarioIgual = new User();
//        usuarioIgual.setApelido("Teste_da_Silva");
//        usuarioIgual.setEmail("igual@gmail.com");
//        usuarioIgual.setIsMod(false);
//        usuarioIgual.setIsVet(false);
//        usuarioIgual.setSenha("123456");
//        
//        when(userRepository.findByApelido("Teste_da_Silva")).thenReturn(usuarioNovo);
//        
//        Exception exception = assertThrows(MesmoApelido.class, () -> {
//            userService.save(usuarioIgual);
//        });
//
//        assertEquals("Apelido ja existente", exception.getMessage());
//    }
//    
//    @Test
//    @DisplayName("Teste atualiza usuário")
//    void cenario03() {
//        usuarioNovo.setEmail("teste@gmail.com");
//        
//        String retorno = userService.update(usuarioNovo, 1L);
//        
//        assertEquals("Usuário atualizado com sucesso!", retorno);
//    }
//    
//    @Test
//    @DisplayName("Teste busca usuário por ID")
//    void cenario04() {
//        when(userRepository.findById(1L)).thenReturn(Optional.of(usuarioNovo));
//        
//        User usuarioEncontrado = userService.findById(1L);
//        
//        assertEquals(usuarioNovo, usuarioEncontrado);
//    }
//    
//    @Test
//    @DisplayName("Teste busca usuário por ID inexistente")
//    void cenario05() {
//        when(userRepository.findById(99L)).thenReturn(Optional.empty());
//        
//        User usuarioEncontrado = userService.findById(99L);
//        
//        assertEquals(null, usuarioEncontrado);
//    }
//}
