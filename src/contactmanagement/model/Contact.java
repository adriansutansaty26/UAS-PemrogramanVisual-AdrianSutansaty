/**
 Contact.java berisi objek untuk Contact
 */
package contactmanagement.model;

public class Contact {
    public String id;
    public String name;
    public String pn;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getpn() {
        return pn;
    }

    public void setpn(String pn) {
        this.pn = pn;
    }
}
