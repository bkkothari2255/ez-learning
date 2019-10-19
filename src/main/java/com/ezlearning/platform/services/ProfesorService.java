package com.ezlearning.platform.services;

import com.ezlearning.platform.model.Curso;
import com.ezlearning.platform.model.Profesor;
import com.ezlearning.platform.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProfesorService implements GenericService<Profesor, Long> {

    List<Profesor> profesores = new ArrayList<>(
            Arrays.asList(new Profesor(new Usuario(1L, "Esteban", "Fernandez", "efernandez@gmail.com",
                    "1234"), "Profesor de Java", new ArrayList<Curso>()),
                    new Profesor(new Usuario(2L, "Juana", "Magdalena", "jmagdalena@outlook.com",
                            "1234"), "Profesora de UX", new ArrayList<Curso>()))
    );

    @Override
    public void create(Profesor profesor) {
        profesores.add(profesor);
    }

    @Override
    public List<Profesor> getAll() {
        return profesores;
    }

    @Override
    public void update(Profesor profesor) {
        Profesor currentProfesor = findById(profesor.getUsuario().getId_usuario());
        if (currentProfesor != null) {
            int index = profesores.indexOf(currentProfesor);
            profesores.set(index, profesor);
        }
    }

    @Override
    public void delete(Profesor profesor) {
        profesores.remove(profesor);
    }

    @Override
    public Profesor findById(Long id_profesor) {
        Profesor profesor = profesores.stream()
                .filter(p -> p.getUsuario().getId_usuario() == id_profesor).findFirst().orElse(null);
        return profesor;
    }
}
