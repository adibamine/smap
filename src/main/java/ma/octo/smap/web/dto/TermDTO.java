package ma.octo.smap.web.dto;

/**
 * Created by adib on 28/03/17.
 */
public class TermDTO {

    private String term;
    private String lang;

    public TermDTO() {
    }

    public TermDTO(String term, String lang) {
        this.term = term;
        this.lang = lang;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getLang() { return lang; }

    public void setLang(String lang) { this.lang = lang; }

}
