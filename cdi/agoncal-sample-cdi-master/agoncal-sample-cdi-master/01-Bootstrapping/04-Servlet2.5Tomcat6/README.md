# Sample - CDI - Bootstrapping CDI in Servlet 2.5 with Tomcat 6.x

## Purpose of this sample

The purpose of this sample is to use CDI to inject beans (Hello & World) into a Servlet (MainServlet25) in Tomcat 6.x.

The CDI container is not by default in Tomcat it needs to be bootstrapped thanks to the `context.xml` file.

[Read more on my blog](http://agoncal.wordpress.com/2011/01/12/bootstrapping-cdi-in-several-environments)

## Class diagram

![image](https://github.com/agoncal/agoncal-sample-cdi-bootstrapping/raw/master/cdibootstrap.png)

## Compile and package

Being Maven centric, you can compile and package this sample with `mvn clean compile`, `mvn clean package` or `mvn clean install`.

## Deploy the sample

Copy the `sampleCdiBootstrappingServlet25Tomcat6.war` file to `$GLASSFISH_HOME/glassfish/domains/domain1/autodeploy` or use the GlassFish admin command line : `asadmin deploy --force sampleCdiBootstrappingServlet25Tomcat6.war`.

## Execute the sample

Once deployed, go to the following URL to invoke the servlet : `http://localhost:8080/sampleCdiBootstrappingServlet25Tomcat6/mainServlet`



<div class="footer">
    <span class="footerTitle"><span class="uc">a</span>ntonio <span class="uc">g</span>oncalves</span>
</div>