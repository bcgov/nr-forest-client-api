<!-- PROJECT SHIELDS -->

[![Contributors](https://img.shields.io/github/contributors/bcgov/nr-forest-client-api)](/../../graphs/contributors)
[![Forks](https://img.shields.io/github/forks/bcgov/nr-forest-client-api)](/../../network/members)
[![Stargazers](https://img.shields.io/github/stars/bcgov/nr-forest-client-api)](/../../stargazers)
[![Issues](https://img.shields.io/github/issues/bcgov/nr-forest-client-api)](/../../issues)
[![MIT License](https://img.shields.io/github/license/bcgov/nr-forest-client-api.svg)](/LICENSE.md)
[![Lifecycle](https://img.shields.io/badge/Lifecycle-Experimental-339999)](https://github.com/bcgov/repomountie/blob/master/doc/lifecycle-badges.md)

The nr-forest-client-api application provides the API that allows systems to consume forest client data without having to connect to a database. We are working the process to publish our API  

The nr-forest-client-api is a Java application, integrated with the [greenfield-template](https://github.com/bcgov/greenfield-template) to automate the process for testing, security scanning, code quality checking, image building and deploying. It is hosted in openshift, protected and published through [API Services Portal](https://api.gov.bc.ca/)



## Configuring IntelliJ Code Style

You will find a copy of the [google code style](docs/google_checks.xml) inside our [docs](docs) folder.

On the settings screen, add the checks as the following image:

[![intellij code style](docs/intellij-code-style.png)](docs/intellij-code-style.png)


## Configuring IntelliJ Run Configuration

To set your profile on IntelliJ, just run the
[application main class](src/main/java/ca/bc/gov/app/BootApplication.java)
and edit the configuration as the following image.

[![intellij run configuration](docs/intellij-run-config.png)](docs/intellij-run-config.png)

## Configuring Eclipse Code Style

You will find a copy of the [google code style](docs/eclipse-java-google-style.xml) inside our [docs](docs) folder.

On the window > preferences screen, go to Java > Code Style > Formatter,
import the xml file and keep **GoogleStyle** selected as the following image:

[![eclipse code style](docs/eclipse-code-style.png)](docs/eclipse-code-style.png)


## Configuring Eclipse Run Configuration

To set your profile on Eclipse, just run the
[application main class](src/main/java/ca/bc/gov/app/LegacyApplication.java)
and edit the configuration as the following images.

[![eclipse run configuration main](docs/eclipse-run-config1.png)](docs/eclipse-run-config1.png)

[![eclipse run configuration params](docs/eclipse-run-config2.png)](docs/eclipse-run-config2.png)


## Setting up Lombok on Eclipse

If you're running eclipse, you will need to manually install lombok in order for it to work.
The easiest way of doing that is by running any mavem command that would trigger the download of the lib, such as
`mvn clean compile`.

Once it's done, navigate to your repository folder (its usually inside your user folder, called *.m2/repository*)
and look the latest version of lombok (inside org/projectlombok/lombok/) and run the lombok jar,
like `java -jar lombok-X.Y.Z.jar`.

[![eclipse lombok install](docs/eclipse-lombok.png)](docs/eclipse-lombok.png)

A screen will pop up, listing all the possible IDEs, select yours and install/update it. Once it's done,
restart eclipse.
