package com.example.frota.caminhao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/caminhao/auth")
@CrossOrigin("*")
public class CaminhaoControllerAuth {

    private final Set<String> CHAVES_VALIDAS = Set.of(
            "cco123",
            "azul123",
            "frota-secret-key"
    );

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private CaminhaoMapper caminhaoMapper;

    @GetMapping
    public ResponseEntity<List<AtualizacaoCaminhao>> listarTodos() {
        List<Caminhao> caminhoes = caminhaoService.procurarTodos();
        List<AtualizacaoCaminhao> dtos = caminhoes.stream()
                .map(caminhaoMapper::toAtualizacaoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtualizacaoCaminhao> buscarPorId(@PathVariable Long id) {
        return caminhaoService.procurarPorId(id)
                .map(caminhaoMapper::toAtualizacaoDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Transactional
    // 2. MÉTODO MODIFICADO
    public ResponseEntity<?> criar(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody @Valid AtualizacaoCaminhao dto) {

        if (!CHAVES_VALIDAS.contains(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"erro\":\"Chave API inválida\"}");
        }

        try {
            Caminhao caminhaoSalvo = caminhaoService.salvarOuAtualizar(dto);
            AtualizacaoCaminhao dtoSalvo = caminhaoMapper.toAtualizacaoDto(caminhaoSalvo);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Marca não encontrada\"}");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<AtualizacaoCaminhao> atualizar(@RequestBody @Valid AtualizacaoCaminhao dto) {
        if (dto.id() == null) {
            return ResponseEntity.badRequest().build(); 
        }
        try {
            Caminhao caminhaoSalvo = caminhaoService.salvarOuAtualizar(dto);
            AtualizacaoCaminhao dtoSalvo = caminhaoMapper.toAtualizacaoDto(caminhaoSalvo);
            return ResponseEntity.ok(dtoSalvo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        try {
            if (caminhaoService.procurarPorId(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            caminhaoService.apagarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /*
    private final Set<String> CHAVES_VALIDAS = Set.of(
            "cco123",
            "azul123",
            "frota-secret-key"
    );

    @Autowired
    private CaminhaoService caminhaoService;

    @Autowired
    private CaminhaoMapper caminhaoMapper;

    private ResponseEntity<?> validarChave(String apiKey) {
        if (!CHAVES_VALIDAS.contains(apiKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("{\"erro\":\"Chave API inválida\"}");
        }
        return null; // Retorna nulo se a chave for válida
    }

    @GetMapping
    public ResponseEntity<?> listarTodos(@RequestHeader("X-API-KEY") String apiKey) {
        ResponseEntity<?> erro = validarChave(apiKey);
        if (erro != null) return erro;

        List<Caminhao> caminhoes = caminhaoService.procurarTodos();
        List<AtualizacaoCaminhao> dtos = caminhoes.stream()
                .map(caminhaoMapper::toAtualizacaoDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(
            @RequestHeader("X-API-KEY") String apiKey,
            @PathVariable Long id) {
        
        ResponseEntity<?> erro = validarChave(apiKey);
        if (erro != null) return erro;

        Optional<Caminhao> caminhao = caminhaoService.procurarPorId(id);
        if (caminhao.isPresent()) {
            AtualizacaoCaminhao dto = caminhaoMapper.toAtualizacaoDto(caminhao.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> criar(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody @Valid AtualizacaoCaminhao dto) {

        ResponseEntity<?> erro = validarChave(apiKey);
        if (erro != null) return erro;

        try {
            Caminhao caminhaoSalvo = caminhaoService.salvarOuAtualizar(dto);
            AtualizacaoCaminhao dtoSalvo = caminhaoMapper.toAtualizacaoDto(caminhaoSalvo);
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoSalvo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Marca não encontrada\"}");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> atualizar(
            @RequestHeader("X-API-KEY") String apiKey,
            @RequestBody @Valid AtualizacaoCaminhao dto) {
        
        ResponseEntity<?> erro = validarChave(apiKey);
        if (erro != null) return erro;

        if (dto.id() == null) {
            return ResponseEntity.badRequest().body("{\"erro\":\"ID é obrigatório para atualização\"}");
        }
        try {
            Caminhao caminhaoSalvo = caminhaoService.salvarOuAtualizar(dto);
            AtualizacaoCaminhao dtoSalvo = caminhaoMapper.toAtualizacaoDto(caminhaoSalvo);
            return ResponseEntity.ok(dtoSalvo);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(
            @RequestHeader("X-API-KEY") String apiKey,
            @PathVariable Long id) {
        
        ResponseEntity<?> erro = validarChave(apiKey);
        if (erro != null) return erro;

        try {
            if (caminhaoService.procurarPorId(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            caminhaoService.apagarPorId(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"erro\":\"Falha ao excluir recurso\"}");
        }
    }
    */
}