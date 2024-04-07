package br.com.antonioLino.jobsapi.web.mappers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.antonioLino.jobsapi.configuration.BeanMapper;
import br.com.antonioLino.jobsapi.entities.Jobs;
import br.com.antonioLino.jobsapi.services.JobsService;
import br.com.antonioLino.jobsapi.web.dtos.JobsDTO;

@Service
public class JobsMapper {

    @Autowired
    public JobsService jobsService;

    @Autowired
    public static Jobs jobs(JobsDTO dto){
        new BeanMapper();
        return BeanMapper.mapper().map(dto, Jobs.class);
    }

    @Autowired
    public static JobsDTO jobsDTO (Jobs jobs){
        String title = jobs.getTitle();
        String description = jobs.getDescription();
        BigDecimal salary = jobs.getSalary();
        PropertyMap<Jobs, JobsDTO> pros = new PropertyMap<Jobs,JobsDTO>() {
            @Override
            protected void configure(){
                map().setTitle(title);
                map().setDescription(description);
                map().setSalary(salary);;
            }
        };

        TypeMap<JobsDTO, Jobs> typeMap = BeanMapper.mapper().createTypeMap(JobsDTO.class, Jobs.class);
        typeMap.addMapping(JobsDTO::getTitle, Jobs::setTitle);
        typeMap.addMapping(JobsDTO::getDescription, Jobs::setDescription);
        typeMap.addMapping(JobsDTO::getSalary, Jobs::setSalary);
        
        return BeanMapper.mapper().map(Jobs.class, JobsDTO.class);
    }

    public static List<JobsDTO> toListDTO (List<Jobs> jobs){
        return jobs.stream().map(job -> jobsDTO(job)).collect(Collectors.toList());
    }
    
    public static JobsDTO getJobsDTOById(Integer id) {
        Optional<Jobs> optionalJobs = JobsService.getAllByID(id);
        if (optionalJobs.isPresent()) {
            Jobs jobs = optionalJobs.get();
            return ((ModelMapper) BeanMapper.mapper).map(jobs, JobsDTO.class);
        } else {
            // Tratar o caso em que o objeto Jobs não é encontrado pelo ID
            return null; 
        }
    }

}
