package com.gestioneleves.api.service;

import com.gestioneleves.api.dto.AppUserDTO;
import com.gestioneleves.api.entity.AppUser;
import com.gestioneleves.api.repository.AppUserRepository;
import com.gestioneleves.api.service.mapper.AppUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AppUserService {

    private final AppUserRepository repository;
    private final AppUserMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public List<AppUserDTO> findAll() {
        return repository.findAll()//List<AppUser> user = repository.findAll();
                         .stream()           //List<AppUserDTO> dtos = new ArrayList<>();
                         .map(mapper::toDto)           //for (AppUser u : user) {
                                    //    dtos.add(mapper.toDto(u));
                         .collect(Collectors.
                         toList());           //} return dtos;
                
    }

    public AppUserDTO findById(Long id) {
        AppUser user = repository.findById(id)//On va chercher l'utilisateur par son id
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));//si on ne trouve pas l'utilisateur, on lance une exception
        return mapper.toDto(user);//on convertit l'entité en DTO et on la retourne
    }

    public AppUserDTO create(AppUserDTO dto) {
        AppUser entity = mapper.toEntity(dto);//on recupere la dto user que l'on convertit en entité
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));//on lui applique le password que l'on cripte
        AppUser saved = repository.save(entity);//on on fait appelle methode save du repository pour enregistrer l'utilisateur dans la BDD
        return mapper.toDto(saved);//On reconvertit en dto le  user enregistrer et on le retourne
    }

    public List <AppUserDTO> findByChildren(Long id){
        return repository.findByChildrenId(id)
                .stream()
                .map(mapper::toDto)
                .collect(Collectors
                .toList());
    }

    public AppUserDTO findByClassGroup(Long id) {
        AppUser user = repository.findByClassGroupId(id)
                .orElseThrow(() -> new RuntimeException("User non trouvé"));
        return mapper.toDto(user);
    }


    public List<AppUserDTO> getTeachersByTeachingIds(List<Long> teachingIds) {
    return repository.findByTeachingId(teachingIds)
            .stream()
            .map(mapper::toDto)
            .toList();
    }

    
}
