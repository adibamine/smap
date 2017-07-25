package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Term;

import java.util.List;

/**
 * Created by adib on 04/04/17.
 */
public interface TermRepository {

    Term save(Term t);
    List<Term> findAll();

}
