package com.github.AugustoTomaselli.ms_pagamentos.service;

import com.github.AugustoTomaselli.ms_pagamentos.dto.PagamentoDTO;
import com.github.AugustoTomaselli.ms_pagamentos.entities.Pagamento;
import com.github.AugustoTomaselli.ms_pagamentos.exceptions.ResourceNotFoundException;
import com.github.AugustoTomaselli.ms_pagamentos.repository.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PagamentoService {

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Transactional(readOnly = true)
    public List<PagamentoDTO> findAllPagamentos() {
        // Busca todos os registros do banco de dados
        List<Pagamento> pagamentos = pagamentoRepository.findAll();

        // Transforma a lista de Entidades (banco) em uma lista de DTOs (dados para a web)
        return pagamentos.stream()
                .map(PagamentoDTO::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public PagamentoDTO findPagamentoById(Long id) {
        // Tenta buscar pelo ID. Se não achar, lança o erro que você criou
        Pagamento pagamento = pagamentoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        // Retorna o dado transformado em DTO
        return new PagamentoDTO(pagamento);
    }
}