package com.gestioneleves.api.entity;

import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;

@NamedQueries({
    // @NamedQuery(name = "AppUser.findByUsername", query = "Select a from AppUser a where a.username = ?1"),
    // @NamedQuery(name = "AppUser.findByLegalGuardiansId", query = "Select s from Student s join s.legalGuardians g where g.id = ?1 "),
})
@NamedQuery(name = "AppUser.findByClassGroup", query = "Select a from AppUser a join Teaching t where t.classGroup = ?1")
public class AppUser {

}
