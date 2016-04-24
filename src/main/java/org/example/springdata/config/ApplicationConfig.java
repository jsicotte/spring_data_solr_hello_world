package org.example.springdata.config;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Minimal Spring context configuration.
 */
@Configuration
@EnableSolrRepositories
public class ApplicationConfig {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Create a SolrClient. This impl connects to the default port on localhost
     * @return SolrClient instance
     */
    @Bean
    public SolrClient solrClient() throws IOException, SAXException, ParserConfigurationException {
        SolrClient client = new HttpSolrClient("http://localhost:8983/solr/test_core/");
        return client;
    }

    /**
     * Create a SolrTemplate, which is required for the PersonRepository. If this is not included then following
     * exception is thrown:
     * <pre>
     *      org.springframework.beans.factory.NoSuchBeanDefinitionException: No bean named 'solrTemplate' is defined
     * </pre>
     * @return SolrTemplate instance
     */
    @Bean
    public SolrTemplate solrTemplate() throws ParserConfigurationException, SAXException, IOException {
        return new SolrTemplate(solrClient());
    }
}
