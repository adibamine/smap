package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Term;

/**
 * Created by adib on 04/04/17.
 */
public interface TermService {

    void save(Term t);
    Iterable<Term> findAll();

}
