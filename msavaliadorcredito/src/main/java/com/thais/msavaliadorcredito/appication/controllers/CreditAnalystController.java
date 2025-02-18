package com.thais.msavaliadorcredito.appication.controllers;

import com.thais.msavaliadorcredito.domain.dto.DataEvaluationDTO;
import com.thais.msavaliadorcredito.domain.dto.ReturnEvaluationClientDTO;
import com.thais.msavaliadorcredito.domain.models.ClientStatus;
import com.thais.msavaliadorcredito.domain.services.CreditAnalystService;
import com.thais.msavaliadorcredito.exceptions.DataClientNotFoundException;
import com.thais.msavaliadorcredito.exceptions.ErrorCominucationMicroservicesException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("avaliacoes-credito")
public class CreditAnalystController {

    private final CreditAnalystService creditAnalystService;
    @GetMapping
    public String status(){
        return "ok";
    }
    @GetMapping(value="situacao-cliente", params = "cpf")
    public ResponseEntity checkStatusClient(@RequestParam("cpf")String cpf)  {
        try {
         ClientStatus clientStatus = creditAnalystService.getStatusClient(cpf);
            return ResponseEntity.ok(clientStatus);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCominucationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }

    }
    @PostMapping
    public ResponseEntity performEvaluation(@RequestBody DataEvaluationDTO data){
        //evaluation = avaliação
        try {
        ReturnEvaluationClientDTO returnEvaluationClientDTO =  creditAnalystService.makeEvaluation(data.getCpf(), data.getIncome());
        return ResponseEntity.ok(returnEvaluationClientDTO);
        } catch (DataClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (ErrorCominucationMicroservicesException e) {
            return ResponseEntity.status(HttpStatus.resolve(e.getStatus())).body(e.getMessage());
        }
    }
}
