package com.example.demo.service;

import com.example.demo.exceptionHandler.ChavePixNotFoundException;
import com.example.demo.exceptionHandler.InternalServerErrorException;
import com.example.demo.model.ChavePix;
import com.example.demo.model.TipoChave;
import com.example.demo.model.TipoConta;
import com.example.demo.model.TipoPessoa;
import com.example.demo.repository.ChavePixRepository;
import com.example.demo.request.CreateChavePixRequest;
import com.example.demo.request.UpdateChavePixRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@AutoConfigureMockMvc
@SpringBootTest
@DisplayName("ChavePixServiceTest")
public class ChavePixServiceTest {

    @MockBean
    ChavePixRepository chavePixRepository;

    @Autowired
    private ChavePixService chavePixService;

    @Test
    @DisplayName("deve cadastrar chave pix CPF")
    public void deveCadastrarChaveCPF(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("64319882059");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        chavePixService.cadastrarChavePix(mockRequest);
        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }

    @Test
    @DisplayName("deve cadastrar chave pix Celular")
    public void deveCadastrarChaveCelular(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CELULAR);
        mockRequest.setValorChave("+5521123456789");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        chavePixService.cadastrarChavePix(mockRequest);
        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }

    @Test
    @DisplayName("deve cadastrar chave pix email")
    public void deveCadastrarChaveEmail(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.EMAIL);
        mockRequest.setValorChave("teste@gmail.com");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        chavePixService.cadastrarChavePix(mockRequest);
        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }

    @Test
    @DisplayName("deve cadastrar chave pix CNPJ")
    public void deveCadastrarChaveCnpj(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CNPJ);
        mockRequest.setValorChave("23451357000191");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        chavePixService.cadastrarChavePix(mockRequest);
        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }

    @Test
    @DisplayName("deve cadastrar chave pix aleatoria")
    public void deveCadastrarChaveAleatoria(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.ALEATORIO);
        mockRequest.setValorChave("0123456789-abcdefghi-987654321-jklmn");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        chavePixService.cadastrarChavePix(mockRequest);
        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }


    @Test
    @DisplayName("Deve lançar exception se conta F tiver 5 chaves cadastradas")
    public void deveLancarExceptionSeContaFisicaTiverCincoChaves(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("64319882059");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        ArrayList<ChavePix> chavesPix = new ArrayList<>();
        for(int i = 0; i < 5; i++)
            chavesPix.add(new ChavePix());

        when(chavePixRepository.findByValorChave(any())).thenReturn(null);
        doReturn(chavesPix).when(chavePixRepository).findAllByConta(anyInt());

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Número máximo de chaves atingido");
    }

    @Test
    @DisplayName("Deve lançar exception se conta J tiver 20 chaves cadastradas")
    public void deveLancarExceptionSeContaJuridicaTiverCincoChaves(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("64319882059");
        mockRequest.setTipoPessoa(TipoPessoa.J);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        ArrayList<ChavePix> chavesPix = new ArrayList<>();
        for(int i = 0; i < 20; i++)
            chavesPix.add(new ChavePix());

        when(chavePixRepository.findByValorChave(any())).thenReturn(null);
        doReturn(chavesPix).when(chavePixRepository).findAllByConta(anyInt());

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Número máximo de chaves atingido");
    }

    @Test
    @DisplayName("deve lançar exception se o código verificador do CPF não for válido")
    public void deveLancarExceptionCpfComVerificadorInválido(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("64319882051");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("deve lançar exception se tiver letrar no meio do CPF")
    public void deveLancarExceptionCpfComLetras(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("643198820f1");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("deve lançar exception se CPF tiver pontuação")
    public void deveLancarExceptionCpfComPontuacao(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("643.198.820-59");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("Deve lançar exception se já tiver o valor cadastrado")
    public void deveLancarExceptionSeJaTiverValorCadastrado(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CPF);
        mockRequest.setValorChave("64319882059");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        when(chavePixRepository.findByValorChave(any())).thenReturn(new ChavePix());

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Já existe uma chave criada com este valor");
    }

    @Test
    @DisplayName("Deve lançar exception número não estiver completo com cod pais + DDD + 9 digitos")
    public void deveLancarExceptionSeNumeroNaoEstiverCompleto(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CELULAR);
        mockRequest.setValorChave("+552123456789");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("Deve lançar exception se email não tiver @")
    public void deveLancarExceptionEmailSemArroba(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.EMAIL);
        mockRequest.setValorChave("testegmail.com");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("Deve lançar exception se CNPJ tiver digito verificador inválido")
    public void deveLancarExceptionCnpjVerificadorInvalido(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.CNPJ);
        mockRequest.setValorChave("23451357000190");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("Deve lancar exception se chave aleatorio +36 digitos")
    public void deveLancarExceptionChaveAleatoriaMaisTrintaSeisDigitos(){
        CreateChavePixRequest mockRequest = new CreateChavePixRequest();
        mockRequest.setTipoChave(TipoChave.ALEATORIO);
        mockRequest.setValorChave("0123456789012345678901234567891234567");
        mockRequest.setTipoPessoa(TipoPessoa.F);
        mockRequest.setNomeCorrentista("mock");
        mockRequest.setNumeroAgencia(1234);
        mockRequest.setNumeroConta(12345678);
        mockRequest.setTipoConta(TipoConta.CORRENTE);

        assertThatThrownBy(() -> chavePixService.cadastrarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Formato de chave inválido");
    }

    @Test
    @DisplayName("Deve lançar exception caso id não encontrado para update")
    public void deveLancarExceptionIdNaoEncontradoUpdate(){
        UpdateChavePixRequest mockRequest = new UpdateChavePixRequest();
        mockRequest.setId(UUID.randomUUID());

        when(chavePixRepository.findById(mockRequest.getId())).thenReturn(null);

        assertThatThrownBy(() -> chavePixService.atualizarChavePix(mockRequest))
                .isInstanceOf(ChavePixNotFoundException.class)
                .hasMessage("Chave informada não encontrada");
    }

    @Test
    @DisplayName("Deve lançar exception tentativa update com chave inativa")
    public void deveLancarExceptionUpdateChaveInativa(){
        UpdateChavePixRequest mockRequest = new UpdateChavePixRequest();
        mockRequest.setId(UUID.randomUUID());

        when(chavePixRepository.findById(mockRequest.getId())).thenReturn(Optional.of(new ChavePix()));

        assertThatThrownBy(() -> chavePixService.atualizarChavePix(mockRequest))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Não foi possível atualizar. Chave Inativa!");
    }

    @Test
    @DisplayName("Deve atualizar chave pix")
    public void deveAtualizarChavepix(){
        UpdateChavePixRequest mockRequest = new UpdateChavePixRequest();
        mockRequest.setId(UUID.randomUUID());
        mockRequest.setValorChave("teste@gmail.com");
        mockRequest.setTipoChave(TipoChave.EMAIL);

        ChavePix optionalChavePix = new ChavePix();
        optionalChavePix.setAtiva(true);

        when(chavePixRepository.findById(mockRequest.getId())).thenReturn(Optional.of(optionalChavePix));

        chavePixService.atualizarChavePix(mockRequest);

        verify(chavePixRepository, Mockito.times(1)).save(ArgumentMatchers.any(ChavePix.class));
    }

    @Test
    @DisplayName("Deve lançar exception se chave não encoontrada ao desativar")
    public void deveLancarExceptionChaveNaoEncontradaInativacao(){
        when( chavePixRepository.findById(any())).thenReturn(null);

        assertThatThrownBy(() -> chavePixService.inativarChave(UUID.randomUUID()))
                .isInstanceOf(ChavePixNotFoundException.class)
                .hasMessage("Chave informada não encontrada");
    }

    @Test
    @DisplayName("Deve lançar exception se chave já estiver inativa")
    public void deveLancarExceptionChaveInativaInativacao(){
        ChavePix optionalChavePix = new ChavePix();

        when(chavePixRepository.findById(any())).thenReturn(Optional.of(optionalChavePix));
        assertThatThrownBy(() -> chavePixService.inativarChave(UUID.randomUUID()))
                .isInstanceOf(InternalServerErrorException.class)
                .hasMessage("Esta chave já está desativada");
    }

    @Test
    @DisplayName("Deve inativar chave")
    public void deveInativarChave(){
        ChavePix optionalChavePix = new ChavePix();
        optionalChavePix.setAtiva(true);

        when(chavePixRepository.findById(any())).thenReturn(Optional.of(optionalChavePix));

        chavePixService.inativarChave(UUID.randomUUID());

        assertEquals(false, optionalChavePix.isAtiva());
    }
}
