package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Term;
import ma.octo.smap.persistance.repositories.TermRepository;

/**
 * Created by adib on 04/04/17.
 */
public class TermServiceImpl implements TermService {

    private final TermRepository termRepository;

    public TermServiceImpl(TermRepository termRepository) {
        this.termRepository = termRepository;
    }

    @Override
    public void save(Term t) {
        termRepository.save(t);
    }

    @Override
    public Iterable<Term> findAll() {
        return termRepository.findAll();
    }

}
