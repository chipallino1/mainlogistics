package org.agoncal.sample.cdi.inject.setter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Random;
import java.util.logging.Logger;

/**
 * @author Antonio Goncalves
 *         http://www.antoniogoncalves.org
 */
@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    // ======================================
    // =             Attributes             =
    // ======================================

    private Logger logger = Logger.getLogger(IsbnGenerator.class.getName());

    // ======================================
    // =          Lifecycle methods         =
    // ======================================

    @PostConstruct
    private void init() {
        logger.info("=> IsbnGenerator PostConstruct");
        logger.info("================");
    }

    @PreDestroy
    private void release() {
        logger.info("================");
        logger.info("=> IsbnGenerator PreDestroy");
    }

    // ======================================
    // =          Business methods          =
    // ======================================

    @Override
    public String generateNumber() {
        return "13-84356-" + Math.abs(new Random().nextInt());
    }
}