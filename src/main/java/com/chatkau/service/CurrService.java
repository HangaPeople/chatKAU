package com.chatkau.service;

import com.chatkau.dto.CurriculumDto;
import com.chatkau.entity.Curriculum;
import com.chatkau.entity.UserDetail;
import com.chatkau.repository.CurrRepository;
import com.chatkau.repository.UserDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CurrService {

    private final CurrRepository currRepository;
    private final UserDetailRepository userDetailRepository;

    public CurriculumDto createCurriculum(CurriculumDto curriculumDto) {
        UserDetail user = userDetailRepository.findById(curriculumDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Curriculum curr = Curriculum.builder()
                .title(curriculumDto.getTitle())
                .description(curriculumDto.getDescription())
                .user(user)
                .currCours(new ArrayList<>())
                .build();

        currRepository.save(curr);

        return CurriculumDto.fromEntity(curr);
    }

    public List<CurriculumDto> getCurriculumsByUser(Long userId) {
        List<Curriculum> currList = currRepository.findByUserId(userId);
        if (currList == null || currList.isEmpty()) {
            throw new NoSuchElementException();
        }
//        return currList.stream()
//                .map(CurriculumDto::fromEntity)
//                .collect(Collectors.toList());

        List<CurriculumDto> currDtoList = new ArrayList<>();
        for (Curriculum curr : currList) {
            currDtoList.add(CurriculumDto.fromEntity(curr));
        }
        return currDtoList;
    }

    public CurriculumDto updateCurriculum(CurriculumDto curriculumDto) {
        Curriculum curr = currRepository.findById(curriculumDto.getId())
                .orElseThrow(() -> new RuntimeException("Curriculum not found"));

        curr.setTitle(curriculumDto.getTitle());
        curr.setDescription(curriculumDto.getDescription());

        currRepository.save(curr);

        return CurriculumDto.fromEntity(curr);
    }

    public CurriculumDto deleteCurriculum(Long id) {
        Curriculum curr = currRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curriculum not found"));

        currRepository.delete(curr);

        return CurriculumDto.fromEntity(curr);
    }
}
