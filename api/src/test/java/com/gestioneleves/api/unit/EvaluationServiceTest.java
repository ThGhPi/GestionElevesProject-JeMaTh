package com.gestioneleves.api.unit;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EvaluationServiceTest {
    @Autowired
    private NoteService noteService;

    @Test
    void calculateOverallAverageStudent() {
        List<Evaluation> evaluations = List.of(
            new Evaluation(),
            new Evaluation(),
            new Evaluation()
        );
    }
    
}
