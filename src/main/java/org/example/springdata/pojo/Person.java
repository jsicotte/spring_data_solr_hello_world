package org.example.springdata.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

/**
 * Basic POJO for this example
 */
@SolrDocument
public class Person {
    /*
     * If this field is not included then this unhelpful exception will be thrown:
     * java.lang.NullPointerException
	 * at org.springframework.data.solr.repository.support.MappingSolrEntityInformation.getIdAttribute(MappingSolrEntityInformation.java:51)
     */
    @Id
    private String id;

    /*
     * This example is intended to be used with dynamic schema, so the type suffix is used for field names. If one uses
     * an incorrect format for field names then another unhelpful exception will be thrown:
     * java.lang.IllegalArgumentException: [Assertion failed] - this argument is required; it must not be null
	 *     at org.springframework.util.Assert.notNull(Assert.java:115)
     *     at org.springframework.util.Assert.notNull(Assert.java:126)
	 *     at org.springframework.data.solr.core.convert.MappingSolrConverter$SolrPropertyValueProvider.readValue(MappingSolrConverter.java:426)
	 *
	 * Note that the incorrect case is not explicitly setting the field names to *_s.
	 *
	 * The reason why the above exception is thrown is because Spring Data is trying to put data from a multi-value
	 * field into a single string property.
     */
    @Indexed(name = "first_name_s",type = "string")
    private String firstName;
    @Indexed(name = "last_name_s", type = "string")
    private String lastName;

    public Person() {
    }

    public Person(String id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
