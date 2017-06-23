package it.inail.estema.microservices.poc.digident.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Section {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String updateDate;
    private String type;
    
    @Column(columnDefinition="TEXT")
    private String html;

    @Override
    public String toString() {
        return String.format(
                "Section[id=%s, type='%s', html='%s', updateDate='%s']",
                id, type, html,updateDate );
    }
    
    public Long getId() {
              return id;
    }

    public void setId(Long id) {
              this.id = id;
    }

    
    public String getUpdateDate() {
        return updateDate;
    }

    public String getType() {
        return type;
    }

    public String getHtml() {
        return html;
    }


    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHtml(String html) {
        this.html = html;
    }
    
}
