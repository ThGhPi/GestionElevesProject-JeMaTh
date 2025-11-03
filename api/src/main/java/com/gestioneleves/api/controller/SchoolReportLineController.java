package com.gestioneleves.api.controller;

import com.gestioneleves.api.entity.SchoolReportLine;
import com.gestioneleves.api.entity.SchoolReportLinePK;
import com.gestioneleves.api.repository.SchoolReportLineRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/school-report-lines")
public class SchoolReportLineController {

    private final SchoolReportLineRepository lines;

    public SchoolReportLineController(SchoolReportLineRepository lines) { this.lines = lines; }

    @GetMapping
    public List<SchoolReportLine> findAll() { return lines.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolReportLine> findById(@PathVariable SchoolReportLinePK id) {
        return lines.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public SchoolReportLine create(@RequestBody SchoolReportLine l) { return lines.save(l); }

    @PutMapping("/{id}")
    public ResponseEntity<SchoolReportLine> update(@PathVariable SchoolReportLinePK id, @RequestBody SchoolReportLine l) {
        return lines.findById(id).map(x -> {
            l.setId(id);
            return ResponseEntity.ok(lines.save(l));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable SchoolReportLinePK id) {
        if (!lines.existsById(id)) return ResponseEntity.notFound().build();
        lines.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
