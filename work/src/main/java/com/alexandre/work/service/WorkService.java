package com.alexandre.work.service;

import com.alexandre.work.entity.Work;
import com.alexandre.work.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    @Autowired
    private WorkRepository workRepository;

    public Work save(Work work) {
        return workRepository.save(work);
    }

    public List<Work> listAll() {
        return workRepository.findAll();
    }

    public Optional<Work> findByid(Long id) {
        return workRepository.findById(id);
    }

    public void deleteById(Long id) {
        workRepository.deleteById(id);
    }

}
