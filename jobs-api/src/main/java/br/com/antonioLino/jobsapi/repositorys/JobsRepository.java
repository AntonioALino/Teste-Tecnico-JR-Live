package br.com.antonioLino.jobsapi.repositorys;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.antonioLino.jobsapi.entities.Jobs;

public interface JobsRepository extends JpaRepository<Jobs, Integer>{
    
}
