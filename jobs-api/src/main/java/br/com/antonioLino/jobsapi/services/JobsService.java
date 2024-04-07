package br.com.antonioLino.jobsapi.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.antonioLino.jobsapi.configuration.BeanMapper;
import br.com.antonioLino.jobsapi.entities.Jobs;
import br.com.antonioLino.jobsapi.repositorys.JobsRepository;
import br.com.antonioLino.jobsapi.web.dtos.JobsDTO;

@Service
public class JobsService {
    
    @Autowired
    private JobsRepository repository;



    // POST

    public JobsService(JobsRepository repository) {
        this.repository = repository;
    }

    public Jobs create(Jobs jobs){
        return repository.save(jobs);
    } 
    
    // GET ALL

    public List<Jobs> getAll(){
        return repository.findAll();
    }

    //GET BY ID

    public Optional<Jobs> getAllByID(Integer id){
       
        return repository.findById(id);
        
    }

    //PUT BY ID

    public JobsDTO updateByID(Integer id, JobsDTO jobs){

        if (!repository.existsById(id)) {
            return null;
        }   

        Jobs existingJobs = repository.findById(id).orElse(null);
        existingJobs.setTitle(updateByID(id, jobs).getTitle());
        existingJobs.setDescription(updateByID(id, jobs).getDescription());
        existingJobs.setSalary(updateByID(id, jobs).getSalary());

        Jobs savedJobs = repository.save(existingJobs);

        return ((ModelMapper) BeanMapper.mapper).map(savedJobs, JobsDTO.class);
    }

    // DELETE BY ID 

    public void delete(Integer id){
        repository.deleteById(id);
    }

    

   


}
