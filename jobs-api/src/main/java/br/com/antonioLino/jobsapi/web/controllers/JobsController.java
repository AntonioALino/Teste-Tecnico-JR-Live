package br.com.antonioLino.jobsapi.web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.antonioLino.jobsapi.entities.Jobs;
import br.com.antonioLino.jobsapi.services.JobsService;
import br.com.antonioLino.jobsapi.web.dtos.JobsDTO;
import br.com.antonioLino.jobsapi.web.mappers.JobsMapper;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobsController {
    
    
    private JobsService service;

    

    public JobsController(JobsService service) {
        this.service = service;
    }

    @PostMapping()
    public ResponseEntity<Jobs> create(Jobs jobs){
        Jobs job = service.create(jobs);
        return ResponseEntity.ok(job);
    }

    @GetMapping()
    public ResponseEntity<List<JobsDTO>> findAll(){
        List<Jobs> job = service.getAll();
        return ResponseEntity.status(HttpStatus.FOUND).body(JobsMapper.toListDTO(job));
    }   

    @GetMapping("/{id}")
    public ResponseEntity<JobsDTO> findByID(@PathVariable Integer id){
        Optional<Jobs> job = service.getAllByID(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(JobsMapper.getJobsDTOById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobsDTO> updateByID(@PathVariable Integer id, @RequestBody JobsDTO jobs){
        JobsDTO job = service.updateByID(id, jobs);
        return ResponseEntity.ok(job);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <Void> deleteByID(@RequestParam Integer idDelete){
        service.delete(idDelete);
        return ResponseEntity.noContent().build();
    }


}
